package com.shareyuan.core.aspect;

import com.shareyuan.annotation.Limit;
import com.shareyuan.common.LimitType;
import com.shareyuan.exception.LimitException;
import com.shareyuan.utils.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author : kent
 * @Description : 限流注解AOP
 * @Date : 11:34 2019/9/17
 */
@Aspect
@Component
public class LimitAspect {

    private RedisTemplate<String,Object> redisTemplate;

    private static final Logger logger = LoggerFactory.getLogger(LimitAspect.class);

    private DefaultRedisScript<List> getRedisScript;

    public LimitAspect(RedisTemplate redisTemplate) {
        RedisSerializer<String> serializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(serializer);
        redisTemplate.setValueSerializer(serializer);
        redisTemplate.setHashKeySerializer(serializer);
        redisTemplate.setHashValueSerializer(serializer);
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    public void init(){
        getRedisScript = new DefaultRedisScript<List>();
        getRedisScript.setResultType(List.class);
        getRedisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("META-INF/script/request_rate_limiter.lua")));
    }

    @Pointcut("@annotation(com.winstar.annotation.Limit)")
    public void pointcut() {
        
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        //HttpServletRequest request = RequestHolder.getHttpServletRequest();
        //String appId = request.getHeader(Constant.HEADER_APPID);
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method signatureMethod = signature.getMethod();
        Limit limit = signatureMethod.getAnnotation(Limit.class);
        LimitType limitType = limit.limitType();
        String key = limit.key();
        if (StringUtils.isEmpty(key)) {
            switch (limitType) {
                case IP:
                    //key = StringUtils.getIP(request);
                    break;
                case APP:
                    //key = appId;
                    break;
                default:
                    key = signatureMethod.getName();
            }
        }
        List<String> keys = getKeys(StringUtils.join(limit.prefix(), "_", key, "_", "12"/*request.getRequestURI().replaceAll("/","_")*/));
        List<String> args = new ArrayList<>();
        args.add(String.valueOf(limit.rate()));
        args.add(String.valueOf(limit.capacity()));
        args.add(String.valueOf(Instant.now().getEpochSecond()));
        args.add("1");
        List<Long> result;
        try {
            result = redisTemplate.execute(getRedisScript, keys , args.toArray());
        } catch (Exception e) {
            throw new LimitException("api_limit_exception","api限流异常!");
        }
        if (result.get(1)>0) {
            logger.info("key:{}访问成功,还可以获取{}个令牌", keys, result.get(1));
            return joinPoint.proceed();
        } else {
            throw new LimitException("api_request_limit","api("+"12"/*request.getRequestURI()*/+")请求频繁!");
        }
    }
    
    static List<String> getKeys(String id) {
        String prefix = "request_rate_limiter:" + id;
        String tokenKey = prefix + ":tokens";
        String timestampKey = prefix + ":timestamp";
        return Arrays.asList(tokenKey, timestampKey);
    }


}