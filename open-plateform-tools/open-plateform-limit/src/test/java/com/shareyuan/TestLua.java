package com.shareyuan;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
//@TestPropertySource("classpath:binlan.properties")
@SpringBootTest
public class TestLua {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private DefaultRedisScript<String> getRedisScript;

/*    public TestLua(StringRedisTemplate redisTemplate) {
        RedisSerializer<String> serializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(serializer);
        redisTemplate.setValueSerializer(serializer);
        redisTemplate.setHashKeySerializer(serializer);
        redisTemplate.setHashValueSerializer(serializer);
        this.stringRedisTemplate = redisTemplate;
    }*/

    public void init(){
        getRedisScript = new DefaultRedisScript<String>();
        getRedisScript.setResultType(String.class);
        getRedisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("META-INF/scripts/test1.lua")));
    }


    @Test
    public void test1(){
        init();
        Long count = null;
        List<String> keys = getKeys("123");
      /*  List<String> scriptArgs = Arrays.asList(2 + "", 20 + "",
                Instant.now().getEpochSecond() + "", "1");
*/
        List<String> args = new ArrayList<String>();
        args.add("2");
        args.add("10");
        args.add(String.valueOf(Instant.now().getEpochSecond()));
        args.add("1");
        String str = stringRedisTemplate.execute(getRedisScript,keys,args);
        System.out.println(str);
    }


    static List<String> getKeys(String id) {
        // use `{}` around keys to use Redis Key hash tags
        // this allows for using redis cluster

        // Make a unique key per user.
        String prefix = "request_rate_limiter.{" + id;

        // You need two Redis keys for Token Bucket.
        String tokenKey = prefix + "}.tokens";
        String timestampKey = prefix + "}.timestamp";
        return Arrays.asList(tokenKey, timestampKey);
    }

}
