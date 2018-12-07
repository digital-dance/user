package com.digital.dance.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.digital.dance.bo.SystemUserRoleBO;
import com.digital.dance.service.SystemUserRoleService;
import com.digital.dance.user.commons.PrimaryKeyGenerator;
import com.digital.dance.user.commons.unittest.UnitTestBase;

public class SystemUserRoleServiceImplTest extends UnitTestBase {

	private SystemUserRoleService systemUserRoleService;
	
	@Test
	//添加
	public void testAddSystemUserRoleWithNull() {
		context = getInstance("classpath*:**/user-impl-test-context.xml");
		systemUserRoleService = (SystemUserRoleService)context.getBean("systemUserRoleService");
		
		Boolean throwException = false;
		
		try {
			SystemUserRoleBO systemUserRoleBo = new SystemUserRoleBO();
			systemUserRoleService.addSystemUserRole(systemUserRoleBo);
		} catch (Exception e) {
			throwException = true;
		}
		
		org.junit.Assert.assertTrue(throwException);
	}
	
	@Test
	//添加
	public void testAddSystemUserRoleWithData() {
		context = getInstance("classpath*:**/user-impl-test-context.xml");
		systemUserRoleService = (SystemUserRoleService)context.getBean("systemUserRoleService");
		
		Boolean throwException = false;
		
		try {
			SystemUserRoleBO systemUserRoleBo = new SystemUserRoleBO();
			systemUserRoleBo.setRoleId(PrimaryKeyGenerator.generatePrimaryKey("user_role"));
			systemUserRoleBo.setUserId("sr_operator");
			
			systemUserRoleService.addSystemUserRole(systemUserRoleBo);
		} catch (Exception e) {
			throwException = true;
		}
		
		org.junit.Assert.assertFalse(throwException);
	}
	
	@Test
	//批量添加
	public void testAddSystemUserRolesWithNull() {
		context = getInstance("classpath*:**/user-impl-test-context.xml");
		systemUserRoleService = (SystemUserRoleService)context.getBean("systemUserRoleService");
		
		Boolean throwException = false;
		
		try {
			List<SystemUserRoleBO> systemUserRoleBoList = new ArrayList<SystemUserRoleBO>();
			systemUserRoleService.addSystemUserRoles(systemUserRoleBoList);
		} catch (Exception e) {
			throwException = true;
		}
		
		org.junit.Assert.assertFalse(throwException);
	}
	
	@Test
	//批量添加
	public void testAddSystemUserRolesWithData() {
		context = getInstance("classpath*:**/user-impl-test-context.xml");
		systemUserRoleService = (SystemUserRoleService)context.getBean("systemUserRoleService");
		
		Boolean throwException = false;
		
		List<SystemUserRoleBO> systemUserRoleBoList = new ArrayList<SystemUserRoleBO>();
		for(Integer i=1;i<5;i++) {
			SystemUserRoleBO systemUserRoleBo = new SystemUserRoleBO();
			systemUserRoleBo.setRoleId(i.toString());
			systemUserRoleBo.setUserId("a" + (i-1));
			systemUserRoleBoList.add(systemUserRoleBo);
		}
		
		try {
			systemUserRoleService.addSystemUserRoles(systemUserRoleBoList);
		} catch (Exception e) {
			throwException = true;
		}
		
		org.junit.Assert.assertFalse(throwException);
	}
	
	@Test
	//根据userId查找
	public void testFindSystemUserRoleByUserIdWithNonCondition() {
		context = getInstance("classpath*:**/user-impl-test-context.xml");
		systemUserRoleService = (SystemUserRoleService)context.getBean("systemUserRoleService");
		
		Boolean throwException = false;	
		
		try {
			systemUserRoleService.findSystemUserRoleByUserId(null);
		} catch (Exception e) {
			throwException = true;
		}
		
		org.junit.Assert.assertFalse(throwException);
	}
	
	@Test
	//根据userId查找
	public void testFindSystemUserRoleByUserIdWithCondition() {
		context = getInstance("classpath*:**/user-impl-test-context.xml");
		systemUserRoleService = (SystemUserRoleService)context.getBean("systemUserRoleService");
		
		Boolean throwException = false;	
		
		try {
			SystemUserRoleBO systemUserRoleBo = systemUserRoleService.findSystemUserRoleByUserId("a0");
			if(systemUserRoleBo != null) {
				System.out.println(systemUserRoleBo.getRoleId() + "," + systemUserRoleBo.getUserId());
				org.junit.Assert.assertTrue(systemUserRoleBo.getUserId().equals("a0"));
			}
		} catch (Exception e) {
			throwException = true;
		}
		
		org.junit.Assert.assertFalse(throwException);
	}
	
	@Test
	//根据roleId查询
	public void testFindSystemUserRoleByRoleIdWithNonCondition() {
		context = getInstance("classpath*:**/user-impl-test-context.xml");
		systemUserRoleService = (SystemUserRoleService)context.getBean("systemUserRoleService");
		
		Boolean throwException = false;	
		
		try {
			systemUserRoleService.findSystemUserRoleByRoleId(null);
		} catch (Exception e) {
			throwException = true;
		}
		
		org.junit.Assert.assertFalse(throwException);
	}
		
	@Test
	//根据roleId查询
	public void testFindSystemUserRoleByRoleIdWithCondition() {
		context = getInstance("classpath*:**/user-impl-test-context.xml");
		systemUserRoleService = (SystemUserRoleService)context.getBean("systemUserRoleService");
		
		Boolean throwException = false;	
		
		try {
			SystemUserRoleBO systemUserRoleBo = systemUserRoleService.findSystemUserRoleByRoleId("123456");
			if(systemUserRoleBo != null) {
				System.out.println(systemUserRoleBo.getRoleId() + "," + systemUserRoleBo.getUserId());
				org.junit.Assert.assertTrue(systemUserRoleBo.getRoleId().equals("123456"));
			}
		} catch (Exception e) {
			throwException = true;
		}
		
		org.junit.Assert.assertFalse(throwException);
	}
	
	@Test
	//删除
	public void testDeleteSystemUserRolesWithNonCondition() {
		context = getInstance("classpath*:**/user-impl-test-context.xml");
		systemUserRoleService = (SystemUserRoleService)context.getBean("systemUserRoleService");
		
		Boolean throwException = false;	
		
		try {
			List<SystemUserRoleBO> systemUserRoleBos = new ArrayList<SystemUserRoleBO>();
			systemUserRoleService.deleteSystemUserRoles(systemUserRoleBos);
		} catch (Exception e) {
			throwException = true;
		}
		
		org.junit.Assert.assertFalse(throwException);
	}
	
	@Test
	//删除
	public void testDeleteSystemUserRolesWithCondition() {
		context = getInstance("classpath*:**/user-impl-test-context.xml");
		systemUserRoleService = (SystemUserRoleService)context.getBean("systemUserRoleService");
		
		Boolean throwException = false;	
		
		try {
			List<SystemUserRoleBO> systemUserRoleBoList = new ArrayList<SystemUserRoleBO>();
			
			for(Integer i=1;i<5;i++) {
				SystemUserRoleBO systemUserRoleBo = new SystemUserRoleBO();
				systemUserRoleBo.setRoleId(i.toString());
				systemUserRoleBo.setUserId("a" + (i-1));
				systemUserRoleBoList.add(systemUserRoleBo);
			}
			
			systemUserRoleService.deleteSystemUserRoles(systemUserRoleBoList);
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
		context = getInstance("classpath*:**/user-impl-test-context.xml");
		systemUserRoleService = (SystemUserRoleService)context.getBean("systemUserRoleService");

		Boolean throwException = false;
		try {
			SystemUserRoleBO systemUserRoleBo = new SystemUserRoleBO();
			List<SystemUserRoleBO> systemUserRoleBoList = systemUserRoleService.findPagedSystemUserRoles(systemUserRoleBo);
			for (SystemUserRoleBO item : systemUserRoleBoList) {
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
	public void testFindPagedSystemUserRolesWithConditions() {
		context = getInstance("classpath*:**/user-impl-test-context.xml");
		systemUserRoleService = (SystemUserRoleService)context.getBean("systemUserRoleService");

		SystemUserRoleBO systemUserRoleBo = new SystemUserRoleBO();
		systemUserRoleBo.setUserId("sr_operator");

		Boolean throwException = false;

		try {
			List<SystemUserRoleBO> systemUserRoleBoList = systemUserRoleService.findPagedSystemUserRoles(systemUserRoleBo);
			for (SystemUserRoleBO item : systemUserRoleBoList) {
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
		context = getInstance("classpath*:**/user-impl-test-context.xml");
		systemUserRoleService = (SystemUserRoleService)context.getBean("systemUserRoleService");

		SystemUserRoleBO systemUserRoleBo = new SystemUserRoleBO();
		systemUserRoleBo.setUserId("sr_operator");
		systemUserRoleBo.setPageIndex(0);
		systemUserRoleBo.setPageSize(1);

		Boolean throwException = false;

		try {
			List<SystemUserRoleBO> systemUserRoleBoList = systemUserRoleService.findPagedSystemUserRoles(systemUserRoleBo);
			for (SystemUserRoleBO item : systemUserRoleBoList) {
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
		context = getInstance("classpath*:**/user-impl-test-context.xml");
		systemUserRoleService = (SystemUserRoleService)context.getBean("systemUserRoleService");

		Boolean throwException = false;
		try {
			SystemUserRoleBO systemUserRoleBo = new SystemUserRoleBO();
			List<SystemUserRoleBO> systemUserRoleBoList = systemUserRoleService.findAllSystemUserRoles(systemUserRoleBo);
			for (SystemUserRoleBO item : systemUserRoleBoList) {
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
		context = getInstance("classpath*:**/user-impl-test-context.xml");
		systemUserRoleService = (SystemUserRoleService)context.getBean("systemUserRoleService");

		Boolean throwException = false;
		
		SystemUserRoleBO systemUserRoleBo = new SystemUserRoleBO();
		systemUserRoleBo.setRoleId("123456");
		systemUserRoleBo.setUserId("sr_operator");
		
		try {
			List<SystemUserRoleBO> systemUserRoleBoList = systemUserRoleService.findAllSystemUserRoles(systemUserRoleBo);
			for (SystemUserRoleBO item : systemUserRoleBoList) {
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
		context = getInstance("classpath*:**/user-impl-test-context.xml");
		systemUserRoleService = (SystemUserRoleService)context.getBean("systemUserRoleService");

		Boolean throwException = false;
		SystemUserRoleBO systemUserRoleBo = new SystemUserRoleBO();
		try {
			List<SystemUserRoleBO> systemUserRoleBoList = systemUserRoleService.searchPagedSystemUserRoles(systemUserRoleBo);
			for (SystemUserRoleBO item : systemUserRoleBoList) {
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
		context = getInstance("classpath*:**/user-impl-test-context.xml");
		systemUserRoleService = (SystemUserRoleService)context.getBean("systemUserRoleService");

		Boolean throwException = false;
		
		SystemUserRoleBO systemUserRoleBo = new SystemUserRoleBO();
		systemUserRoleBo.setRoleId("23456");
		systemUserRoleBo.setUserId("operator");

		try {
			List<SystemUserRoleBO> systemUserRoleBoList = systemUserRoleService.searchPagedSystemUserRoles(systemUserRoleBo);
			for (SystemUserRoleBO item : systemUserRoleBoList) {
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
		context = getInstance("classpath*:**/user-impl-test-context.xml");
		systemUserRoleService = (SystemUserRoleService)context.getBean("systemUserRoleService");

		Boolean throwException = false;
		
		SystemUserRoleBO systemUserRoleBo = new SystemUserRoleBO();
		systemUserRoleBo.setUserId("operator");
		systemUserRoleBo.setPageIndex(0);
		systemUserRoleBo.setPageSize(1);

		try {
			List<SystemUserRoleBO> systemUserRoleBoList = systemUserRoleService.searchPagedSystemUserRoles(systemUserRoleBo);
			for (SystemUserRoleBO item : systemUserRoleBoList) {
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
		context = getInstance("classpath*:**/user-impl-test-context.xml");
		systemUserRoleService = (SystemUserRoleService)context.getBean("systemUserRoleService");

		Boolean throwException = false;
		
		SystemUserRoleBO systemUserRoleBo = new SystemUserRoleBO();
		try {
			List<SystemUserRoleBO> systemUserRoleBoList = systemUserRoleService.searchAllSystemUserRoles(systemUserRoleBo);
			for (SystemUserRoleBO item : systemUserRoleBoList) {
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
		context = getInstance("classpath*:**/user-impl-test-context.xml");
		systemUserRoleService = (SystemUserRoleService)context.getBean("systemUserRoleService");

		Boolean throwException = false;
		
		SystemUserRoleBO systemUserRoleBo = new SystemUserRoleBO();
		systemUserRoleBo.setUserId("operator");

		try {
			List<SystemUserRoleBO> systemUserRoleBoList = systemUserRoleService.searchAllSystemUserRoles(systemUserRoleBo);
			for (SystemUserRoleBO item : systemUserRoleBoList) {
				System.out.println(item.getRoleId() + "," + item.getUserId());
				org.junit.Assert.assertTrue(item.getUserId().contains("operator"));
			}
		} catch (Exception e) {
			throwException = true;
		}
		org.junit.Assert.assertFalse(throwException);
	}

}
