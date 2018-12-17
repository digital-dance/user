package com.digital.dance.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户表 serverroom.system_user
 * 
 * @author liwy
 *
 *         time:2016年8月22日下午5:30:19
 */
public class UserEO extends UserServiceBaseEO implements Serializable {

	private String userId;

	private String userName;

	private String password;

	private String email;

	private String phone;

	private Boolean rolePrivilegeInd;

	private Integer state;

	private Boolean isRemember;

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
}