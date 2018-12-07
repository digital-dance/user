package com.digital.dance.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;

import org.springframework.ldap.control.PagedResultsDirContextProcessor;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.ContextSource;

import org.springframework.ldap.core.LdapOperations;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapOperationsCallback;
import org.springframework.ldap.core.support.SingleContextSource;
import org.springframework.ldap.filter.AbstractFilter;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;

import com.digital.dance.dao.LdapUserDao;
import com.digital.dance.entity.LdapUserEO;
import com.digital.dance.user.commons.AppPropsConfig;
import com.digital.dance.user.commons.GsonUtils;
import com.digital.dance.user.commons.Log;

public class LdapUserDaoImpl implements LdapUserDao < AbstractFilter > {

	private DataSource dataSource;
	private static final Log logger = new Log(LdapUserDaoImpl.class);

	private String INITIAL_CONTEXT_FACTORY;

	private String CONNECT_POOL;

	private int PAGE_SIZE;

	private String SECURITY_AUTHENTICATION;

	private String SECURITY_CREDENTIALS;

	private String PROVIDER_URL;

	private String SECURITY_PRINCIPAL;

	private String baseDn;

	private LdapTemplate ldapTemplate;
	private ContextSource contextSource;

	public LdapUserDaoImpl() {
	}

	public LdapUserDaoImpl(ContextSource pContextSource) {
		contextSource = pContextSource;
	}

	@SuppressWarnings("unused")
	public DirContext init(String useruid, String userPassword) {
		if(StringUtils.isBlank(useruid)) return null;
		DirContext ctx = null;
		Hashtable<String, String> env = new Hashtable<String, String>();
		// 初始化绑定账号连接参数
		env.put(Context.INITIAL_CONTEXT_FACTORY, INITIAL_CONTEXT_FACTORY);

		env.put(Context.PROVIDER_URL, PROVIDER_URL);
		
		// 用户账号值 格式："liming"
		
		env.put(Context.SECURITY_PRINCIPAL, useruid);

		// 静态密码认证
		env.put(Context.SECURITY_AUTHENTICATION, SECURITY_AUTHENTICATION);
		
		// 用户密码 格式："123456"
		
		env.put(Context.SECURITY_CREDENTIALS, userPassword);

		try {
			ctx = new InitialDirContext(env);

		} catch (NamingException e) {
			logger.error("管理员登陆 ldap 出错 ", e);
			if (ctx != null) {
				try {
					ctx.close();
				} catch (NamingException e1) {
					logger.error("close ladp admin error !", e1);
				}
			}
		}
		if (ctx == null) {
			logger.error("管理员登陆 ldap 出错 ");
			new RuntimeException("管理员登陆 ldap 出错 ");
		}
		return ctx;
	}

	public boolean checkUser(String useruid, String userPassword) {
		DirContext ctx_admin = init(useruid, userPassword);
		if(ctx_admin == null) return false;
		
		return true;

	}

	public AttributesMapper<LdapUserEO> attributesMapper = new AttributesMapper<LdapUserEO>() {

		public LdapUserEO mapFromAttributes(Attributes attributes) throws NamingException {
			LdapUserEO user = new LdapUserEO();
			if (attributes != null) {
				user.setName((attributes.get("name") != null) ? (String) attributes.get("name").get() : "");
				user.setEmployeeId((attributes.get("employeeID") != null) ? (String) attributes.get("employeeID").get() : "");

				user.setSn((attributes.get("sn") != null) ? (String) attributes.get("sn").get() : "");
				
				user.setCn((attributes.get("cn") != null) ? (String) attributes.get("cn").get() : "");

				user.setOu((attributes.get("ou") != null) ? (String) attributes.get("ou").get() : "");
				
				user.setDc((attributes.get("hpeKrbName") != null) ? (String) attributes.get("hpeKrbName").get() : "");

				user.setO((attributes.get("mailNickname") != null) ? (String) attributes.get("mailNickname").get() : "");
				
				user.setC((attributes.get("c") != null) ? (String) attributes.get("c").get() : "");

				user.setTelephoneNumber((attributes.get("mobile") != null) ? (String) attributes.get("mobile").get() : "");
				
				user.setMail((attributes.get("mail") != null) ? (String) attributes.get("mail").get() : "");

				user.setObjectClass((attributes.get("objectClass") != null) ? (String) attributes.get("objectClass").get() : "");
				user.setDistinguishedName((attributes.get("distinguishedName") != null) ? (String) attributes.get("distinguishedName").get() : "");
				user.setEmployeeType((attributes.get("employeeType") != null) ? (String) attributes.get("employeeType").get() : "");
				user.setGivenName((attributes.get("givenName") != null) ? (String) attributes.get("givenName").get() : "");
				user.setHpCrossCompanyManager((attributes.get("hpCrossCompanyManager") != null) ? (String) attributes.get("hpCrossCompanyManager").get() : "");
				user.setHpCrossCompanyManagerID((attributes.get("hpCrossCompanyManagerID") != null) ? (String) attributes.get("hpCrossCompanyManagerID").get() : "");
				user.setSt((attributes.get("hpStatus") != null) ? (String) attributes.get("hpStatus").get() : "");
				
				user.setLocationId((attributes.get("extensionAttribute2") != null) ? (String) attributes.get("extensionAttribute2").get() : "");
				
				user.setDepartment((attributes.get("department") != null) ? (String) attributes.get("department").get() : "");

				user.setDisplayName((attributes.get("displayName") != null) ? (String) attributes.get("displayName").get() : "");
				
				user.setPostalCode((attributes.get("postalCode") != null) ? (String) attributes.get("postalCode").get() : "");
				user.setStreetAddress((attributes.get("streetAddress") != null) ? (String) attributes.get("streetAddress").get() : "");
				user.setCo((attributes.get("co") != null) ? (String) attributes.get("co").get() : "");
				user.setCompany((attributes.get("company") != null) ? (String) attributes.get("company").get() : "");
			}
			return user;
		}
	};

	public AbstractFilter buildFilter(String cn, String ou, String sn, String objectclass) {
		AndFilter andFilter = new AndFilter();
		andFilter.append(new EqualsFilter("objectclass", "top"));
		//andFilter.and(new EqualsFilter("objectclass", "organizationalUnit"));
		if (StringUtils.isNotEmpty(objectclass)) {
			andFilter.and(new EqualsFilter("objectclass", objectclass));
		}

		if (StringUtils.isNotEmpty(cn)) {
			andFilter.and(new EqualsFilter("CN", cn));
		}

		if (StringUtils.isNotEmpty(ou)) {
			andFilter.and(new EqualsFilter("OU", ou));
		}

		if (StringUtils.isNotEmpty(sn)) {
			andFilter.and(new EqualsFilter("sn", sn));
		}
		return (AbstractFilter)andFilter;
	}

	public List<LdapUserEO> getAllLdapDatas(final String pBaseDn, final AbstractFilter filter, final Integer pagSize, final Integer pageIndex, final LdapUserDao < AbstractFilter > ldapUserDao) {

		final SearchControls searchControls = new SearchControls();
		searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);

		final PagedResultsDirContextProcessor processor = new PagedResultsDirContextProcessor(
				(pagSize == null) ? PAGE_SIZE : pagSize);

		return SingleContextSource.doWithSingleContext(contextSource, new LdapOperationsCallback<List<LdapUserEO>>() {

			@Override
			public List<LdapUserEO> doWithLdapOperations(LdapOperations operations) {
				List<LdapUserEO> result = new LinkedList<LdapUserEO>();
				Integer pageIndexPoint = -1;
				do {
					++pageIndexPoint;
					
					List<LdapUserEO> oneResult = operations.search(pBaseDn, filter.encode(), searchControls,
							attributesMapper, processor);
					//test begin
					if(ldapUserDao != null)
						try {
							ldapUserDao.addLdapUsers(oneResult);
						} catch (SQLException e) {
							logger.error(e);
							e.printStackTrace();
						}
					//test end
					result.addAll(oneResult);
					
				} while (processor.hasMore());

				if (result != null && result.size() > 0) {
					result.get(0).setPageIndex(pageIndexPoint);
					result.get(0).setPageSize(pagSize);
				}
				return result;
			}

		});
	}
	
	public List<LdapUserEO> getAllLdapUsers( AbstractFilter filter, final Integer pagSize, final Integer pageIndex) throws SQLException {
		List<LdapUserEO> users = new ArrayList<LdapUserEO>();
		List<LdapUserEO> areas = getAllLdapDatas(baseDn, filter, pagSize, pageIndex, null);
		for(LdapUserEO a : areas){
			if( "Users".equalsIgnoreCase( a.getName() ) ) continue;
			List<LdapUserEO> areaUsers = getAllLdapDatas("OU=" + a.getName() + "," + baseDn, buildFilter("", "", "", "person"), pagSize, pageIndex, this);
			//addLdapUsers(areaUsers);
			users = areaUsers ;
		}
		return users;
	}	
	
	@Override
	public int[] addLdapUsers(List<LdapUserEO> lDAPUsers) throws SQLException {
		
        Connection connection = dataSource.getConnection();		
		
		int[] ret = new int[1];
		int batchSize = 500;
		String delSql = AppPropsConfig.getPropValue("LDAPUSERDAO_DEL_SQL");
		String sql = AppPropsConfig.getPropValue("LDAPUSERDAO_SQL");
		PreparedStatement delPs = connection.prepareStatement(delSql);
		PreparedStatement ps = connection.prepareStatement(sql);
		//PreparedStatement ps = connection.prepareStatement("delete from ldap_user where employeeId=?;insert into ldap_user (streetAddress, employeeType, dc, employeeId, mail, sn, ou, c, department, locationId, o, objectClass, givenName, postalCode, cn, hpCrossCompanyManagerID, name, telephoneNumber, st, displayName, distinguishedName, hpCrossCompanyManager) values (?,?,?...)");
		connection.setAutoCommit(false);
		for (int i = 0; i < lDAPUsers.size(); i++) {
			Map<Object, Object> map = null;			
			String lStr = GsonUtils.toJson(lDAPUsers.get(i));
			map = GsonUtils.toObject(lStr, Map.class);
			String employeeId = (map.containsKey("employeeId") && map.get("employeeId") != null) ? map.get("employeeId").toString() : "";
			if("".equals(employeeId)) continue;
			delPs.setString(1, employeeId);
			
			int k = 1;			
			for(Map.Entry<Object, Object> e : map.entrySet()){
		    	ps.setString(k, ( e != null && e.getValue() != null) ? e.getValue().toString() : "" );
		    	++k;
		    }
		    delPs.addBatch();
		    ps.addBatch();

		    if ((i + 1) % batchSize == 0) {
		    	delPs.executeBatch();
		    	
		    	ret = ps.executeBatch();
		    	
		    	connection.commit();
		    }
		}

		if (lDAPUsers.size() % batchSize != 0) {
			delPs.executeBatch();
			
			ret = ps.executeBatch();
			
			connection.commit();
		}
		connection.close();   
        
		return ret;
	}

	public String getINITIAL_CONTEXT_FACTORY() {
		return INITIAL_CONTEXT_FACTORY;
	}

	public void setINITIAL_CONTEXT_FACTORY(String iNITIAL_CONTEXT_FACTORY) {
		INITIAL_CONTEXT_FACTORY = iNITIAL_CONTEXT_FACTORY;
	}

	public String getPROVIDER_URL() {
		return PROVIDER_URL;
	}

	public void setPROVIDER_URL(String pROVIDER_URL) {
		PROVIDER_URL = pROVIDER_URL;
	}

	public String getSECURITY_AUTHENTICATION() {
		return SECURITY_AUTHENTICATION;
	}

	public void setSECURITY_AUTHENTICATION(String sECURITY_AUTHENTICATION) {
		SECURITY_AUTHENTICATION = sECURITY_AUTHENTICATION;
	}

	public String getSECURITY_PRINCIPAL() {
		return SECURITY_PRINCIPAL;
	}

	public void setSECURITY_PRINCIPAL(String sECURITY_PRINCIPAL) {
		SECURITY_PRINCIPAL = sECURITY_PRINCIPAL;
	}

	public String getSECURITY_CREDENTIALS() {
		return SECURITY_CREDENTIALS;
	}

	public void setSECURITY_CREDENTIALS(String sECURITY_CREDENTIALS) {
		SECURITY_CREDENTIALS = sECURITY_CREDENTIALS;
	}

	public String getCONNECT_POOL() {
		return CONNECT_POOL;
	}

	public void setCONNECT_POOL(String cONNECT_POOL) {
		CONNECT_POOL = cONNECT_POOL;
	}

	/**
	 * @return the ldapTemplate
	 */
	public LdapTemplate getLdapTemplate() {
		return ldapTemplate;
	}

	/**
	 * @param ldapTemplate
	 *            the ldapTemplate to set
	 */
	public void setLdapTemplate(LdapTemplate ldapTemplate) {
		this.ldapTemplate = ldapTemplate;
	}

	/**
	 * @return the baseDn
	 */
	public String getBaseDn() {
		return baseDn;
	}

	/**
	 * @param baseDn the baseDn to set
	 */
	public void setBaseDn(String baseDn) {
		this.baseDn = baseDn;
	}	
	
	/**
	 * @return the dataSource
	 */
	public DataSource getDataSource() {
		return dataSource;
	}
	/**
	 * @param dataSource the dataSource to set
	 */
	public void setDataSource(com.alibaba.druid.pool.DruidDataSource dataSource) {
		this.dataSource = dataSource;
	}

}
