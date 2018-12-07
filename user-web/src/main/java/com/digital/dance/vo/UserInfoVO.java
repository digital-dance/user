package com.digital.dance.vo;

/**
 * 用户美的付信息查询
 * @author liwy
 *
 * time:2016年8月25日下午9:36:08
 */
public class UserInfoVO extends UserServiceBaseVO{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5571158326557303597L;
	//登录美的付用户名
	private String login_name;
	//真实姓名
	private String real_name;
	//用户手机号
	private String mobile_no;
	//是否实名
	private String real_name_flag;
	//是否有支付密码
	private String pay_pwd_flag;
	public String getLogin_name() {
		return login_name;
	}
	public void setLogin_name(String login_name) {
		this.login_name = login_name;
	}
	public String getReal_name() {
		return real_name;
	}
	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}
	public String getMobile_no() {
		return mobile_no;
	}
	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}
	public String getReal_name_flag() {
		return real_name_flag;
	}
	public void setReal_name_flag(String real_name_flag) {
		this.real_name_flag = real_name_flag;
	}
	public String getPay_pwd_flag() {
		return pay_pwd_flag;
	}
	public void setPay_pwd_flag(String pay_pwd_flag) {
		this.pay_pwd_flag = pay_pwd_flag;
	}
	
	
	
}
