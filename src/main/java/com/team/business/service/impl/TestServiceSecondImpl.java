package com.team.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.business.cache.redis.RedisService;
import com.team.business.service.TestService;

@Service("impl2")
public class TestServiceSecondImpl implements TestService {
	@Autowired
	private RedisService redisService;
	@Override
	public void test() {
		// TODO Auto-generated method stub
		System.out.println("i A the Seconed");
		System.out.println("second is:"+redisService.get("sandy"));
	}

}
