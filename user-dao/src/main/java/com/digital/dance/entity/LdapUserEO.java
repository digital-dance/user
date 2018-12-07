package com.digital.dance.entity;

import java.io.Serializable;

/**
 * <p>Project:			<p>
 * <p>Module:			<p>
 * <p>Description:		<p>
 *
 * @author Xinyu Liu
 * @date 2016年5月9日 下午7:08:42
 */
public class LdapUserEO implements Serializable {

	/**  */
	private static final long serialVersionUID = 5572426009427552027L;
	
	private String objectClass;
	/**
	 * uid：userId，通常指某个用户的登录名，与Linux系统中用户的uid不同
	 */
	private String uid;
	/**
	 * CN=Common Name 为用户名或服务器名，最长可以到80个字符，可以为中文；
	 */
	private String cn;
	/**
	 * surname
	 */
	private String sn;
	/**
	 * OU=Organization Unit为组织单元，最多可以有四级，每级最长32个字符，可以为中文；
	 */
	private String ou;
	/**
	 * Domain Component
	 */
	private String dc;
	/**
	 * Organization 为组织名，可以3—64个字符长
	 */
	private String o;
	/**
	 * C=Country为国家名，可选，为2个字符长
	 */
	private String c;
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
	
	protected Integer pageIndex;
	protected Integer pageSize;
	
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
	private String co	;//401333
	private String company	;//No. 28 Xi Yuan Yi Lu

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
	
	public String getObjectClass() {
		return objectClass;
	}
	public void setObjectClass(String objectClass) {
		this.objectClass = objectClass;
	}
	
	/**
	 * @return the uid
	 */
	public String getUid() {
		return uid;
	}
	/**
	 * @param uid the uid to set
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}
	
	public String getCn() {
		return cn;
	}
	public void setCn(String cn) {
		this.cn = cn;
	}
	/**
	 * @return the sn
	 */
	public String getSn() {
		return sn;
	}
	/**
	 * @param sn the sn to set
	 */
	public void setSn(String sn) {
		this.sn = sn;
	}
	/**
	 * @return the ou
	 */
	public String getOu() {
		return ou;
	}
	/**
	 * @param ou the ou to set
	 */
	public void setOu(String ou) {
		this.ou = ou;
	}
	/**
	 * @return the dc
	 */
	public String getDc() {
		return dc;
	}
	/**
	 * @param dc the dc to set
	 */
	public void setDc(String dc) {
		this.dc = dc;
	}
	/**
	 * @return the o
	 */
	public String getO() {
		return o;
	}
	/**
	 * @param o the o to set
	 */
	public void setO(String o) {
		this.o = o;
	}
	/**
	 * @return the c
	 */
	public String getC() {
		return c;
	}
	/**
	 * @param c the c to set
	 */
	public void setC(String c) {
		this.c = c;
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

	public String getMail() {
		return mail;
	}
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

	public static long getSerialversionuid() {
		return serialVersionUID;
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
	/**
	 * @return the co
	 */
	public String getCo() {
		return co;
	}
	/**
	 * @param co the co to set
	 */
	public void setCo(String co) {
		this.co = co;
	}
	/**
	 * @return the company
	 */
	public String getCompany() {
		return company;
	}
	/**
	 * @param company the company to set
	 */
	public void setCompany(String company) {
		this.company = company;
	}
	
	
}
