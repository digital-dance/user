package com.digital.dance.entity;

import java.io.Serializable;

public class UserCategoryEO extends UserServiceBaseEO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String categoryId ;  
	private String categoryName ;
	/**
	 * @return the categoryId
	 */
	public String getCategoryId() {
		return categoryId;
	}
	/**
	 * @param categoryId the categoryId to set
	 */
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	/**
	 * @return the categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}
	/**
	 * @param categoryName the categoryName to set
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}	
	 
}
