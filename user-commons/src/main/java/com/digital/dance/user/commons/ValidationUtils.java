/**
 * 
 */
package com.digital.dance.user.commons;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>Project:			<p>
 * <p>Module:			<p>
 * <p>Description:	数据验证工具�?	<p>
 *
 * @author wangjx
 * @date 2016�?6�?14�? 上午9:59:28
 */
public class ValidationUtils {

	/** 
	* Email正则表达�?
	*/
	public static final String EMAIL = "\\w+(\\.\\w+)*@\\w+(\\.\\w+)+";

	/** 
	 * 电话号码正则表达�?
	 */
	public static final String PHONE = "(^(\\d{2,4}[-_－�?�]?)?\\d{3,8}([-_－�?�]?\\d{3,8})?([-_－�?�]?\\d{1,7})?$)|(^0?1[35]\\d{9}$)";

	/** 
	 * 手机号码正则表达�?
	 */
	public static final String MOBILE = "^(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])\\d{8}$";

	/** 
	 * 正整数正则表达式 
	 */
	public static final String INTEGER_NEGATIVE = "^[1-9]\\d*|0$";

	/** 
	 * 负整数正则表达式 
	 */
	public static final String INTEGER_POSITIVE = "^-[1-9]\\d*|0$";

	/** 
	 * 邮编正则表达�?  [0-9]\d{5}(?!\d) 国内6位邮�? 
	 */
	public static final String CODE = "[0-9]\\d{5}(?!\\d)";

	/** 
	 * 过滤特殊字符串正�? 
	 *
	 */
	public static final String STR_SPECIAL = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#�?%…�??&*（）—�??+|{}【�?��?�；：�?��?��?��?�，、？]";

	/*** 
	 * 日期正则 支持�? 
	 *  YYYY-MM-DD  
	 */
	public static final String DATE_FORMAT1 = "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)";

	/** 
	 * URL正则表达�? 
	  * 匹配 http www ftp 
	 */
	public static final String URL = "^(http|www|ftp|)?(://)?(\\w+(-\\w+)*)(\\.(\\w+(-\\w+)*))*((:\\d+)?)(/(\\w+(-\\w+)*))*(\\.?(\\w)*)(\\?)?" + "(((\\w*%)*(\\w*\\?)*(\\w*:)*(\\w*\\+)*(\\w*\\.)*(\\w*&)*(\\w*-)*(\\w*=)*(\\w*%)*(\\w*\\?)*" + "(\\w*:)*(\\w*\\+)*(\\w*\\.)*" + "(\\w*&)*(\\w*-)*(\\w*=)*)*(\\w*)*)$";

	/**
	 * 机构代码
	 */
	public static final String ORG_CODE = "^[A-Z0-9]{8}-[A-Z0-9]$";

	/**
	 * 匹配数字组成的字符串  ^[0-9]+$ 
	 */
	public static final String STR_NUM = "^[0-9]+$";

	/**
	 * 金额类型
	 */
	public static final String CURRENCY = "^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){1,2})?$";

	// //------------------验证方法
	/** 
	 * 判断字段是否为空 符合返回ture 
	 * @param str 
	 * @return boolean 
	 */
	public static synchronized boolean StrisNull( String str ) {
		return null == str || str.trim().length() <= 0 ? true : false;
	}

	/** 
	 * 判断字段是非�? 符合返回ture 
	 * @param str 
	 * @return boolean 
	 */
	public static boolean StrNotNull( String str ) {
		return !StrisNull( str );
	}

	/** 
	 * 字符串null转空 
	 * @param str 
	 * @return boolean 
	 */
	public static String nulltoStr( String str ) {
		return StrisNull( str ) ? "" : str;
	}

	/** 
	 * 字符串null赋�?�默认�??  
	 * @param str    目标字符�? 
	 * @param defaut 默认�? 
	 * @return String 
	 */
	public static String nulltoStr( String str, String defaut ) {
		return StrisNull( str ) ? defaut : str;
	}

	/** 
	 * 判断字段是否为Email 符合返回ture 
	 * @param str 
	 * @return boolean 
	 */
	public static boolean isEmail( String str ) {
		return Regular( str, EMAIL );
	}

	/** 
	 * 判断是否为电话号�? 符合返回ture 
	 * @param str 
	 * @return boolean 
	 */
	public static boolean isPhone( String str ) {
		return Regular( str, PHONE );
	}

	/** 
	 * 判断是否为手机号�? 符合返回ture 
	 * @param str 
	 * @return boolean 
	 */
	public static boolean isMobile( String str ) {
		return Regular( str, MOBILE );
	}

	/** 
	 * 判断是否为Url 符合返回ture 
	 * @param str 
	 * @return boolean 
	 */
	public static boolean isUrl( String str ) {
		return Regular( str, URL );
	}

	/** 
	 * 判断字段是否为正整数正则表达�? >=0 符合返回ture 
	 * @param str 
	 * @return boolean 
	 */
	public static boolean isINTEGER_NEGATIVE( String str ) {
		return Regular( str, INTEGER_NEGATIVE );
	}

	/** 
	 * 判断字段是否为负整数正则表达�? <=0 符合返回ture 
	 * @param str 
	 * @return boolean 
	 */
	public static boolean isINTEGER_POSITIVE( String str ) {
		return Regular( str, INTEGER_POSITIVE );
	}

	/**
	 * 验证2010-12-10
	 * @param str
	 * @return
	 */
	public static boolean isDate1( String str ) {
		return Regular( str, DATE_FORMAT1 );
	}

	/** 
	 * 判断字段是否超长 
	 * 字串为空返回fasle, 超过长度{leng}返回ture 反之返回false 
	 * @param str 
	 * @param leng 
	 * @return boolean 
	 */
	public static boolean isLengOut( String str, int leng ) {
		return StrisNull( str ) ? false : str.trim().length() > leng;
	}

	/** 
	 * 判断字段是否为邮�? 符合返回ture 
	 * @param str 
	 * @return boolean 
	 */
	public static boolean isCode( String str ) {
		return Regular( str, CODE );
	}

	/** 
	 * 过滤特殊字符�? 返回过滤后的字符�? 
	 * @param str 
	 * @return boolean 
	 */
	public static String filterStr( String str ) {
		Pattern p = Pattern.compile( STR_SPECIAL );
		Matcher m = p.matcher( str );
		return m.replaceAll( "" ).trim();
	}

	/**
	 * 校验机构代码格式
	 * @return
	 */
	public static boolean isOrgCode( String str ) {
		return Regular( str, ORG_CODE );
	}

	/** 
	 * 判断字符串是不是数字组成 
	 * @param str 
	 * @return boolean 
	 */
	public static boolean isStrNUM( String str ) {
		return Regular( str, STR_NUM );
	}

	/** 
	 * 判断是否为金额类�?
	 * @param str 
	 * @return boolean 
	 */
	public static boolean isCurrency( String str ) {
		return Regular( str, CURRENCY );
	}

	/** 
	 * 匹配是否符合正则表达式pattern 匹配返回true 
	 * @param str 匹配的字符串 
	 * @param pattern 匹配模式 
	 * @return boolean 
	 */
	private static boolean Regular( String str, String pattern ) {
		if( null == str || str.trim().length() <= 0 )
			return false;
		Pattern p = Pattern.compile( pattern );
		Matcher m = p.matcher( str );
		return m.matches();
	}
}
