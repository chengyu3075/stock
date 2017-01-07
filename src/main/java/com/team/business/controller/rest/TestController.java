package com.team.business.controller.rest;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.team.business.service.TestService;
import com.team.business.util.annonations.Frequency;

@Controller
@Scope("prototype")
@RequestMapping("/test")
public class TestController {

	@Resource(name="impl1")
	private TestService testServcie1;
	
	@Resource(name="impl2")
	private TestService testServcie2;
	
	@ResponseBody
	@RequestMapping(value = "first", method = RequestMethod.GET, produces = "application/json;charset=UTF-8", headers = {
			"Accept=application/json",
			"Content-Type=application/json;charset=utf-8" })
	public String test1(){
		testServcie1.test();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
