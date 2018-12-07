package com.digital.dance.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.digital.dance.user.commons.AppPropsConfig;
import com.digital.dance.user.commons.CookieUtil;
import com.digital.dance.framework.codis.Codis;
import com.digital.dance.framework.redis.Redis;
import com.digital.dance.user.commons.unittest.UnitTestBase;

public class CodisServiceImplTest extends UnitTestBase{
	
	private Redis redis;
	private Codis codis;
	
	@Test
	public void testRedis() {
		context = getInstance("classpath*:**/user-impl-test-context.xml");
		redis = (Redis)context.getBean("redis");
		redis.setex("redis_ghqq", 60, "redis_testV1_ex");
		Boolean throwException=false;
		String codisvalu=redis.get("redis_ghqq", String.class);
		
		org.junit.Assert.assertTrue("redis_testV1_ex".equals(codisvalu));
	}
	
	@Test
	public void testCodis() {
		context = getInstance("classpath*:**/user-impl-test-context.xml");
		codis = (Codis)context.getBean("codis");
		
		codis.setex("codis_ghqq", 60, "codis_testV1_ex");		
		String codisvalu = codis.get("codis_ghqq", String.class);
		
		org.junit.Assert.assertTrue("codis_testV1_ex".equals(codisvalu));
		
		codis.setex("codis_ghqq", 60, "codis_testV2_ex");		
		String codisvalu2=codis.get("codis_ghqq", String.class);
		
		org.junit.Assert.assertTrue("codis_testV2_ex".equals(codisvalu2));
	}
	
	@Test
	public void main(){
		context = UnitTestBase.getInstance("classpath*:**/user-impl-test-context.xml");
		String ENCRYPT_SECURITY_CREDENTIALS = AppPropsConfig.getPropValue("ENCRYPT_SECURITY_CREDENTIALS");//"chn-sr-support@hpe.com";
		System.out.println(ENCRYPT_SECURITY_CREDENTIALS);
		
		if(CookieUtil.isHexString( ENCRYPT_SECURITY_CREDENTIALS ) ) {
			ENCRYPT_SECURITY_CREDENTIALS = CookieUtil.decode( ENCRYPT_SECURITY_CREDENTIALS );
			
		}else{
			ENCRYPT_SECURITY_CREDENTIALS = CookieUtil.encode( ENCRYPT_SECURITY_CREDENTIALS );
		}
		System.out.println(ENCRYPT_SECURITY_CREDENTIALS);
		String ENCRYPT_SECURITY_PRINCIPAL = AppPropsConfig.getPropValue("ENCRYPT_SECURITY_PRINCIPAL");
    	System.out.println(ENCRYPT_SECURITY_PRINCIPAL);
    	
		if(CookieUtil.isHexString( ENCRYPT_SECURITY_PRINCIPAL ) ) {
			ENCRYPT_SECURITY_PRINCIPAL = CookieUtil.decode( ENCRYPT_SECURITY_PRINCIPAL );
		}else {
			ENCRYPT_SECURITY_PRINCIPAL = CookieUtil.encode( ENCRYPT_SECURITY_PRINCIPAL );
		}
    	System.out.println(ENCRYPT_SECURITY_PRINCIPAL);
    	
    	String ENCRYPT_USER_DN = AppPropsConfig.getPropValue("ENCRYPT_USER_DN");
    	System.out.println(ENCRYPT_USER_DN);
    	
		if(CookieUtil.isHexString( ENCRYPT_USER_DN ) ) {
			ENCRYPT_USER_DN = CookieUtil.decode( ENCRYPT_USER_DN );
		}else {
			ENCRYPT_USER_DN = CookieUtil.encode( ENCRYPT_USER_DN );
		}		
    	System.out.println(ENCRYPT_USER_DN);
    	
    	System.out.println("--------------------------------------------------");
    	
		System.out.println((CookieUtil.decode("AF828E45F2834521065121ACD0F4DF9DFBBE1C2B3D47BF6CCF6D104F66489500")));
		System.out.println((("AF828E45F2834521065121ACD0F4DF9DFBBE1C2B3D47BF6CCF6D104F66489500")));
		System.out.println("--------------------------------------------------");
		System.out.println((CookieUtil.decode("E8152FAE660F63E3963A946C4EDB92E255C9E080375C6561D48B5B076DD89ED6")));
		System.out.println((("E8152FAE660F63E3963A946C4EDB92E255C9E080375C6561D48B5B076DD89ED6")));
		System.out.println("--------------------------------------------------");
		System.out.println((CookieUtil.decode("05159CD3AA26584527825B6D5C563906")));
		System.out.println((("05159CD3AA26584527825B6D5C563906")));
		System.out.println("--------------------------------------------------");
		System.out.println((CookieUtil.decode("9fe260cd1e8413a4e89044cefb6e1c0d770757e86454627d11a4f143dd34c8ad44564bf3b10533e6ea23e032750cfe63d7275b6debf92cccf86c73fe4b868764c91ebfbb0dee2d40f88502366d22a6aaab121207a2e0e4f049659be399976082")));
		System.out.println((("9fe260cd1e8413a4e89044cefb6e1c0d770757e86454627d11a4f143dd34c8ad44564bf3b10533e6ea23e032750cfe63d7275b6debf92cccf86c73fe4b868764c91ebfbb0dee2d40f88502366d22a6aaab121207a2e0e4f049659be399976082")));
		System.out.println("--------------------------------------------------");
		System.out.println((CookieUtil.decode("af828e45f2834521065121acd0f4df9dfbbe1c2b3d47bf6ccf6d104f66489500")));
		System.out.println((("af828e45f2834521065121acd0f4df9dfbbe1c2b3d47bf6ccf6d104f66489500")));
		System.out.println("--------------------------------------------------");
		System.out.println((CookieUtil.decode("031e5afd07d4fcec543c8a37f4b95b66")));
		System.out.println((("031e5afd07d4fcec543c8a37f4b95b66")));
		System.out.println("--------------------------------------------------");
		String my = "iamlxrf4_3";
		String myen = CookieUtil.encode(my);
		System.out.println(myen);
		System.out.println(CookieUtil.decode(myen));
		System.out.println("--------------------------------------------------");
//    	String pass = "zzzzzzz";
//    	
//    	System.out.println(CookieUtil.encode(pass));
//    	
//		System.out.println(CookieUtil.decode(CookieUtil.encode(pass)));
//		
//		LdapUserServiceImpl s = (LdapUserServiceImpl)context.getBean("ldapUserService");
//		boolean ret = s.checkUser(ENCRYPT_SECURITY_PRINCIPAL, ENCRYPT_SECURITY_CREDENTIALS);
//		try {
//			s.getAllLDAPUsers(s.buildFilter("", "", "", "organizationalUnit"), 500, 100);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	public static void main(String[] args){
		
	}
}
