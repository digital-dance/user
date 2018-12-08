package com.digital.dance.home;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.digital.dance.user.commons.Log;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.digital.dance.user.commons.CookieUtil;
import com.digital.dance.user.commons.GsonUtils;
import com.digital.dance.vo.UserVO;


/**
 * 拦截器，拦截用户是否已经登陆，没有登陆返回登陆页面
 * @author liwy
 *
 */
public class ControllerInterceptor extends HandlerInterceptorAdapter {
	private Log log = new Log(ControllerInterceptor.class);
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		log.info("拦截地址为"+request.getRequestURL());
		//request.setAttribute("basePath",request.getContextPath());
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");
		response.setHeader("Access-Control-Allow-Headers", "Origin, SESSION, Cookie, Set-Cookie, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With, Access-Control-Allow-Origin, Access-Control-Allow-Methods, X-Auth-Token, Access-Control-Allow-Credientials");
		//response.setHeader("Access-Control-Allow-Headers", "X-Custom-Header");
		response.setHeader("Access-Control-Allow-Credientials", "true");
		return true;
	}
	
	private UserVO getUserVofromSession(HttpServletRequest request)
    {
        try
        {
            Boolean isfromSession = true;
            //获取session中保存的用户信息
            String userStr = CookieUtil.getCookieOrSessionUser(request, isfromSession);
            return ( userStr != null && !userStr.equals("") ) ? GsonUtils.getJson( userStr, UserVO.class ) : null;
        }
        catch (Exception e)
        {
            // TODO log
            e.printStackTrace();
            return null;
        }
    }
}
