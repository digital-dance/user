package com.digital.dance.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.digital.dance.bo.UserCategoryBO;
import com.digital.dance.service.UserCategoryService;
import com.digital.dance.user.commons.unittest.UnitTestBase;

public class UserCategoryServiceImplTest extends UnitTestBase{
	
	private UserCategoryService UserCategoryService;
	
	@Test
	public void testAddUserCategory_NullParameter() {
		context = getInstance("classpath*:**/user-impl-test-context.xml");
		UserCategoryService = context.getBean(UserCategoryService.class);
		// TODO UserCategoryService.addUserCategory test
		Boolean throwException=false;
		UserCategoryBO userCategoryBO=null;
		if(UserCategoryService.addUserCategory(userCategoryBO)==-1)
			throwException=true;
		org.junit.Assert.assertTrue(throwException);
	}
	
	@Test
	public void testAddUserCategory_WithoutNecessaryProperty() {
		context = getInstance("classpath*:**/user-impl-test-context.xml");
		UserCategoryService = context.getBean(UserCategoryService.class);
		// TODO UserCategoryService.addUserCategory test
		/*
		 * none property
		 */
		Boolean throwException=false;
		UserCategoryBO userCategoryBO=new UserCategoryBO();
		try{
			UserCategoryService.addUserCategory(userCategoryBO);
		}catch(Exception e){
			throwException=true;
		}
		org.junit.Assert.assertTrue(throwException);
		/*
		 *only categoryName
		 */
		throwException=false;
		userCategoryBO.setCategoryName("zh");
		try{
			UserCategoryService.addUserCategory(userCategoryBO);
		}catch(Exception e){
			throwException=true;
		}
	}
	
	@Test
	public void testAddUserCategory_WithNecessaryProperty() {
		context = getInstance("classpath*:**/user-impl-test-context.xml");
		UserCategoryService = context.getBean(UserCategoryService.class);
		// TODO UserCategoryService.addUserCategory test
		/*
		 * please delete the record after the method,because there is no method for deleting
		 */
		
		UserCategoryBO userCategoryBO=new UserCategoryBO();
		userCategoryBO.setCategoryId("zhID");
		userCategoryBO.setCategoryName("zhName");
		deleteUserCategorys( userCategoryBO );
		Integer ret = UserCategoryService.addUserCategory(userCategoryBO);
		
		org.junit.Assert.assertTrue( ret == 1 );
		deleteUserCategorys(userCategoryBO);
	}
	
	@Test
	public void testFindUserCategoryByName_NullParameter() {
		context = getInstance("classpath*:**/user-impl-test-context.xml");
		UserCategoryService = context.getBean(UserCategoryService.class);
		// TODO UserCategoryService.findUserCategoryByName test
		Boolean throwException=true;
		String categoryName=null;
		try{
			UserCategoryService.findUserCategoryByName(categoryName);
		}catch(Exception e){
			throwException=false;
		}
		org.junit.Assert.assertTrue(throwException);
	}
	
	@Test
	public void testFindUserCategoryByName_NotExist() {
		context = getInstance("classpath*:**/user-impl-test-context.xml");
		UserCategoryService = context.getBean(UserCategoryService.class);
		// TODO UserCategoryService.findUserCategoryByName test
		Boolean throwException=true;
		String categoryName="not exist";
		UserCategoryBO userCategoryBO=UserCategoryService.findUserCategoryByName(categoryName);
		if(userCategoryBO!=null)
			throwException=false;
		org.junit.Assert.assertTrue(throwException);
	}
	
	@Test
	public void testFindUserCategoryByName_Exist() {
		context = getInstance("classpath*:**/user-impl-test-context.xml");
		UserCategoryService = context.getBean(UserCategoryService.class);
		// TODO UserCategoryService.findUserCategoryByName test
		Boolean throwException=false;
		String categoryName="GD";
		UserCategoryBO userCategoryBO=UserCategoryService.findUserCategoryByName(categoryName);
		if(userCategoryBO!=null)
			throwException=true;
		org.junit.Assert.assertTrue(throwException);
		
		throwException=false;
		categoryName="None-GD";
		userCategoryBO=UserCategoryService.findUserCategoryByName(categoryName);
		if(userCategoryBO!=null)
			throwException=true;
		org.junit.Assert.assertTrue(throwException);
	}
	
	@Test
	public void testFindUserCategoryById_NullParameter() {
		context = getInstance("classpath*:**/user-impl-test-context.xml");
		UserCategoryService = context.getBean(UserCategoryService.class);
		// TODO UserCategoryService.findUserCategoryById test
		Boolean throwException=true;
		String categoryId=null;
		try{
			UserCategoryService.findUserCategoryById(categoryId);
		}catch(Exception e){
			throwException=false;
		}
		org.junit.Assert.assertTrue(throwException);
	}
	
	@Test
	public void testFindUserCategoryById_NotExist() {
		context = getInstance("classpath*:**/user-impl-test-context.xml");
		UserCategoryService = context.getBean(UserCategoryService.class);
		// TODO UserCategoryService.findUserCategoryById test
		Boolean throwException=true;
		String categoryId="not exist";
		UserCategoryBO userCategoryBO=UserCategoryService.findUserCategoryById(categoryId);
		if(userCategoryBO!=null)
			throwException=false;
		org.junit.Assert.assertTrue(throwException);
	}
	
	@Test
	public void testFindUserCategoryById_Exist() {
		context = getInstance("classpath*:**/user-impl-test-context.xml");
		UserCategoryService = context.getBean(UserCategoryService.class);
		
		String categoryId="10000";
		UserCategoryBO userCategoryBO = UserCategoryService.findUserCategoryById(categoryId);
		
		org.junit.Assert.assertTrue( userCategoryBO != null );
		
		categoryId="10001";
		userCategoryBO = UserCategoryService.findUserCategoryById(categoryId);
		
		org.junit.Assert.assertTrue( userCategoryBO != null );
	}
	
	@Test
	public void testSearchPagedUserCategorys_NullParameter(){
		context = getInstance("classpath*:**/user-impl-test-context.xml");
		UserCategoryService = context.getBean(UserCategoryService.class);
		// TODO UserCategoryService.searchPagedUserCategorys test
		Boolean throwException=false;
		UserCategoryBO userCategoryBO=null;
		List<UserCategoryBO> list;
		try{
		list=UserCategoryService.searchPagedUserCategorys(userCategoryBO);
		}catch(Exception e){
			throwException=true;
		}
		org.junit.Assert.assertTrue(throwException);
	}
	
	@Test
	public void testSearchPagedUserCategorys_ImproperPage(){
		context = getInstance("classpath*:**/user-impl-test-context.xml");
		UserCategoryService = context.getBean(UserCategoryService.class);
		// TODO UserCategoryService.searchPagedUserCategorys test
		Boolean throwException=false;
		UserCategoryBO userCategoryBO=new UserCategoryBO();
		userCategoryBO.setPageSize(10);
		userCategoryBO.setPageIndex(-1);
		List<UserCategoryBO> list;
		try{
			list=UserCategoryService.searchPagedUserCategorys(userCategoryBO);
		}catch(Exception e){
			throwException=true;
		}
		org.junit.Assert.assertTrue(throwException);
	}
	
	@Test
	public void testSearchPagedUserCategorys_ProperPage(){
		context = getInstance("classpath*:**/user-impl-test-context.xml");
		UserCategoryService = context.getBean(UserCategoryService.class);
		// TODO UserCategoryService.searchPagedUserCategorys test
		
		UserCategoryBO userCategoryBO=new UserCategoryBO();
		userCategoryBO.setPageSize(10);
		userCategoryBO.setPageIndex(0);
		List<UserCategoryBO> list;
		list=UserCategoryService.searchPagedUserCategorys(userCategoryBO);
		
		org.junit.Assert.assertTrue( list.size() > 1 );
	}
	
	private void deleteUserCategorys(UserCategoryBO userCategory){
		List<UserCategoryBO> userCategorys = new ArrayList<UserCategoryBO>();
		userCategorys.add(userCategory);
		UserCategoryService.deleteUserCategorys(userCategorys);
	}
	
	@Test
	public void testSearchUserCategorys_NullParameter(){
		context = getInstance("classpath*:**/user-impl-test-context.xml");
		UserCategoryService = context.getBean(UserCategoryService.class);
		// TODO UserCategoryService.searchUserCategorys test
		Boolean throwException=false;
		UserCategoryBO userCategoryBO=null;
		List<UserCategoryBO> list;
		try{
			list=UserCategoryService.searchUserCategorys(userCategoryBO);
		}catch(Exception e){
			throwException=true;
		}
		org.junit.Assert.assertTrue(throwException);
	}
	
	@Test
	public void testSearchUserCategorys_ProperProperty(){
		context = getInstance("classpath*:**/user-impl-test-context.xml");
		UserCategoryService = context.getBean(UserCategoryService.class);
		// TODO UserCategoryService.searchUserCategorys test
		Boolean throwException=false;
		UserCategoryBO userCategoryBO=new UserCategoryBO();
		userCategoryBO.setCategoryName("GD");
		List<UserCategoryBO> list;
		list=UserCategoryService.searchUserCategorys(userCategoryBO);
		if(list.size()==2)
			throwException=true;
		org.junit.Assert.assertTrue(throwException);
	}
	
}
