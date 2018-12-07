package com.digital.dance.user.commons;

import java.util.UUID;

import com.digital.dance.framework.identity.utils.IdentityUtil;

public class PrimaryKeyGenerator {
	public static final String SEQUENCES = "sequences";
	public static String generatePrimaryKey(String tableName){
		return com.digital.dance.framework.commons.PrimaryKeyGenerator.generatePrimaryKey(tableName);
		/*
		 if(IdentityUtil.getIdentityService() == null 
		 	 || StringTools.isEmpty( tableName ) 
		 	 || !AppPropsConfig.containsKey(tableName) ){
			return UUID.randomUUID().toString().replaceAll("-", "");
		 } else {
			String prefix = AppPropsConfig.getPropValue(tableName.toLowerCase());
			String id = IdentityUtil.generateId("serverroom", "userservice", "user", tableName).toString();
			if( StringTools.isEmpty( prefix ) || SEQUENCES.equals(prefix) ){
				return id; 
			}
			return prefix + id;
		 }*/
	}
	
	public static String generateBusinessPrimaryKey(String tableName){
		return com.digital.dance.framework.commons.PrimaryKeyGenerator.generateBusinessPrimaryKey(tableName);
		/*String prefix = AppPropsConfig.getPropValue(tableName);
		if(IdentityUtil.getIdentityService() == null){
			return UUID.randomUUID().toString().replaceAll("-", "");
		} else {
			String id = IdentityUtil.generateId("serverroom", "userservice", "user", tableName).toString();
			if( StringTools.isEmpty( prefix ) ){
				return id; 
			}
			return prefix + id;
		}*/
	}
}
