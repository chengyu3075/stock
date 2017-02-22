package com.team.validator.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.team.validator.utils.PhoneValidator;

@Target ({ElementType.TYPE, ElementType.METHOD,ElementType.FIELD,ElementType.ANNOTATION_TYPE})    
@Retention (RetentionPolicy.RUNTIME)  
@Constraint(validatedBy = PhoneValidator.class)
@Documented
public @interface Phone {

	String message() default "请输入正确的手机号码";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
	
}
