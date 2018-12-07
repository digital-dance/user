package com.digital.dance.entity;

import java.io.Serializable;

/**
 * 用户表 serverroom.system_user
 * 
 * @author liwy
 *
 *         time:2016年8月22日下午5:30:19
 */
public class UserEO extends UserServiceBaseEO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4939601671901089154L;
	private String userId           ;
	private String userName         ;
	private String userDisplayName  ;
	private String userEmail        ;
	private String userCategory     ;
	private String userMobile       ;
	private String password         ;
	/**
	 * 电话号码，应该带有所在的国家的代码
	 */
	private String telephoneNumber;
	private String distinguishedName;//	CN=aijing.lin@hpe.com,OU=CN,OU=Users,OU=Accounts,DC=asiapacific,DC=cpqcorp,DC=net;
	/**
	 * mail：电子信箱地址。
	 */
	private String mail;
	private String locationId;//extensionAttribute2	SX008E020000LV00
	private String employeeName;
	private String employeeId;
	
	private String name;
	private String employeeType	;
	private String givenName	;
	private String hpCrossCompanyManager	;
	private String hpCrossCompanyManagerID	;
	private String st	;
	private String department	;//ES GD CN Proj Custom Apps Development
	private String displayName	;//Lin, Aijing (Elin, ES-Apps-GD-China-CQ)
	private String postalCode	;//401333
	private String streetAddress	;//No. 28 Xi Yuan Yi Lu
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the userDisplayName
	 */
	public String getUserDisplayName() {
		return userDisplayName;
	}
	/**
	 * @param userDisplayName the userDisplayName to set
	 */
	public void setUserDisplayName(String userDisplayName) {
		this.userDisplayName = userDisplayName;
	}
	/**
	 * @return the userEmail
	 */
	public String getUserEmail() {
		return userEmail;
	}
	/**
	 * @param userEmail the userEmail to set
	 */
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	/**
	 * @return the userCategory
	 */
	public String getUserCategory() {
		return userCategory;
	}
	/**
	 * @param userCategory the userCategory to set
	 */
	public void setUserCategory(String userCategory) {
		this.userCategory = userCategory;
	}
	/**
	 * @return the userMobile
	 */
	public String getUserMobile() {
		return userMobile;
	}
	/**
	 * @param userMobile the userMobile to set
	 */
	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the telephoneNumber
	 */
	public String getTelephoneNumber() {
		return telephoneNumber;
	}
	/**
	 * @param telephoneNumber the telephoneNumber to set
	 */
	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}
	/**
	 * @return the distinguishedName
	 */
	public String getDistinguishedName() {
		return distinguishedName;
	}
	/**
	 * @param distinguishedName the distinguishedName to set
	 */
	public void setDistinguishedName(String distinguishedName) {
		this.distinguishedName = distinguishedName;
	}
	/**
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}
	/**
	 * @param mail the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}
	/**
	 * @return the locationId
	 */
	public String getLocationId() {
		return locationId;
	}
	/**
	 * @param locationId the locationId to set
	 */
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	/**
	 * @return the employeeName
	 */
	public String getEmployeeName() {
		return employeeName;
	}
	/**
	 * @param employeeName the employeeName to set
	 */
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	/**
	 * @return the employeeId
	 */
	public String getEmployeeId() {
		return employeeId;
	}
	/**
	 * @param employeeId the employeeId to set
	 */
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
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
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the employeeType
	 */
	public String getEmployeeType() {
		return employeeType;
	}
	/**
	 * @param employeeType the employeeType to set
	 */
	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}
	/**
	 * @return the givenName
	 */
	public String getGivenName() {
		return givenName;
	}
	/**
	 * @param givenName the givenName to set
	 */
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}
	/**
	 * @return the hpCrossCompanyManager
	 */
	public String getHpCrossCompanyManager() {
		return hpCrossCompanyManager;
	}
	/**
	 * @param hpCrossCompanyManager the hpCrossCompanyManager to set
	 */
	public void setHpCrossCompanyManager(String hpCrossCompanyManager) {
		this.hpCrossCompanyManager = hpCrossCompanyManager;
	}
	/**
	 * @return the hpCrossCompanyManagerID
	 */
	public String getHpCrossCompanyManagerID() {
		return hpCrossCompanyManagerID;
	}
	/**
	 * @param hpCrossCompanyManagerID the hpCrossCompanyManagerID to set
	 */
	public void setHpCrossCompanyManagerID(String hpCrossCompanyManagerID) {
		this.hpCrossCompanyManagerID = hpCrossCompanyManagerID;
	}
	/**
	 * @return the st
	 */
	public String getSt() {
		return st;
	}
	/**
	 * @param st the st to set
	 */
	public void setSt(String st) {
		this.st = st;
	}
	/**
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}
	/**
	 * @param department the department to set
	 */
	public void setDepartment(String department) {
		this.department = department;
	}
	/**
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}
	/**
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	/**
	 * @return the postalCode
	 */
	public String getPostalCode() {
		return postalCode;
	}
	/**
	 * @param postalCode the postalCode to set
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	/**
	 * @return the streetAddress
	 */
	public String getStreetAddress() {
		return streetAddress;
	}
	/**
	 * @param streetAddress the streetAddress to set
	 */
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

}
