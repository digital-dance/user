package com.digital.dance.user.commons;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
//import com.pay1pay.rsa.util.MD5Util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 
 * 
 * @author  李武元
 * @version ECF-FRONT v1.0 
 * @since   ECF-FRONT v1.0
 */  
public class CookieUtil {
	private static Log logger = new Log(CookieUtil.class);
	
	public static String COOKIE_PATH = "/";
	public static final String SERVER_ROOM_USER = "SERVER_ROOM_USER";
	
	/**
	 * get user from cookie or session
	 * @param request
	 * @param isfromSession
	 * @return
	 * @throws Exception
	 */
	public static String getCookieOrSessionUser(HttpServletRequest request, Boolean isfromSession)throws Exception{
		return com.digital.dance.framework.infrastructure.commons.CookieUtil.getCookieOrSessionUser( request,  isfromSession);
//		String userStr = "";
//		if(isfromSession && request.getSession() != null){
//			Object userStrObj = request.getSession()
//                    .getAttribute(CookieUtil.SERVER_ROOM_USER);
//			userStr =  userStrObj != null ? userStrObj.toString() : null;
//			logger.info("当前登陆的用户Session："+userStr);
//		} else {
//			userStr = CookieUtil.getCookieValue(request,CookieUtil.SERVER_ROOM_USER);
//			logger.info("当前登陆的用户COOKIE："+userStr);
//		}
//		
//		if( userStr != null && !userStr.equals("") ){
//			//解密cookie数据，获取用户信息
//		    String user=new String(AESEncryptUtil.aes(AESEncryptUtil.decodeHex(userStr), 
//		    		Md5Util.hashEncode(AppPropsConfig.getPropValue("COOKIE_KYE"), "UTF-8", "MD5", false)
//	                .substring(8, 24)
//	                .getBytes(), 
//	                Cipher.DECRYPT_MODE), "UTF-8");
//			request.getSession().setAttribute(CookieUtil.SERVER_ROOM_USER, user);
//			return user;
//		}
//		return null;
	}
	
	public static String decode(String encodedStr){
		return com.digital.dance.framework.infrastructure.commons.CookieUtil.decode( encodedStr );
		
	}
	
	public static String encode(String dataStr){
		
		return com.digital.dance.framework.infrastructure.commons.CookieUtil.encode( dataStr );
	}
	
	public static boolean isHexString(String input) {
		return com.digital.dance.framework.infrastructure.commons.CookieUtil.isHexString( input );
	}
	
	/**
	 * write user to cookie and session
	 * @param request
	 * @param response
	 * @param s
	 * @throws Exception
	 */
	public static void writeCookieAndSessionUser(HttpServletRequest request,HttpServletResponse response ,String s) throws Exception{
		com.digital.dance.framework.infrastructure.commons.CookieUtil.writeCookieAndSessionUser( request,  response, s);
//		String result=AESEncryptUtil.encodeHex(AESEncryptUtil.aes(s
//                .getBytes(), Md5Util.hashEncode(AppPropsConfig.getPropValue("COOKIE_KYE"), "UTF-8", "MD5", false)
//                .substring(8, 24)
//                .getBytes(),
//                Cipher.ENCRYPT_MODE));
//		CookieUtil.writeCookie(response,CookieUtil.SERVER_ROOM_USER, result);
//		request.getSession().setAttribute(CookieUtil.SERVER_ROOM_USER, result);
	}
	
	public static String getCookieValueByName(HttpServletRequest request,
			String name) {
		return com.digital.dance.framework.infrastructure.commons.CookieUtil.getCookieValueByName( request,  name );
//		Cookie cookie = getCookieByName(request, name);
//		if (cookie != null && cookie.getValue()!=null) {
//			return cookie.getValue();
//		} else {
//			return null;
//		}
	}

	public static Cookie getCookieByName(HttpServletRequest request, String name) {
		return com.digital.dance.framework.infrastructure.commons.CookieUtil.getCookieByName( request, name );
//		Map<String, Cookie> cookieMap = ReadCookieMap(request);
//		if (cookieMap.containsKey(name)) {
//			Cookie cookie = (Cookie) cookieMap.get(name);
//			return cookie;
//		} else {
//			return null;
//		}
	}

//	private static Map<String, Cookie> ReadCookieMap(HttpServletRequest request) {
//		Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
//		Cookie[] cookies = request.getCookies();
//		if (null != cookies) {
//			for (Cookie cookie : cookies) {
//				cookieMap.put(cookie.getName(), cookie);
//			}
//		}
//		return cookieMap;
//	}

	public static void addCookie(HttpServletResponse response, Cookie cookie) {
		com.digital.dance.framework.infrastructure.commons.CookieUtil.addCookie( response, cookie );
//		cookie.setValue(filterCookieValue(cookie.getValue()));
//		response.addCookie(cookie);
	}

	public static String filterCookieValue(String value) {
		return com.digital.dance.framework.infrastructure.commons.CookieUtil.filterCookieValue( value );
//		if (value != null) {
//			value = value.replace("\r", "");
//			value = value.replace("\n", "");
//			value = value.replace("%0D", "");
//			value = value.replace("%0A", "");
//			value = value.replace("%0d", "");
//			value = value.replace("%0a", "");
//		}
//		return value;
	}
	
	public static Cookie getCookie(Cookie[] cookies, String name) {
		return com.digital.dance.framework.infrastructure.commons.CookieUtil.getCookie( cookies, name );
//		if(cookies != null) {
//			for (Cookie c : cookies) {
//				if (name.equals(c.getName())) {
//					return c;
//				}
//			}
//		}
//		return null;
	}
	
	public static String getCookieValue(HttpServletRequest request, String name) {
		return com.digital.dance.framework.infrastructure.commons.CookieUtil.getCookieValue( request, name );
//		Cookie c = getCookie(request.getCookies(), name);
//		
//		if (c == null) {
//			return getCookieValueByHeader(request, name);
//		}else {
//			return c.getValue();
//		}
	}
	
	public static String getCookieValueByHeader(HttpServletRequest request, String name) {
		return com.digital.dance.framework.infrastructure.commons.CookieUtil.getCookieValueByHeader( request, name );
//		String cValue = null;
//		// 通过上面的Cookie取不到,应该是tomcat的bug导致
//		// 直接自己解析http头
//		String cookie = request.getHeader("Cookie");
//		// log.info("cookie:" + cookie);
//		if (cookie != null) {
//			for (String item : cookie.split(";")) {
//				String[] keyValue = item.split("=");
//				if (keyValue.length != 2) {
//					// log.info("item:"+item);
//					continue;
//				}
//				String key = keyValue[0].trim();
//				String value = keyValue[1].trim();
//				if (key.equals(name)) {
//					cValue = value;
//					break;
//				}
//			}
//		}
//		return cValue;
	}

	public static boolean writeCookie(HttpServletResponse response, String name, String value){
		return writeCookie(response,name,value,-1);
	}

	public static boolean writeCookie(HttpServletResponse response, String name, String value,int maxAge){
		return com.digital.dance.framework.infrastructure.commons.CookieUtil.writeCookie( response, name, value, maxAge );
//		Cookie cookie = new Cookie(name, filterCookieValue(value));
//		cookie.setPath(COOKIE_PATH);
//		if(maxAge>=0){
//			cookie.setMaxAge(maxAge);
//		}
//		response.addCookie(cookie);
//		return true;
	}
	
    

 
    public static String getCookieByName(String cookieName, HttpServletRequest request){
    	return com.digital.dance.framework.infrastructure.commons.CookieUtil.getCookieByName(cookieName,request);
    }
    
    
    public static <T> T readJsonCookie(String cookieName, HttpServletRequest request, Class<T> cls){
		String cookieValueAfterDecode = com.digital.dance.framework.infrastructure.commons.CookieUtil.getCookieValue( request, cookieName );;
//    	//根据cookieName取cookieValue                                                                                                      
//    	Cookie cookies[] = request.getCookies(); 
//    
//    	String cookieValueAfterDecode = null;                                                                                                                        
//        return null;

		return GsonUtils.toObject(cookieValueAfterDecode, cls);
    }
                                                                                                                                
                                                                                                                               
    public static void clearCookie(HttpServletResponse response)throws Exception{
    	com.digital.dance.framework.infrastructure.commons.CookieUtil.clearCookie( response );
//	   	Cookie cookie = new Cookie(SERVER_ROOM_USER, null);                                                                  
//	   	cookie.setMaxAge(0);                                                                                                 
//	   	response.addCookie(cookie);                                                                                          
   }

}
