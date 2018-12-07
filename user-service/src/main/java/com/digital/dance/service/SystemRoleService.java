package com.digital.dance.service;

import java.util.List;

import com.digital.dance.bo.SystemRoleBO;

public interface SystemRoleService {
	/**
	 * 
	 * @param systemRole
	 * @return
	 */
	public Integer addSystemRole(SystemRoleBO systemRole);
	/**
	 * 
	 * @param roleName
	 * @return
	 */
	public SystemRoleBO findSystemRoleByRoleName(String roleName);
	/**
	 * 
	 * @param roleId
	 * @return
	 */
	public SystemRoleBO findSystemRoleByRoleId(String roleId);
	/**
	 * 
	 * @param systemRole
	 * @return
	 */
	public List<SystemRoleBO> findPagedSystemRoles(SystemRoleBO systemRole);
	/**
	 * 
	 * @param systemRole
	 * @return
	 */
	public List<SystemRoleBO> findAllSystemRoles(SystemRoleBO systemRole);
	
	/**
	 * 
	 * @param systemRole
	 * @return
	 */
	public List<SystemRoleBO> searchPagedSystemRoles(SystemRoleBO systemRole);
	
	/**
	 * 
	 * @param systemRole
	 * @return
	 */
	public List<SystemRoleBO> searchAllSystemRoles(SystemRoleBO systemRole);
	
	/**
	 * delete SystemUserRole
	 * @param userId
	 * @return
	 */
	public Integer deleteSystemRoles(List<SystemRoleBO> systemRoles);
	
	
	/**
	 * update SystemUserRole
	 * @param userId
	 * @return
	 */
	public Integer updateSystemRoles(List<SystemRoleBO> systemRoles);
}
