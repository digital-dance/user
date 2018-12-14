package com.digital.dance.dao;

import java.util.List;

import com.digital.dance.entity.SystemRoleEO;

public interface SystemRoleDao {
	/**
	 * add system role
	 * @param systemRole
	 * @return
	 */
	public Integer addSystemRole(SystemRoleEO systemRole);
	
	/**
	 * find system role by role name
	 * @param roleName
	 * @return
	 */
	public SystemRoleEO findSystemRoleByRoleName(String roleName);
	
	/**
	 * find system role by role id
	 * @param roleId
	 * @return
	 */
	public SystemRoleEO findSystemRoleByRoleId(String roleId);
	
	/**
	 * delete SystemRoles
	 * @param systemRoles
	 * @return
	 */
	public Integer deleteSystemRoles(List<SystemRoleEO> systemRoles);
	
	
	/**
	 * update SystemRole
	 * @param systemRoles
	 * @return
	 */
	public Integer updateSystemRoles(List<SystemRoleEO> systemRoles);
	
	/**
	 * find paged system roles
	 * @param systemRole
	 * @return
	 */
	public List<SystemRoleEO> findPagedSystemRoles(SystemRoleEO systemRole);
	
	/**
	 * find all system roles
	 * @param systemRole
	 * @return
	 */
	public List<SystemRoleEO> findAllSystemRoles(SystemRoleEO systemRole);
	
	/**
	 * search paged system roles
	 * @param systemRole
	 * @return
	 */	
	public List<SystemRoleEO> searchPagedSystemRoles(SystemRoleEO systemRole);
	
	/**
	 * search all system roles
	 * @param systemRole
	 * @return
	 */
	public List<SystemRoleEO> searchAllSystemRoles(SystemRoleEO systemRole);
}
