package com.team.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.business.cache.redis.RedisService;
import com.team.business.service.TestService;

@Service("impl1")
public class TestServcieFirstImpl implements TestService {
	
	@Autowired
	private RedisService redisService;
	
	
	@Override
	public void test() {
		// TODO Auto-generated method stub
		System.out.println("i A the First");
		redisService.set("sandy", "chengyu", 60*60);
		System.out.println("value is:"+redisService.get("sandy"));
	}

}
