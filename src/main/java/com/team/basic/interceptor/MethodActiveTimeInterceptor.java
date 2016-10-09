package com.team.basic.interceptor;

import java.util.HashMap;
import java.util.Map;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang.time.StopWatch;

public class MethodActiveTimeInterceptor implements MethodInterceptor {
	public static Map<String,Long[]> methodTest = new HashMap<String, Long[]>();
	@Override
	public Object invoke(MethodInvocation arg0) throws Throwable {
		// TODO Auto-generated method stub
		StopWatch watch = new StopWatch();
		watch.start(); 
		Object obj = arg0.proceed();
		watch.stop();
		String methodName = arg0.getMethod().getName();
		Long time = watch.getTime();
		System.out.println("methodName:"+methodName+"***timelong:"+time);
		if(methodTest.containsKey(methodName)) {
		      Long[] x = methodTest.get(methodName);
		      x[0]++;
		      x[1] += time;
		    }else{
		      methodTest.put(methodName, new Long[] {1L,time});
		    }
		    return obj;
	}

}
