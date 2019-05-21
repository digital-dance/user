package com.digital.dance.home.role;

import java.util.ArrayList;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.digital.dance.user.commons.ResponseVo;

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

import com.digital.dance.bo.SystemRoleBO;

import com.digital.dance.user.commons.Constants;

import com.digital.dance.service.SystemRoleService;

import com.digital.dance.vo.SystemRoleVO;


/**
 * 
 * @author liuxy
 *
 */
@Controller
@RequestMapping("/role")
public class RoleController
{    
    @Autowired
    private SystemRoleService systemRoleService;
       
    Log log = new Log(RoleController.class);
    
    /**
     * add a System Role
     */
    @RequestMapping(value="/rolevo", method=RequestMethod.POST)
    @ResponseBody
    public ResponseVo addSystemRole(HttpServletRequest request,
            HttpServletResponse response, @RequestBody SystemRoleVO systemRoleVo)
            throws Exception
    {
    	ResponseVo reVo = new ResponseVo();
        Integer ret = 0;
        try
        {
        	SystemRoleBO systemRoleBo= new SystemRoleBO();
        	BeanUtils.copyProperties(systemRoleVo, systemRoleBo);
        	String roleId = PrimaryKeyGenerator.generatePrimaryKey("user_role");
        	systemRoleBo.setRoleId(roleId);
        	
        	ret = systemRoleService.addSystemRole(systemRoleBo);
        }
        catch (Exception e)
        {
        	log.error(RoleController.class.getName() + ".addSystemRole:", e);
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
     * find SystemRole By RoleName
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("roleName")
    @ResponseBody
    public ResponseVo findSystemRoleByRoleName(HttpServletRequest request,
            HttpServletResponse response, String roleName) throws Exception
    {
    	ResponseVo reVo = new ResponseVo();
        reVo.setResult("");
        
        try
        {
        	SystemRoleBO systemRoleBo = systemRoleService.findSystemRoleByRoleName(roleName);
        	if(systemRoleBo != null) {
	        	SystemRoleVO systemRoleVO = new SystemRoleVO();
	        	BeanUtils.copyProperties(systemRoleBo, systemRoleVO);
	        	reVo.setResult(systemRoleVO);
        	}
        }
        catch (Exception e)
        {
        	log.error(RoleController.class.getName() + ".findSystemRoleByRoleName:", e);
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
     * find SystemRole By RoleName
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("roleId")
    @ResponseBody
    public ResponseVo findSystemRoleByRoleId(HttpServletRequest request,
            HttpServletResponse response, String roleId) throws Exception
    {
    	ResponseVo reVo = new ResponseVo();
        reVo.setResult("");
        
        try
        {
        	SystemRoleBO systemRoleBo = systemRoleService.findSystemRoleByRoleId(roleId);
        	if(systemRoleBo != null) {
	        	SystemRoleVO systemRoleVO = new SystemRoleVO();
	        	BeanUtils.copyProperties(systemRoleBo, systemRoleVO);
	        	reVo.setResult(systemRoleVO);
        	}
        }
        catch (Exception e)
        {
        	log.error(RoleController.class.getName() + ".findSystemRoleByRoleId:", e);
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
     * find SystemRole By RoleName
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("allroles")
    @ResponseBody
    public ResponseVo findAllSystemRoles(HttpServletRequest request,
            HttpServletResponse response,  @ModelAttribute SystemRoleVO systemRoleVo) throws Exception
    {
    	ResponseVo reVo = new ResponseVo();
        reVo.setResult("");
        
        try
        {
        	SystemRoleBO systemRoleBo= new SystemRoleBO();
        	BeanUtils.copyProperties(systemRoleVo, systemRoleBo);
        	List<SystemRoleBO> systemRoleBos = systemRoleService.findAllSystemRoles(systemRoleBo);
        	
        	List<SystemRoleVO> systemRoleVos = new ArrayList<SystemRoleVO>();
    		for(SystemRoleBO ite : systemRoleBos){
    			SystemRoleVO vo = new SystemRoleVO();
    			BeanUtils.copyProperties(ite, vo);
    			systemRoleVos.add(vo);
    		}
        	
        	reVo.setResult(systemRoleVos);
        }
        catch (Exception e)
        {
        	log.error(RoleController.class.getName() + ".findAllSystemRoles:", e);
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
     * find SystemRole By RoleName
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("paged/roles")
    @ResponseBody
    public ResponseVo findPagedSystemRoles(HttpServletRequest request,
            HttpServletResponse response,  @ModelAttribute SystemRoleVO systemRoleVo) throws Exception
    {
    	ResponseVo reVo = new ResponseVo();
        reVo.setResult("");
        
        try
        {
        	SystemRoleBO systemRoleBo= new SystemRoleBO();
        	BeanUtils.copyProperties(systemRoleVo, systemRoleBo);
        	List<SystemRoleBO> systemRoleBos = systemRoleService.findPagedSystemRoles(systemRoleBo);
        	
        	List<SystemRoleVO> systemRoleVos = new ArrayList<SystemRoleVO>();
    		for(SystemRoleBO ite : systemRoleBos){
    			SystemRoleVO vo = new SystemRoleVO();
    			BeanUtils.copyProperties(ite, vo);
    			systemRoleVos.add(vo);
    		}
        	
        	reVo.setResult(systemRoleVos);
        }
        catch (Exception e)
        {
        	log.error(RoleController.class.getName() + ".findPagedSystemRoles:", e);
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
     * find SystemRole By RoleName
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("allsimilarroles")
    @ResponseBody
    public ResponseVo searchAllSystemRoles(HttpServletRequest request,
            HttpServletResponse response,  @ModelAttribute SystemRoleVO systemRoleVo) throws Exception
    {
    	ResponseVo reVo = new ResponseVo();
        reVo.setResult("");
        
        try
        {
        	SystemRoleBO systemRoleBo= new SystemRoleBO();
        	BeanUtils.copyProperties(systemRoleVo, systemRoleBo);
        	List<SystemRoleBO> systemRoleBos = systemRoleService.searchAllSystemRoles(systemRoleBo);
        	
        	List<SystemRoleVO> systemRoleVos = new ArrayList<SystemRoleVO>();
    		for(SystemRoleBO ite : systemRoleBos){
    			SystemRoleVO vo = new SystemRoleVO();
    			BeanUtils.copyProperties(ite, vo);
    			systemRoleVos.add(vo);
    		}
        	
        	reVo.setResult(systemRoleVos);
        }
        catch (Exception e)
        {
        	log.error(RoleController.class.getName() + ".searchAllSystemRoles:", e);
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
     * find SystemRole By RoleName
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("paged/similarroles")
    @ResponseBody
    public ResponseVo searchPagedSystemRoles(HttpServletRequest request,
            HttpServletResponse response,  @ModelAttribute SystemRoleVO systemRoleVo) throws Exception
    {
    	ResponseVo reVo = new ResponseVo();
        reVo.setResult("");
        
        try
        {
        	SystemRoleBO systemRoleBo= new SystemRoleBO();
        	BeanUtils.copyProperties(systemRoleVo, systemRoleBo);
        	List<SystemRoleBO> systemRoleBos = systemRoleService.searchPagedSystemRoles(systemRoleBo);
        	
        	List<SystemRoleVO> systemRoleVos = new ArrayList<SystemRoleVO>();
    		for(SystemRoleBO ite : systemRoleBos){
    			SystemRoleVO vo = new SystemRoleVO();
    			BeanUtils.copyProperties(ite, vo);
    			systemRoleVos.add(vo);
    		}
        	
        	reVo.setResult(systemRoleVos);
        }
        catch (Exception e)
        {
        	log.error(RoleController.class.getName() + ".searchPagedSystemRoles:", e);
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
    public ResponseVo deleteSystemRoles(HttpServletRequest request,
            HttpServletResponse response,  @RequestBody List<SystemRoleVO> systemRoleVos) throws Exception{
    	ResponseVo reVo = new ResponseVo();
    	Integer ret = 0;
        
        try {
			List<SystemRoleBO> systemRoleBos = new ArrayList<SystemRoleBO>();
			for(SystemRoleVO item : systemRoleVos) {
				SystemRoleBO systemRoleBO = new SystemRoleBO();
				BeanUtils.copyProperties(item, systemRoleBO);
				systemRoleBos.add(systemRoleBO);
			}
	    	ret = systemRoleService.deleteSystemRoles(systemRoleBos);
	        reVo.setResult(ret);
		} catch (Exception e) {
			log.error(RoleController.class.getName() + ".deleteSystemRoles:" + e);
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
    @RequestMapping(value="vos", method=RequestMethod.PUT)
    @ResponseBody
    public ResponseVo updateSystemRoles(HttpServletRequest request,
            HttpServletResponse response,  @RequestBody List<SystemRoleVO> systemRoleVos) throws Exception{
    	ResponseVo reVo = new ResponseVo();
    	Integer ret = 0;
        
        try {
			List<SystemRoleBO> systemRoleBos = new ArrayList<SystemRoleBO>();
			for(SystemRoleVO item : systemRoleVos) {
				SystemRoleBO systemRoleBo = new SystemRoleBO();
				BeanUtils.copyProperties(item, systemRoleBo);
				systemRoleBos.add(systemRoleBo);
			}
	        ret = systemRoleService.updateSystemRoles(systemRoleBos);
		} catch (Exception e) {
			log.error(RoleController.class.getName() + ".updateSystemRoles:" + e);
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
}
