package com.qxf.redis;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: qiuxinfa
 * @Date: 2020/4/19
 * @Description: 集群式的连接池
 */
public class SharedJedisPoolDemo {
    public static void main(String[] args) {
        // 构建连接池配置信息
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        // 设置最大连接数
        poolConfig.setMaxTotal(20);

        // 定义集群信息
        List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();

        JedisShardInfo jedisShardInfo = new JedisShardInfo("192.168.231.128", 6379);
        jedisShardInfo.setPassword("123");
        //这里可以添加多个
        shards.add(jedisShardInfo);

        // 定义集群连接池
        ShardedJedisPool shardedJedisPool = new ShardedJedisPool(poolConfig, shards);
        ShardedJedis shardedJedis = null;
        try {
            // 从连接池中获取到jedis分片对象
            shardedJedis = shardedJedisPool.getResource();
            // 从redis中获取数据
            String value = shardedJedis.get("aa");
            System.out.println(value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != shardedJedis) {
                // 关闭，检测连接是否有效，有效则放回到连接池中，无效则重置状态
                shardedJedis.close();
            }
        }
        // 关闭连接池
        shardedJedisPool.close();
    }
}
