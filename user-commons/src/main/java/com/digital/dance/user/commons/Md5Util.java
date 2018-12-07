package com.digital.dance.user.commons;

import java.security.MessageDigest;

/**
 * MD5算法 
 * @author liwy
 *
 * time:2016年8月23日下午2:23:07
 */
public class Md5Util {
		private final static Log log = new Log(Md5Util.class);
		public final static String getMD5(String s) {
			char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
					'a', 'b', 'c', 'd', 'e', 'f' };
			try {
				byte[] strTemp = s.getBytes();
				MessageDigest mdTemp = MessageDigest.getInstance("MD5");
				
				mdTemp.update(strTemp);
				
				byte[] md = mdTemp.digest();
				int j = md.length;
				char str[] = new char[j * 2];
				int k = 0;
				for (int i = 0; i < j; i++) {
					byte byte0 = md[i];
					str[k++] = hexDigits[byte0 >>> 4 & 0xf];
					str[k++] = hexDigits[byte0 & 0xf];
				}
				return new String(str);
			} catch (Exception e) {
				log.error("getMD5", e);
				return null;
			}
		}
		
		public static void main(String[] args) {
		    
		}

		/** 16进制的字符数组 */
	    private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
	            "e", "f" };

	    /**
	     * MD5摘要
	     * 
	     * @param source
	     *            需要加密的原字符串
	     * @param encoding
	     *            指定编码类型
	     * @param uppercase
	     *            是否转为大写字符串
	     * @return
	     */
	    public static String mD5Encode(String source, String encoding, boolean uppercase) {
	        String result = null;
	        try {
	            result = source;
	            // 获得MD5摘要对象
	            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
	            // 使用指定的字节数组更新摘要信息
	            messageDigest.update(result.getBytes(encoding));
	            // messageDigest.digest()获得16位长度
	            result = byteArrayToHexString(messageDigest.digest());

	        } catch (Exception e) {

				log.error("mD5Encode", e);
	            e.printStackTrace();
	        }
	        return uppercase ? result.toUpperCase() : result;
	    }
	    
	    /**
	     * hash摘要
	     * @param source
	     * @param encoding
	     * @param algorithName
	     * @param uppercase
	     * @return
	     */
	    public static String hashEncode(String source, String encoding, String algorithName, boolean uppercase) {
	        String result = null;
	        try {
	            result = source;
	            // 获得MD5摘要对象
	            MessageDigest messageDigest = MessageDigest.getInstance( algorithName );
	            // 使用指定的字节数组更新摘要信息
	            messageDigest.update(result.getBytes(encoding));
	            // messageDigest.digest()获得16位长度
	            result = byteArrayToHexString(messageDigest.digest());

	        } catch (Exception e) {

				log.error("hashEncode", e);
	            e.printStackTrace();
	        }
	        return uppercase ? result.toUpperCase() : result;
	    }
	    
	    /**
	     * hash encrypt
	     * @param source
	     * @param encoding
	     * @param algorithName
	     * @return
	     */
	    public static byte[] hashEncrypt(String source, String encoding, String algorithName) {
	    	byte[] result = null;
	        try {
	            result = source.getBytes(encoding);
	           
	            result = hashEncrypt(result, algorithName);

	        } catch (Exception e) {

				log.error("hashEncrypt", e);
	            e.printStackTrace();
	        }
	        return result;
	    }
	    
	    /**
	     * hash encrypt
	     * @param bytes
	     * @param algorithName
	     * @return
	     */
	    public static byte[] hashEncrypt(byte[] bytes, String algorithName ) {
	    	byte[] result = null;
	        try {	            
	            // 获得MD5摘要对象
	            MessageDigest messageDigest = MessageDigest.getInstance(algorithName);//("MD5");
	            // 使用指定的字节数组更新摘要信息
	            messageDigest.update(bytes);
	            // messageDigest.digest()获得16位长度
	            result = messageDigest.digest();

	        } catch (Exception e) {

				log.error("hashEncrypt", e);
	            e.printStackTrace();
	        }
	        return result;
	    }

	    /**
	     * 转换字节数组为16进制字符串
	     * 
	     * @param bytes
	     *            字节数组
	     * @return
	     */
	    public static String byteArrayToHexString(byte[] bytes) {
	        StringBuilder stringBuilder = new StringBuilder();
	        for (byte tem : bytes) {
	            stringBuilder.append(byteToHexString(tem));
	        }
	        return stringBuilder.toString();
	    }

	    /**
	     * 转换byte到16进制
	     * 
	     * @param b
	     *            要转换的byte
	     * @return 16进制对应的字符
	     */
	    public static String byteToHexString(byte b) {
	        int n = b;
	        if (n < 0) {
	            n = 256 + n;
	        }
	        int d1 = n / 16;
	        int d2 = n % 16;
	        return hexDigits[d1] + hexDigits[d2];
	    }

}
