package com.digital.dance.service;

import java.util.List;

import com.digital.dance.bo.UserBO;

public interface UserService {

	public Integer validatToken( String token );
	public Integer saveToken(String token);

	public UserBO checkUser( String userEmail, String password );
	/**
	 * 
	 * @param user
	 * @return
	 */
	public Integer addUser(UserBO user) throws Exception;
	/**
	 * 
	 * @param userName
	 * @return
	 */
	public UserBO findUserByUserName(String userName);
	/**
	 * 
	 * @param userId
	 * @return
	 */
	public UserBO findUserByUserId(String userId);
	/**
	 * 
	 * @param user
	 * @return
	 */
	public List<UserBO> findPagedUsers(UserBO user);
	/**
	 * 
	 * @param user
	 * @return
	 */
	public List<UserBO> findAllUsers(UserBO user);
	
	/**
	 * 
	 * @param user
	 * @return
	 */
	public List<UserBO> searchPagedUsers(UserBO user);
	
	/**
	 * 
	 * @param user
	 * @return
	 */
	public List<UserBO> searchAllUsers(UserBO user);
	
	/**
	 * delete users
	 * @param userId
	 * @return
	 */
	public Integer deleteUsers(List<UserBO> users);
	
	
	/**
	 * update user
	 * @param userId
	 * @return
	 */
	public Integer updateUsers(List<UserBO> users);
}
