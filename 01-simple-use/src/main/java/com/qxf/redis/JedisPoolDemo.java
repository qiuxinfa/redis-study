package com.qxf.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Auther: qiuxinfa
 * @Date: 2020/4/19
 * @Description: 使用连接池
 */
public class JedisPoolDemo {
    public static void main(String[] args) {
        //Jedis连接池配置信息
        JedisPoolConfig config = new JedisPoolConfig();
        //这里可以设置很多东西，这里只设置了最大连接数为20个
        config.setMaxTotal(20);
        //构造Jedis连接池
        JedisPool pool = new JedisPool(config,"192.168.231.128", 6379);
        //获取jedis连接
        Jedis jedis = pool.getResource();
        //密码
        jedis.auth("123");
        //向Redis添加数据
        jedis.set("aa","lisi");
        //取出数据并打印
        System.out.println("aa: "+jedis.get("aa"));
        //关闭连接，不是真正的关闭，是放回连接池
        jedis.close();
        //关闭连接池
        pool.close();
    }
}
