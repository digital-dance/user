/**
 * 
 */
package com.digital.dance.service.impl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.ldap.filter.AbstractFilter;

import com.digital.dance.user.commons.AppPropsConfig;
import com.digital.dance.user.commons.Log;

/**
 * 
 * <pre>
 * 。
 * </pre>
 * 
 * @author developer developer@hpe.com.cn
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录
 *    修改后版本:     修改人：  修改日期:     修改内容:
 * </pre>
 */
public class LdapUserScheduleServiceImpl implements InitializingBean {

	private static final Log logger = new Log( LdapUserScheduleServiceImpl.class );

	private com.digital.dance.service.LdapUserService<AbstractFilter> ldapUserService;

	private static int PAGE_SIZE = 500;
	public static boolean isRunning;

	@Override
	public void afterPropertiesSet() throws Exception {
		logger.debug( "pull LDAPUsers once begin." );
		isRunning = false;

		logger.debug( "pull LDAPUsers once end." );
	}

	public void pullLDAPUsers() {
		logger.debug( "pull LDAPUsers cycliy begin." );

		Calendar calendar = Calendar.getInstance();
		calendar.setTime( new Date() );
		calendar.add( Calendar.MINUTE, -5 );
		Date startDateTime = calendar.getTime();		
		Date endDateTime = new Date();
		logger.debug( String.format( "pull LDAPUsers: start at %s ", sdf.format( startDateTime ) ) );
		if ( LdapUserScheduleServiceImpl.isRunning ){
			logger.debug( String.format( "pull LDAPUsers: It has been running. " ) );
			return;
		} else {
			try {
				pullAction( startDateTime);
			} catch (Exception ex ){
				isRunning = false;
				endDateTime = new Date();
				logger.error(String.format( "pull LDAPUsers end for %s . pull LDAPUsers: from %s to %s ", ex.getMessage(), sdf.format( startDateTime ), sdf.format( endDateTime ) ), ex);
				return;
			}
			endDateTime = new Date();
			logger.debug( String.format( "pull LDAPUsers end. pull LDAPUsers: from %s to %s ", sdf.format( startDateTime ), sdf.format( endDateTime ) ) );

		}
	}

	private static SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );

	public synchronized void pullAction( Date startDateTime ) throws SQLException {
		int pageIndex = 0;
		try {
			PAGE_SIZE = Integer.parseInt( AppPropsConfig.getPropValue("LDAP_USER_PAGE_SIZE") );
		} catch (Exception ex ){
			logger.error(ex);
		}
		
		logger.debug( String.format( "ldapUserService pull LDAPUsers: query PAGE_SIZE: %d start at %s .", PAGE_SIZE, sdf.format( startDateTime ) ) );
		isRunning = true;
		ldapUserService.getAllLDAPUsers(ldapUserService.buildFilter("", "", "", "organizationalUnit"), PAGE_SIZE, pageIndex );
		isRunning = false;
		Date endDateTime = new Date();
		logger.debug( String.format( "ldapUserService pull LDAPUsers: query PAGE_SIZE: %d from %s to %s.", PAGE_SIZE, sdf.format( startDateTime ), sdf.format( endDateTime ) ) );
	}

	/**
	 * @return the ldapUserService
	 */
	public com.digital.dance.service.LdapUserService<AbstractFilter> getLdapUserService() {
		return ldapUserService;
	}

	/**
	 * @param ldapUserService the ldapUserService to set
	 */
	public void setLdapUserService(com.digital.dance.service.LdapUserService<AbstractFilter> ldapUserService) {
		this.ldapUserService = ldapUserService;
	}

}
