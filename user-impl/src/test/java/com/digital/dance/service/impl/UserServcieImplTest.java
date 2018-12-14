package com.digital.dance.service.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.digital.dance.bo.UserBO;
import com.digital.dance.dao.UserDao;
import com.digital.dance.entity.UserEO;
import com.digital.dance.service.UserService;
import com.digital.dance.user.commons.unittest.UnitTestBase;

public class UserServcieImplTest extends UnitTestBase {

	private UserService userService;
	
	@Test
	public void testAddUserWithNull() {
		context = getInstance("classpath*:**/user-impl-test-context.xml");
		UserService userService = (UserService)context.getBean("userService");
		//TODO userService.addUser test
		UserBO user = null;
		Boolean throwException = false;
		try{
			Integer ret = userService.addUser(user);
		} catch (Exception e){
			throwException = true;
		}
		
		org.junit.Assert.assertTrue(true);
	}
	
	
	
	@Test
	public void testAddUserWithExist() {
		context = getInstance("classpath*:**/user-impl-test-context.xml");
		UserService userService = (UserService)context.getBean("userService");
		//TODO userService.addUser test
		UserBO user = new UserBO();
		Boolean throwException = false;
		String userId="111";
		String userMobile="135";
		try{
			UserBO userBO=userService.findUserByUserId(userId);
			if(userBO.getUserId().equals(userId)){
				throwException = true;
			}
//			if(userBO.getUserMobile().equals(userMobile)){
//				throwException = true;
//			}
			Integer ret = userService.addUser(user);
		} catch (Exception e){
			throwException = true;
		}
		
		org.junit.Assert.assertTrue(throwException);
	}
	
	@Test
	public void testAddUserWithCondition() {
		context = getInstance("classpath*:**/user-impl-test-context.xml");
		UserService userService = (UserService)context.getBean("userService");
		//TODO userService.addUser test
		UserBO user = new UserBO();
		user.setUserId("111");
		user.setUserName("Tomcat");
		user.setUserName("Tomcat");
		deleteUsers( user, userService );
		Boolean throwException = false;
		try{
			if(user!=null){
				Integer ret = userService.addUser(user);
			}
			
		} catch (Exception e){
			throwException = true;
		}
		
		org.junit.Assert.assertFalse(throwException);
		deleteUsers( user, userService );
	}
	
	private void deleteUsers(UserBO user, UserService userService){
		List<UserBO> users = new ArrayList<UserBO>();
		users.add(user);
		userService.deleteUsers(users);
	}

	@Test
	public void testFindUserByUserNameWithNull() {
		context = getInstance("classpath*:**/user-impl-test-context.xml");
		userService = (UserService)context.getBean("userService");
		
		Boolean throwException = false;
		try {
			UserBO userBO = userService.findUserByUserName(null);
		} catch (Exception e) {
			throwException = true;
		}
		org.junit.Assert.assertFalse(throwException);
	}
	
	@Test
	public void testFindUserByUserNameWithCondition() {
		context = getInstance("classpath*:**/user-impl-test-context.xml");
		userService = (UserService)context.getBean("userService");
		
		Boolean throwException = false;
		try {
			UserBO userBO = userService.findUserByUserName("Operator SR");
			if(userBO != null) { 
				org.junit.Assert.assertTrue(userBO.getUserName().equals("Operator SR"));
			}
		} catch (Exception e) {
			throwException = true;
		}
		org.junit.Assert.assertFalse(throwException);
	}
	
	@Test
	public void testFindUserByUserIdWithNull() {
		context = getInstance("classpath*:**/user-impl-test-context.xml");
		userService = (UserService)context.getBean("userService");
		
		Boolean throwException = false;
		try {
			UserBO userBO = userService.findUserByUserId(null);
		} catch (Exception e) {
			throwException = true;
		}
		org.junit.Assert.assertFalse(throwException);
	}
	
	@Test
	public void testFindUserByUserIdWithCondition() {
		context = getInstance("classpath*:**/user-impl-test-context.xml");
		userService = (UserService)context.getBean("userService");
		
		Boolean throwException = false;
		try {
			UserBO userBO = userService.findUserByUserId("sr_manager");
			if(userBO != null) {
				org.junit.Assert.assertTrue(userBO.getUserId().equals("sr_manager"));
			}
		} catch (Exception e) {
			throwException = true;
		}
		org.junit.Assert.assertFalse(throwException);
	}
	
	/**
	 * 	测试查询条件为空
	 */
	@Test
	public void testFindPagedUsersWithNonConditions() {
		context = getInstance("classpath*:**/user-impl-test-context.xml");
		userService = (UserService)context.getBean("userService");

		Boolean throwException = false;
		List<UserBO> userBOList;
		try {
			userBOList = userService.findPagedUsers(new UserBO());
			for (UserBO item : userBOList) {
				System.out.println(item.getUserId() + "," + item.getUserName() + "," + item.getUserName() + ","
						+ item.getEmail() + "," + item.getPhone());
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
	public void testFindPagedUsersWithConditions() {
		context = getInstance("classpath*:**/user-impl-test-context.xml");
		userService = (UserService)context.getBean("userService");

		UserBO userBO = new UserBO();
		userBO.setUserId("sr_manager");
		userBO.setPhone("00294DE4-03F9-4F1E-A3B4-898E57063DCD");

		Boolean throwException = false;

		try {
			List<UserBO> userBOList2 = userService.findPagedUsers(userBO);
			for (UserBO item : userBOList2) {
				System.out.println(item.getUserId() + "," + item.getUserName() + "," + item.getUserName() + ","
						+ item.getEmail() + "," + item.getPhone());

				org.junit.Assert.assertTrue(item.getUserId().equals("sr_manager")
						&& item.getPhone().equals("00294DE4-03F9-4F1E-A3B4-898E57063DCD"));
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
		userService = (UserService)context.getBean("userService");

		UserBO userBO = new UserBO();
		userBO.setPageIndex(0);
		userBO.setPageSize(4);
		userBO.setEmail("operatorsr@hpe.com");
		userBO.setUserName("Operator SR");

		Boolean throwException = false;

		try {
			List<UserBO> userBOList = userService.findPagedUsers(userBO);
			for (UserBO item : userBOList) {
				System.out.println(item.getUserId() + "," + item.getUserName() + "," + item.getUserName() + ","
						+ item.getEmail() + "," + item.getPhone());

				org.junit.Assert.assertTrue(
						item.getEmail().equals("operatorsr@hpe.com") && item.getUserName().equals("Operator SR"));
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
	public void testFindAllUsersWithNonConditions() {
		context = getInstance("classpath*:**/user-impl-test-context.xml");
		userService = (UserService)context.getBean("userService");

		Boolean throwException = false;
		List<UserBO> userBOList;
		try {
			userBOList = userService.findPagedUsers(new UserBO());
			for (UserBO item : userBOList) {
				System.out.println(item.getUserId() + "," + item.getUserName() + "," + item.getUserName() + ","
						+ item.getEmail() + "," + item.getPhone());
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
	public void testFindAllUsersWithConditions() {
		context = getInstance("classpath*:**/user-impl-test-context.xml");
		userService = (UserService)context.getBean("userService");

		UserBO userBO = new UserBO();
		userBO.setUserId("sr_manager");
		userBO.setPhone("00294DE4-03F9-4F1E-A3B4-898E57063DCD");

		Boolean throwException = false;

		try {
			List<UserBO> userBOList2 = userService.findPagedUsers(userBO);
			for (UserBO item : userBOList2) {
				System.out.println(item.getUserId() + "," + item.getUserName() + "," + item.getUserName() + ","
						+ item.getEmail() + "," + item.getPhone());

				org.junit.Assert.assertTrue(item.getUserId().equals("sr_manager")
						&& item.getPhone().equals("00294DE4-03F9-4F1E-A3B4-898E57063DCD"));
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
	public void testSearchPagedUsersWithNonConditions() {
		context = getInstance("classpath*:**/user-dao-test.xml");
		userService = (UserService)context.getBean("userService");

		Boolean throwException = false;
		List<UserBO> userBOList;
		try {
			userBOList = userService.searchPagedUsers(new UserBO());
			for (UserBO item : userBOList) {
				System.out.println(item.getUserId() + "," + item.getUserName() + "," + item.getUserName() + ","
						+ item.getEmail() + "," + item.getPhone());
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
	public void testSearchPagedUsersWithConditions() {
		context = getInstance("classpath*:**/user-dao-test.xml");
		userService = (UserService)context.getBean("userService");

		UserBO userBO = new UserBO();
		userBO.setUserId("manager");
		userBO.setPhone("00294DE4");

		Boolean throwException = false;

		try {
			List<UserBO> userBOList2 = userService.searchPagedUsers(userBO);
			for (UserBO item : userBOList2) {
				System.out.println(item.getUserId() + "," + item.getUserName() + "," + item.getUserName() + ","
						+ item.getEmail() + "," + item.getPhone());
				org.junit.Assert.assertTrue(
						item.getUserId().contains("manager") && item.getPhone().contains("00294DE4"));
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
	public void testSearchPagedUsersWithConditionsAndPages() {
		context = getInstance("classpath*:**/user-impl-test-context.xml");
		userService = (UserService)context.getBean("userService");

		UserBO userBO = new UserBO();
		userBO.setPageIndex(0);
		userBO.setPageSize(4);
		userBO.setEmail("@hpe.com");
		userBO.setUserName("SR");

		Boolean throwException = false;

		try {
			List<UserBO> userBOList = userService.searchPagedUsers(userBO);
			for (UserBO item : userBOList) {
				System.out.println(item.getUserId() + "," + item.getUserName() + "," + item.getUserName() + ","
						+ item.getEmail() + "," + item.getPhone());
				org.junit.Assert
						.assertTrue(item.getEmail().contains("@hpe.com") && item.getUserName().contains("SR"));
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
	public void testSearchAllUsersWithNonConditions() {
		context = getInstance("classpath*:**/user-impl-test-context.xml");
		userService = (UserService)context.getBean("userService");

		Boolean throwException = false;
		List<UserBO> userBOList;
		try {
			userBOList = userService.searchPagedUsers(new UserBO());
			for (UserBO item : userBOList) {
				System.out.println(item.getUserId() + "," + item.getUserName() + "," + item.getUserName() + ","
						+ item.getEmail() + "," + item.getPhone());
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
	public void testSearchAllUsersWithConditions() {
		context = getInstance("classpath*:**/user-dao-test.xml");
		userService = (UserService)context.getBean("userService");

		UserBO userBO = new UserBO();
		userBO.setUserId("manager");
		userBO.setPhone("00294DE4");

		Boolean throwException = false;

		try {
			List<UserBO> userBOList2 = userService.searchPagedUsers(userBO);
			for (UserBO item : userBOList2) {
				System.out.println(item.getUserId() + "," + item.getUserName() + "," + item.getUserName() + ","
						+ item.getEmail() + "," + item.getPhone());
				org.junit.Assert.assertTrue(
						item.getUserId().contains("manager") && item.getPhone().contains("00294DE4"));
			}
		} catch (Exception e) {
			throwException = true;
		}
		org.junit.Assert.assertFalse(throwException);
	}
	
	@Test
	public void testUpdateUsers_NullParameter() {
		context = getInstance("classpath*:**/user-impl-test-context.xml");
		UserService userService = (UserService)context.getBean("userService");
		// TODO userService.updateUsers test list is null
		List<UserBO> list = null;
		try {
			userService.updateUsers(list);
			org.junit.Assert.assertTrue(false);
		} catch (Exception e) {
			org.junit.Assert.assertTrue(true);
		}
	}

	@Test
	public void testUpdateUsers_EmptyList() {
		context = getInstance("classpath*:**/user-impl-test-context.xml");
		UserService userService = (UserService)context.getBean("userService");
		// TODO userService.updateUsers test list is empty
		List<UserBO> list = new ArrayList<UserBO>();
		try {
			userService.updateUsers(list);
			org.junit.Assert.assertTrue(true);
		} catch (Exception e) {
			org.junit.Assert.assertTrue(false);
		}
	}

	@Test
	public void testUpdateUsers_IncompleteEntity() {
		context = getInstance("classpath*:**/user-impl-test-context.xml");
		UserService userService = (UserService)context.getBean("userService");
		// TODO userService.updateUsers test one entity without necessary property
		List<UserBO> list = new ArrayList<UserBO>();
		UserBO userBO = new UserBO();
		list.add(userBO);
		try {
			userService.updateUsers(list);
			org.junit.Assert.assertTrue(false);
		} catch (Exception e) {
			org.junit.Assert.assertTrue(true);
		}
		userBO.setUserId("test userBO only has userId");
		try {
			userService.updateUsers(list);
			org.junit.Assert.assertTrue(false);
		} catch (Exception e) {
			org.junit.Assert.assertTrue(true);
		}
	}

	@Test
	public void testUpdateUsers_UpdateNotExist() {
		context = getInstance("classpath*:**/user-impl-test-context.xml");
		UserService userService = (UserService)context.getBean("userService");
		// TODO userService.updateUsers test one entity which not exist
		List<UserBO> list = new ArrayList<UserBO>();
		int judge;
		UserBO userBO = new UserBO();
		userBO.setUserId("not exist");
		userBO.setUserName("no1.name");
		list.add(userBO);
		userService.deleteUsers(list);
		judge = userService.updateUsers(list);
		if (judge == 1)
			org.junit.Assert.assertTrue(false);
		else
			org.junit.Assert.assertTrue(true);
	}

	@Test
	public void testUpdateUsers_SingleUpdate() {
		context = getInstance("classpath*:**/user-impl-test-context.xml");
		UserService userService = (UserService)context.getBean("userService");
		// TODO userService.updateUsers test one entity with all property
		List<UserBO> list = new ArrayList<UserBO>();
		int judge;
		UserBO userBO = new UserBO();
		userBO.setUserId("userid1");
		userBO.setUserName("usernameUpdate1");
		userBO.setUserName("displaynameUpdate1");
		userBO.setEmail("email@hpe.comUpdate1");
		userBO.setPhone("usercategoryUpdate1");
//		userBO.setUserMobile("mobileUpdate1");
		list.add(userBO);
		userService.deleteUsers(list);
		try {
			judge=userService.addUser(userBO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			judge = -1;
		}
		if(judge!=1)
			org.junit.Assert.assertTrue(false);
		judge=userService.updateUsers(list);
		if(judge!=1)
			org.junit.Assert.assertTrue(false);
		judge=userService.deleteUsers(list);
		if(judge!=1)
			org.junit.Assert.assertTrue(false);
		org.junit.Assert.assertTrue(true);
	}

	@Test
	public void testUpdateUsers_BatchUpdate() {
		context = getInstance("classpath*:**/user-impl-test-context.xml");
		UserService userService = (UserService)context.getBean("userService");
		// TODO userService.updateUsers test 15 entities with all property
		List<UserBO> list = new ArrayList<UserBO>();
		int maxnumber=15;
		int judge;
		for (int i = 1; i <= maxnumber; i++) {
			UserBO userBO = new UserBO();
			userBO.setUserId("uderid" + i);
			userBO.setUserName("userName" + i);
			userBO.setUserName("display" + i);
			userBO.setEmail("email@hpe.com" + i);
			userBO.setPhone("userCategory" + i);
//			userBO.setUserMobile("mobile" + i);
			try {
				judge=userService.addUser(userBO);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				judge = -1;
			}
			if(judge!=1)
				org.junit.Assert.assertTrue(false);
			userBO.setUserName("usernameUpdate" + i);
			userBO.setUserName("displayUpdate" + i);
			userBO.setEmail("email@hpe.comUpdate" + i);
			userBO.setPhone("usercategoryUpdate" + i);
//			userBO.setUserMobile("mobileUpdate" + i);
			list.add(userBO);
		}
		judge=userService.updateUsers(list);
		System.out.println(judge);
		if(judge!=1)
			org.junit.Assert.assertTrue(false);
		judge=userService.deleteUsers(list);
		if(judge!=1)
			org.junit.Assert.assertTrue(false);
		org.junit.Assert.assertTrue(true);
	}
	/**
	 * DeleteUser
	 * @author lynn
	 *
	 */
	@Test
	public void testDeleteUserWithNull() {
		context = getInstance("classpath*:**/user-impl-test-context.xml");
		UserService userService = (UserService)context.getBean("userService");
		//TODO userDao.deleteUser test
		Boolean throwException = false;
		try{
			userService.deleteUsers(null);
			System.out.println("删除不成功！！");
		}
		catch(Exception e){
			throwException = true;
		}
		org.junit.Assert.assertTrue(throwException);
	}
	/**
	 * DeleteUser
	 * @author lynn
	 *
	 */
	@Test
	public void testDeleteUserWithConditionList0() {
		context = getInstance("classpath*:**/user-impl-test-context.xml");
		UserService userService = (UserService)context.getBean("userService");
		Boolean throwException = false;
		List<UserBO> users=new ArrayList<UserBO>();
		//TODO userDao.deleteUser test
		try{
			userService.deleteUsers(users);
			System.out.println("运行成功！！");
		}
		catch(Exception e){
			throwException = true;
		}
		org.junit.Assert.assertFalse(throwException);
	}
	/**
	 * DeleteUser
	 * @author lynn
	 *
	 */
	@Test
	public void testDeleteUserWithConditionUesr0() {
		context = getInstance("classpath*:**/user-impl-test-context.xml");
		UserService userService = (UserService)context.getBean("userService");
		Boolean throwException = false;
		List<UserBO> users=new ArrayList<UserBO>();
		//TODO userDao.deleteUser test
		try{
			UserBO user=new UserBO();
			users.add(user);
			userService.deleteUsers(users);
			System.out.println("运行成功！！");
		}
		catch(Exception e){
			throwException = true;
		}
		org.junit.Assert.assertFalse(throwException);
	}
	/**
	 * DeleteUser
	 * @author lynn
	 *
	 */
	@Test
	public void testDeleteUserWithCondition1() {
		context = getInstance("classpath*:**/user-impl-test-context.xml");
		UserService userService = (UserService)context.getBean("userService");
		Boolean throwException = false;
		List<UserBO> users=new ArrayList<UserBO>();
		//TODO userDao.deleteUser test
		try{		
				UserBO user=new UserBO();
				user.setUserId("gd_billing_manager");
				users.add(user);			
				userService.deleteUsers(users);
			System.out.println("删除成功！！");
		}
		catch(Exception e){
			throwException = true;
		}
		org.junit.Assert.assertFalse(throwException);
	}
	/**
	 * DeleteUser
	 * @author lynn
	 *
	 */
	@Test
	public void testDeleteUserWithConditions() {
		context = getInstance("classpath*:**/user-impl-test-context.xml");
		UserService userService = (UserService)context.getBean("userService");
		Boolean throwException = false;
		List<UserBO> users=new ArrayList<UserBO>();
		//TODO userDao.deleteUser test
		try{
			for(int i=5;i<20;i++){
				String userId = String.valueOf(i);
				UserBO user=new UserBO();
				user.setUserId(userId);
				users.add(user);
			}
			userService.deleteUsers(users);
			System.out.println("删除成功！！");
		}
		catch(Exception e){
			throwException = true;
		}
		org.junit.Assert.assertFalse(throwException);
	}
}
