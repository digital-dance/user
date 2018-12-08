package com.digital.dance.home.login;


import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.digital.dance.commons.exception.BizException;
import com.digital.dance.commons.serialize.json.utils.JSONUtils;
import com.digital.dance.framework.codis.Codis;
import com.digital.dance.framework.sso.filter.SSOLoginFilter;
import com.digital.dance.user.commons.*;
import com.digital.dance.framework.infrastructure.commons.Constants;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.filter.AbstractFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.rocketmq.client.producer.MessageQueueSelector;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.alibaba.rocketmq.common.message.MessageQueue;
import com.digital.dance.bo.SystemUserRoleBO;
import com.digital.dance.bo.UserBO;
import com.digital.dance.commons.security.utils.DESCoderUtil;
import com.digital.dance.commons.security.utils.RSACoderUtil;
import com.digital.dance.commons.security.utils.SHAUtil;
import com.digital.dance.framework.message.handler.MQMessageHandler;
import com.digital.dance.framework.message.producer.impl.DefaultMQProducer;
import com.digital.dance.framework.message.selector.DefaultMQPullConsumerMessageSelector;
import com.digital.dance.framework.sso.entity.LoginInfo;
import com.digital.dance.framework.sso.entity.LoginUserRole;
import com.digital.dance.framework.sso.entity.LogoutInfo;
import com.digital.dance.framework.sso.enums.ClientTypeEnums;
import com.digital.dance.framework.sso.util.BrowserContext;
import com.digital.dance.framework.sso.util.LoginContext;
import com.digital.dance.framework.sso.util.SSOLoginManageHelper;
import com.digital.dance.framework.infrastructure.commons.GsonUtils;
import com.digital.dance.service.LdapUserService;
import com.digital.dance.service.SystemUserRoleService;
import com.digital.dance.service.UserService;
import com.digital.dance.vo.SystemUserRoleVO;
import com.digital.dance.vo.UserVO;


/**
 *
 * @author liwy
 *
 */
@RequestMapping("/login")
@Controller
public class LoginController
{
//	@Autowired
//	private LdapUserService<AbstractFilter> ldapUserService;

    @Autowired
    private Codis codis;

    @Autowired
    private SSOLoginManageHelper ssologinManageHelper;

    @Autowired
    private UserService userService;
    @Autowired
    private SystemUserRoleService systemUserRoleService;

//    @Autowired
//    private ServerroomDefaultMQProducer messageProducer;
//
//    @Autowired
//    private DefaultMQPullConsumerMessageSelector defaultMQPullConsumerMessageSelector;
//
//    @Autowired
//    private DefaultMQPullConsumerMessageSelector defaultMQPullConsumerMessageSelectorDamon;

    Log log = new Log(LoginController.class);

    /**
     * 登陆验证
     */
    @RequestMapping(value="user", method=RequestMethod.POST)
    @ResponseBody
    public ResponseVo loginvaildate(HttpServletRequest request,
                                    HttpServletResponse response, @RequestBody UserVO userVo)
            throws Exception
    {
        boolean checked = false;
        UserVO user = new UserVO();
        String clientType = request.getParameter("clientType");
        ResponseVo reVo = new ResponseVo();
        reVo.setResult("");
        LoginInfo loginInfo = SSOLoginFilter.getLoginInfoFromSession(request);
        if(loginInfo != null){
            checked = true;
            BeanUtils.copyProperties(loginInfo, user);

            List<SystemUserRoleVO> rolevos = new ArrayList<SystemUserRoleVO>();
            List<LoginUserRole> loginUserRoles = loginInfo.getUserRoles();
            if( loginUserRoles != null){
                for(LoginUserRole r : loginUserRoles){
                    SystemUserRoleVO v = new SystemUserRoleVO();
                    BeanUtils.copyProperties(r, v);
                    rolevos.add(v);
                }
            }

            user.setRoles(rolevos);

            reVo.setCode(Constants.RETURN_CODE_SUCCESS);
            reVo.setMsg(Constants.SUCCESS_MSG);
            reVo.setResult(user);
            return reVo;
        }

        try
        {
            String userEmail = userVo.getUserEmail();
            String psw = userVo.getPassword();
            String md5Psw = Md5Util.getMD5(psw);
//			UserBO pUB = new UserBO();
//			pUB.setUserEmail(userEmail);
            UserBO userBO = null;

            if(userService != null){
                userBO  = userService.checkUser(userEmail, md5Psw);

                //fake begin
                //UserBO userBO = new UserBO();// userService.findUserByUserName(userName);
                //userBO.setUserId("test0");
                //fake end
                if(userBO != null && userBO.getUserId()!= null && !"".equals(userBO.getUserId())){
                    checked = true;
                }
            }
            if( !checked ){

                reVo.setCode(Constants.RETURN_CODE_FAILED);
                reVo.setMsg(Constants.FAILED_MSG);
                reVo.setResult(null);
                return reVo;
            } else {
                BeanUtils.copyProperties(userBO, user);

                SystemUserRoleBO pRole = new SystemUserRoleBO();
                pRole.setRoleId(null);
                pRole.setUserId(userBO.getUserId());
                List<SystemUserRoleBO> roles =  systemUserRoleService.findAllSystemUserRoles(pRole);
                List<SystemUserRoleVO> rolevos = new ArrayList<SystemUserRoleVO>();
                List<LoginUserRole> loginUserRoles = new ArrayList<LoginUserRole>();
                for(SystemUserRoleBO r : roles){
                    SystemUserRoleVO v = new SystemUserRoleVO();
                    BeanUtils.copyProperties(r, v);
                    rolevos.add(v);
                    LoginUserRole lur = new LoginUserRole();
                    BeanUtils.copyProperties(r, lur);
                    loginUserRoles.add(lur);
                }
                user.setRoles(rolevos);

                HttpSession session = request.getSession();
                if(session != null){
                    user.setSessionId(session.getId());
                    CookieUtil.writeCookieAndSessionUser(request,response,
                            GsonUtils.toJson(user));
                }

                loginInfo = new LoginInfo();
                BeanUtils.copyProperties(userBO, loginInfo);
                loginInfo.setJsessionidCAS(session.getId());

                LoginContext.setLoginInfo(loginInfo);
                SSOLoginFilter.setLoginInfo2Session(request, loginInfo);

                String browserContext = "";
                try {//记录浏览器参数
                    browserContext = BrowserContext
                            .buildBrowserContextValue(String.valueOf(ClientTypeEnums.WEB.getClientType()), request);
                    loginInfo.setBrowserContext(browserContext);
                } catch (NoSuchAlgorithmException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    log.error(e.getMessage(), e);
                }
                //loginInfo.setSessionId(session.getId());
                loginInfo.setUserRoles(loginUserRoles);
                String tokenJson = JSONUtils.toJson(loginInfo);
                String casPrivateKey = (String)RSACoderUtil.getKey().get(RSACoderUtil.private_Key);

                String token = RSACoderUtil.encryptByPrivateKey(casPrivateKey, tokenJson);

                String callBackUrl = request.getSession().getAttribute(SSOLoginManageHelper.BIZ_URL).toString();
                if( !StringTools.isEmpty(callBackUrl)){
//                    callBackUrl = URLDecoder.decode(callBackUrl, "UTF-8");
                    String encodedToken = URLEncoder.encode( token, "UTF-8" );
                    String ret = callBackUrl.split("\\?")[0] + "?token=" + encodedToken;
                    user.setRedirect(ret);

                    if( !( ClientTypeEnums.WEBAJAX.getClientType().equals(clientType) ) ){
                        response.sendRedirect(ret);
                    }
                }

                reVo.setResult(user);
                reVo.setCode(Constants.RETURN_CODE_SUCCESS);
                reVo.setMsg(Constants.SUCCESS_MSG);
            }
//            else {
//            	reVo.setCode(Constants.RETURN_CODE_FAILED);
//                reVo.setMsg("failed");
//                reVo.setResult(null);
//            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            reVo.setCode(Constants.RETURN_CODE_FAILED);
            reVo.setMsg( "登陆失败:" + e.getMessage() );
            reVo.setResult(null);
            log.error("loginvaildate", e);
            return reVo;
        }

        return reVo;
    }

    @RequestMapping("session_in")
    @ResponseBody
    public ResponseVo sessionVaildate(HttpServletRequest request,
                                      HttpServletResponse response)
            throws Exception{
        final ResponseVo reVo = new ResponseVo();
        reVo.setResult("");
        try
        {
            HttpSession session_t = request.getSession();
            session_t.setAttribute( (String)request.getParameter("key"), request.getParameter("value") );

            reVo.setResult( session_t.getId() + " : " + com.digital.dance.framework.codis.utils.CodisUtil.getMaxInactiveInterval() );

        } catch (Exception ex){
            reVo.setResult("0");
            log.error("sessionVaildate", ex);
        }
        return reVo;
    }

    @RequestMapping("session")
    @ResponseBody
    public ResponseVo session(HttpServletRequest request,
                              HttpServletResponse response)
            throws Exception{ ResponseVo reVo = new ResponseVo();
        reVo.setResult("");
        try
        {
            HttpSession session_t = request.getSession();

            String ret = (String)session_t.getAttribute( (String)request.getParameter("key") );
            reVo.setResult(session_t.getId() + " : " + ret);
        } catch (Exception ex){
            reVo.setResult("exception");
            log.error("session", ex);
        }
        return reVo;
    }

    @RequestMapping("session/id")
    @ResponseBody
    public ResponseVo getSessionId(HttpServletRequest request,
                                   HttpServletResponse response)
            throws Exception{ ResponseVo reVo = new ResponseVo();
        reVo.setResult("");
        try
        {
            HttpSession session_t = request.getSession();

            //String ret = (String)session_t.getAttribute( (String)request.getParameter("key") );
            reVo.setResult("sessionId" + " : " + session_t.getId());
        } catch (Exception ex){
            reVo.setResult("exception");
            log.error("session", ex);
        }
        return reVo;
    }

    @RequestMapping("/out")
    @ResponseBody
    public ResponseVo logout(HttpServletRequest request,
                             HttpServletResponse response)
            throws Exception{
        ResponseVo reVo = new ResponseVo();
        LoginInfo loginInfo = LoginContext.getLoginInfo();
        LogoutInfo logoutInfo = new LogoutInfo();
        logoutInfo.setJsessionidCAS(loginInfo.getJsessionidCAS());
        logoutInfo.setUserId(loginInfo.getUserId());
        logoutInfo.setUserName(loginInfo.getUserName());
        reVo.setResult("");
        try
        {
            String logoutInfoJson = GsonUtils.toJsonStr(logoutInfo);
            SSOLoginFilter.setLoginInfo2Session(request, null);
//            	reVo.setResult(sendResult);
        } catch (Exception ex){
            reVo.setResult("exception");
            log.error("session", ex);
        }
        return reVo;
    }

    @RequestMapping(value = "/md5" )
    @ResponseBody
    public ResponseVo getMd5(HttpServletRequest request,
                             HttpServletResponse response)
            throws Exception{
        ResponseVo reVo = new ResponseVo();
        reVo.setResult("");
        String value = request.getParameter("value");
        try
        {
            String md5Psw = Md5Util.getMD5(value);

            reVo.setResult(md5Psw);
        } catch (Exception ex){
            reVo.setResult("exception");
            log.error("getMd5", ex);
        }
        return reVo;
    }

    @RequestMapping(value = "pubkey", method = RequestMethod.POST )
    @ResponseBody
    public ResponseVo getPubKey(HttpServletRequest request,
                                HttpServletResponse response)
            throws Exception{
        ResponseVo reVo = new ResponseVo();
        reVo.setResult("");
        try
        {
            String casPubKey = (String)RSACoderUtil.getKey().get(RSACoderUtil.public_Key);

            reVo.setResult(casPubKey);
        } catch (Exception ex){
            reVo.setResult("exception");
            log.error("session", ex);
        }
        return reVo;
    }

    @RequestMapping(value = "caspubkey" )
    @ResponseBody
    public ResponseVo pullPubKey(HttpServletRequest request,
                                 HttpServletResponse response)
            throws Exception{
        ResponseVo reVo = new ResponseVo();
        reVo.setResult("");
        try
        {
            String casPubKey = (String)RSACoderUtil.getKey().get(RSACoderUtil.public_Key);

            reVo.setResult(casPubKey);
        } catch (Exception ex){
            reVo.setResult("exception");
            log.error("session", ex);
        }
        return reVo;
    }
    //AppPropsConfig
    @RequestMapping(value = "props" )
    @ResponseBody
    public ResponseVo getProps(HttpServletRequest request,
                               HttpServletResponse response)
            throws Exception{
        ResponseVo reVo = new ResponseVo();
        reVo.setResult("");
        try
        {
            String ret = AppPropsConfig.getPropValue(request.getParameter("key"));

            reVo.setResult(ret);
        } catch (Exception ex){
            reVo.setResult("exception");
            log.error("session", ex);
        }
        return reVo;
    }

    @RequestMapping(value = "encodedprops" )
    @ResponseBody
    public ResponseVo getEncodedProps(HttpServletRequest request,
                                      HttpServletResponse response)
            throws Exception{
        ResponseVo reVo = new ResponseVo();
        reVo.setResult("");
        try
        {
            String ret = AppPropsConfig.getPropValue(request.getParameter("key"));

            reVo.setResult( CookieUtil.encode(ret) );
        } catch (Exception ex){
            reVo.setResult("exception");
            log.error("session", ex);
        }
        return reVo;
    }

    @RequestMapping(value = "encryptprops" )
    @ResponseBody
    public ResponseVo getEncryptProps(HttpServletRequest request,
                                      HttpServletResponse response)
            throws Exception{
        ResponseVo reVo = new ResponseVo();
        reVo.setResult("");
        try
        {
            String ret = AppPropsConfig.getPropValue(request.getParameter("key"));
            String casPrivateKey = (String)RSACoderUtil.getKey().get(RSACoderUtil.private_Key);

            String token = RSACoderUtil.encryptByPrivateKey(casPrivateKey, ret);
            reVo.setResult(URLEncoder.encode( token, "UTF-8" ) );
        } catch (Exception ex){
            reVo.setResult("exception");
            log.error("session", ex);
        }
        return reVo;
    }

    @RequestMapping(value = "faketoken" )
    @ResponseBody
    public ResponseVo getFakeToken(HttpServletRequest request,
                                   HttpServletResponse response)
            throws Exception{
        ResponseVo reVo = new ResponseVo();
        reVo.setResult("");
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setUserId("1234567890");
        loginInfo.setUserEmail("2506225465@qq.com");
        loginInfo.setUserName("2506225465@qq.com");
        try
        {
            String tokenJson = JSONUtils.toJson(loginInfo);

            String casPrivateKey = (String)RSACoderUtil.getKey().get(RSACoderUtil.private_Key);

            String fakeToken = RSACoderUtil.encryptByPrivateKey(casPrivateKey, tokenJson);
            reVo.setResult( URLEncoder.encode( fakeToken, "UTF-8" ) );
        } catch (Exception ex){
            reVo.setResult("exception");
            log.error("session", ex);
        }
        return reVo;
    }

    @RequestMapping(value = "decryptprops" )
    @ResponseBody
    public ResponseVo getdecryptProps(HttpServletRequest request,
                                      HttpServletResponse response)
            throws Exception{
        ResponseVo reVo = new ResponseVo();
        reVo.setResult("");
        try
        {
            String ret = (request.getParameter("key"));
//            ret = URLDecoder.decode(ret, "UTF-8");
            String casPubKey = (String)RSACoderUtil.getKey().get(RSACoderUtil.public_Key);

            String token = RSACoderUtil.decryptByPublicKey(casPubKey, ret);
            reVo.setResult( token );

            String pubKey = "";
            try {
//			pubKey = callCasPubKeyService;
                //TO DO get CasPubKey begin
                Map<String, String> map = new HashMap<String, String>();

                String pubKeyResponse = com.digital.dance.framework.infrastructure.commons.HttpClientUtil.executePostRequest(ssologinManageHelper.getLoginServiceUrl()+"/pubKey", new HashMap<String, String>(), map);

                com.digital.dance.framework.infrastructure.commons.ResponseVo pubKeyResponseVo = (com.digital.dance.framework.infrastructure.commons.ResponseVo) JSONUtils.toObject(pubKeyResponse, com.digital.dance.framework.infrastructure.commons.ResponseVo.class);
                pubKey = pubKeyResponseVo.getResult().toString();
                //TO DO get CasPubKey end
            } catch (Exception e1) {

                log.error(e1.getMessage(), e1);
                throw new BizException("get pubKey error.", e1);
            }
        } catch (Exception ex){
            reVo.setResult("exception");
            log.error("session", ex);
        }
        return reVo;
    }


    @RequestMapping(value = "remotepubkey" )
    @ResponseBody
    public ResponseVo getRemotePubKey(HttpServletRequest request,
                                      HttpServletResponse response)
            throws Exception{
        ResponseVo reVo = new ResponseVo();
        reVo.setResult("");
        try
        {
            String pubKey = "";
            try {
//			pubKey = callCasPubKeyService;
                //TO DO get CasPubKey begin
                Map<String, String> map = new HashMap<String, String>();

                String pubKeyResponse = com.digital.dance.framework.infrastructure.commons.HttpClientUtil.executePostRequest(ssologinManageHelper.getLoginServiceUrl()+"/pubKey", new HashMap<String, String>(), map);

                com.digital.dance.framework.infrastructure.commons.ResponseVo pubKeyResponseVo = (com.digital.dance.framework.infrastructure.commons.ResponseVo) JSONUtils.toObject(pubKeyResponse, com.digital.dance.framework.infrastructure.commons.ResponseVo.class);
                pubKey = pubKeyResponseVo.getResult().toString();
                reVo.setResult( pubKey );
            } catch (Exception e1) {

                log.error(e1.getMessage(), e1);
                throw new BizException("get pubKey error.", e1);
            }
        } catch (Exception ex){
            reVo.setResult("exception");
            log.error("session", ex);
        }
        return reVo;
    }

    @RequestMapping(value = "remotetoken" )
    @ResponseBody
    public ResponseVo resolveToken(HttpServletRequest request,
                                   HttpServletResponse response)
            throws Exception{
        ResponseVo reVo = new ResponseVo();
        reVo.setResult("");
        String token = request.getParameter("token");

        LoginInfo loginInfo = null;
        String pubKey = "";
        try {
//			pubKey = callCasPubKeyService;
            //TO DO get CasPubKey begin
            Map<String, String> map = new HashMap<String, String>();

            String pubKeyResponse = com.digital.dance.framework.infrastructure.commons.HttpClientUtil.executePostRequest(ssologinManageHelper.getLoginServiceUrl()+"/pubkey", new HashMap<String, String>(), map);

            com.digital.dance.framework.infrastructure.commons.ResponseVo pubKeyResponseVo = (com.digital.dance.framework.infrastructure.commons.ResponseVo) JSONUtils.toObject(pubKeyResponse, com.digital.dance.framework.infrastructure.commons.ResponseVo.class);
            pubKey = pubKeyResponseVo.getResult().toString();
            //TO DO get CasPubKey end
        } catch (Exception e1) {
            reVo.setCode(Constants.RETURN_CODE_FAILED);
            reVo.setResult(e1.getMessage());
            log.error(e1.getMessage(), e1);
//            throw new BizException("get pubKey error.", e1);
        }
        try {
            com.digital.dance.framework.infrastructure.commons.ResponseVo responseVo = new com.digital.dance.framework.infrastructure.commons.ResponseVo();
            if( StringUtils.isNotBlank( token ) ){

                //token = URLDecoder.decode(token, "UTF-8");

                //TO DO validationToken begin
                Map<String, String> pMap = new HashMap<String, String>();
                pMap.put("token", token);
                String validationToken = com.digital.dance.framework.infrastructure.commons.HttpClientUtil.executePostRequest(ssologinManageHelper.getLoginServiceUrl()+"/token/validation", new HashMap<String, String>(), pMap);
                responseVo = (com.digital.dance.framework.infrastructure.commons.ResponseVo) JSONUtils.toObject(validationToken, com.digital.dance.framework.infrastructure.commons.ResponseVo.class);
                //TO DO validationToken end
            }
            if( Constants.RETURN_CODE_SUCCESS.equals(responseVo.getCode()) ){

                String tokenJson = RSACoderUtil.decryptByPublicKey(pubKey, token);

                loginInfo = (LoginInfo) JSONUtils.toObject(tokenJson, LoginInfo.class);

                if ((loginInfo == null) || (loginInfo.getUserId() == null)) {
                    throw new BizException("decode token error.");
                }

                //TO DO save token to db token table begin
                Map<String, String> map = new HashMap<String, String>();
                map.put("token", token);
                String releaseToken = com.digital.dance.framework.infrastructure.commons.HttpClientUtil.executePostRequest(ssologinManageHelper.getLoginServiceUrl()+"/token/persistence", new HashMap<String, String>(), map);
                responseVo = (com.digital.dance.framework.infrastructure.commons.ResponseVo) JSONUtils.toObject(releaseToken, com.digital.dance.framework.infrastructure.commons.ResponseVo.class);
                //TO DO save token to db token table end
                if( Constants.RETURN_CODE_SUCCESS.equals(responseVo.getCode()) ){
                    SSOLoginFilter.setLoginInfo2Session(request, loginInfo);
                    LoginContext.setLoginInfo(loginInfo);

                    reVo.setCode(Constants.RETURN_CODE_SUCCESS);
                    reVo.setResult(loginInfo);
                }

            }
        } catch (Exception ex){
            reVo.setCode(Constants.RETURN_CODE_FAILED);
            reVo.setResult(ex.getMessage());
            log.error("sso client error.", ex);
//            throw new BizException("decode token error.");
        }
        return reVo;
    }

    @RequestMapping(value = "codis" )
    @ResponseBody
    public ResponseVo codis2Redis(HttpServletRequest request,
                                  HttpServletResponse response)
            throws Exception{
        ResponseVo reVo = new ResponseVo();
        reVo.setResult("");
        try
        {
            codis.set("codis", request.getParameter("value"));

            reVo.setResult(Constants.SUCCESS_MSG);
        } catch (Exception ex){
            reVo.setResult("exception");
            log.error("session", ex);
        }
        return reVo;
    }

    @RequestMapping(value = "codis/value" )
    @ResponseBody
    public ResponseVo codisFromRedis(HttpServletRequest request,
                                     HttpServletResponse response)
            throws Exception{
        ResponseVo reVo = new ResponseVo();
        reVo.setResult("");
        try
        {
            String ret = codis.get("codis", String.class);

            reVo.setResult(ret);
        } catch (Exception ex){
            reVo.setResult("exception");
            log.error("session", ex);
        }
        return reVo;
    }

    @RequestMapping(value = "token/validation", method = RequestMethod.POST )
    @ResponseBody
    public ResponseVo validatToken(HttpServletRequest request,
                                   HttpServletResponse response)
            throws Exception{
        ResponseVo reVo = new ResponseVo();
        reVo.setResult("");
        try
        {
            String token = request.getParameter("token");
            Integer ret = userService.validatToken(token);
            if( ret != null && ret < 1 )
                reVo.setCode(Constants.RETURN_CODE_SUCCESS);
            reVo.setResult(ret);
        } catch (Exception ex){
            reVo.setResult("exception");
            log.error("/token/validation", ex);
        }
        return reVo;
    }

    @RequestMapping(value = "token/persistence", method = RequestMethod.POST )
    @ResponseBody
    public ResponseVo saveToken(HttpServletRequest request,
                                HttpServletResponse response)
            throws Exception{
        ResponseVo reVo = new ResponseVo();
        reVo.setResult("");
        try
        {
            String token = request.getParameter("token");
            Integer ret = userService.saveToken(token);
            reVo.setCode(Constants.RETURN_CODE_SUCCESS);
            reVo.setResult(ret);
        } catch (Exception ex){
            reVo.setResult("exception");
            log.error("/token/persistence", ex);
        }
        return reVo;
    }
}
