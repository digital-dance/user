package com.digital.dance.home.userrole;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.digital.dance.user.commons.Log;
import com.digital.dance.bo.SystemUserRoleBO;
import com.digital.dance.service.SystemUserRoleService;
import com.digital.dance.user.commons.Constants;
import com.digital.dance.user.commons.ResponseVo;
import com.digital.dance.vo.SystemUserRoleVO;

/**
 * @author liuxy
 */
@Controller
@RequestMapping("/userrole")
public class UserRoleController {
	
	private Log Log = new Log(UserRoleController.class);
	
	@Autowired
	private SystemUserRoleService systemUserRoleService;
	
	/**
	 * add a system user role
	 * @param request
	 * @param response
	 * @param systemUserRoleVo
	 * @return
	 */
    @RequestMapping(value="vo")
    @ResponseBody
	public ResponseVo addSystemUserRole(HttpServletRequest request,
            HttpServletResponse response,  @RequestBody SystemUserRoleVO systemUserRoleVo) throws Exception{
    	ResponseVo reVo = new ResponseVo();
    	Integer ret = 0;
       
        try {
			SystemUserRoleBO systemUserRoleBo = new SystemUserRoleBO();
			BeanUtils.copyProperties(systemUserRoleVo, systemUserRoleBo);
			ret = systemUserRoleService.addSystemUserRole(systemUserRoleBo);
		} catch (Exception e) {
			Log.error(SystemUserRoleService.class.getName() + ".addSystemUserRole:", e);
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
	 * add system user roles
	 * @param request
	 * @param response
	 * @param systemUserRoleVos
	 * @return
	 */
    @RequestMapping(value="vos", method=RequestMethod.POST)
    @ResponseBody
	public ResponseVo addSystemUserRoles(HttpServletRequest request,
            HttpServletResponse response,  @RequestBody List<SystemUserRoleVO> systemUserRoleVos) throws Exception{
    	ResponseVo reVo = new ResponseVo();
    	Integer ret = 0;
    	
    	try {
			List<SystemUserRoleBO> systemUserRoleBos = new ArrayList<SystemUserRoleBO>();
			for(SystemUserRoleVO item : systemUserRoleVos) {
				SystemUserRoleBO systemUserRoleBo = new SystemUserRoleBO();
				BeanUtils.copyProperties(item, systemUserRoleBo);
				systemUserRoleBos.add(systemUserRoleBo);
			}
			ret = systemUserRoleService.addSystemUserRoles(systemUserRoleBos);
		} catch (Exception e) {
			Log.error(UserRoleController.class.getName() + ".addSystemUserRoles:", e);
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
 * find SystemUserRole By UserId
 * @param request
 * @param response
 * @param userId
 * @return
 * @throws Exception
 */
    @RequestMapping("userId")
    @ResponseBody
	public ResponseVo findSystemUserRoleByUserId(HttpServletRequest request,
            HttpServletResponse response, String userId) throws Exception{
    	ResponseVo reVo = new ResponseVo();
        reVo.setResult("");
        
        try {
		    SystemUserRoleBO systemUserRoleBo = systemUserRoleService.findSystemUserRoleByUserId(userId);
		    if(systemUserRoleBo != null) {
		    	SystemUserRoleVO systemUserRoleVo = new SystemUserRoleVO();
		    	BeanUtils.copyProperties(systemUserRoleBo, systemUserRoleVo);
		    	reVo.setResult(systemUserRoleVo);
		    }
		} catch (Exception e) {
			Log.error(UserRoleController.class.getName() + ".findSystemUserRoleByUserId:", e);
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
 * find SystemUserRole By RoleId
 * @param request
 * @param response
 * @param roleId
 * @return
 * @throws Exception
 */
    @RequestMapping("roleId")
    @ResponseBody
	public ResponseVo findSystemUserRoleByRoleId(HttpServletRequest request,
            HttpServletResponse response, String roleId) throws Exception{
    	ResponseVo reVo = new ResponseVo();
        reVo.setResult("");
        
        try {
			SystemUserRoleBO systemUserRoleBO = systemUserRoleService.findSystemUserRoleByRoleId(roleId);
			if(systemUserRoleBO != null) {
				SystemUserRoleVO systemUserRoleVo = new SystemUserRoleVO();
				BeanUtils.copyProperties(systemUserRoleBO, systemUserRoleVo);
				reVo.setResult(systemUserRoleVo);
			}
		} catch (Exception e) {
			Log.error(UserRoleController.class.getName() + ".findSystemUserRoleByRoleId:", e);
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
 * delete SystemUserRoles
 * @param request
 * @param response
 * @param systemUserRoleVos
 * @return
 * @throws Exception
 */
    @RequestMapping(value="vos", method=RequestMethod.DELETE)
    @ResponseBody
	public ResponseVo deleteSystemUserRoles(HttpServletRequest request,
            HttpServletResponse response,  @RequestBody List<SystemUserRoleVO> systemUserRoleVos) throws Exception{
    	ResponseVo reVo = new ResponseVo();
        Integer ret = 0;
        
        try {
			List<SystemUserRoleBO> systemUserRoleBos = new ArrayList<SystemUserRoleBO>();
			for(SystemUserRoleVO item : systemUserRoleVos) {
				SystemUserRoleBO systemUserRoleBo = new SystemUserRoleBO();
				BeanUtils.copyProperties(item, systemUserRoleBo);
				systemUserRoleBos.add(systemUserRoleBo);
			}
			ret = systemUserRoleService.deleteSystemUserRoles(systemUserRoleBos);
		} catch (Exception e) {
			Log.error(UserRoleController.class.getName() + ".deleteSystemUserRoles:", e);
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
 * update SystemUserRoles
 * @param request
 * @param response
 * @param systemUserRoleVos
 * @return
 * @throws Exception
 */
    @RequestMapping(value="vos", method=RequestMethod.PUT)
    @ResponseBody
	public ResponseVo updateSystemUserRoles(HttpServletRequest request,
            HttpServletResponse response,  @RequestBody List<SystemUserRoleVO> systemUserRoleVos) throws Exception{
    	ResponseVo reVo = new ResponseVo();
        Integer ret = 0;
        
        try {
			List<SystemUserRoleBO> systemUserRoleBos = new ArrayList<SystemUserRoleBO>();
			for(SystemUserRoleVO item : systemUserRoleVos) {
				SystemUserRoleBO systemUserRoleBo = new SystemUserRoleBO();
				BeanUtils.copyProperties(item, systemUserRoleBo);
				systemUserRoleBos.add(systemUserRoleBo);
			}
			ret = systemUserRoleService.updateSystemUserRoles(systemUserRoleBos);
		} catch (Exception e) {
			Log.error(UserRoleController.class.getName() + ".updateSystemUserRoles:", e);
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
	 * find Paged SystemUserRoles
	 * @param request
	 * @param response
	 * @param systemUserRoleVo
	 * @return
	 * @throws Exception
	 */
    @RequestMapping("paged/userroles")
    @ResponseBody
	public ResponseVo findPagedSystemUserRoles(HttpServletRequest request,
            HttpServletResponse response,  @ModelAttribute SystemUserRoleVO systemUserRoleVo) throws Exception{
    	ResponseVo reVo = new ResponseVo();
        reVo.setResult("");
        
        try {
			SystemUserRoleBO systemUserRoleBo = new SystemUserRoleBO();
			BeanUtils.copyProperties(systemUserRoleVo, systemUserRoleBo);
			
			List<SystemUserRoleBO> systemUserRoleBos = systemUserRoleService.findPagedSystemUserRoles(systemUserRoleBo);
			if(systemUserRoleBos != null) {
				List<SystemUserRoleVO> systemUserRoleVos = new ArrayList<SystemUserRoleVO>();
				for(SystemUserRoleBO item : systemUserRoleBos) { 
					SystemUserRoleVO userRoleVo = new SystemUserRoleVO();
		    		BeanUtils.copyProperties(item, userRoleVo);
		    		systemUserRoleVos.add(userRoleVo);
				}
				reVo.setResult(systemUserRoleVos);
			}
		} catch (Exception e) {
			Log.error(UserRoleController.class.getName() + ".findPagedSystemUserRoles:", e);
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
	 * find all systemUserRoles
	 * @param request
	 * @param response
	 * @param systemUserRoleVo
	 * @return
	 * @throws Exception
	 */    
   @RequestMapping("alluserroles")
   @ResponseBody
	public ResponseVo findAllSystemUserRoles(HttpServletRequest request,
            HttpServletResponse response,  @ModelAttribute SystemUserRoleVO systemUserRoleVo) throws Exception{
    	ResponseVo reVo = new ResponseVo();
        reVo.setResult("");
        
        try {
			SystemUserRoleBO systemUserRoleBo = new SystemUserRoleBO();
			BeanUtils.copyProperties(systemUserRoleVo, systemUserRoleBo);
			
			List<SystemUserRoleBO> systemUserRoleBos = systemUserRoleService.findAllSystemUserRoles(systemUserRoleBo);
			if(systemUserRoleBos != null) {
				List<SystemUserRoleVO> systemUserRoleVos = new ArrayList<SystemUserRoleVO>();
				for(SystemUserRoleBO item : systemUserRoleBos) { 
					SystemUserRoleVO userRoleVo = new SystemUserRoleVO();
		    		BeanUtils.copyProperties(item, userRoleVo);
		    		systemUserRoleVos.add(userRoleVo);
				}
				reVo.setResult(systemUserRoleVos);
			}
		} catch (Exception e) {
			Log.error(UserRoleController.class.getName() + ".findAllSystemUserRoles:", e);
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
	 * find Paged SystemUserRoles
	 * @param request
	 * @param response
	 * @param systemUserRoleVo
	 * @return
	 * @throws Exception
	 */
   @RequestMapping("paged/similaruserroles")
   @ResponseBody
	public ResponseVo searchPagedSystemUserRoles(HttpServletRequest request,
           HttpServletResponse response,  @ModelAttribute SystemUserRoleVO systemUserRoleVo) throws Exception{
   	   ResponseVo reVo = new ResponseVo();
       reVo.setResult("");
       
       try {
			SystemUserRoleBO systemUserRoleBo = new SystemUserRoleBO();
			BeanUtils.copyProperties(systemUserRoleVo, systemUserRoleBo);
			
			List<SystemUserRoleBO> systemUserRoleBos = systemUserRoleService.searchPagedSystemUserRoles(systemUserRoleBo);
			if(systemUserRoleBos != null) {
				List<SystemUserRoleVO> systemUserRoleVos = new ArrayList<SystemUserRoleVO>();
				for(SystemUserRoleBO item : systemUserRoleBos) { 
					SystemUserRoleVO userRoleVo = new SystemUserRoleVO();
		    		BeanUtils.copyProperties(item, userRoleVo);
		    		systemUserRoleVos.add(userRoleVo);
				}
				reVo.setResult(systemUserRoleVos);
			}
		} catch (Exception e) {
			Log.error(UserRoleController.class.getName() + ".searchPagedSystemUserRoles:", e);
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
	 * find all systemUserRoles
	 * @param request
	 * @param response
	 * @param systemUserRoleVo
	 * @return
	 * @throws Exception
	 */    
  @RequestMapping("allsimilaruserroles")
  @ResponseBody
	public ResponseVo searchAllSystemUserRoles(HttpServletRequest request,
           HttpServletResponse response,  @ModelAttribute SystemUserRoleVO systemUserRoleVo) throws Exception{
   	ResponseVo reVo = new ResponseVo();
       reVo.setResult("");
       
       try {
			SystemUserRoleBO systemUserRoleBo = new SystemUserRoleBO();
			BeanUtils.copyProperties(systemUserRoleVo, systemUserRoleBo);
			
			List<SystemUserRoleBO> systemUserRoleBos = systemUserRoleService.searchAllSystemUserRoles(systemUserRoleBo);
			if(systemUserRoleBos != null) {
				List<SystemUserRoleVO> systemUserRoleVos = new ArrayList<SystemUserRoleVO>();
				for(SystemUserRoleBO item : systemUserRoleBos) { 
					SystemUserRoleVO userRoleVo = new SystemUserRoleVO();
		    		BeanUtils.copyProperties(item, userRoleVo);
		    		systemUserRoleVos.add(userRoleVo);
				}
				reVo.setResult(systemUserRoleVos);
			}
		} catch (Exception e) {
			Log.error(UserRoleController.class.getName() + ".searchAllSystemUserRoles:", e);
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
