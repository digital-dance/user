package com.digital.dance.service;

import java.util.List;

import com.digital.dance.bo.SystemUserRoleBO;

public interface SystemUserRoleService {
	/**
	 * 
	 * @param systemUserRole
	 * @return
	 */
	public Integer addSystemUserRole(SystemUserRoleBO systemUserRole);
	
	/**
	 * add system user roles
	 * @param systemUserRoles
	 * @return
	 */
	public Integer addSystemUserRoles(List<SystemUserRoleBO> systemUserRoles);
	
	/**
	 * 
	 * @param userId
	 * @return
	 */
	public SystemUserRoleBO findSystemUserRoleByUserId(String userId);
	/**
	 * 
	 * @param roleId
	 * @return
	 */
	public SystemUserRoleBO findSystemUserRoleByRoleId(String roleId);
	
	/**
	 * delete SystemUserRole
	 * @param userId
	 * @return
	 */
	public Integer deleteSystemUserRoles(List<SystemUserRoleBO> systemUserRoles);
	
	
	/**
	 * update SystemUserRole
	 * @param userId
	 * @return
	 */
	public Integer updateSystemUserRoles(List<SystemUserRoleBO> systemUserRoles);
	
	/**
	 * 
	 * @param systemUserRole
	 * @return
	 */
	public List<SystemUserRoleBO> findPagedSystemUserRoles(SystemUserRoleBO systemUserRole);
	/**
	 * 
	 * @param systemUserRole
	 * @return
	 */
	public List<SystemUserRoleBO> findAllSystemUserRoles(SystemUserRoleBO systemUserRole);
	
	/**
	 * 
	 * @param systemUserRole
	 * @return
	 */
	public List<SystemUserRoleBO> searchPagedSystemUserRoles(SystemUserRoleBO systemUserRole);
	/**
	 * 
	 * @param systemUserRole
	 * @return
	 */
	public List<SystemUserRoleBO> searchAllSystemUserRoles(SystemUserRoleBO systemUserRole);
}
