package com.digital.dance.entity;

import java.io.Serializable;

public class UserServiceBaseEO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9019152665688082008L;
	protected Integer pageIndex;
	protected Integer pageSize;
	protected Integer offsetNum;
	/**
	 * @return the pageIndex
	 */
	public Integer getPageIndex() {
		return pageIndex;
	}
	/**
	 * @param pageIndex the pageIndex to set
	 */
	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
		setOffsetNum(0);
	}
	/**
	 * @return the pageSize
	 */
	public Integer getPageSize() {
		return pageSize;
	}
	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
		setOffsetNum(0);
	}
	/**
	 * @return the offsetNum
	 */
	public Integer getOffsetNum() {
		this.offsetNum = (pageIndex == null ? 0 : pageIndex) * (pageSize == null ? 0 : pageSize);
		return this.offsetNum;
	}
	/**
	 * @param offsetNum the offsetNum to set
	 */
	public void setOffsetNum(Integer offsetNum) {
		this.offsetNum = (pageIndex == null ? 0 : pageIndex) * (pageSize == null ? 0 : pageSize);
	}
}
