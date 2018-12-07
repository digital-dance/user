package com.digital.dance.home.userCategory;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.digital.dance.bo.UserBO;
import com.digital.dance.bo.UserCategoryBO;
import com.digital.dance.home.user.UserController;
import com.digital.dance.service.UserCategoryService;
import com.digital.dance.service.UserService;
import com.digital.dance.user.commons.BeanUtils;
import com.digital.dance.user.commons.Constants;
import com.digital.dance.user.commons.Log;
import com.digital.dance.user.commons.PrimaryKeyGenerator;
import com.digital.dance.user.commons.ResponseVo;
import com.digital.dance.vo.UserCategoryVO;

@Controller
@RequestMapping("/userCategory")
public class UserCategoryController {
    
    @Autowired
    private UserCategoryService userCategoryService;
       
    Log log = new Log(UserCategoryController.class);
    
    /**
     * UserCategory manager
     */
    @RequestMapping("/vo")
    @ResponseBody
    public ResponseVo addUserCategory(HttpServletRequest request,
            HttpServletResponse response, @RequestBody UserCategoryVO userCategoryVO)
            throws Exception
    {
        ResponseVo reVo = new ResponseVo();
        Integer ret = 0;
        try
        {
        	UserCategoryBO userCategoryBO= new UserCategoryBO();
            if(userCategoryVO!= null){
            	BeanUtils.copyProperties(userCategoryVO, userCategoryBO);
            	String categoryId = PrimaryKeyGenerator.generatePrimaryKey("user_category");
            	userCategoryBO.setCategoryId(categoryId);
            	ret=userCategoryService.addUserCategory(userCategoryBO);
            }
        	
        }
        catch (Exception e)
        {
        	log.error(UserCategoryController.class.getName()+".addUserCategory", e);
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
     * find UserCategory by categotyName
     * 
     * @param request
     * @param response
     * @return user info in ResponseVo json object
     * @throws Exception
     */
    @RequestMapping("categoryName")
    @ResponseBody
    public ResponseVo findUserCategoryByUserName(HttpServletRequest request,
            HttpServletResponse response, String categoryName) throws Exception
    {
    	ResponseVo reVo = new ResponseVo();
        reVo.setResult("");
        
        try
        {
        	UserCategoryBO userCategoryBO=userCategoryService.findUserCategoryByName(categoryName);
            if(userCategoryBO != null){
            	UserCategoryVO userCategoryVO=new UserCategoryVO();
            	BeanUtils.copyProperties(userCategoryBO, userCategoryVO);
            	reVo.setResult(userCategoryVO);
            }
        }
        catch (Exception e)
        {
        	log.error(UserCategoryController.class.getName() + ".findUserCategoryByName:", e);
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
     * find UserCategory by categoryId
     * 
     * @param request
     * @param response
     * @return user info in ResponseVo json object
     * @throws Exception
     */
    @RequestMapping("categoryId")
    @ResponseBody
    public ResponseVo findUserCategoryByUserId(HttpServletRequest request,
            HttpServletResponse response, String categoryId) throws Exception
    {
    	ResponseVo reVo = new ResponseVo();
        reVo.setResult("");
        try
        {
        	UserCategoryBO userCategoryBO=userCategoryService.findUserCategoryById(categoryId);
            if(userCategoryBO != null){
            	UserCategoryVO userCategoryVO=new UserCategoryVO();
            	BeanUtils.copyProperties(userCategoryBO, userCategoryVO);
            	reVo.setResult(userCategoryVO);
            }
        }
        catch (Exception e)
        {
        	log.error(UserController.class.getName() + ".findUserCategoryById:", e);
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
     * find all UserCategories
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("allusercategories")
    @ResponseBody
    public ResponseVo findAllUsers(HttpServletRequest request,
            HttpServletResponse response,  @ModelAttribute UserCategoryVO userCategoryVO) throws Exception
    {
    	ResponseVo reVo = new ResponseVo();
        reVo.setResult("");
        
        try
        {
        	UserCategoryBO userCategoryBO= new UserCategoryBO();
        	BeanUtils.copyProperties(userCategoryVO, userCategoryBO);
        	List<UserCategoryBO> userCategoryBOs = userCategoryService.searchUserCategorys(userCategoryBO);
        	
        	List<UserCategoryVO> userCategoryVOs = new ArrayList<UserCategoryVO>();
    		for(UserCategoryBO ite : userCategoryBOs){
    			UserCategoryVO vo = new UserCategoryVO();
    			BeanUtils.copyProperties(ite, vo);
    			userCategoryVOs.add(vo);
    		}
        	
        	reVo.setResult(userCategoryVOs);
        }
        catch (Exception e)
        {
        	log.error(UserCategoryController.class.getName() + ".searchUserCategorys:", e);
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
    
    @RequestMapping(value="vos", method=RequestMethod.DELETE)
    @ResponseBody
    public ResponseVo deleteUserCategories(HttpServletRequest request,
            HttpServletResponse response,  @RequestBody List<UserCategoryVO> userCategoryVOs) throws Exception{
    	ResponseVo reVo = new ResponseVo();
        reVo.setResult("");
        Integer ret = 0;
        //TODO delete Users
        try {
			if(userCategoryVOs != null){
				List<UserCategoryBO> userCategoryBOs = new ArrayList<UserCategoryBO>();
				for(UserCategoryVO ite : userCategoryVOs){
					UserCategoryBO bo = new UserCategoryBO();
					BeanUtils.copyProperties(ite, bo);
					userCategoryBOs.add(bo);
				}
				ret = userCategoryService.deleteUserCategorys(userCategoryBOs);
				reVo.setResult(ret);
			}
		} catch (Exception e) {
			log.error(UserController.class.getName() + ".deleteUserCategorys:" + e);
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
     * find Paged UserCategories
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("paged/usercategories")
    @ResponseBody
    public ResponseVo findPagedUsers(HttpServletRequest request,
            HttpServletResponse response,  @ModelAttribute UserCategoryVO userCategoryVO) throws Exception
    {
    	ResponseVo reVo = new ResponseVo();
        reVo.setResult("");
        
        try
        {
        	UserCategoryBO userCategoryBO= new UserCategoryBO();
        	BeanUtils.copyProperties(userCategoryVO, userCategoryBO);
        	List<UserCategoryBO> userCategoryBOs = userCategoryService.searchPagedUserCategorys(userCategoryBO);
        	
        	List<UserCategoryVO> userCategoryVOs = new ArrayList<UserCategoryVO>();
    		for(UserCategoryBO ite : userCategoryBOs){
    			UserCategoryVO vo = new UserCategoryVO();
    			BeanUtils.copyProperties(ite, vo);
    			userCategoryVOs.add(vo);
    		}
        	
        	reVo.setResult(userCategoryVOs);
        }
        catch (Exception e)
        {
        	log.error(UserController.class.getName() + ".searchPagedUserCategorys:", e);
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
    


}
