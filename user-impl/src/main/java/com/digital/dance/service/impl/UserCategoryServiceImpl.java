package com.digital.dance.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digital.dance.bo.UserCategoryBO;
import com.digital.dance.dao.UserCategoryDao;
import com.digital.dance.entity.UserCategoryEO;
import com.digital.dance.service.UserCategoryService;
import com.digital.dance.user.commons.BeanUtils;

//@Service
public class UserCategoryServiceImpl implements UserCategoryService {
	
	@Autowired
	private UserCategoryDao userCategoryDao;
	
	@Override
	public Integer addUserCategory(UserCategoryBO userCategory) {
		// TODO Auto-generated method stub		
		UserCategoryEO userCategoryEO=new UserCategoryEO();
		if(userCategory==null){
			return -1;
		}
		
		BeanUtils.copyProperties(userCategory, userCategoryEO);
		Integer ret=userCategoryDao.addUserCategory(userCategoryEO);
		return ret;
	}

	@Override
	public UserCategoryBO findUserCategoryByName(String categoryName) {
		// TODO Auto-generated method stub
		UserCategoryEO userCategoryEO=userCategoryDao.findUserCategoryByName(categoryName);
		if(userCategoryEO==null)
			return null;
		UserCategoryBO userCategoryBO=new UserCategoryBO();
		BeanUtils.copyProperties(userCategoryEO, userCategoryBO);
		return userCategoryBO;
	}

	@Override
	public UserCategoryBO findUserCategoryById(String categoryId) {
		// TODO Auto-generated method stub
		UserCategoryEO userCategoryEO=userCategoryDao.findUserCategoryById(categoryId);
		if(userCategoryEO==null)
			return null;
		UserCategoryBO userCategoryBO=new UserCategoryBO();
		BeanUtils.copyProperties(userCategoryEO, userCategoryBO);
		return userCategoryBO;
	}

	@Override
	public List<UserCategoryBO> searchPagedUserCategorys(UserCategoryBO userCategory) {
		// TODO Auto-generated method stub
		UserCategoryEO userCategoryEO=new UserCategoryEO();
		BeanUtils.copyProperties(userCategory, userCategoryEO);
		List<UserCategoryEO> userCategoryEOs=userCategoryDao.searchPagedUserCategorys(userCategoryEO);
		if(userCategoryEOs==null)
			return null;
		
		List<UserCategoryBO> userCategoryBOs=new ArrayList<UserCategoryBO>();
		for(UserCategoryEO item:userCategoryEOs){
			UserCategoryBO userCategoryBO=new UserCategoryBO();
			BeanUtils.copyProperties(item, userCategoryBO);
			userCategoryBOs.add(userCategoryBO);
		}
		return userCategoryBOs;
	}

	@Override
	public List<UserCategoryBO> searchUserCategorys(UserCategoryBO userCategory) {
		// TODO Auto-generated method stub
		UserCategoryEO userCategoryEO=new UserCategoryEO();
		BeanUtils.copyProperties(userCategory, userCategoryEO);
		List<UserCategoryEO> userCategoryEOs=userCategoryDao.searchUserCategorys(userCategoryEO);
		if(userCategoryEOs==null)
			return null;
		
		List<UserCategoryBO> userCategoryBOs=new ArrayList<UserCategoryBO>();
		for(UserCategoryEO item:userCategoryEOs){
			UserCategoryBO userCategoryBO=new UserCategoryBO();
			BeanUtils.copyProperties(item, userCategoryBO);
			userCategoryBOs.add(userCategoryBO);
		}
		return userCategoryBOs;
	}
	
	/**
	 * delete userCategorys
	 * @param userCategory list
	 * @return
	 */
	public Integer deleteUserCategorys(List<UserCategoryBO> userCategorys){
		List<UserCategoryEO> userCategoryEos = new ArrayList<UserCategoryEO>();
		for( UserCategoryBO item : userCategorys ) {
			UserCategoryEO userCategoryEO = new UserCategoryEO();
			BeanUtils.copyProperties( item, userCategoryEO );
			userCategoryEos.add( userCategoryEO );
		}
		
		Integer result = userCategoryDao.deleteUserCategorys( userCategoryEos );
		return result;
	}
}
