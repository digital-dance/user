package com.digital.dance.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.digital.dance.dao.SystemRoleDao;
import com.digital.dance.entity.SystemRoleEO;
import com.digital.dance.entity.UserEO;
import com.digital.dance.user.commons.unittest.UnitTestBase;

public class SystemRoleDaoTest extends UnitTestBase {

	private SystemRoleDao systemRoleDao;
	
	@Test
	public void testAddSystemRoleWithNull() {
		context = getInstance("classpath*:**/user-dao-test.xml");
		systemRoleDao = context.getBean(SystemRoleDao.class);
		
		Boolean throwException = false;
		
		try {
			SystemRoleEO systemRoleEo = new SystemRoleEO();
			systemRoleDao.addSystemRole(systemRoleEo);
		} catch (Exception e) {
			throwException = true;
		}
		
		org.junit.Assert.assertTrue(throwException);
	}
	
	@Test
	public void testAddSystemRoleWithData() {
		context = getInstance("classpath*:**/user-dao-test.xml");
		systemRoleDao = context.getBean(SystemRoleDao.class);
		
		Boolean throwException = false;
		
		try {
			SystemRoleEO systemRoleEo = new SystemRoleEO();
			systemRoleEo.setRoleId("4321");
			systemRoleEo.setRoleName("dcba");
			
			systemRoleDao.addSystemRole(systemRoleEo);
		} catch (Exception e) {
			throwException = true;
		}
		
		org.junit.Assert.assertFalse(throwException);
	}
	
	@Test
	public void testFindSystemRoleByRoleNameWithNonCondition() {
		context = getInstance("classpath*:**/user-dao-test.xml");
		systemRoleDao = context.getBean(SystemRoleDao.class);
		
		Boolean throwException = false;	
		
		try {
			systemRoleDao.findSystemRoleByRoleName(null);
		} catch (Exception e) {
			throwException = true;
		}
		
		org.junit.Assert.assertFalse(throwException);
	}
	
	@Test
	public void testFindSystemRoleByRoleNameWithCondition() {
		context = getInstance("classpath*:**/user-dao-test.xml");
		systemRoleDao = context.getBean(SystemRoleDao.class);
		
		Boolean throwException = false;	
		
		try {
			SystemRoleEO systemRoleEo = systemRoleDao.findSystemRoleByRoleName("dcba");
			if(systemRoleEo != null) {
				System.out.println(systemRoleEo.getRoleId() + "," + systemRoleEo.getRoleName());
				org.junit.Assert.assertTrue(systemRoleEo.getRoleName().equals("dcba"));
			}
		} catch (Exception e) {
			throwException = true;
		}
		
		org.junit.Assert.assertFalse(throwException);
	}
	
	@Test
	public void testFindSystemRoleByRoleIdWithNonCondition() {
		context = getInstance("classpath*:**/user-dao-test.xml");
		systemRoleDao = context.getBean(SystemRoleDao.class);
		
		Boolean throwException = false;	
		
		try {
			systemRoleDao.findSystemRoleByRoleId(null);
		} catch (Exception e) {
			throwException = true;
		}
		
		org.junit.Assert.assertFalse(throwException);
	}
		
	@Test
	public void testFindSystemRoleByRoleIdWithCondition() {
		context = getInstance("classpath*:**/user-dao-test.xml");
		systemRoleDao = context.getBean(SystemRoleDao.class);
		
		Boolean throwException = false;	
		
		try {
			SystemRoleEO systemRoleEo = systemRoleDao.findSystemRoleByRoleId("4321");
			if(systemRoleEo != null) {
				System.out.println(systemRoleEo.getRoleId() + "," + systemRoleEo.getRoleName());
				org.junit.Assert.assertTrue(systemRoleEo.getRoleId().equals("4321"));
			}
		} catch (Exception e) {
			throwException = true;
		}
		
		org.junit.Assert.assertFalse(throwException);
	}
	
	@Test
	public void testDeleteSystemRolesWithNonCondition() {
		context = getInstance("classpath*:**/user-dao-test.xml");
		systemRoleDao = context.getBean(SystemRoleDao.class);
		
		Boolean throwException = false;	
		
		try {
			List<SystemRoleEO> systemRoleEos = new ArrayList<SystemRoleEO>();
			systemRoleDao.deleteSystemRoles(systemRoleEos);
		} catch (Exception e) {
			throwException = true;
		}
		
		org.junit.Assert.assertFalse(throwException);
	}
	
	@Test
	public void testDeleteSystemRolesWithCondition() {
		context = getInstance("classpath*:**/user-dao-test.xml");
		systemRoleDao = context.getBean(SystemRoleDao.class);
		
		Boolean throwException = false;	
		
		try {
			List<SystemRoleEO> systemRoleEos = new ArrayList<SystemRoleEO>();
			
			SystemRoleEO systemRoleEo1 = new SystemRoleEO();
			systemRoleEo1.setRoleId("4321");
			systemRoleEos.add(systemRoleEo1);
			
			//表中不存在roleid为1234的role记录
			SystemRoleEO systemRoleEo2 = new SystemRoleEO();
			systemRoleEo2.setRoleId("1234");
			systemRoleEos.add(systemRoleEo2);
			
			systemRoleDao.deleteSystemRoles(systemRoleEos);
		} catch (Exception e) {
			throwException = true;
		}
		
		org.junit.Assert.assertFalse(throwException);
	}
	
	@Test
	public void testUpdateSystemRolesSystemRolesWithNull() {
		context = getInstance("classpath*:**/user-dao-test.xml");
		systemRoleDao = context.getBean(SystemRoleDao.class);
		
		Boolean throwException = false;	
		
		try {
			List<SystemRoleEO> systemRoleEos = new ArrayList<SystemRoleEO>();
			systemRoleDao.updateSystemRoles(systemRoleEos);
		} catch (Exception e) {
			throwException = true;
		}
		
		org.junit.Assert.assertFalse(throwException);
	}
	
	@Test
	public void testUpdateSystemRolesSystemRolesWithCondition() {
		context = getInstance("classpath*:**/user-dao-test.xml");
		systemRoleDao = context.getBean(SystemRoleDao.class);
		
		Boolean throwException = false;	
		
		try {
			List<SystemRoleEO> systemRoleEos = new ArrayList<SystemRoleEO>();
			
			SystemRoleEO systemRoleEo1 = new SystemRoleEO();
			systemRoleEo1.setRoleId("4321");
			systemRoleEo1.setRoleName("Developer");
			systemRoleEos.add(systemRoleEo1);
			
			//表中不存在roleid为1234的role记录
			SystemRoleEO systemRoleEo2 = new SystemRoleEO();
			systemRoleEo2.setRoleId("1234");
			systemRoleEo2.setRoleName("SA");
			systemRoleEos.add(systemRoleEo2);
			
			systemRoleDao.updateSystemRoles(systemRoleEos);
		} catch (Exception e) {
			throwException = true;
		}
		
		org.junit.Assert.assertFalse(throwException);
	}	

	/**
	 * 	测试查询条件为空
	 */
	@Test
	public void testFindPagedSystemRolesWithNonConditions() {
		context = getInstance("classpath*:**/user-dao-test.xml");
		systemRoleDao = context.getBean(SystemRoleDao.class);

		Boolean throwException = false;
		try {
			List<SystemRoleEO> systemRoleEoList = systemRoleDao.findPagedSystemRoles(null);
			for (SystemRoleEO item : systemRoleEoList) {
				System.out.println(item.getRoleId() + "," + item.getRoleName());
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
	public void testFindPagedSystemRolesWithConditions() {
		context = getInstance("classpath*:**/user-dao-test.xml");
		systemRoleDao = context.getBean(SystemRoleDao.class);

		SystemRoleEO systemRoleEo = new SystemRoleEO();
		systemRoleEo.setRoleId("123456");
		systemRoleEo.setRoleName("Tester");

		Boolean throwException = false;

		try {
			List<SystemRoleEO> systemRoleEoList = systemRoleDao.findPagedSystemRoles(systemRoleEo);
			for (SystemRoleEO item : systemRoleEoList) {
				System.out.println(item.getRoleId() + "," + item.getRoleName());
				org.junit.Assert.assertTrue(item.getRoleId().equals("123456") && item.getRoleName().equals("Tester"));
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
		systemRoleDao = context.getBean(SystemRoleDao.class);

		SystemRoleEO systemRoleEo = new SystemRoleEO();
		systemRoleEo.setRoleName("Tester");
		systemRoleEo.setPageIndex(0);
		systemRoleEo.setPageSize(1);

		Boolean throwException = false;

		try {
			List<SystemRoleEO> systemRoleEoList = systemRoleDao.findPagedSystemRoles(systemRoleEo);
			for (SystemRoleEO item : systemRoleEoList) {
				System.out.println(item.getRoleId() + "," + item.getRoleName());
				org.junit.Assert.assertTrue(item.getRoleName().equals("Tester"));
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
	public void testFindAllSystemRolesWithNonConditions() {
		context = getInstance("classpath*:**/user-dao-test.xml");
		systemRoleDao = context.getBean(SystemRoleDao.class);

		Boolean throwException = false;
		try {		
			List<SystemRoleEO> systemRoleEoList = systemRoleDao.findAllSystemRoles(null);
			for (SystemRoleEO item : systemRoleEoList) {
				System.out.println(item.getRoleId() + "," + item.getRoleName());
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
	public void testFindAllSystemRolessWithConditions() {
		context = getInstance("classpath*:**/user-dao-test.xml");
		systemRoleDao = context.getBean(SystemRoleDao.class);

		Boolean throwException = false;
		
		SystemRoleEO systemRoleEo = new SystemRoleEO();
		systemRoleEo.setRoleId("123456");
		systemRoleEo.setRoleName("Tester");
		
		try {
			List<SystemRoleEO> systemRoleEoList = systemRoleDao.findAllSystemRoles(systemRoleEo);
			for (SystemRoleEO item : systemRoleEoList) {
				System.out.println(item.getRoleId() + "," + item.getRoleName());
				org.junit.Assert.assertTrue(item.getRoleId().equals("123456") && item.getRoleName().equals("Tester"));
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
	public void testSearchPagedSystemRolesWithNonConditions() {
		context = getInstance("classpath*:**/user-dao-test.xml");
		systemRoleDao = context.getBean(SystemRoleDao.class);

		Boolean throwException = false;
		try {
			List<SystemRoleEO> systemRoleEoList = systemRoleDao.searchPagedSystemRoles(null);
			for (SystemRoleEO item : systemRoleEoList) {
				System.out.println(item.getRoleId() + "," + item.getRoleName());
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
	public void testSearchPagedSystemRolesWithConditions() {
		context = getInstance("classpath*:**/user-dao-test.xml");
		systemRoleDao = context.getBean(SystemRoleDao.class);

		Boolean throwException = false;
		
		SystemRoleEO systemRoleEo = new SystemRoleEO();
		systemRoleEo.setRoleId("23456");
		systemRoleEo.setRoleName("Tester");

		try {
			List<SystemRoleEO> systemRoleEoList = systemRoleDao.searchPagedSystemRoles(systemRoleEo);
			for (SystemRoleEO item : systemRoleEoList) {
				System.out.println(item.getRoleId() + "," + item.getRoleName());
				org.junit.Assert.assertTrue(item.getRoleId().contains("23456") && item.getRoleName().contains("Tester"));
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
	public void testSearchPagedSystemRolesWithConditionsAndPages() {
		context = getInstance("classpath*:**/user-dao-test.xml");
		systemRoleDao = context.getBean(SystemRoleDao.class);

		Boolean throwException = false;
		
		SystemRoleEO systemRoleEo = new SystemRoleEO();
		systemRoleEo.setRoleName("Server Room");
		systemRoleEo.setPageIndex(1);
		systemRoleEo.setPageSize(2);

		try {
			List<SystemRoleEO> systemRoleEoList = systemRoleDao.searchPagedSystemRoles(systemRoleEo);
			for (SystemRoleEO item : systemRoleEoList) {
				System.out.println(item.getRoleId() + "," + item.getRoleName());
				org.junit.Assert.assertTrue(item.getRoleName().contains("Server Room"));
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
	public void testSearchAllSystemRolesWithNonConditions() {
		context = getInstance("classpath*:**/user-dao-test.xml");
		systemRoleDao = context.getBean(SystemRoleDao.class);

		Boolean throwException = false;
		try {
			List<SystemRoleEO> systemRoleEoList = systemRoleDao.searchAllSystemRoles(null);
			for (SystemRoleEO item : systemRoleEoList) {
				System.out.println(item.getRoleId() + "," + item.getRoleName());
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
	public void testSearchAllSystemRolesWithConditions() {
		context = getInstance("classpath*:**/user-dao-test.xml");
		systemRoleDao = context.getBean(SystemRoleDao.class);

		Boolean throwException = false;
		
		SystemRoleEO systemRoleEo = new SystemRoleEO();
		systemRoleEo.setRoleName("Server Room");

		try {
			List<SystemRoleEO> systemRoleEoList = systemRoleDao.searchAllSystemRoles(systemRoleEo);
			for (SystemRoleEO item : systemRoleEoList) {
				System.out.println(item.getRoleId() + "," + item.getRoleName());
				org.junit.Assert.assertTrue(item.getRoleName().contains("Server Room"));
			}
		} catch (Exception e) {
			throwException = true;
		}
		org.junit.Assert.assertFalse(throwException);
	}

}
