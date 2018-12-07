/**
 * 
 */
package com.digital.dance.service;

import java.sql.SQLException;
import java.util.List;

import com.digital.dance.bo.LDAPUser;

/**
 * <p>Project:			<p>
 * <p>Module:			<p>
 * <p>Description:		<p>
 *
 * @author Xinyu Liu
 * @date 2016年5月9日 下午7:08:16
 */
public interface LdapUserService <T> {

	public boolean checkUser( String useruid, String userPassword );
	public List<LDAPUser> getAllLDAPUsers(final T filter, final Integer pagSize, final Integer pageIndex) throws SQLException;
	public T buildFilter(String uid, String cn, String sn, String objectclass);
}
