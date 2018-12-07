package com.digital.dance.user.commons;

public class BeanUtils {

	/**
	 * copy properties
	 * @param source
	 * @param target
	 */
	public static void copyProperties(Object source, Object target){
		org.springframework.beans.BeanUtils.copyProperties(source, target);
	}
}
