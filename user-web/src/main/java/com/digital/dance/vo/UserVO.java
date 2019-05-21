package com.digital.dance.vo;

import com.digital.dance.bo.UserServiceBaseBO;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author liuxy
 *
 * time:2016年8月23日上午10:05:45
 */
public class UserVO extends UserServiceBaseVO implements Serializable {

	private String userId;

	private String userName;

	private String password;

	private String email;

	private String phone;

	private Boolean rolePrivilegeInd;

	private Boolean isRemember;
	public Long tokenTimeOut = Long.valueOf(-1L);

	private Integer state;

	private Date insertOn;

	private String insertBy;

	private Date updateOn;

	private String updateBy;

	public Boolean getIsRemember() {
		return this.isRemember;
	}

	public void setIsRemember(Boolean isRemember) {
		this.isRemember = isRemember;
	}

	/**
	 * @return the tokenTimeOut
	 */
	public Long getTokenTimeOut() {
		return tokenTimeOut;
	}

	/**
	 * @param tokenTimeOut the tokenTimeOut to set
	 */
	public void setTokenTimeOut(Long tokenTimeOut) {
		this.tokenTimeOut = tokenTimeOut;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId == null ? null : userId.trim();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName == null ? null : userName.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	public Boolean getRolePrivilegeInd() {
		return rolePrivilegeInd;
	}

	public void setRolePrivilegeInd(Boolean rolePrivilegeInd) {
		this.rolePrivilegeInd = rolePrivilegeInd;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Date getInsertOn() {
		return insertOn;
	}

	public void setInsertOn(Date insertOn) {
		this.insertOn = insertOn;
	}

	public String getInsertBy() {
		return insertBy;
	}

	public void setInsertBy(String insertBy) {
		this.insertBy = insertBy == null ? null : insertBy.trim();
	}

	public Date getUpdateOn() {
		return updateOn;
	}

	public void setUpdateOn(Date updateOn) {
		this.updateOn = updateOn;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy == null ? null : updateBy.trim();
	}

	// 短信验证码
	private String validCode;

	// 会话ID
	private String sessionId;

	/**
	 * @return the validCode
	 */
	public String getValidCode() {
		return validCode;
	}

	/**
	 * @param validCode the validCode to set
	 */
	public void setValidCode(String validCode) {
		this.validCode = validCode;
	}

	/**
	 * @return the sessionId
	 */
	public String getSessionId() {
		return sessionId;
	}

	/**
	 * @param sessionId the sessionId to set
	 */
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	private List<SystemUserRoleVO> roles ;
	
	/**
	 * @return the roles
	 */
	public List<SystemUserRoleVO> getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(List<SystemUserRoleVO> roles) {
		this.roles = roles;
	}

	private String redirect;
	/**
	 * @return the redirect
	 */
	public String getRedirect() {
		return redirect;
	}

	/**
	 * @param redirect the redirect to set
	 */
	public void setRedirect(String redirect) {
		this.redirect = redirect;
	}
}
