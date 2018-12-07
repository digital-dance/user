package com.digital.dance.home;

import javax.servlet.http.HttpServletRequest;

import com.digital.dance.user.commons.AESEncryptUtil;
import com.digital.dance.user.commons.CookieUtil;
import com.digital.dance.user.commons.GsonUtils;
import com.digital.dance.user.commons.AppPropsConfig;
import com.digital.dance.vo.UserVO;
//import com.pay1pay.rsa.util.MD5Util;
import com.digital.dance.user.commons.Md5Util;

/**
 * 
 * 
 * @author  李武元
 * @version ECF-user v1.0 
 * @since   ECF-user v1.0
 */
public class BaseController
{
    public UserVO getUserVo(HttpServletRequest request)
    {
        try
        {
            /*return GsonUtils.getJson(new String(
                    AESEncryptUtil.aes(AESEncryptUtil.decodeHex(request.getSession()
                            .getAttribute(CookieUtil.SERVER_ROOM_USER)
                            .toString()),
                    		Md5Util.hashEncode(PropsConfig.getPropValue("COOKIE_KYE"), "UTF-8", "MD5", false)
                                    .substring(8, 24)
                                    .getBytes(),
                            Cipher.DECRYPT_MODE), "UTF-8"),
                    UserVO.class);*/
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
    
    public String getUserName(HttpServletRequest request)
    {
        try
        {
            /*return GsonUtils.getJson(new String(
                    AESEncryptUtil.aes(AESEncryptUtil.decodeHex(request.getSession()
                            .getAttribute(CookieUtil.SERVER_ROOM_USER)
                            .toString()),
                    		Md5Util.hashEncode(PropsConfig.getPropValue("COOKIE_KYE"), "UTF-8", "MD5", false)
                                    .substring(8, 24)
                                    .getBytes(),
                            Cipher.DECRYPT_MODE), "UTF-8"),
                    UserVO.class).getUserName();*/
            
            Boolean isfromSession = true;
            String userStr = CookieUtil.getCookieOrSessionUser(request, isfromSession);
            UserVO userVO = ( userStr != null && !userStr.equals("") ) ? GsonUtils.getJson( userStr, UserVO.class ) : null;
            return (userVO != null) ? userVO.getUserName() : "";
        }
        catch (Exception e)
        {
            // TODO log
            e.printStackTrace();
            return "";
        }
    }
}
