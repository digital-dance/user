package com.digital.dance.service;

import java.util.List;

import com.digital.dance.bo.UserCategoryBO;

public interface UserCategoryService {
	/**
	 * 
	 * @param userCategory
	 * @return
	 */
	public Integer addUserCategory(UserCategoryBO userCategory);
	/**
	 * 
	 * @param categoryName
	 * @return
	 */
	public UserCategoryBO findUserCategoryByName(String categoryName);
	/**
	 * 
	 * @param categoryId
	 * @return
	 */
	public UserCategoryBO findUserCategoryById(String categoryId);
	/**
	 * search paged user categorys
	 * @param userCategory
	 * @return
	 */
	public List<UserCategoryBO> searchPagedUserCategorys(UserCategoryBO userCategory);
	/**
	 * search user categorys
	 * @param userCategory
	 * @return
	 */
	public  List<UserCategoryBO> searchUserCategorys(UserCategoryBO userCategory);
	
	/**
	 * delete userCategorys
	 * @param userCategory list
	 * @return
	 */
	public Integer deleteUserCategorys(List<UserCategoryBO> userCategorys);
}
