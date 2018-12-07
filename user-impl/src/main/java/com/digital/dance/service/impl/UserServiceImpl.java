package com.digital.dance.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.digital.dance.user.commons.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digital.dance.bo.UserBO;
import com.digital.dance.dao.UserDao;
import com.digital.dance.entity.UserEO;
import com.digital.dance.service.UserService;

/**
 * 
 * @author liwy
 *
 * time:2016年8月24日下午5:41:56
 */
//@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;

	@Override
	public Integer validatToken(String token) {
		return userDao.validatToken(token);
	}

	@Override
	public Integer saveToken(String token) {
		return userDao.saveToken(token);
	}

	@Override
	public UserBO checkUser(String userEmail, String password) {
		UserEO userEo = userDao.checkUser(userEmail, password);
		if(userEo == null){
			return null;
		}
		UserBO userBo=new UserBO();
		BeanUtils.copyProperties(userEo, userBo);
		return userBo;
	}

	public Integer addUser(UserBO user) throws Exception{
		
		UserEO userEo = new UserEO();
        if( user == null ){
        	return -1;
        }
        if(user.getUserName() == null || "".equals(user.getUserName())){
        	throw new Exception("user name can not be null or empty");
        }
        UserEO userEoOld=userDao.findUserByUserName(user.getUserName());
        if( userEoOld !=null && user.getUserName().equals(userEoOld.getUserName()) ){
        	throw new Exception("user name exist in the system.");
        }
		BeanUtils.copyProperties(user, userEo);
		
		Integer ret = userDao.addUser(userEo);
		return ret;
	}

	@Override
    public UserBO findUserByUserName(String userName) {
        UserEO userEo=userDao.findUserByUserName(userName);
        if(userEo == null){
        	return null;
        }
        UserBO userBo=new UserBO();
        BeanUtils.copyProperties(userEo, userBo);
        return userBo;
    }

	@Override
	public UserBO findUserByUserId(String userId) {

        UserEO userEo=userDao.findUserByUserId(userId);
        if(userEo == null){
        	return null;
        }
        UserBO userBo=new UserBO();
        BeanUtils.copyProperties(userEo, userBo);
        return userBo;
	}

	/**
	 * 
	 */
	@Override
	public List<UserBO> findPagedUsers(UserBO user) {
		UserEO userEo = new UserEO();
		BeanUtils.copyProperties(user, userEo);
		
		List<UserEO> userEos = userDao.findPagedUsers(userEo);
        if(userEos == null){
        	return null;
        }
		List<UserBO> userBos = new ArrayList<UserBO>();
		for(UserEO ite : userEos){
			UserBO userBo = new UserBO();
			BeanUtils.copyProperties(ite, userBo);
			userBos.add(userBo);
		}
		return userBos;
	}

	/**
	 * 
	 */
	@Override
	public List<UserBO> findAllUsers(UserBO user) {
		UserEO userEo = new UserEO();
		BeanUtils.copyProperties(user, userEo);
		
		List<UserEO> userEos = userDao.findAllUsers(userEo);
        if(userEos == null){
        	return null;
        }
		List<UserBO> userBos = new ArrayList<UserBO>();
		for(UserEO ite : userEos){
			UserBO userBo = new UserBO();
			BeanUtils.copyProperties(ite, userBo);
			userBos.add(userBo);
		}
		return userBos;
	}

	@Override
	public List<UserBO> searchPagedUsers(UserBO user) {
		UserEO userEo = new UserEO();
		BeanUtils.copyProperties(user, userEo);
		
		List<UserEO> userEos = userDao.searchPagedUsers(userEo);
		if(userEos == null) {
			return null;
		}
		
		List<UserBO> userBos = new ArrayList<UserBO>();
		for(UserEO item : userEos){
			UserBO userBo = new UserBO();
			BeanUtils.copyProperties(item, userBo);
			userBos.add(userBo);
		}
		return userBos;
	}
	
	@Override
	public List<UserBO> searchAllUsers(UserBO user) {
		UserEO userEo = new UserEO();
		BeanUtils.copyProperties(user, userEo);
		
		List<UserEO> userEos = userDao.searchAllUsers(userEo);
		if(userEos == null) {
			return null;
		}
		
		List<UserBO> userBos = new ArrayList<UserBO>();
		for(UserEO item : userEos){
			UserBO userBo = new UserBO();
			BeanUtils.copyProperties(item, userBo);
			userBos.add(userBo);
		}
		return userBos;
	}
	
	@Override
	public Integer deleteUsers(List<UserBO> users) {
		List<UserEO> userEos = new ArrayList<UserEO>();
		for(UserBO ite : users){
			UserEO userEo = new UserEO();
			BeanUtils.copyProperties(ite, userEo);
			userEos.add(userEo);
		}
		return userDao.deleteUsers(userEos);
	}

	@Override
	public Integer updateUsers(List<UserBO> users) {
		List<UserEO> userEos = new ArrayList<UserEO>();
		for(UserBO ite : users){
			UserEO userEo = new UserEO();
			BeanUtils.copyProperties(ite, userEo);
			userEos.add(userEo);
		}
		return userDao.updateUsers(userEos);
	}
}
