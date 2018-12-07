package com.digital.dance.user.commons;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

public class AESEncryptUtil {
	private static final String AES = "AES";
	/**
	 * 使用AES加密或解密无编码的原始字节数组, 返回无编码的字节数组结果.
	 * 
	 * @param input 原始字节数组
	 * @param key 符合AES要求的密钥
	 * @param mode Cipher.ENCRYPT_MODE 或 Cipher.DECRYPT_MODE
	 */
	public static byte[] aes(byte[] input, byte[] key, int mode) {
		SecretKey secretKey = new SecretKeySpec(key, AES);
		Cipher cipher;
		try {
			cipher = Cipher.getInstance(AES);
			cipher.init(mode, secretKey);
			return cipher.doFinal(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw  new RuntimeException();
		}
	}
	
	/**
	 * Hex编码.
	 */
	public static String encodeHex(byte[] input) {
		return Hex.encodeHexString(input);
	}
	
	/**
	 * Hex解码.
	 */
	public static byte[] decodeHex(String input) {
		try {
			return Hex.decodeHex(input.toCharArray());
		} catch (DecoderException e) {
			e.printStackTrace();
			throw  new RuntimeException();
		}
	}
}
