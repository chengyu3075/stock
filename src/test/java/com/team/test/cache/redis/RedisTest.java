package com.team.test.cache.redis;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.team.business.cache.redis.RedisService;

public class RedisTest {
	
	@Test
	public void setValue() throws Exception{
		ApplicationContext ct=new ClassPathXmlApplicationContext("classpath:spring/spring-*.xml");
		RedisService rs = (RedisService) ct.getBean("redisService");
		rs.set("sandycheng", "chengyu", 60*60);
		assertEquals(8, rs.get("sandycheng"));
	}
	
}

