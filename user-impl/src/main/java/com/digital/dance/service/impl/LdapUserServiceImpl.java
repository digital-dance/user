/**
 * 
 */
package com.digital.dance.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

import org.springframework.ldap.filter.AbstractFilter;

import com.digital.dance.bo.LDAPUser;
import com.digital.dance.dao.LdapUserDao;
import com.digital.dance.entity.LdapUserEO;
import com.digital.dance.user.commons.BeanUtils;

/**
 * <p>
 * Project:
 * <p>
 * <p>
 * Module:
 * <p>
 * <p>
 * Description:
 * <p>
 *
 * @author Xinyu Liu
 * @date 2016年5月9日 下午7:09:50
 */
public class LdapUserServiceImpl implements com.digital.dance.service.LdapUserService<AbstractFilter> {

	private LdapUserDao<AbstractFilter> ldapUserDao;

	public boolean checkUser(String useruid, String userPassword) {
		
		return ldapUserDao.checkUser(useruid, userPassword);
	}

	public AbstractFilter buildFilter(String cn, String ou, String sn, String objectclass) {
		
		return (AbstractFilter)ldapUserDao.buildFilter(cn, ou, sn, objectclass);
	}

	public List<LDAPUser> getAllLDAPDatas(final String pBaseDn, final AbstractFilter filter, final Integer pagSize, final Integer pageIndex) {
		List<LdapUserEO> users = ldapUserDao.getAllLdapDatas(pBaseDn, filter, pagSize, pageIndex, null);
		
		return convert(users);
	}
	
	public List<LDAPUser> getAllLDAPUsers( AbstractFilter filter, final Integer pagSize, final Integer pageIndex) throws SQLException {
		List<LdapUserEO> users = ldapUserDao.getAllLdapUsers(filter, pagSize, pageIndex);
		
		return convert(users);
	}

	private List<LDAPUser> convert(List<LdapUserEO> users){
		if(users == null) return null;
		List<LDAPUser> lDAPUsers = new ArrayList<LDAPUser>();
		for(LdapUserEO u : users){
			LDAPUser l = new LDAPUser();
			BeanUtils.copyProperties(u, l);
			lDAPUsers.add(l);
		}
		return lDAPUsers;
	}

	/**
	 * @return the ldapUserDao
	 */
	public LdapUserDao<AbstractFilter> getLdapUserDao() {
		return ldapUserDao;
	}

	/**
	 * @param ldapUserDao the ldapUserDao to set
	 */
	public void setLdapUserDao(LdapUserDao<AbstractFilter> ldapUserDao) {
		this.ldapUserDao = ldapUserDao;
	}

}
