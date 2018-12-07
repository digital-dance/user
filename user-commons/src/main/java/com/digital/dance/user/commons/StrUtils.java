package com.digital.dance.user.commons;


import java.util.Random;

public class StrUtils {

	
	public static final String RANDOM_STR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public static final String CODE_RANDOM_STR = "0123456789abcdefghijklmnopqrstuvwxyz";
	public static final String NUMBER_STR = "0123456789";
	public static final Random random = new Random();

	/**
	 * 获取长度为length的随机字符串
	 * 
	 * @param lenth
	 * @return
	 */
	public static String getRandomString(int length) {
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			sb.append(RANDOM_STR.charAt(random.nextInt(62)));
		}
		return sb.toString();
	}

	public static String getRandomCode(int length) {
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			sb.append(CODE_RANDOM_STR.charAt(random.nextInt(36)));
		}
		return sb.toString();
	}
	
	public static String getRandomNumCode(int length) {
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			sb.append(NUMBER_STR.charAt(random.nextInt(10)));
		}
		return sb.toString();
	}

	public static String getRandomNum(int length) {
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			sb.append(random.nextInt(10));
		}
		return sb.toString();
	}

	public static String roundCode() {
		return getRandomNumCode(6);
	}
	
	public static void main(String[]args){
				
	}
}
