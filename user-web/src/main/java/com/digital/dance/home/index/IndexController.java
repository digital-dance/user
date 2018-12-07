package com.digital.dance.home.index;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.digital.dance.commons.security.utils.DESCoderUtil;
import com.digital.dance.commons.security.utils.RSACoderUtil;
import com.digital.dance.commons.security.utils.SHAUtil;
import com.digital.dance.commons.serialize.json.utils.JSONUtils;
//import com.digital.dance.framework.infrastructure.commons.StringTools;
import com.digital.dance.framework.sso.entity.LoginInfo;
import com.digital.dance.framework.sso.enums.ClientTypeEnums;
import com.digital.dance.framework.sso.filter.SSOLoginFilter;
import com.digital.dance.framework.sso.util.SSOLoginManageHelper;
import com.digital.dance.framework.infrastructure.commons.Constants;
import com.digital.dance.framework.infrastructure.commons.GsonUtils;
import com.digital.dance.user.commons.Log;
import com.digital.dance.user.commons.ResponseVo;

import com.digital.dance.user.commons.StringTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 
 * @author liwy
 *
 * time:2016年8月22日上午9:15:00
 */
@Controller
@RequestMapping({ "/index", " " })
public class IndexController {
	
	@Autowired
	private SSOLoginManageHelper ssologinManageHelper;
	
	private Log log=new Log(IndexController.class);
	
	/**
	 * 登陆首页
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("home")
    @ResponseBody
	public String index(HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("进入首页页面");
		String callBackUrl = request.getParameter(SSOLoginManageHelper.BIZ_URL);
		request.getSession().setAttribute(SSOLoginManageHelper.BIZ_URL, callBackUrl);
		LoginInfo loginInfo = SSOLoginFilter.getLoginInfoFromSession(request);

		//String casPubKey = (String)RSACoderUtil.getKey().get(RSACoderUtil.public_Key);
		String casPrivateKey = (String)RSACoderUtil.getKey().get(RSACoderUtil.private_Key);

		String ret = ssologinManageHelper.getApiLoginUrl();
		if( loginInfo != null && !StringTools.isEmpty(callBackUrl)){
			callBackUrl = URLDecoder.decode(callBackUrl, "UTF-8");
			String token = JSONUtils.toJson(loginInfo);
			token = RSACoderUtil.encryptByPrivateKey(casPrivateKey, token);
			token = URLEncoder.encode( token, "UTF-8" );
			ret = callBackUrl.split("\\?")[0] + "?token=" + token;

		} else if( loginInfo != null ){
			ResponseVo responsedata = new ResponseVo();
			responsedata.setCode(Constants.ReturnCode.SUCCESS.Code());
			responsedata.setMsg(Constants.SUCCESS_MSG);
			responsedata.setResult("/index/home");
			return GsonUtils.toJsonStr(responsedata);
		}
		//跳转到登录页
		String clientType = request.getParameter("clientType");
		if( ClientTypeEnums.WEBAJAX.getClientType().equals(clientType) ){
			
			ResponseVo responsedata = new ResponseVo();
			responsedata.setCode(Constants.ReturnCode.REDIRECT.Code());
			responsedata.setMsg("sendRedirect from browser.");
			responsedata.setResult(ret);
			return GsonUtils.toJsonStr(responsedata);
		}
		response.sendRedirect(ret);
		return ret;
	}
}
