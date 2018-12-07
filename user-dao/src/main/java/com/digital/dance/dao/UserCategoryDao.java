package com.digital.dance.dao;

import java.util.List;

import com.digital.dance.entity.UserCategoryEO;

public interface UserCategoryDao {
	/**
	 * add a user category
	 * @param userCategory
	 * @return
	 */
	public Integer addUserCategory(UserCategoryEO userCategory);
	/**
	 * find user category by name
	 * @param categoryName
	 * @return
	 */
	public UserCategoryEO findUserCategoryByName(String categoryName);
	/**
	 * find user category by id
	 * @param categoryId
	 * @return
	 */
	public UserCategoryEO findUserCategoryById(String categoryId);

	/**
	 * search paged user categorys
	 * @param userCategory
	 * @return
	 */
	public List<UserCategoryEO> searchPagedUserCategorys(UserCategoryEO userCategory);
	/**
	 * search user categorys
	 * @param userCategory
	 * @return
	 */
	public  List<UserCategoryEO> searchUserCategorys(UserCategoryEO userCategory);
	
	/**
	 * delete userCategorys
	 * @param userCategory list
	 * @return
	 */
	public Integer deleteUserCategorys(List<UserCategoryEO> userCategorys);
}
