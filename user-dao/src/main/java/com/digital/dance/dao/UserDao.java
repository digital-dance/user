package com.digital.dance.dao;

import java.util.List;

import com.digital.dance.entity.UserEO;
import org.apache.ibatis.annotations.Param;

public interface UserDao {

	public Integer validatToken(@Param("token") String token);
	public Integer saveToken(@Param("token") String token);

	public UserEO checkUser(@Param("userEmail") String userEmail, @Param("password") String password );
	/**
	 * add user
	 * @param user
	 * @return
	 */
	public Integer addUser(UserEO user);
	
	/**
	 * find user by user name
	 * @param userName
	 * @return
	 */
	public UserEO findUserByUserName(String userName);
	
	/**
	 * find user by user id
	 * @param userId
	 * @return
	 */
	public UserEO findUserByUserId(String userId);
	
	/**
	 * delete users
	 * @param users
	 * @return
	 */
	public Integer deleteUsers(List<UserEO> users);
	
	
	/**
	 * update user
	 * @param users
	 * @return
	 */
	public Integer updateUsers(List<UserEO> users);
	
	/**
	 * find paged users
	 * @param user
	 * @return
	 */
	public List<UserEO> findPagedUsers(UserEO user);
	
	/**
	 * find all users
	 * @param user
	 * @return
	 */
	public List<UserEO> findAllUsers(UserEO user);	

	/**
	 * find paged users with fuzzy query
	 * @param user
	 * @return
	 */
	public List<UserEO> searchPagedUsers(UserEO user);
	
	/**
	 * find all users with fuzzy query
	 * @param user
	 * @return
	 */
	public List<UserEO> searchAllUsers(UserEO user);
}
