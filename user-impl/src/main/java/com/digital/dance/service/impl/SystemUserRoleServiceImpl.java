package com.digital.dance.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.digital.dance.bo.SystemUserRoleBO;
import com.digital.dance.dao.SystemUserRoleDao;
import com.digital.dance.entity.SystemUserRoleEO;
import com.digital.dance.service.SystemUserRoleService;
import com.digital.dance.user.commons.BeanUtils;

//@Service
public class SystemUserRoleServiceImpl implements SystemUserRoleService {

	@Autowired
	private SystemUserRoleDao systemUserRoleDao;
	
	@Override
	public Integer addSystemUserRole(SystemUserRoleBO systemUserRole) {
		SystemUserRoleEO systemUserRoleEo = new SystemUserRoleEO();
		if(systemUserRole == null) {
			return -1;
		}
		
		BeanUtils.copyProperties(systemUserRole, systemUserRoleEo);
		Integer result = systemUserRoleDao.addSystemUserRole(systemUserRoleEo);
		return result;
	}
	
	/**
	 * add system user roles
	 * @param systemUserRole
	 * @return
	 */
	public Integer addSystemUserRoles(List<SystemUserRoleBO> systemUserRoles){
		if(systemUserRoles == null) {
			return -1;
		}
		
		List<SystemUserRoleEO> systemUserRoleEos = new ArrayList<SystemUserRoleEO>();
		for(SystemUserRoleBO systemUserRole : systemUserRoles) {
			SystemUserRoleEO systemUserEo = new SystemUserRoleEO();
			BeanUtils.copyProperties(systemUserRole, systemUserEo);
			systemUserRoleEos.add(systemUserEo);
		}
		
		Integer result = systemUserRoleDao.addSystemUserRoles(systemUserRoleEos);
		return result;
	}

	@Override
	public SystemUserRoleBO findSystemUserRoleByUserId(String userId) {
		SystemUserRoleEO systemUserRoleEo = systemUserRoleDao.findSystemUserRoleByUserId(userId);
		if(systemUserRoleEo == null) {
			return null;
		}
		
		SystemUserRoleBO systemUserRoleBo = new SystemUserRoleBO();
		BeanUtils.copyProperties(systemUserRoleEo, systemUserRoleBo);
		return systemUserRoleBo;
	}

	@Override
	public SystemUserRoleBO findSystemUserRoleByRoleId(String roleId) {
		SystemUserRoleEO systemUserRoleEo = systemUserRoleDao.findSystemUserRoleByRoleId(roleId);
		if(systemUserRoleEo == null) {
			return null;
		}
		
		SystemUserRoleBO systemUserRoleBo = new SystemUserRoleBO();
		BeanUtils.copyProperties(systemUserRoleEo, systemUserRoleBo);
		return systemUserRoleBo;
	}

	@Override
	public List<SystemUserRoleBO> findPagedSystemUserRoles(SystemUserRoleBO systemUserRole) {
		SystemUserRoleEO systemUserRoleEo = new SystemUserRoleEO();
		BeanUtils.copyProperties(systemUserRole, systemUserRoleEo);
		
		List<SystemUserRoleEO> systemUserRoleEos = systemUserRoleDao.findPagedSystemUserRoles(systemUserRoleEo);
		if(systemUserRoleEos == null) {
			return null;
		}
		
		List<SystemUserRoleBO> systemUserRoleBos = new ArrayList<SystemUserRoleBO>();
		for(SystemUserRoleEO item : systemUserRoleEos) {
			SystemUserRoleBO systemUserRoleBo = new SystemUserRoleBO();
			BeanUtils.copyProperties(item, systemUserRoleBo);
			systemUserRoleBos.add(systemUserRoleBo);
		}
		return systemUserRoleBos;
	}

	@Override
	public List<SystemUserRoleBO> findAllSystemUserRoles(SystemUserRoleBO systemUserRole) {
		SystemUserRoleEO systemUserRoleEo = new SystemUserRoleEO();
		BeanUtils.copyProperties(systemUserRole, systemUserRoleEo);
		
		List<SystemUserRoleEO> systemUserRoleEos = systemUserRoleDao.findAllSystemUserRoles(systemUserRoleEo);
		if(systemUserRoleEos == null) {
			return null;
		}
		
		List<SystemUserRoleBO> systemUserRoleBos = new ArrayList<SystemUserRoleBO>();
		for(SystemUserRoleEO item : systemUserRoleEos) {
			SystemUserRoleBO systemUserRoleBo = new SystemUserRoleBO();
			BeanUtils.copyProperties(item, systemUserRoleBo);
			systemUserRoleBos.add(systemUserRoleBo);
		}
		return systemUserRoleBos;
	}

	@Override
	public List<SystemUserRoleBO> searchPagedSystemUserRoles(SystemUserRoleBO systemUserRole) {
		SystemUserRoleEO systemUserRoleEo = new SystemUserRoleEO();
		BeanUtils.copyProperties(systemUserRole, systemUserRoleEo);
		
		List<SystemUserRoleEO> systemUserRoleEos = systemUserRoleDao.searchPagedSystemUserRoles(systemUserRoleEo);
		if(systemUserRoleEos == null) {
			return null;
		}
		
		List<SystemUserRoleBO> systemUserRoleBos = new ArrayList<SystemUserRoleBO>();
		for(SystemUserRoleEO item : systemUserRoleEos) {
			SystemUserRoleBO systemUserRoleBo = new SystemUserRoleBO();
			BeanUtils.copyProperties(item, systemUserRoleBo);
			systemUserRoleBos.add(systemUserRoleBo);
		}
		return systemUserRoleBos;
	}

	@Override
	public List<SystemUserRoleBO> searchAllSystemUserRoles(SystemUserRoleBO systemUserRole) {
		SystemUserRoleEO systemUserRoleEo = new SystemUserRoleEO();
		BeanUtils.copyProperties(systemUserRole, systemUserRoleEo);
		
		List<SystemUserRoleEO> systemUserRoleEos = systemUserRoleDao.searchAllSystemUserRoles(systemUserRoleEo);
		if(systemUserRoleEos == null) {
			return null;
		}
		
		List<SystemUserRoleBO> systemUserRoleBos = new ArrayList<SystemUserRoleBO>();
		for(SystemUserRoleEO item : systemUserRoleEos) {
			SystemUserRoleBO systemUserRoleBo = new SystemUserRoleBO();
			BeanUtils.copyProperties(item, systemUserRoleBo);
			systemUserRoleBos.add(systemUserRoleBo);
		}
		return systemUserRoleBos;
	}
	
	@Override
	public Integer deleteSystemUserRoles(List<SystemUserRoleBO> systemUserRoles) {
		if(systemUserRoles == null) {
			return -1;
		}
		
		List<SystemUserRoleEO> systemUserRoleEos = new ArrayList<SystemUserRoleEO>();
		for(SystemUserRoleBO systemUserRole : systemUserRoles) {
			SystemUserRoleEO systemUserEo = new SystemUserRoleEO();
			BeanUtils.copyProperties(systemUserRole, systemUserEo);
			systemUserRoleEos.add(systemUserEo);
		}
		
		Integer result = systemUserRoleDao.deleteSystemUserRoles(systemUserRoleEos);
		return result;
	}

	@Override
	public Integer updateSystemUserRoles(List<SystemUserRoleBO> systemUserRoles) {
		return null;
	}

}
