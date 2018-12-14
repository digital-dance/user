package com.digital.dance.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.digital.dance.user.commons.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digital.dance.bo.SystemRoleBO;
import com.digital.dance.dao.SystemRoleDao;
import com.digital.dance.entity.SystemRoleEO;
import com.digital.dance.service.SystemRoleService;

//@Service
public class SystemRoleServiceImpl implements SystemRoleService {

	@Autowired
    private SystemRoleDao systemRoleDao;
	@Override
	public Integer addSystemRole(SystemRoleBO systemRole) {
        if(systemRole == null){
        	return -1;
        }
		SystemRoleEO systemRoleEo = new SystemRoleEO();
		BeanUtils.copyProperties(systemRole, systemRoleEo);
		return systemRoleDao.addSystemRole(systemRoleEo);
	}

	@Override
	public SystemRoleBO findSystemRoleByRoleName(String roleName) {
		SystemRoleBO systemRoleBo = new SystemRoleBO();
		SystemRoleEO systemRoleEo = systemRoleDao.findSystemRoleByRoleName(roleName);
        if(systemRoleEo == null){
        	return null;
        }
		BeanUtils.copyProperties(systemRoleEo, systemRoleBo);
		return systemRoleBo;
	}

	@Override
	public SystemRoleBO findSystemRoleByRoleId(String roleId) {
		SystemRoleBO systemRoleBo = new SystemRoleBO();
		SystemRoleEO systemRoleEo = systemRoleDao.findSystemRoleByRoleId(roleId);
        if(systemRoleEo == null){
        	return null;
        }
		BeanUtils.copyProperties(systemRoleEo, systemRoleBo);
		return systemRoleBo;
	}

	@Override
	public List<SystemRoleBO> findPagedSystemRoles(SystemRoleBO systemRole) {
		
		SystemRoleEO systemRoleEo = new SystemRoleEO();
		BeanUtils.copyProperties(systemRole, systemRoleEo);
		
		List<SystemRoleEO> systemRoleEos = systemRoleDao.findPagedSystemRoles(systemRoleEo);
        if(systemRoleEos == null){
        	return null;
        }
		List<SystemRoleBO> systemRoleBos = new ArrayList<SystemRoleBO>();
		for(SystemRoleEO ite : systemRoleEos){
			SystemRoleBO systemRoleBo = new SystemRoleBO();
			BeanUtils.copyProperties(ite, systemRoleBo);

			systemRoleBo.setPageIndex(systemRole.getPageIndex());
			systemRoleBo.setPageSize(systemRole.getPageSize());
			systemRoleBos.add(systemRoleBo);
		}
		return systemRoleBos;
	}

	@Override
	public List<SystemRoleBO> findAllSystemRoles(SystemRoleBO systemRole) {
		
		SystemRoleEO systemRoleEo = new SystemRoleEO();
		BeanUtils.copyProperties(systemRole, systemRoleEo);
		
		List<SystemRoleEO> systemRoleEos = systemRoleDao.findAllSystemRoles(systemRoleEo);
        if(systemRoleEos == null){
        	return null;
        }
		List<SystemRoleBO> systemRoleBos = new ArrayList<SystemRoleBO>();
		for(SystemRoleEO ite : systemRoleEos){
			SystemRoleBO systemRoleBo = new SystemRoleBO();
			BeanUtils.copyProperties(ite, systemRoleBo);
			systemRoleBos.add(systemRoleBo);
		}
		return systemRoleBos;
	}

	@Override
	public Integer deleteSystemRoles(List<SystemRoleBO> systemRoles) {
		List<SystemRoleEO> systemRoleEos = new ArrayList<SystemRoleEO>();
		for(SystemRoleBO item : systemRoles) {
			SystemRoleEO systemRoleEo = new SystemRoleEO();
			BeanUtils.copyProperties(item, systemRoleEo);
			systemRoleEos.add(systemRoleEo);
		}
		
		Integer result = systemRoleDao.deleteSystemRoles(systemRoleEos);
		return result;
	}

	@Override
	public Integer updateSystemRoles(List<SystemRoleBO> systemRoles) {
		List<SystemRoleEO> systemRoleEos = new ArrayList<SystemRoleEO>();
		for(SystemRoleBO item : systemRoles) {
			SystemRoleEO systemRoleEo = new SystemRoleEO();
			BeanUtils.copyProperties(item, systemRoleEo);
			systemRoleEos.add(systemRoleEo);
		}
		
		Integer result = systemRoleDao.updateSystemRoles(systemRoleEos);
		return result;
	}

	@Override
	public List<SystemRoleBO> searchPagedSystemRoles(SystemRoleBO systemRole) {
		SystemRoleEO systemRoleEo = new SystemRoleEO();
		BeanUtils.copyProperties(systemRole, systemRoleEo);
		List<SystemRoleEO> systemRoleEos = systemRoleDao.searchPagedSystemRoles(systemRoleEo);
		
		List<SystemRoleBO> systemRoleBos = new ArrayList<SystemRoleBO>();
		for(SystemRoleEO item : systemRoleEos) {
			SystemRoleBO systemRoleBo = new SystemRoleBO();
			BeanUtils.copyProperties(item, systemRoleBo);

			systemRoleBo.setPageIndex(systemRole.getPageIndex());
			systemRoleBo.setPageSize(systemRole.getPageSize());
			systemRoleBos.add(systemRoleBo);
		}
		return systemRoleBos;
	}

	@Override
	public List<SystemRoleBO> searchAllSystemRoles(SystemRoleBO systemRole) {
		SystemRoleEO systemRoleEo = new SystemRoleEO();
		BeanUtils.copyProperties(systemRole, systemRoleEo);
		List<SystemRoleEO> systemRoleEos = systemRoleDao.searchAllSystemRoles(systemRoleEo);
		
		List<SystemRoleBO> systemRoleBos = new ArrayList<SystemRoleBO>();
		for(SystemRoleEO item : systemRoleEos) {
			SystemRoleBO systemRoleBo = new SystemRoleBO();
			BeanUtils.copyProperties(item, systemRoleBo);
			systemRoleBos.add(systemRoleBo);
		}
		return systemRoleBos;
	}

}
