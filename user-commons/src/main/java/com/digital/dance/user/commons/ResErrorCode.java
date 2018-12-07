package com.digital.dance.user.commons;

/**
 * http response错误码
 * @author LIUJC9
 *
 */
public enum ResErrorCode {
    
	PROCESS_ERROR(10),   //程序执行出错
	LOGIN_INFO_ERROR(20);// 登录信息出错（未登录或登录超时）
	
	private Integer value;

	private ResErrorCode(Integer value) {
		this.value = value;
	}

	public Integer value() {
		return this.value;
	}
}
