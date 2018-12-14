package com.digital.dance.bo;

import java.io.Serializable;
import java.util.Date;

public class SystemRoleBO  extends UserServiceBaseBO implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 5001750870812701410L;
	private String sysOrgRoleId;

	private String orgId;

	private String departmentId;

	private String roleId;

	private String roleCode;

	private String roleName;

	private Integer orgCategoryCode;

	private String orgCategoryName;

	private String roleDesc;

	private Boolean isDefault;

	private Integer state;

	private Date insertOn;

	private String insertBy;

	private Date updateOn;

	private String updateBy;

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

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode == null ? null : roleCode.trim();
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName == null ? null : roleName.trim();
	}

	public Integer getOrgCategoryCode() {
		return orgCategoryCode;
	}

	public void setOrgCategoryCode(Integer orgCategoryCode) {
		this.orgCategoryCode = orgCategoryCode;
	}

	public String getOrgCategoryName() {
		return orgCategoryName;
	}

	public void setOrgCategoryName(String orgCategoryName) {
		this.orgCategoryName = orgCategoryName == null ? null : orgCategoryName.trim();
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc == null ? null : roleDesc.trim();
	}

	public Boolean getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}

}