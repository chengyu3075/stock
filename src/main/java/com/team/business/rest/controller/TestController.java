package com.team.business.rest.controller;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.team.business.service.TestService;
import com.team.business.util.Frequency;

@Controller
@Scope("prototype")
@RequestMapping("/test")
public class TestController {

	@Resource(name="impl1")
	private TestService testServcie1;
	
	@Resource(name="impl2")
	private TestService testServcie2;
	
	@ResponseBody
	@RequestMapping("first")
	public String test1(){
		testServcie1.test();
		return "";
	}
	
    @Frequency(name="method", limit=3, time=1000) 
	@ResponseBody
	@RequestMapping("second")
	public String test2(){
		testServcie2.test();
		return "";
	}
	
}