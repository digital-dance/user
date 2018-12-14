package com.digital.dance.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.digital.dance.dao.SystemUserRoleDao;
import com.digital.dance.entity.SystemUserRoleEO;
import com.digital.dance.entity.UserEO;
import com.digital.dance.user.commons.PrimaryKeyGenerator;
import com.digital.dance.user.commons.unittest.UnitTestBase;

public class SystemUserRoleDaoTest extends UnitTestBase {

	private SystemUserRoleDao systemUserRoleDao;
	
	@Test
	//添加
	public void testAddSystemUserRoleWithNull() {
		context = getInstance("classpath*:**/user-dao-test.xml");
		systemUserRoleDao = context.getBean(SystemUserRoleDao.class);
		
		Boolean throwException = false;
		
		try {
			SystemUserRoleEO systemUserRoleEo = new SystemUserRoleEO();
			systemUserRoleDao.addSystemUserRole(systemUserRoleEo);
		} catch (Exception e) {
			throwException = true;
		}
		
		org.junit.Assert.assertTrue(throwException);
	}
	
	@Test
	//添加
	public void testAddSystemUserRoleWithData() {
		context = getInstance("classpath*:**/user-dao-test.xml");
		systemUserRoleDao = context.getBean(SystemUserRoleDao.class);
		
		Boolean throwException = false;
		
		try {
			SystemUserRoleEO systemUserRoleEo = new SystemUserRoleEO();
			systemUserRoleEo.setRoleId(PrimaryKeyGenerator.generatePrimaryKey("user_role"));
			systemUserRoleEo.setUserId("sr_operator");
			
			systemUserRoleDao.addSystemUserRole(systemUserRoleEo);
		} catch (Exception e) {
			throwException = true;
		}
		
		org.junit.Assert.assertFalse(throwException);
	}
	
	@Test
	//批量添加
	public void testAddSystemUserRolesWithNull() {
		context = getInstance("classpath*:**/user-dao-test.xml");
		systemUserRoleDao = context.getBean(SystemUserRoleDao.class);
		
		Boolean throwException = false;
		
		try {
			List<SystemUserRoleEO> systemUserRoleEoList = new ArrayList<SystemUserRoleEO>();
			systemUserRoleDao.addSystemUserRoles(systemUserRoleEoList);
		} catch (Exception e) {
			throwException = true;
		}
		
		org.junit.Assert.assertFalse(throwException);
	}
	
	@Test
	//批量添加
	public void testAddSystemUserRolesWithData() {
		context = getInstance("classpath*:**/user-dao-test.xml");
		systemUserRoleDao = context.getBean(SystemUserRoleDao.class);
		
		Boolean throwException = false;
		
		List<SystemUserRoleEO> systemUserRoleEoList = new ArrayList<SystemUserRoleEO>();
		for(Integer i=1;i<5;i++) {
			SystemUserRoleEO systemUserRoleEo = new SystemUserRoleEO();
			systemUserRoleEo.setRoleId(i.toString());
			systemUserRoleEo.setUserId("a" + (i-1));
			systemUserRoleEoList.add(systemUserRoleEo);
		}
		
		try {
			systemUserRoleDao.addSystemUserRoles(systemUserRoleEoList);
		} catch (Exception e) {
			throwException = true;
		}
		
		org.junit.Assert.assertFalse(throwException);
	}
	
	@Test
	//根据userId查找
	public void testFindSystemUserRoleByUserIdWithNonCondition() {
		context = getInstance("classpath*:**/user-dao-test.xml");
		systemUserRoleDao = context.getBean(SystemUserRoleDao.class);
		
		Boolean throwException = false;	
		
		try {
			systemUserRoleDao.findSystemUserRoleByUserId(null);
		} catch (Exception e) {
			throwException = true;
		}
		
		org.junit.Assert.assertFalse(throwException);
	}
	
	@Test
	//根据userId查找
	public void testFindSystemUserRoleByUserIdWithCondition() {
		context = getInstance("classpath*:**/user-dao-test.xml");
		systemUserRoleDao = context.getBean(SystemUserRoleDao.class);
		
		Boolean throwException = false;	
		
		try {
			SystemUserRoleEO systemUserRoleEo = systemUserRoleDao.findSystemUserRoleByUserId("a0");
			if(systemUserRoleEo != null) {
				System.out.println(systemUserRoleEo.getRoleId() + "," + systemUserRoleEo.getUserId());
				org.junit.Assert.assertTrue(systemUserRoleEo.getUserId().equals("a0"));
			}
		} catch (Exception e) {
			throwException = true;
		}
		
		org.junit.Assert.assertFalse(throwException);
	}
	
	@Test
	//根据roleId查询
	public void testFindSystemUserRoleByRoleIdWithNonCondition() {
		context = getInstance("classpath*:**/user-dao-test.xml");
		systemUserRoleDao = context.getBean(SystemUserRoleDao.class);
		
		Boolean throwException = false;	
		
		try {
			systemUserRoleDao.findSystemUserRoleByRoleId(null);
		} catch (Exception e) {
			throwException = true;
		}
		
		org.junit.Assert.assertFalse(throwException);
	}
		
	@Test
	//根据roleId查询
	public void testFindSystemUserRoleByRoleIdWithCondition() {
		context = getInstance("classpath*:**/user-dao-test.xml");
		systemUserRoleDao = context.getBean(SystemUserRoleDao.class);
		
		Boolean throwException = false;	
		
		try {
			SystemUserRoleEO systemUserRoleEo = systemUserRoleDao.findSystemUserRoleByRoleId("123456");
			if(systemUserRoleEo != null) {
				System.out.println(systemUserRoleEo.getRoleId() + "," + systemUserRoleEo.getUserId());
				org.junit.Assert.assertTrue(systemUserRoleEo.getRoleId().equals("123456"));
			}
		} catch (Exception e) {
			throwException = true;
		}
		
		org.junit.Assert.assertFalse(throwException);
	}
	
	@Test
	//删除
	public void testDeleteSystemUserRolesWithNonCondition() {
		context = getInstance("classpath*:**/user-dao-test.xml");
		systemUserRoleDao = context.getBean(SystemUserRoleDao.class);
		
		Boolean throwException = false;	
		
		try {
			List<SystemUserRoleEO> systemUserRoleEos = new ArrayList<SystemUserRoleEO>();
			systemUserRoleDao.deleteSystemUserRoles(systemUserRoleEos);
		} catch (Exception e) {
			throwException = true;
		}
		
		org.junit.Assert.assertFalse(throwException);
	}
	
	@Test
	//删除
	public void testDeleteSystemUserRolesWithCondition() {
		context = getInstance("classpath*:**/user-dao-test.xml");
		systemUserRoleDao = context.getBean(SystemUserRoleDao.class);
		
		Boolean throwException = false;	
		
		try {
			List<SystemUserRoleEO> systemUserRoleEoList = new ArrayList<SystemUserRoleEO>();
			
			for(Integer i=1;i<5;i++) {
				SystemUserRoleEO systemUserRoleEo = new SystemUserRoleEO();
				systemUserRoleEo.setRoleId(i.toString());
				systemUserRoleEo.setUserId("a" + (i-1));
				systemUserRoleEoList.add(systemUserRoleEo);
			}
			
			systemUserRoleDao.deleteSystemUserRoles(systemUserRoleEoList);
		} catch (Exception e) {
			throwException = true;
		}
		
		org.junit.Assert.assertFalse(throwException);
	}
		

	/**
	 * 	测试查询条件为空
	 */
	@Test
	public void testFindPagedSystemUserRolesWithNonConditions() {
		context = getInstance("classpath*:**/user-dao-test.xml");
		systemUserRoleDao = context.getBean(SystemUserRoleDao.class);

		Boolean throwException = false;
		try {
			SystemUserRoleEO systemUserRoleEo = new SystemUserRoleEO();
			List<SystemUserRoleEO> systemUserRoleEoList = systemUserRoleDao.findPagedSystemUserRoles(systemUserRoleEo);
			for (SystemUserRoleEO item : systemUserRoleEoList) {
				System.out.println(item.getRoleId() + "," + item.getOrgId());
			}
		} catch (Exception e) {
			throwException = true;
		}
		org.junit.Assert.assertFalse(throwException);

	}

	/**
	 * 测试条件查询，不分页
	 */
	@Test
	public void testFindPagedSystemUserRolesWithConditions() {
		context = getInstance("classpath*:**/user-dao-test.xml");
		systemUserRoleDao = context.getBean(SystemUserRoleDao.class);

		SystemUserRoleEO systemUserRoleEo = new SystemUserRoleEO();
		systemUserRoleEo.setUserId("sr_operator");

		Boolean throwException = false;

		try {
			List<SystemUserRoleEO> systemUserRoleEoList = systemUserRoleDao.findPagedSystemUserRoles(systemUserRoleEo);
			for (SystemUserRoleEO item : systemUserRoleEoList) {
				System.out.println(item.getRoleId() + "," + item.getUserId());
				org.junit.Assert.assertTrue(item.getUserId().equals("sr_operator"));
			}
		} catch (Exception e) {
			throwException = true;
		}
		
		org.junit.Assert.assertFalse(throwException);
	}

	/**
	 * 测试条件查询，分页
	 */
	@Test
	public void testFindPagedUsersWithConditionsAndPages() {
		context = getInstance("classpath*:**/user-dao-test.xml");
		systemUserRoleDao = context.getBean(SystemUserRoleDao.class);

		SystemUserRoleEO systemUserRoleEo = new SystemUserRoleEO();
		systemUserRoleEo.setUserId("sr_operator");
		systemUserRoleEo.setPageIndex(0);
		systemUserRoleEo.setPageSize(1);

		Boolean throwException = false;

		try {
			List<SystemUserRoleEO> systemUserRoleEoList = systemUserRoleDao.findPagedSystemUserRoles(systemUserRoleEo);
			for (SystemUserRoleEO item : systemUserRoleEoList) {
				System.out.println(item.getRoleId() + "," + item.getUserId());
				org.junit.Assert.assertTrue(item.getUserId().equals("sr_operator"));
			}
		} catch (Exception e) {
			throwException = true;
		}
		org.junit.Assert.assertFalse(throwException);
	}

	/**
	 * 测试查询条件为空
	 */
	@Test
	public void testFindAllSystemUserRolesWithNonConditions() {
		context = getInstance("classpath*:**/user-dao-test.xml");
		systemUserRoleDao = context.getBean(SystemUserRoleDao.class);

		Boolean throwException = false;
		try {
			SystemUserRoleEO systemUserRoleEo = new SystemUserRoleEO();
			List<SystemUserRoleEO> systemUserRoleEoList = systemUserRoleDao.findAllSystemUserRoles(systemUserRoleEo);
			for (SystemUserRoleEO item : systemUserRoleEoList) {
				System.out.println(item.getRoleId() + "," + item.getUserId());
			}
		} catch (Exception e) {
			throwException = true;
		}
		org.junit.Assert.assertFalse(throwException);
	}

	/**
	 * 测试条件查询
	 */
	@Test
	public void testFindAllSystemUserRolessWithConditions() {
		context = getInstance("classpath*:**/user-dao-test.xml");
		systemUserRoleDao = context.getBean(SystemUserRoleDao.class);

		Boolean throwException = false;
		
		SystemUserRoleEO systemUserRoleEo = new SystemUserRoleEO();
		systemUserRoleEo.setRoleId("123456");
		systemUserRoleEo.setUserId("sr_operator");
		
		try {
			List<SystemUserRoleEO> systemUserRoleEoList = systemUserRoleDao.findAllSystemUserRoles(systemUserRoleEo);
			for (SystemUserRoleEO item : systemUserRoleEoList) {
				System.out.println(item.getRoleId() + "," + item.getUserId());
				org.junit.Assert.assertTrue(item.getRoleId().equals("123456") && item.getUserId().equals("sr_operator"));
			}
		} catch (Exception e) {
			throwException = true;
		}
		org.junit.Assert.assertFalse(throwException);
	}

	
	/**
	 * 测试模糊条件查询，查询条件为空
	 */
	@Test
	public void testSearchPagedSystemUserRolesWithNonConditions() {
		context = getInstance("classpath*:**/user-dao-test.xml");
		systemUserRoleDao = context.getBean(SystemUserRoleDao.class);

		Boolean throwException = false;
		SystemUserRoleEO systemUserRoleEo = new SystemUserRoleEO();
		try {
			List<SystemUserRoleEO> systemUserRoleEoList = systemUserRoleDao.searchPagedSystemUserRoles(systemUserRoleEo);
			for (SystemUserRoleEO item : systemUserRoleEoList) {
				System.out.println(item.getRoleId() + "," + item.getUserId());
			}
		} catch (Exception e) {
			throwException = true;
		}
		org.junit.Assert.assertFalse(throwException);

	}

	/**
	 * 测试模糊条件查询，不分页
	 */
	@Test
	public void testSearchPagedSystemUserRolesWithConditions() {
		context = getInstance("classpath*:**/user-dao-test.xml");
		systemUserRoleDao = context.getBean(SystemUserRoleDao.class);

		Boolean throwException = false;
		
		SystemUserRoleEO systemUserRoleEo = new SystemUserRoleEO();
		systemUserRoleEo.setRoleId("23456");
		systemUserRoleEo.setUserId("operator");

		try {
			List<SystemUserRoleEO> systemUserRoleEoList = systemUserRoleDao.searchPagedSystemUserRoles(systemUserRoleEo);
			for (SystemUserRoleEO item : systemUserRoleEoList) {
				System.out.println(item.getRoleId() + "," + item.getUserId());
				org.junit.Assert.assertTrue(item.getRoleId().contains("23456") && item.getUserId().contains("operator"));
			}
		} catch (Exception e) {
			throwException = true;
		}
		org.junit.Assert.assertFalse(throwException);
	}

	/**
	 * 测试模糊条件查询，分页
	 */
	@Test
	public void testSearchPagedSystemUserRolesWithConditionsAndPages() {
		context = getInstance("classpath*:**/user-dao-test.xml");
		systemUserRoleDao = context.getBean(SystemUserRoleDao.class);

		Boolean throwException = false;
		
		SystemUserRoleEO systemUserRoleEo = new SystemUserRoleEO();
		systemUserRoleEo.setUserId("operator");
		systemUserRoleEo.setPageIndex(0);
		systemUserRoleEo.setPageSize(1);

		try {
			List<SystemUserRoleEO> systemUserRoleEoList = systemUserRoleDao.searchPagedSystemUserRoles(systemUserRoleEo);
			for (SystemUserRoleEO item : systemUserRoleEoList) {
				System.out.println(item.getRoleId() + "," + item.getUserId());
				org.junit.Assert.assertTrue(item.getUserId().contains("operator"));
			}
		} catch (Exception e) {
			throwException = true;
		}
		org.junit.Assert.assertFalse(throwException);
	}

	/**
	 * 测试模糊条件查询，查询条件为空
	 */
	@Test
	public void testSearchAllSystemUserRolesWithNonConditions() {
		context = getInstance("classpath*:**/user-dao-test.xml");
		systemUserRoleDao = context.getBean(SystemUserRoleDao.class);

		Boolean throwException = false;
		
		SystemUserRoleEO systemUserRoleEo = new SystemUserRoleEO();
		try {
			List<SystemUserRoleEO> systemUserRoleEoList = systemUserRoleDao.searchAllSystemUserRoles(systemUserRoleEo);
			for (SystemUserRoleEO item : systemUserRoleEoList) {
				System.out.println(item.getRoleId() + "," + item.getUserId());
			}
		} catch (Exception e) {
			throwException = true;
		}
		org.junit.Assert.assertFalse(throwException);

	}

	/**
	 *  测试模糊条件查询
	 */
	@Test
	public void testSearchAllSystemUserRolesWithConditions() {
		context = getInstance("classpath*:**/user-dao-test.xml");
		systemUserRoleDao = context.getBean(SystemUserRoleDao.class);

		Boolean throwException = false;
		
		SystemUserRoleEO systemUserRoleEo = new SystemUserRoleEO();
		systemUserRoleEo.setUserId("operator");

		try {
			List<SystemUserRoleEO> systemUserRoleEoList = systemUserRoleDao.searchAllSystemUserRoles(systemUserRoleEo);
			for (SystemUserRoleEO item : systemUserRoleEoList) {
				System.out.println(item.getRoleId() + "," + item.getUserId());
				org.junit.Assert.assertTrue(item.getUserId().contains("operator"));
			}
		} catch (Exception e) {
			throwException = true;
		}
		org.junit.Assert.assertFalse(throwException);
	}

}
