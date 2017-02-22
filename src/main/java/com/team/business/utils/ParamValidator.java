package com.team.business.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 参数验证工具类
 * @author chengyu
 *
 */
public class ParamValidator {
	/**
	 * 验证手机号码
	 * @param phone
	 * @return
	 */
	public static boolean validatePhone(String phone){
		Pattern pattern = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher m = pattern.matcher(String.valueOf(phone));
		return m.matches();
	}

}
