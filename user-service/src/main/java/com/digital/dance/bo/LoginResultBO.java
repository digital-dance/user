package com.digital.dance.bo;

public class LoginResultBO implements java.io.Serializable {

	private static final long serialVersionUID = -1269553351326742293L;
	private String timestamp;
	private String token;
	private String bizurl;
	private String keygroup;
	private String md5;
	private String keyversion;
	private String jsessionid;
	private String ttl;
	
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getBizurl() {
		return bizurl;
	}
	public void setBizurl(String bizurl) {
		this.bizurl = bizurl;
	}
	public String getKeygroup() {
		return keygroup;
	}
	public void setKeygroup(String keygroup) {
		this.keygroup = keygroup;
	}
	public String getMd5() {
		return md5;
	}
	public void setMd5(String md5) {
		this.md5 = md5;
	}
	public String getKeyversion() {
		return keyversion;
	}
	public void setKeyversion(String keyversion) {
		this.keyversion = keyversion;
	}
	public String getJsessionid() {
		return jsessionid;
	}
	public void setJsessionid(String jsessionid) {
		this.jsessionid = jsessionid;
	}
	public String getTtl() {
		return ttl;
	}
	public void setTtl(String ttl) {
		this.ttl = ttl;
	}
}
