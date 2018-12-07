package com.digital.dance.dao;

import java.sql.SQLException;
import java.util.List;

import com.digital.dance.entity.LdapUserEO;

public interface LdapUserDao  <T> {
	public int[] addLdapUsers(List<LdapUserEO> ldapUsers) throws SQLException;
	
	public boolean checkUser( String useruid, String userPassword );
	public List<LdapUserEO> getAllLdapDatas(final String pBaseDn, final T filter, final Integer pagSize, final Integer pageIndex, final LdapUserDao  <T> ldapUserDao);
	public List<LdapUserEO> getAllLdapUsers(final T filter, final Integer pagSize, final Integer pageIndex) throws SQLException;
	public T buildFilter(String cn, String ou, String sn, String objectclass);
}
