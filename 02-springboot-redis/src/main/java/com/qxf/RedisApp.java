package com.qxf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @Auther: qiuxinfa
 * @Date: 2020/4/19
 * @Description: com.qxf
 */
@SpringBootApplication
@EnableCaching
public class RedisApp {

    public static void main(String[] args) {
        SpringApplication.run(RedisApp.class,args);
    }
}
