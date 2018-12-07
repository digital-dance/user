package com.digital.dance.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.digital.dance.dao.UserCategoryDao;
import com.digital.dance.entity.UserCategoryEO;
import com.digital.dance.user.commons.PrimaryKeyGenerator;
import com.digital.dance.user.commons.unittest.UnitTestBase;

public class UserCategoryDaoTest extends UnitTestBase{
	
	private UserCategoryDao userCategoryDao;
	
	@Test
	public void testAddUserCategory_NullParameter() {
		context = getInstance("classpath*:**/user-dao-test.xml");
		userCategoryDao = context.getBean(UserCategoryDao.class);
		// TODO userCategoryDao.addUserCategory test
		Boolean throwException=false;
		UserCategoryEO userCategoryEO=null;
		try{
			userCategoryDao.addUserCategory(userCategoryEO);
		}catch(Exception e){
			throwException=true;
		}
		org.junit.Assert.assertTrue(throwException);
	}
	
	@Test
	public void testAddUserCategory_WithoutNecessaryProperty() {
		context = getInstance("classpath*:**/user-dao-test.xml");
		userCategoryDao = context.getBean(UserCategoryDao.class);
		// TODO userCategoryDao.addUserCategory test
		/*
		 * none property
		 */
		Boolean throwException=false;
		UserCategoryEO userCategoryEO=new UserCategoryEO();
		try{
			userCategoryDao.addUserCategory(userCategoryEO);
		}catch(Exception e){
			throwException=true;
		}
		org.junit.Assert.assertTrue(throwException);
		/*
		 *only categoryName
		 */
		throwException=false;
		userCategoryEO.setCategoryName("zh");
		try{
			userCategoryDao.addUserCategory(userCategoryEO);
		}catch(Exception e){
			throwException=true;
		}
	}
	
	@Test
	public void testAddUserCategory_WithNecessaryProperty() {
		context = getInstance("classpath*:**/user-dao-test.xml");
		userCategoryDao = context.getBean(UserCategoryDao.class);
		// TODO userCategoryDao.addUserCategory test
		/*
		 * please delete the record after the method,because there is no method for deleting
		 */
		
		UserCategoryEO userCategoryEO=new UserCategoryEO();
		userCategoryEO.setCategoryId("zhID");
		userCategoryEO.setCategoryName("zhName");
		deleteUserCategorys(userCategoryEO);
		Integer ret = userCategoryDao.addUserCategory(userCategoryEO);
			
		org.junit.Assert.assertTrue(ret == 1);
		deleteUserCategorys(userCategoryEO);
	}
	
	@Test
	public void testFindUserCategoryByName_NullParameter() {
		context = getInstance("classpath*:**/user-dao-test.xml");
		userCategoryDao = context.getBean(UserCategoryDao.class);
		// TODO userCategoryDao.findUserCategoryByName test
		Boolean throwException=true;
		String categoryName=null;
		try{
			userCategoryDao.findUserCategoryByName(categoryName);
		}catch(Exception e){
			throwException=false;
		}
		org.junit.Assert.assertTrue(throwException);
	}
	
	@Test
	public void testFindUserCategoryByName_NotExist() {
		context = getInstance("classpath*:**/user-dao-test.xml");
		userCategoryDao = context.getBean(UserCategoryDao.class);
		// TODO userCategoryDao.findUserCategoryByName test
		Boolean throwException=true;
		String categoryName="not exist";
		UserCategoryEO userCategoryEO=userCategoryDao.findUserCategoryByName(categoryName);
		if(userCategoryEO!=null)
			throwException=false;
		org.junit.Assert.assertTrue(throwException);
	}
	
	@Test
	public void testFindUserCategoryByName_Exist() {
		context = getInstance("classpath*:**/user-dao-test.xml");
		userCategoryDao = context.getBean(UserCategoryDao.class);
		// TODO userCategoryDao.findUserCategoryByName test
		
		String categoryName="GD";
		UserCategoryEO userCategoryEO=userCategoryDao.findUserCategoryByName(categoryName);
		
		org.junit.Assert.assertTrue(userCategoryEO!=null);
		
		categoryName="None-GD";
		userCategoryEO=userCategoryDao.findUserCategoryByName(categoryName);
		
		org.junit.Assert.assertTrue(userCategoryEO!=null);
	}
	
	@Test
	public void testFindUserCategoryById_NullParameter() {
		context = getInstance("classpath*:**/user-dao-test.xml");
		userCategoryDao = context.getBean(UserCategoryDao.class);
		// TODO userCategoryDao.findUserCategoryById test
		Boolean throwException=true;
		String categoryId=null;
		try{
			userCategoryDao.findUserCategoryById(categoryId);
		}catch(Exception e){
			throwException=false;
		}
		org.junit.Assert.assertTrue(throwException);
	}
	
	@Test
	public void testFindUserCategoryById_NotExist() {
		context = getInstance("classpath*:**/user-dao-test.xml");
		userCategoryDao = context.getBean(UserCategoryDao.class);
		// TODO userCategoryDao.findUserCategoryById test
		Boolean throwException=true;
		String categoryId="not exist";
		UserCategoryEO userCategoryEO=userCategoryDao.findUserCategoryById(categoryId);
		if(userCategoryEO!=null)
			throwException=false;
		org.junit.Assert.assertTrue(throwException);
	}
	
	@Test
	public void testFindUserCategoryById_Exist() {
		context = getInstance("classpath*:**/user-dao-test.xml");
		userCategoryDao = context.getBean(UserCategoryDao.class);
		
		String categoryId = PrimaryKeyGenerator.generatePrimaryKey("user_category");
		UserCategoryEO userCategoryEO = new UserCategoryEO();
		userCategoryEO.setCategoryId(categoryId);
		userCategoryEO.setCategoryName("test");
		Integer ret = userCategoryDao.addUserCategory(userCategoryEO);
		
		org.junit.Assert.assertTrue(ret > 0);
		
		userCategoryEO = userCategoryDao.findUserCategoryById(categoryId);
		
		org.junit.Assert.assertTrue(userCategoryEO!=null);
		
		deleteUserCategorys(userCategoryEO);
	}
	
	private void deleteUserCategorys(UserCategoryEO userCategory){
		List<UserCategoryEO> userCategorys = new ArrayList<UserCategoryEO>();
		userCategorys.add(userCategory);
		userCategoryDao.deleteUserCategorys(userCategorys);
	}
	
	@Test
	public void testSearchPagedUserCategorys_NullParameter(){
		context = getInstance("classpath*:**/user-dao-test.xml");
		userCategoryDao = context.getBean(UserCategoryDao.class);
		// TODO userCategoryDao.searchPagedUserCategorys test
		
		UserCategoryEO userCategoryEO=null;
		List<UserCategoryEO> list;
		list=userCategoryDao.searchPagedUserCategorys(userCategoryEO);
		
		org.junit.Assert.assertTrue(list.size() > 1);
	}
	
	@Test
	public void testSearchPagedUserCategorys_ImproperPage(){
		context = getInstance("classpath*:**/user-dao-test.xml");
		userCategoryDao = context.getBean(UserCategoryDao.class);
		// TODO userCategoryDao.searchPagedUserCategorys test
		Boolean throwException=false;
		UserCategoryEO userCategoryEO=new UserCategoryEO();
		userCategoryEO.setPageSize(10);
		userCategoryEO.setPageIndex(-1);
		List<UserCategoryEO> list;
		try{
			list=userCategoryDao.searchPagedUserCategorys(userCategoryEO);
		}catch(Exception e){
			throwException=true;
		}
		org.junit.Assert.assertTrue(throwException);
	}
	
	@Test
	public void testSearchPagedUserCategorys_ProperPage(){
		context = getInstance("classpath*:**/user-dao-test.xml");
		userCategoryDao = context.getBean(UserCategoryDao.class);
		// TODO userCategoryDao.searchPagedUserCategorys test
		
		UserCategoryEO userCategoryEO=new UserCategoryEO();
		userCategoryEO.setPageSize(10);
		userCategoryEO.setPageIndex(0);
		List<UserCategoryEO> list;
		list=userCategoryDao.searchPagedUserCategorys(userCategoryEO);
		
		org.junit.Assert.assertTrue(list.size() > 1);
	}
	
	@Test
	public void testSearchUserCategorys_NullParameter(){
		context = getInstance("classpath*:**/user-dao-test.xml");
		userCategoryDao = context.getBean(UserCategoryDao.class);
		// TODO userCategoryDao.searchUserCategorys test
		
		UserCategoryEO userCategoryEO=null;
		List<UserCategoryEO> list;
		list=userCategoryDao.searchUserCategorys(userCategoryEO);
		
		org.junit.Assert.assertTrue(list.size() > 0);
	}
	
	@Test
	public void testSearchUserCategorys_ProperProperty(){
		context = getInstance("classpath*:**/user-dao-test.xml");
		userCategoryDao = context.getBean(UserCategoryDao.class);
		// TODO userCategoryDao.searchUserCategorys test

		UserCategoryEO userCategoryEO=new UserCategoryEO();
		userCategoryEO.setCategoryName("GD");
		List<UserCategoryEO> list;
		list=userCategoryDao.searchUserCategorys(userCategoryEO);

		org.junit.Assert.assertTrue(list.size() > 0);;
	}
	
}
