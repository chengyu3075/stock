package com.team.business.utils;

import java.util.Random;

public class NumberUtils {
	
	private static final String PARAM_INVALID = "长度需在4到8个字符之间";
	/**
	 * 生成指定长度的随机数字(长度在4和10之间)
	 * @param len
	 * @return
	 */
	public static String generateRandomNum(int len){
		if(len>10||len<4){
			throw new IllegalArgumentException(PARAM_INVALID);
		}
		Random random = new Random();
		int max = 10;
		StringBuffer buffer = new StringBuffer();
		for(int i = 0; i< len; i++){
			buffer.append(random.nextInt(max));
		}
		return buffer.toString();
	}
	

}
