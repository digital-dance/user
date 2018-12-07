package com.digital.dance.dao;

import java.util.List;

import com.digital.dance.entity.SystemUserRoleEO;

public interface SystemUserRoleDao {
	/**
	 * add a system user role
	 * @param systemUserRole
	 * @return
	 */
	public Integer addSystemUserRole(SystemUserRoleEO systemUserRole);
	
	/**
	 * add system user roles
	 * @param systemUserRole
	 * @return
	 */
	public Integer addSystemUserRoles(List<SystemUserRoleEO> systemUserRoles);
	
	/**
	 * find system user role by userId
	 * @param userId
	 * @return
	 */
	public SystemUserRoleEO findSystemUserRoleByUserId(String userId);
	
	/**
	 * find system user role by role id
	 * @param roleId
	 * @return
	 */
	public SystemUserRoleEO findSystemUserRoleByRoleId(String roleId);
	
	/**
	 * delete SystemUserRole
	 * @param userId
	 * @return
	 */
	public Integer deleteSystemUserRoles(List<SystemUserRoleEO> systemUserRoles);
	
	
	/**
	 * update SystemUserRole
	 * @param userId
	 * @return
	 */
	public Integer updateSystemUserRoles(List<SystemUserRoleEO> systemUserRoles);
	
	/**
	 * find paged system user roles
	 * @param systemUserRole
	 * @return
	 */
	public List<SystemUserRoleEO> findPagedSystemUserRoles(SystemUserRoleEO systemUserRole);
	
	/**
	 * find all system user roles
	 * @param systemUserRole
	 * @return
	 */
	public List<SystemUserRoleEO> findAllSystemUserRoles(SystemUserRoleEO systemUserRole);

	/**
	 * search paged system user roles
	 * @param systemUserRole
	 * @return
	 */
	public List<SystemUserRoleEO> searchPagedSystemUserRoles(SystemUserRoleEO systemUserRole);
	
	/**
	 * search all system user roles
	 * @param systemUserRole
	 * @return
	 */
	public List<SystemUserRoleEO> searchAllSystemUserRoles(SystemUserRoleEO systemUserRole);
}
