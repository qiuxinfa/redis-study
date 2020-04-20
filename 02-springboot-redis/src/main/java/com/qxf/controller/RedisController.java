package com.qxf.controller;

import com.qxf.pojo.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        //操作对象，Person实现了序列化接口
        Person person = new Person("123", "张三", 22);
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set("person",person);
        Person per = (Person) valueOperations.get("person");
        System.out.println("person: "+per);

        //操作字符串string
        stringRedisTemplate.opsForValue().set("k1","v1");
        System.out.println("k1: "+stringRedisTemplate.opsForValue().get("k1"));
        //操作列表list，从左边添加，表示当做 栈 来用
        stringRedisTemplate.opsForList().leftPushAll("list1","a1","a2","a3");
        BoundListOperations<String, String> list1 = stringRedisTemplate.boundListOps("list1");
        List<String> range = list1.range(0, -1);
        for (String val : range){
            System.out.println("list当做栈： "+val);
        }

        //操作hash
        Map<String,String> map = new HashMap<String, String>();
        map.put("h1","v1");
        map.put("h2","v2");
        stringRedisTemplate.opsForHash().putAll("myHash",map);
        BoundHashOperations<String, String, String> myHash = stringRedisTemplate.boundHashOps("myHash");
        myHash.put("h3","v3");
        Map<String, String> entries = myHash.entries();
        entries.keySet();
        for (String key : entries.keySet()){
            System.out.println("hash key: "+key+" value: "+entries.get(key));
        }

        //操作集合set
        stringRedisTemplate.opsForSet().add("set1", "s1", "s2");
        BoundSetOperations<String, String> set1 = stringRedisTemplate.boundSetOps("set1");
        set1.add("s3","s4");
        String s = set1.randomMember();
        System.out.println("set1: "+s);
    }
}
