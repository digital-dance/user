package com.digital.dance.home.user;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.digital.dance.user.commons.Log;
import com.digital.dance.user.commons.PrimaryKeyGenerator;
import com.digital.dance.user.commons.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.ResponseBody;

import com.digital.dance.bo.UserBO;

import com.digital.dance.user.commons.Constants;

import com.digital.dance.user.commons.ResponseVo;
import com.digital.dance.user.commons.StringTools;
import com.digital.dance.service.UserService;

import com.digital.dance.vo.UserVO;


/**
 * 
 * @author liwy
 *
 */
@Controller
@RequestMapping("/user")
public class UserController
{
    
    @Autowired
    private UserService userService;
       
    Log log = new Log(UserController.class);
    
    /**
     * user manager
     */
    @RequestMapping("/register")
    @ResponseBody
    public ResponseVo addUser(HttpServletRequest request,
            HttpServletResponse response, @RequestBody UserVO userVo)
            throws Exception
    {
        ResponseVo reVo = new ResponseVo();
        Integer ret = 0;
        try
        {
        	UserBO userBO= new UserBO();
            if(userVo != null){
            	if(StringTools.isEmpty(userVo.getUserName())){
            		throw new Exception("userName can not be null or empty.");
            	}
            	BeanUtils.copyProperties(userVo, userBO);
            	String userId = PrimaryKeyGenerator.generatePrimaryKey("system_user");
            	userBO.setUserId(userId);
            	ret = userService.addUser(userBO);
            }
        	
        }
        catch (Exception e)
        {
        	log.error(UserController.class.getName(), e);
            e.printStackTrace();
            reVo.setCode(Constants.RETURN_CODE_FAILED);
            reVo.setMsg(e.getMessage());
            reVo.setResult(null);
            return reVo;
        }
        reVo.setCode(Constants.RETURN_CODE_SUCCESS);
        reVo.setMsg(Constants.SUCCESS_MSG);
        reVo.setResult(ret);
        return reVo;
        
    }

    /**
     * find user by userName
     * 
     * @param request
     * @param response
     * @return user info in ResponseVo json object
     * @throws Exception
     */
    @RequestMapping("userName")
    @ResponseBody
    public ResponseVo findUserByUserName(HttpServletRequest request,
            HttpServletResponse response, String userName) throws Exception
    {
    	ResponseVo reVo = new ResponseVo();
        reVo.setResult("");
        
        try
        {
        	UserBO userBO = userService.findUserByUserName(userName);
            if(userBO != null){
            	UserVO userVO = new UserVO();
            	BeanUtils.copyProperties(userBO, userVO);
            	reVo.setResult(userVO);
            }
        }
        catch (Exception e)
        {
        	log.error(UserController.class.getName() + ".findUserByUserName:", e);
            e.printStackTrace();
            reVo.setCode(Constants.RETURN_CODE_FAILED);
            reVo.setMsg(e.getMessage());
            reVo.setResult(null);
            return reVo;
        }
        reVo.setCode(Constants.RETURN_CODE_SUCCESS);
        reVo.setMsg(Constants.SUCCESS_MSG);
        
        return reVo;
    }
 

    /**
     * find User by userId
     * 
     * @param request
     * @param response
     * @return user info in ResponseVo json object
     * @throws Exception
     */
    @RequestMapping("userId")
    @ResponseBody
    public ResponseVo findUserByUserId(HttpServletRequest request,
            HttpServletResponse response, String userId) throws Exception
    {
    	ResponseVo reVo = new ResponseVo();
        reVo.setResult("");
        
        try
        {
        	UserBO userBO = userService.findUserByUserId(userId);
            if(userBO != null){
            	UserVO userVO = new UserVO();
            	BeanUtils.copyProperties(userBO, userVO);
            	reVo.setResult(userVO);
            }
        }
        catch (Exception e)
        {
        	log.error(UserController.class.getName() + ".findUserByUserId:", e);
            e.printStackTrace();
            reVo.setCode(Constants.RETURN_CODE_FAILED);
            reVo.setMsg(e.getMessage());
            reVo.setResult(null);
            return reVo;
        }
        reVo.setCode(Constants.RETURN_CODE_SUCCESS);
        reVo.setMsg(Constants.SUCCESS_MSG);
        
        return reVo;
    }
    /**
     * find User by userId
     *
     * @param request
     * @param response
     * @return user info in ResponseVo json object
     * @throws Exception
     */
    @RequestMapping("primarykey")
    @ResponseBody
    public ResponseVo generatePrimaryKey(HttpServletRequest request,
                                       HttpServletResponse response, String table) throws Exception
    {
        ResponseVo reVo = new ResponseVo();
        reVo.setResult("");

        try
        {
            String primaryKey = PrimaryKeyGenerator.generatePrimaryKey("sys_user");
            reVo.setResult(primaryKey);
        }
        catch (Exception e)
        {
            log.error(UserController.class.getName() + ".generatePrimaryKey:", e);
            e.printStackTrace();
            reVo.setCode(Constants.RETURN_CODE_FAILED);
            reVo.setMsg(e.getMessage());
            reVo.setResult(null);
            return reVo;
        }
        reVo.setCode(Constants.RETURN_CODE_SUCCESS);
        reVo.setMsg(Constants.SUCCESS_MSG);

        return reVo;
    }
    
    /**
     * find all Users
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("allusers")
    @ResponseBody
    public ResponseVo findAllUsers(HttpServletRequest request,
            HttpServletResponse response,  @ModelAttribute UserVO userVo) throws Exception
    {
    	ResponseVo reVo = new ResponseVo();
        reVo.setResult("");
        
        try
        {
        	UserBO userBo= new UserBO();
        	BeanUtils.copyProperties(userVo, userBo);
        	List<UserBO> userBos = userService.findAllUsers(userBo);
        	
        	List<UserVO> userVos = new ArrayList<UserVO>();
    		for(UserBO ite : userBos){
    			UserVO vo = new UserVO();
    			BeanUtils.copyProperties(ite, vo);
    			vo.setSessionId(request.getSession().getId());
    			userVos.add(vo);
    		}
        	
        	reVo.setResult(userVos);
        }
        catch (Exception e)
        {
        	log.error(UserController.class.getName() + ".findAllUsers:", e);
            e.printStackTrace();
            reVo.setCode(Constants.RETURN_CODE_FAILED);
            reVo.setMsg(e.getMessage());
            reVo.setResult(null);
            return reVo;
        }
        reVo.setCode(Constants.RETURN_CODE_SUCCESS);
        reVo.setMsg(Constants.SUCCESS_MSG);
        return reVo;
    }
    
    /**
     * find Paged Users
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("paged/users")
    @ResponseBody
    public ResponseVo findPagedUsers(HttpServletRequest request,
            HttpServletResponse response,  @ModelAttribute UserVO userVo) throws Exception
    {
    	ResponseVo reVo = new ResponseVo();
        reVo.setResult("");
        
        try
        {
        	UserBO userBo= new UserBO();
        	BeanUtils.copyProperties(userVo, userBo);
        	List<UserBO> userBos = userService.findPagedUsers(userBo);
        	
        	List<UserVO> userVos = new ArrayList<UserVO>();
    		for(UserBO ite : userBos){
    			UserVO vo = new UserVO();
    			BeanUtils.copyProperties(ite, vo);
    			userVos.add(vo);
    		}
        	
        	reVo.setResult(userVos);
        }
        catch (Exception e)
        {
        	log.error(UserController.class.getName() + ".findPagedUsers:", e);
            e.printStackTrace();
            reVo.setCode(Constants.RETURN_CODE_FAILED);
            reVo.setMsg(e.getMessage());
            reVo.setResult(null);
            return reVo;
        }
        reVo.setCode(Constants.RETURN_CODE_SUCCESS);
        reVo.setMsg(Constants.SUCCESS_MSG);
        return reVo;
    }
    
    /**
     * 
     * @param request
     * @param response
     * @param userVo
     * @return
     */
    @RequestMapping("allsimilarusers")
    @ResponseBody
	public ResponseVo searchAllUsers(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute UserVO userVo) {
		ResponseVo reVo = new ResponseVo();
		reVo.setResult("");
		
		try {
			UserBO userBo = new UserBO();
			BeanUtils.copyProperties(userVo, userBo);
			List<UserBO> userBos = userService.searchAllUsers(userBo);
			
			List<UserVO> userVos = new ArrayList<UserVO>();
			for(UserBO item : userBos) {
				UserVO userVoItem = new UserVO();
				BeanUtils.copyProperties(item, userVoItem);
				userVos.add(userVoItem);
			}
			reVo.setResult(userVos);
		} catch (Exception e) {
			log.error(UserController.class.getName() + ".searchAllUsers:" + e);
			e.printStackTrace();
			reVo.setCode(Constants.RETURN_CODE_FAILED);
			reVo.setMsg(e.getMessage());
			reVo.setResult(null);
			return reVo;
		}
		
		reVo.setCode(Constants.RETURN_CODE_SUCCESS);	
		reVo.setMsg(Constants.SUCCESS_MSG);
		return reVo;
	}
    
    /**
     * 
     * @param request
     * @param response
     * @param userVo
     * @return
     */
    @RequestMapping("paged/similarusers")
    @ResponseBody
	public ResponseVo searchPagedUsers(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute UserVO userVo) {
		ResponseVo reVo = new ResponseVo();
		reVo.setResult("");
		
		try {
			UserBO userBo = new UserBO();
			BeanUtils.copyProperties(userVo, userBo);
			List<UserBO> userBos = userService.searchPagedUsers(userBo);
			
			List<UserVO> userVos = new ArrayList<UserVO>();
			for(UserBO item : userBos) {
				UserVO userVoItem = new UserVO();
				BeanUtils.copyProperties(item, userVoItem);
				userVos.add(userVoItem);
			}
			reVo.setResult(userVos);
		} catch (Exception e) {
			log.error(UserController.class.getName() + ".searchPagedUsers:" + e);
			e.printStackTrace();
			reVo.setCode(Constants.RETURN_CODE_FAILED);
			reVo.setMsg(e.getMessage());
			reVo.setResult(null);
			return reVo;
		}
		
		reVo.setCode(Constants.RETURN_CODE_SUCCESS);	
		reVo.setMsg(Constants.SUCCESS_MSG);
		return reVo;
	}
    
    /**
     * delete users
     * @param request
     * @param response
     * @param userVos
     * @return
     * @throws Exception
     */
    @RequestMapping(value="vos", method=RequestMethod.DELETE)
    @ResponseBody
    public ResponseVo deleteUsers(HttpServletRequest request,
            HttpServletResponse response,  @RequestBody List<UserVO> userVos) throws Exception{
    	ResponseVo reVo = new ResponseVo();
        reVo.setResult("");
        Integer ret = 0;
        //TODO delete Users
        try {
			if(userVos != null){
				List<UserBO> userBos = new ArrayList<UserBO>();
				for(UserVO ite : userVos){
					UserBO bo = new UserBO();
					BeanUtils.copyProperties(ite, bo);
					userBos.add(bo);
				}
				ret = userService.deleteUsers(userBos);
				reVo.setResult(ret);
			}
		} catch (Exception e) {
			log.error(UserController.class.getName() + ".deleteUsers:" + e);
			e.printStackTrace();
			reVo.setCode(Constants.RETURN_CODE_FAILED);
			reVo.setMsg(Constants.FAILED_MSG);
			reVo.setResult(null);
			return reVo;
		}
        
        reVo.setCode(Constants.RETURN_CODE_SUCCESS);
        reVo.setMsg(Constants.SUCCESS_MSG);
        return reVo;
        
    }
    
    /**
     * update users
     * @param request
     * @param response
     * @param userVos
     * @return
     * @throws Exception
     */
    @RequestMapping(value="vos", method=RequestMethod.PUT)
    @ResponseBody
    public ResponseVo updateUsers(HttpServletRequest request,
            HttpServletResponse response, @RequestBody List<UserVO> userVos) throws Exception{
    	ResponseVo reVo = new ResponseVo();
        reVo.setResult("");
        Integer ret = 0;
        
        try {
			if(userVos != null){
				List<UserBO> userBos = new ArrayList<UserBO>();
				for(UserVO ite : userVos){
					UserBO bo = new UserBO();
					BeanUtils.copyProperties(ite, bo);
					userBos.add(bo);
				}
				ret = userService.updateUsers(userBos);
				reVo.setResult(ret);
			}
		} catch (Exception e) {
			log.error(UserController.class.getName() + ".updateUsers:" + e);
			e.printStackTrace();
			reVo.setCode(Constants.RETURN_CODE_FAILED);
			reVo.setMsg(Constants.FAILED_MSG);
			reVo.setResult(null);
			return reVo;
		}
        
        reVo.setCode(Constants.RETURN_CODE_SUCCESS);
        reVo.setMsg(Constants.SUCCESS_MSG);
        return reVo;
        
    }

}
