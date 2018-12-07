package com.digital.dance.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.digital.dance.bo.SystemRoleBO;
import com.digital.dance.service.SystemRoleService;
import com.digital.dance.user.commons.PrimaryKeyGenerator;
import com.digital.dance.user.commons.unittest.UnitTestBase;

public class SystemRoleServiceImplTest extends UnitTestBase {
	
	private SystemRoleService systemRoleService;
	
	@Test
	public void testAddSystemRoleWithNull() {
		context = getInstance("classpath*:**/user-impl-test-context.xml");
		systemRoleService = (SystemRoleService)(SystemRoleService)context.getBean("systemRoleService");
		
		Boolean throwException = false;
		
		try {
			SystemRoleBO systemRoleBo = new SystemRoleBO();
			systemRoleService.addSystemRole(systemRoleBo);
		} catch (Exception e) {
			throwException = true;
		}
		
		org.junit.Assert.assertTrue(throwException);
	}
	
	@Test
	public void testAddSystemRoleWithData() {
		context = getInstance("classpath*:**/user-impl-test-context.xml");
		systemRoleService = (SystemRoleService)context.getBean("systemRoleService");
		
		Boolean throwException = false;
		
		try {
			SystemRoleBO systemRoleBo = new SystemRoleBO();
			systemRoleBo.setRoleId(PrimaryKeyGenerator.generatePrimaryKey("user_role"));
			systemRoleBo.setRoleName("Teacher");
			
			systemRoleService.addSystemRole(systemRoleBo);
		} catch (Exception e) {
			throwException = true;
		}
		
		org.junit.Assert.assertFalse(throwException);
	}
	
	@Test
	public void testFindSystemRoleByRoleNameWithNonCondition() {
		context = getInstance("classpath*:**/user-impl-test-context.xml");
		systemRoleService = (SystemRoleService)context.getBean("systemRoleService");
		
		Boolean throwException = false;	
		
		try {
			systemRoleService.findSystemRoleByRoleName(null);
		} catch (Exception e) {
			throwException = true;
		}
		
		org.junit.Assert.assertFalse(throwException);
	}
	
	@Test
	public void testFindSystemRoleByRoleNameWithCondition() {
		context = getInstance("classpath*:**/user-impl-test-context.xml");
		systemRoleService = (SystemRoleService)context.getBean("systemRoleService");
		
		Boolean throwException = false;	
		
		try {
			SystemRoleBO systemRoleBo = systemRoleService.findSystemRoleByRoleName("dcba");
			if(systemRoleBo != null) {
				System.out.println(systemRoleBo.getRoleId() + "," + systemRoleBo.getRoleName());
				org.junit.Assert.assertTrue(systemRoleBo.getRoleName().equals("dcba"));
			}
		} catch (Exception e) {
			throwException = true;
		}
		
		org.junit.Assert.assertFalse(throwException);
	}
	
	@Test
	public void testFindSystemRoleByRoleIdWithNonCondition() {
		context = getInstance("classpath*:**/user-impl-test-context.xml");
		systemRoleService = (SystemRoleService)context.getBean("systemRoleService");
		
		Boolean throwException = false;	
		
		try {
			systemRoleService.findSystemRoleByRoleId(null);
		} catch (Exception e) {
			throwException = true;
		}
		
		org.junit.Assert.assertFalse(throwException);
	}
		
	@Test
	public void testFindSystemRoleByRoleIdWithCondition() {
		context = getInstance("classpath*:**/user-impl-test-context.xml");
		systemRoleService = (SystemRoleService)context.getBean("systemRoleService");
		
		Boolean throwException = false;	
		
		try {
			SystemRoleBO systemRoleBo = systemRoleService.findSystemRoleByRoleId("4321");
			if(systemRoleBo != null) {
				System.out.println(systemRoleBo.getRoleId() + "," + systemRoleBo.getRoleName());
				org.junit.Assert.assertTrue(systemRoleBo.getRoleId().equals("4321"));
			}
		} catch (Exception e) {
			throwException = true;
		}
		
		org.junit.Assert.assertFalse(throwException);
	}
	
	@Test
	public void testDeleteSystemRolesWithNonCondition() {
		context = getInstance("classpath*:**/user-impl-test-context.xml");
		systemRoleService = (SystemRoleService)context.getBean("systemRoleService");
		
		Boolean throwException = false;	
		
		try {
			List<SystemRoleBO> systemRoleBos = new ArrayList<SystemRoleBO>();
			systemRoleService.deleteSystemRoles(systemRoleBos);
		} catch (Exception e) {
			throwException = true;
		}
		
		org.junit.Assert.assertFalse(throwException);
	}
	
	@Test
	public void testDeleteSystemRolesWithCondition() {
		context = getInstance("classpath*:**/user-impl-test-context.xml");
		systemRoleService = (SystemRoleService)context.getBean("systemRoleService");
		
		Boolean throwException = false;	
		
		try {
			List<SystemRoleBO> systemRoleBos = new ArrayList<SystemRoleBO>();
			
			SystemRoleBO systemRoleBo1 = new SystemRoleBO();
			systemRoleBo1.setRoleId("4321");
			systemRoleBos.add(systemRoleBo1);
			
			//表中不存在roleid为1234的role记录
			SystemRoleBO systemRoleBo2 = new SystemRoleBO();
			systemRoleBo2.setRoleId("1234");
			systemRoleBos.add(systemRoleBo2);
			
			systemRoleService.deleteSystemRoles(systemRoleBos);
		} catch (Exception e) {
			throwException = true;
		}
		
		org.junit.Assert.assertFalse(throwException);
	}
	
	@Test
	public void testUpdateSystemRolesSystemRolesWithNull() {
		context = getInstance("classpath*:**/user-impl-test-context.xml");
		systemRoleService = (SystemRoleService)context.getBean("systemRoleService");
		
		Boolean throwException = false;	
		
		try {
			List<SystemRoleBO> systemRoleBos = new ArrayList<SystemRoleBO>();
			systemRoleService.updateSystemRoles(systemRoleBos);
		} catch (Exception e) {
			throwException = true;
		}
		
		org.junit.Assert.assertFalse(throwException);
	}
	
	@Test
	public void testUpdateSystemRolesSystemRolesWithCondition() {
		context = getInstance("classpath*:**/user-impl-test-context.xml");
		systemRoleService = (SystemRoleService)context.getBean("systemRoleService");
		
		Boolean throwException = false;	
		
		try {
			List<SystemRoleBO> systemRoleBos = new ArrayList<SystemRoleBO>();
			
			SystemRoleBO systemRoleBo1 = new SystemRoleBO();
			systemRoleBo1.setRoleId("4321");
			systemRoleBo1.setRoleName("Developer");
			systemRoleBos.add(systemRoleBo1);
			
			//表中不存在roleid为1234的role记录
			SystemRoleBO systemRoleBo2 = new SystemRoleBO();
			systemRoleBo2.setRoleId("1234");
			systemRoleBo2.setRoleName("SA");
			systemRoleBos.add(systemRoleBo2);
			
			systemRoleService.updateSystemRoles(systemRoleBos);
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
		context = getInstance("classpath*:**/user-impl-test-context.xml");
		systemRoleService = (SystemRoleService)context.getBean("systemRoleService");

		Boolean throwException = false;
		try {
			SystemRoleBO systemRoleBo = new SystemRoleBO();
			List<SystemRoleBO> systemRoleBoList = systemRoleService.findPagedSystemRoles(systemRoleBo);
			for (SystemRoleBO item : systemRoleBoList) {
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
		context = getInstance("classpath*:**/user-impl-test-context.xml");
		systemRoleService = (SystemRoleService)context.getBean("systemRoleService");

		SystemRoleBO systemRoleBo = new SystemRoleBO();
		systemRoleBo.setRoleId("123456");
		systemRoleBo.setRoleName("Tester");

		Boolean throwException = false;

		try {
			List<SystemRoleBO> systemRoleBoList = systemRoleService.findPagedSystemRoles(systemRoleBo);
			for (SystemRoleBO item : systemRoleBoList) {
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
		context = getInstance("classpath*:**/user-impl-test-context.xml");
		systemRoleService = (SystemRoleService)context.getBean("systemRoleService");

		SystemRoleBO systemRoleBo = new SystemRoleBO();
		systemRoleBo.setRoleName("Tester");
		systemRoleBo.setPageIndex(0);
		systemRoleBo.setPageSize(1);

		Boolean throwException = false;

		try {
			List<SystemRoleBO> systemRoleBoList = systemRoleService.findPagedSystemRoles(systemRoleBo);
			for (SystemRoleBO item : systemRoleBoList) {
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
		context = getInstance("classpath*:**/user-impl-test-context.xml");
		systemRoleService = (SystemRoleService)context.getBean("systemRoleService");

		Boolean throwException = false;
		try {		
			SystemRoleBO systemRoleBo = new SystemRoleBO();
			List<SystemRoleBO> systemRoleBoList = systemRoleService.findAllSystemRoles(systemRoleBo);
			for (SystemRoleBO item : systemRoleBoList) {
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
		context = getInstance("classpath*:**/user-impl-test-context.xml");
		systemRoleService = (SystemRoleService)context.getBean("systemRoleService");

		Boolean throwException = false;
		
		SystemRoleBO systemRoleBo = new SystemRoleBO();
		systemRoleBo.setRoleId("123456");
		systemRoleBo.setRoleName("Tester");
		
		try {
			List<SystemRoleBO> systemRoleBoList = systemRoleService.findAllSystemRoles(systemRoleBo);
			for (SystemRoleBO item : systemRoleBoList) {
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
		context = getInstance("classpath*:**/user-impl-test-context.xml");
		systemRoleService = (SystemRoleService)context.getBean("systemRoleService");

		Boolean throwException = false;
		try {
			SystemRoleBO systemRoleBo = new SystemRoleBO();
			List<SystemRoleBO> systemRoleBoList = systemRoleService.searchPagedSystemRoles(systemRoleBo);
			for (SystemRoleBO item : systemRoleBoList) {
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
		context = getInstance("classpath*:**/user-impl-test-context.xml");
		systemRoleService = (SystemRoleService)context.getBean("systemRoleService");

		Boolean throwException = false;
		
		SystemRoleBO systemRoleBo = new SystemRoleBO();
		systemRoleBo.setRoleId("23456");
		systemRoleBo.setRoleName("Tester");

		try {
			List<SystemRoleBO> systemRoleBoList = systemRoleService.searchPagedSystemRoles(systemRoleBo);
			for (SystemRoleBO item : systemRoleBoList) {
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
		context = getInstance("classpath*:**/user-impl-test-context.xml");
		systemRoleService = (SystemRoleService)context.getBean("systemRoleService");

		Boolean throwException = false;
		
		SystemRoleBO systemRoleBo = new SystemRoleBO();
		systemRoleBo.setRoleName("Server Room");
		systemRoleBo.setPageIndex(1);
		systemRoleBo.setPageSize(2);

		try {
			List<SystemRoleBO> systemRoleBoList = systemRoleService.searchPagedSystemRoles(systemRoleBo);
			for (SystemRoleBO item : systemRoleBoList) {
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
		context = getInstance("classpath*:**/user-impl-test-context.xml");
		systemRoleService = (SystemRoleService)context.getBean("systemRoleService");

		Boolean throwException = false;
		try {
			SystemRoleBO systemRoleBo = new SystemRoleBO();
			List<SystemRoleBO> systemRoleBoList = systemRoleService.searchAllSystemRoles(systemRoleBo);
			for (SystemRoleBO item : systemRoleBoList) {
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
		context = getInstance("classpath*:**/user-impl-test-context.xml");
		systemRoleService = (SystemRoleService)context.getBean("systemRoleService");

		Boolean throwException = false;
		
		SystemRoleBO systemRoleBo = new SystemRoleBO();
		systemRoleBo.setRoleName("Server Room");

		try {
			List<SystemRoleBO> systemRoleBoList = systemRoleService.searchAllSystemRoles(systemRoleBo);
			for (SystemRoleBO item : systemRoleBoList) {
				System.out.println(item.getRoleId() + "," + item.getRoleName());
				org.junit.Assert.assertTrue(item.getRoleName().contains("Server Room"));
			}
		} catch (Exception e) {
			throwException = true;
		}
		org.junit.Assert.assertFalse(throwException);
	}

}
