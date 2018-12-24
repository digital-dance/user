package com.digital.dance.vo;

import com.digital.dance.bo.UserServiceBaseBO;

import java.io.Serializable;
import java.util.Date;

public class SystemUserRoleVO extends UserServiceBaseVO implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = -7548265387019896426L;
	private String sysUserRoleId;

	private String sysOrgRoleId;

	private String orgId;

	private String departmentId;

	public String getDepartmentPath() {
		return departmentPath;
	}

	public void setDepartmentPath(String departmentPath) {
		this.departmentPath = departmentPath;
	}

	private String departmentPath;

	public String getRolePath() {
		return rolePath;
	}

	public void setRolePath(String rolePath) {
		this.rolePath = rolePath;
	}

	private String rolePath;

	private String roleId;

	private String userId;

	private Integer state;

	private Date insertOn;

	private String insertBy;

	private Date updateOn;

	private String updateBy;

	public String getSysUserRoleId() {
		return sysUserRoleId;
	}

	public void setSysUserRoleId(String sysUserRoleId) {
		this.sysUserRoleId = sysUserRoleId == null ? null : sysUserRoleId.trim();
	}

	public String getSysOrgRoleId() {
		return sysOrgRoleId;
	}

	public void setSysOrgRoleId(String sysOrgRoleId) {
		this.sysOrgRoleId = sysOrgRoleId == null ? null : sysOrgRoleId.trim();
	}
	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId == null ? null : orgId.trim();
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId == null ? null : departmentId.trim();
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId == null ? null : roleId.trim();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId == null ? null : userId.trim();
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