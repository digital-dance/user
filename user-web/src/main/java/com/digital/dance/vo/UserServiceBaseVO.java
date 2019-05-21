package com.digital.dance.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户接口返回基础数据
 * @author liuxy
 *
 * time:2016年8月25日下午9:37:38
 */
public class UserServiceBaseVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8383791594542209766L;

	protected Integer pageIndex;
	protected Integer pageSize;
	protected String statusFlag;
	protected Date requestDate;
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
	}
	public String getStatusFlag() {
		return statusFlag;
	}
	public void setStatusFlag(String statusFlag) {
		this.statusFlag = statusFlag;
	}
	/**
	 * @return the requestDate
	 */
	public Date getRequestDate() {
		return requestDate;
	}
	/**
	 * @param requestDate the requestDate to set
	 */
	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

}
