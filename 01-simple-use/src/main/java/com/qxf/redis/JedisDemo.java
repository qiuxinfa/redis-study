package com.qxf.redis;

import redis.clients.jedis.Jedis;

import java.util.Map;

/**
 * @Auther: qiuxinfa
 * @Date: 2020/4/19
 * @Description: 简单实用
 */
public class JedisDemo {
    public static void main(String[] args) {
        // 构造jedis对象
        Jedis jedis = new Jedis("192.168.231.128", 6379);
        //设置密码
        jedis.auth("123");
        //向Redis添加数据
        jedis.set("aa","zhangsan");
        //取出数据并打印
        System.out.println("aa: "+jedis.get("aa"));
        System.out.println("stu: "+jedis.get("stu"));
        //取出hash存放的值
        Map<String, String> map = jedis.hgetAll("user");
        System.out.println("user.id: "+map.get("id"));
        System.out.println("user.name: "+map.get("name"));
        //关闭连接
        jedis.close();
    }
}
