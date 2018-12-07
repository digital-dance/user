package com.digital.dance.user.commons;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * xss攻击filter
 * @author liwy
 *
 */
public class XssFilter extends com.digital.dance.framework.infrastructure.commons.XssFilter {
//	FilterConfig filterConfig = null;
//	@Override
//	public void init(FilterConfig filterConfig) throws ServletException {
//		this.filterConfig = filterConfig;
//	}
//
//	@Override
//	public void doFilter(ServletRequest request, ServletResponse response,
//			FilterChain chain) throws IOException, ServletException {
//		chain.doFilter(new XssHttpServletRequestWrapper((HttpServletRequest)request),response);
//		((HttpServletResponse)response).setHeader("Access-Control-Allow-Origin", "*");
//		((HttpServletResponse)response).setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");
//		((HttpServletResponse)response).setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With, Access-Control-Allow-Origin, Access-Control-Allow-Methods, X-Auth-Token, Access-Control-Allow-Credientials");
//		//((HttpServletResponse)response).setHeader("Access-Control-Allow-Headers", "X-Custom-Header");
//		//((HttpServletResponse)response).setHeader("Access-Control-Allow-Credientials", "true");
//	}
//
//	@Override
//	public void destroy() {
//		filterConfig = null;
//	}

}
