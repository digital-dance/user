/**
 * 
 */
package com.digital.dance.dao.impl;

import com.digital.dance.dao.UserDao;
import com.digital.dance.entity.UserEO;
import com.digital.dance.user.commons.PrimaryKeyGenerator;
import com.digital.dance.user.commons.unittest.UnitTestBase;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * @author xizhou
 *
 */
public class UserDaoTest extends UnitTestBase {

	private UserDao userDao;

	@Test
	public void testAddUserWithNull() {
		context = getInstance("classpath*:**/user-dao-test.xml");
		userDao = context.getBean(UserDao.class);
		Boolean throwException = false;
		UserEO user=null;
		try {
			Integer ret = userDao.addUser(user);
		} catch (Exception e) {
			throwException = true;
		}
		org.junit.Assert.assertTrue(throwException);
	}
	
	
	@Test
	public void testAddUserWithExist() {
		context = getInstance("classpath*:**/user-dao-test.xml");
		userDao = context.getBean(UserDao.class);
		Boolean throwException = false;
		UserEO user=new UserEO();
		String userId="1";
		String userName="Tom";
		String userDisplayName="Tomcat";
		try {
			UserEO userEo=userDao.findUserByUserId(userId);
			if(userEo.getUserName().equals(userName)){
				throwException = true;
			}
			if(userEo.getUserDisplayName().equals(userDisplayName)){
				throwException = true;
			}
			Integer ret = userDao.addUser(user);
		
		} catch (Exception e) {
			throwException = true;
		}
		org.junit.Assert.assertTrue(throwException);
	}
	
	@Test
	public void testAddUserWithCondition() {
		context = getInstance("classpath*:**/user-dao-test.xml");
		userDao = context.getBean(UserDao.class);
		Boolean throwException = false;
		UserEO user=new UserEO();
		String userId = PrimaryKeyGenerator.generatePrimaryKey("system_user");
		user.setUserId(userId);
		user.setUserName("Tom");
		user.setUserDisplayName("Tomcat");
		user.setUserEmail("@hpe.com");
		user.setUserCategory("1");
		user.setUserMobile("12345");
		try {
			if(user!=null){
			Integer userEo = userDao.addUser(user);
			}
		} catch (Exception e) {
			throwException = true;
		}
		org.junit.Assert.assertFalse(throwException);
	}
	

	@Test
	public void testFindUserByUserNameWithNull() {
		context = getInstance("classpath*:**/user-dao-test.xml");
		userDao = context.getBean(UserDao.class);
		
		Boolean throwException = false;
		try {
			UserEO userEo = userDao.findUserByUserName(null);
		} catch (Exception e) {
			throwException = true;
		}
		org.junit.Assert.assertFalse(throwException);
	}
	
	@Test
	public void testFindUserByUserNameWithCondition() {
		context = getInstance("classpath*:**/user-dao-test.xml");
		userDao = context.getBean(UserDao.class);
		
		Boolean throwException = false;
		try {
			UserEO userEo = userDao.findUserByUserName("Operator SR");
			if(userEo != null) { 
				org.junit.Assert.assertTrue(userEo.getUserName().equals("Operator SR"));
			}
		} catch (Exception e) {
			throwException = true;
		}
		org.junit.Assert.assertFalse(throwException);
	}
	
	@Test
	public void testFindUserByUserIdWithNull() {
		context = getInstance("classpath*:**/user-dao-test.xml");
		userDao = context.getBean(UserDao.class);
		
		Boolean throwException = false;
		try {
			UserEO userEo = userDao.findUserByUserId(null);
		} catch (Exception e) {
			throwException = true;
		}
		org.junit.Assert.assertFalse(throwException);
	}
	
	@Test
	public void testFindUserByUserIdWithCondition() {
		context = getInstance("classpath*:**/user-dao-test.xml");
		userDao = context.getBean(UserDao.class);
		
		Boolean throwException = false;
		try {
			UserEO userEo = userDao.findUserByUserId("sr_manager");
			if(userEo != null) {
				org.junit.Assert.assertTrue(userEo.getUserId().equals("sr_manager"));
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
		context = getInstance("classpath*:**/user-dao-test.xml");
		userDao = context.getBean(UserDao.class);

		Boolean throwException = false;
		List<UserEO> userEoList;
		try {
			userEoList = userDao.findPagedUsers(null);
			for (UserEO item : userEoList) {
				System.out.println(item.getUserId() + "," + item.getUserName() + "," + item.getUserDisplayName() + ","
						+ item.getUserEmail() + "," + item.getUserCategory());
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
		context = getInstance("classpath*:**/user-dao-test.xml");
		userDao = context.getBean(UserDao.class);

		UserEO userEo = new UserEO();
		userEo.setUserId("sr_manager");
		userEo.setUserCategory("00294DE4-03F9-4F1E-A3B4-898E57063DCD");

		Boolean throwException = false;

		try {
			List<UserEO> userEoList2 = userDao.findPagedUsers(userEo);
			for (UserEO item : userEoList2) {
				System.out.println(item.getUserId() + "," + item.getUserName() + "," + item.getUserDisplayName() + ","
						+ item.getUserEmail() + "," + item.getUserCategory());

				org.junit.Assert.assertTrue(item.getUserId().equals("sr_manager")
						&& item.getUserCategory().equals("00294DE4-03F9-4F1E-A3B4-898E57063DCD"));
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
		userDao = context.getBean(UserDao.class);

		UserEO userEo = new UserEO();
		userEo.setPageIndex(0);
		userEo.setPageSize(4);
		userEo.setUserEmail("operatorsr@hpe.com");
		userEo.setUserName("Operator SR");

		Boolean throwException = false;

		try {
			List<UserEO> userEoList = userDao.findPagedUsers(userEo);
			for (UserEO item : userEoList) {
				System.out.println(item.getUserId() + "," + item.getUserName() + "," + item.getUserDisplayName() + ","
						+ item.getUserEmail() + "," + item.getUserCategory());

				org.junit.Assert.assertTrue(
						item.getUserEmail().equals("operatorsr@hpe.com") && item.getUserName().equals("Operator SR"));
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
		context = getInstance("classpath*:**/user-dao-test.xml");
		userDao = context.getBean(UserDao.class);

		Boolean throwException = false;
		List<UserEO> userEoList;
		try {
			userEoList = userDao.findPagedUsers(null);
			for (UserEO item : userEoList) {
				System.out.println(item.getUserId() + "," + item.getUserName() + "," + item.getUserDisplayName() + ","
						+ item.getUserEmail() + "," + item.getUserCategory());
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
		context = getInstance("classpath*:**/user-dao-test.xml");
		userDao = context.getBean(UserDao.class);

		UserEO userEo = new UserEO();
		userEo.setUserId("sr_manager");
		userEo.setUserCategory("00294DE4-03F9-4F1E-A3B4-898E57063DCD");

		Boolean throwException = false;

		try {
			List<UserEO> userEoList2 = userDao.findPagedUsers(userEo);
			for (UserEO item : userEoList2) {
				System.out.println(item.getUserId() + "," + item.getUserName() + "," + item.getUserDisplayName() + ","
						+ item.getUserEmail() + "," + item.getUserCategory());

				org.junit.Assert.assertTrue(item.getUserId().equals("sr_manager")
						&& item.getUserCategory().equals("00294DE4-03F9-4F1E-A3B4-898E57063DCD"));
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
		userDao = context.getBean(UserDao.class);

		Boolean throwException = false;
		List<UserEO> userEoList;
		try {
			userEoList = userDao.searchPagedUsers(null);
			for (UserEO item : userEoList) {
				System.out.println(item.getUserId() + "," + item.getUserName() + "," + item.getUserDisplayName() + ","
						+ item.getUserEmail() + "," + item.getUserCategory());
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
		userDao = context.getBean(UserDao.class);

		UserEO userEo = new UserEO();
		userEo.setUserId("manager");
		userEo.setUserCategory("00294DE4");

		Boolean throwException = false;

		try {
			List<UserEO> userEoList2 = userDao.searchPagedUsers(userEo);
			for (UserEO item : userEoList2) {
				System.out.println(item.getUserId() + "," + item.getUserName() + "," + item.getUserDisplayName() + ","
						+ item.getUserEmail() + "," + item.getUserCategory());
				org.junit.Assert.assertTrue(
						item.getUserId().contains("manager") && item.getUserCategory().contains("00294DE4"));
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
		context = getInstance("classpath*:**/user-dao-test.xml");
		userDao = context.getBean(UserDao.class);

		UserEO userEo = new UserEO();
		userEo.setPageIndex(0);
		userEo.setPageSize(4);
		userEo.setUserEmail("@hpe.com");
		userEo.setUserName("SR");

		Boolean throwException = false;

		try {
			List<UserEO> userEoList = userDao.searchPagedUsers(userEo);
			for (UserEO item : userEoList) {
				System.out.println(item.getUserId() + "," + item.getUserName() + "," + item.getUserDisplayName() + ","
						+ item.getUserEmail() + "," + item.getUserCategory());
				org.junit.Assert
						.assertTrue(item.getUserEmail().contains("@hpe.com") && item.getUserName().contains("SR"));
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
		context = getInstance("classpath*:**/user-dao-test.xml");
		userDao = context.getBean(UserDao.class);

		Boolean throwException = false;
		List<UserEO> userEoList;
		try {
			userEoList = userDao.searchPagedUsers(null);
			for (UserEO item : userEoList) {
				System.out.println(item.getUserId() + "," + item.getUserName() + "," + item.getUserDisplayName() + ","
						+ item.getUserEmail() + "," + item.getUserCategory());
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
		userDao = context.getBean(UserDao.class);

		UserEO userEo = new UserEO();
		userEo.setUserId("manager");
		userEo.setUserCategory("00294DE4");

		Boolean throwException = false;

		try {
			List<UserEO> userEoList2 = userDao.searchPagedUsers(userEo);
			for (UserEO item : userEoList2) {
				System.out.println(item.getUserId() + "," + item.getUserName() + "," + item.getUserDisplayName() + ","
						+ item.getUserEmail() + "," + item.getUserCategory());
				org.junit.Assert.assertTrue(
						item.getUserId().contains("manager") && item.getUserCategory().contains("00294DE4"));
			}
		} catch (Exception e) {
			throwException = true;
		}
		org.junit.Assert.assertFalse(throwException);
	}

	@Test
	public void testUpdateUsers_NullParameter() {
		context = getInstance("classpath*:**/user-dao-test.xml");
		userDao = context.getBean(UserDao.class);
		// TODO userDao.updateUsers test list is null
		List<UserEO> list = null;
		try {
			userDao.updateUsers(list);
			org.junit.Assert.assertTrue(false);
		} catch (Exception e) {
			org.junit.Assert.assertTrue(true);
		}
	}

	@Test
	public void testUpdateUsers_EmptyList() {
		context = getInstance("classpath*:**/user-dao-test.xml");
		userDao = context.getBean(UserDao.class);
		// TODO userDao.updateUsers test list is empty
		List<UserEO> list = new ArrayList<UserEO>();
		try {
			userDao.updateUsers(list);
			org.junit.Assert.assertTrue(true);
		} catch (Exception e) {
			org.junit.Assert.assertTrue(false);
		}
	}

	@Test
	public void testUpdateUsers_IncompleteEntity() {
		context = getInstance("classpath*:**/user-dao-test.xml");
		userDao = context.getBean(UserDao.class);
		// TODO userDao.updateUsers test one entity without necessary property
		List<UserEO> list = new ArrayList<UserEO>();
		UserEO userEO = new UserEO();
		list.add(userEO);
		try {
			userDao.updateUsers(list);
			org.junit.Assert.assertTrue(false);
		} catch (Exception e) {
			org.junit.Assert.assertTrue(true);
		}
		userEO.setUserId("test userEO only has userId");
		try {
			userDao.updateUsers(list);
			org.junit.Assert.assertTrue(false);
		} catch (Exception e) {
			org.junit.Assert.assertTrue(true);
		}
	}

	@Test
	public void testUpdateUsers_UpdateNotExist() {
		context = getInstance("classpath*:**/user-dao-test.xml");
		userDao = context.getBean(UserDao.class);
		// TODO userDao.updateUsers test one entity which not exist
		List<UserEO> list = new ArrayList<UserEO>();
		int judge;
		UserEO userEO = new UserEO();
		userEO.setUserId("not exist");
		userEO.setUserName("no1.name");
		list.add(userEO);
		userDao.deleteUsers(list);
		judge = userDao.updateUsers(list);
		if (judge == 1)
			org.junit.Assert.assertTrue(false);
		else
			org.junit.Assert.assertTrue(true);
	}

	@Test
	public void testUpdateUsers_SingleUpdate() {
		context = getInstance("classpath*:**/user-dao-test.xml");
		userDao = context.getBean(UserDao.class);
		// TODO userDao.updateUsers test one entity with all property
		List<UserEO> list = new ArrayList<UserEO>();
		int judge;
		UserEO userEO = new UserEO();
		userEO.setUserId("userid1");
		userEO.setUserName("usernameUpdate1");
		userEO.setUserDisplayName("displaynameUpdate1");
		userEO.setUserEmail("email@hpe.comUpdate1");
		userEO.setUserCategory("usercategoryUpdate1");
		userEO.setUserMobile("mobileUpdate1");
		list.add(userEO);
		userDao.deleteUsers(list);
		judge=userDao.addUser(userEO);
		if(judge!=1)
			org.junit.Assert.assertTrue(false);
		judge=userDao.updateUsers(list);
		if(judge!=1)
			org.junit.Assert.assertTrue(false);
		judge=userDao.deleteUsers(list);
		if(judge!=1)
			org.junit.Assert.assertTrue(false);
		org.junit.Assert.assertTrue(true);
	}

	@Test
	public void testUpdateUsers_BatchUpdate() {
		context = getInstance("classpath*:**/user-dao-test.xml");
		userDao = context.getBean(UserDao.class);
		// TODO userDao.updateUsers test 15 entities with all property
		List<UserEO> list = new ArrayList<UserEO>();
		int maxnumber=15;
		int judge;
		for (int i = 1; i <= maxnumber; i++) {
			UserEO userEO = new UserEO();
			userEO.setUserId("uderid" + i);
			userEO.setUserName("userName" + i);
			userEO.setUserDisplayName("display" + i);
			userEO.setUserEmail("email@hpe.com" + i);
			userEO.setUserCategory("userCategory" + i);
			userEO.setUserMobile("mobile" + i);
			judge=userDao.addUser(userEO);
			if(judge!=1)
				org.junit.Assert.assertTrue(false);
			userEO.setUserName("usernameUpdate" + i);
			userEO.setUserDisplayName("displayUpdate" + i);
			userEO.setUserEmail("email@hpe.comUpdate" + i);
			userEO.setUserCategory("usercategoryUpdate" + i);
			userEO.setUserMobile("mobileUpdate" + i);
			list.add(userEO);
		}
		judge=userDao.updateUsers(list);
		System.out.println(judge);
		if(judge!=1)
			org.junit.Assert.assertTrue(false);
		judge=userDao.deleteUsers(list);
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
		context = getInstance("classpath*:**/user-dao-test.xml");
		userDao = context.getBean(UserDao.class);
		//TODO userDao.deleteUser test
		Boolean throwException = false;
		try{
			userDao.deleteUsers(null);
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
		context = getInstance("classpath*:**/user-dao-test.xml");
		userDao = context.getBean(UserDao.class);
		Boolean throwException = false;
		List<UserEO> users=new ArrayList<UserEO>();
		//TODO userDao.deleteUser test
		try{
			userDao.deleteUsers(users);
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
	public void testDeleteUserWithConditionUesr0() {
		context = getInstance("classpath*:**/user-dao-test.xml");
		userDao = context.getBean(UserDao.class);
		Boolean throwException = false;
		List<UserEO> users=new ArrayList<UserEO>();
		//TODO userDao.deleteUser test
		try{
			UserEO user=new UserEO();
			users.add(user);
			userDao.deleteUsers(users);
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
	public void testDeleteUserWithCondition1() {
		context = getInstance("classpath*:**/user-dao-test.xml");
		userDao = context.getBean(UserDao.class);
		Boolean throwException = false;
		List<UserEO> users=new ArrayList<UserEO>();
		//TODO userDao.deleteUser test
		try{		
				UserEO user=new UserEO();
				user.setUserId("gd_account");
				users.add(user);			
			userDao.deleteUsers(users);
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
		context = getInstance("classpath*:**/user-dao-test.xml");
		userDao = context.getBean(UserDao.class);
		Boolean throwException = false;
		List<UserEO> users=new ArrayList<UserEO>();
		//TODO userDao.deleteUser test
		try{
			for(int i=5;i<20;i++){
				String userId = String.valueOf(i);
				UserEO user=new UserEO();
				user.setUserId(userId);
				users.add(user);
			}
			userDao.deleteUsers(users);
			System.out.println("删除成功！！");
		}
		catch(Exception e){
			throwException = true;
		}
		org.junit.Assert.assertFalse(throwException);
	}
}
