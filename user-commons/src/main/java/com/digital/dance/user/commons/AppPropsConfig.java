package com.digital.dance.user.commons;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.InputStream;
import java.util.Properties;
//import com.digital.dance.framework.infrastructure.commons.AppPropsConfig;
//import com.digital.dance.user.commons.Log;


/**
 * 读取配置文件工具类
 * @author liwy
 *
 * time:2016年8月23日下午5:37:38
 */
public class AppPropsConfig{
//	/****
//	 *增加根据环境变量来获取配置文件信息,环境变量名ECF_CONFIG
//	 * 这里使用confi+变量+.properties格式
//	 * 优先使用config.properties文件
//	 * ******/
//	public static String APP_PROP_CONFIG = "APP_PROP_CONFIG";
//	public static String fname = "system";
//	public static String suffix = ".properties";
//	
//	private static   Log logger=new Log(AppPropsConfig.class);
//	
//	private static Properties props = new Properties();
//	
//	static{
//		load();
//	}
//	
//	private static synchronized void load() {
//		
//		try {
//			if(props==null || props.isEmpty()){
//				 String  f = System.getenv(APP_PROP_CONFIG);
//				 InputStream is = AppPropsConfig.class.getClassLoader().getResourceAsStream(f!=null?(fname+f+suffix):(fname+suffix));
//				 if(is != null ){
//					 props.load(is);
//				 } else {
//					 logger.error("PropsConfig.load FileNotFoundException");
//				 }
//			}
//			
//		} catch (FileNotFoundException e) {
//			logger.error("PropsConfig.load FileNotFoundException", e);
//			e.printStackTrace();
//		} catch (IOException e) {
//			logger.error("PropsConfig.load IOException", e);
//		}
//			
//	}
	
	public static synchronized void init(){
//		if(props==null || props.isEmpty()){
//			load() ;
//		}
		com.digital.dance.framework.infrastructure.commons.AppPropsConfig.initCfgProperties();
	}

	public  static Boolean containsKey(String key) {
		return com.digital.dance.framework.infrastructure.commons.AppPropsConfig.containsPropertyKey(key);
//		if(StringTools.isEmpty( key )){
//			return false;
//		}
//		if(props == null || props.isEmpty()){
//			load();
//		}
//		if(props == null || props.isEmpty()){
//			return false;
//		}
//		return props.containsKey( key );
	}

	public  static String getPropValue(String key) {
		return com.digital.dance.framework.infrastructure.commons.AppPropsConfig.getPropertyValue(key);
//		if(props == null || props.isEmpty()){
//			load();
//		}
//		if(props == null || props.isEmpty()){
//			return null;
//		}
//		return (String)props.get(key);
	}
	public static int getPropValue(String key,int defaultV) {
		return com.digital.dance.framework.infrastructure.commons.AppPropsConfig.getPropertyValue(key, defaultV);
		//return getPropValue(key)!=null?Integer.parseInt(getPropValue(key)):defaultV;
	}
	public static String getPropValue(String key,String defaultV) {
		return com.digital.dance.framework.infrastructure.commons.AppPropsConfig.getPropertyValue(key, defaultV);
		//return getPropValue(key)!=null?getPropValue(key):defaultV;
	}
	
	public static Properties getPproperties(){
		return com.digital.dance.framework.infrastructure.commons.AppPropsConfig.getProperties();
//		init();
//		return props;
	}

}
