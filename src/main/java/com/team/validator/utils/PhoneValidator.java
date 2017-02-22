package com.team.validator.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.team.validator.annotation.Phone;


public class PhoneValidator  implements ConstraintValidator<Phone, Long> {

	//5~10位的数字与字母组合
	private static Pattern pattern = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
	
	@Override
	public void initialize(Phone arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(Long value, ConstraintValidatorContext arg1) {
		if( value==null ){
			return false;
		}
		Matcher m = pattern.matcher(String.valueOf(value));
		return m.matches();
	}

}
