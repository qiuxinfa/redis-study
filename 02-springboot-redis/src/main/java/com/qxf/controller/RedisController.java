package com.qxf.controller;

import com.qxf.pojo.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: qiuxinfa
 * @Date: 2020/4/19
 * @Description: com.qxf.com.qxf.controller
 */
@RestController
public class RedisController {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/test")
    public void testRedis(){
        Person person = new Person("123", "张三", 22);
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set("person",person);
        Person per = (Person) valueOperations.get("person");
        System.out.println("person: "+per);

        stringRedisTemplate.opsForValue().set("k1","v1");
        System.out.println("k1: "+stringRedisTemplate.opsForValue().get("k1"));
    }
}
