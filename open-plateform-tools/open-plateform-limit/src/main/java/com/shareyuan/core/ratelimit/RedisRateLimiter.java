package com.shareyuan.core.ratelimit;

import org.springframework.beans.BeansException;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @Author : kent
 * @Description : 限流实现类
 * @Date : 11:37 2019/9/17
 */
@ConfigurationProperties("open.plateform.limit.redis-rate-limiter")
public class RedisRateLimiter extends AbstractRateLimiter<RedisRateLimiter.Config> implements ApplicationContextAware {

    public static final String REDIS_SCRIPT_NAME = "redisRequestRateLimiterScript";

    private ReactiveRedisTemplate<String, String> redisTemplate;

    private AtomicBoolean initialized = new AtomicBoolean(false);

    private RedisScript<List<Long>> script;


    protected RedisRateLimiter(Class<RedisRateLimiter.Config> configClass, String configurationPropertyName, Validator validator) {
        super(configClass, configurationPropertyName, validator);
    }

    @Override
    public Mono<Response> isAllowed(String routeId, String id) {
        return null;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (initialized.compareAndSet(false, true)) {
            this.redisTemplate = applicationContext.getBean("stringReactiveRedisTemplate", ReactiveRedisTemplate.class);
            this.script = applicationContext.getBean(REDIS_SCRIPT_NAME, RedisScript.class);
            if (applicationContext.getBeanNamesForType(Validator.class).length > 0) {
                this.setValidator(applicationContext.getBean(Validator.class));
            }
        }
    }

    @Validated
    public static class Config {
        @Min(1)
        private int replenishRate;

        @Min(1)
        private int burstCapacity = 1;

        public int getReplenishRate() {
            return replenishRate;
        }

        public Config setReplenishRate(int replenishRate) {
            this.replenishRate = replenishRate;
            return this;
        }

        public int getBurstCapacity() {
            return burstCapacity;
        }

        public Config setBurstCapacity(int burstCapacity) {
            this.burstCapacity = burstCapacity;
            return this;
        }

        @Override
        public String toString() {
            return "Config{" +
                    "replenishRate=" + replenishRate +
                    ", burstCapacity=" + burstCapacity +
                    '}';
        }
    }

}
