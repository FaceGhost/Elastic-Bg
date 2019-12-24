package com.faceghost.elasticbg.controller;


import com.faceghost.elasticbg.base.exception.BusiException;
import com.faceghost.elasticbg.base.model.SystemUser;
import com.faceghost.elasticbg.base.utils.ExceptionUtil;
import com.faceghost.elasticbg.base.utils.JsonUtil;
import com.faceghost.elasticbg.base.vo.ExtjsCheckTreeVo;
import com.faceghost.elasticbg.base.vo.FeignResultVo;
import com.faceghost.elasticbg.base.vo.PageVo;
import com.faceghost.elasticbg.base.vo.SystemUserVo;
import com.faceghost.elasticbg.service.SystemUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/systemUser")
public class SystemUserServiceController {

	@Autowired
	private SystemUserService systemUserService;
	
	/**
	 * 根据name获取bean
	 * 
	 * @param name
	 * @return
	 */
	 @RequestMapping(value = "/getSystemUserByUserName",method = RequestMethod.POST)
	 public FeignResultVo getSystemUserByUserName(@RequestParam("name") String name){
		 FeignResultVo R = FeignResultVo.initErr();
		 try {
			 SystemUser data = systemUserService.getSystemUserByUserName(name);
			 R = FeignResultVo.initSuc(JsonUtil.toJSON(data));
		 }catch (BusiException e){
			 R = FeignResultVo.initErr(e.getMessage());
		 }catch (Exception e){
			 log.error(ExceptionUtil.getExDetail(e));
		 }
		 return R;

	 }

	/**
	 * 登录成功或失败更新 
	 * 
	 * @param username
	 * @param status
	 * @param retryNumber
	 * @param lastRetryTime
	 * @return
	 */
	 @RequestMapping(value = "/updateByLogin",method = RequestMethod.POST)
	 public FeignResultVo updateByLogin(
			           @RequestParam("username") String username,
                       @RequestParam("status") int status,
                       @RequestParam("retryNumber") int retryNumber,
                       @RequestParam("lastRetryTime") Date lastRetryTime){
		 FeignResultVo R = FeignResultVo.initErr();
		  try {
			 Integer data =   systemUserService.updateByLogin(username,status,retryNumber,lastRetryTime);
			 R = FeignResultVo.initSuc(JsonUtil.toJSON(data));
		 }catch (BusiException e){
			 R = FeignResultVo.initErr(e.getMessage());
		 }catch (Exception e){
			 log.error(ExceptionUtil.getExDetail(e));
		 }
		 return R;


	 }

	/**
	 * 用户管理-分页显示
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/getSystemUserPageVo",method = RequestMethod.POST)
	public FeignResultVo getSystemUserPageVo(@RequestBody SystemUserVo searchVo){
		FeignResultVo R = FeignResultVo.initErr();
		try {
			PageVo data =  systemUserService.getSystemUserPageVo(searchVo);
			R = FeignResultVo.initSuc(JsonUtil.toJSON(data));
		}catch (BusiException e){
			R = FeignResultVo.initErr(e.getMessage());
		}catch (Exception e){
			log.error(ExceptionUtil.getExDetail(e));
		}
		return R;
	}

	/**
	 * 用户管理-获取角色
	 * @param orgId
	 * @param uid
	 * @return
	 */
	 @RequestMapping(value = "/getSystemUserRole",method = RequestMethod.POST)
	 public FeignResultVo getSystemUserRole(
				@RequestParam("orgId") Integer orgId,
				@RequestParam("uid") String uid){
		 FeignResultVo R = FeignResultVo.initErr();
		 try {
			 List<ExtjsCheckTreeVo> data =  systemUserService.getSystemUserRole(orgId,uid);
			 R = FeignResultVo.initSuc(JsonUtil.toJSON(data));
		 }catch (BusiException e){
			 R = FeignResultVo.initErr(e.getMessage());
		 }catch (Exception e){
			 log.error(ExceptionUtil.getExDetail(e));
		 }
		 return R;
	 }

	/**
	 * 用户管理-新增,更新用户
	 * @param bean
	 * @param userRoles
	 * @return
	 */
	@RequestMapping(value = "/execAddSystemUser",method = RequestMethod.POST)
	public FeignResultVo execAddSystemUser(
								 @RequestBody SystemUser bean,
                                 @RequestParam("userRoles") String userRoles){
		FeignResultVo R = FeignResultVo.initErr();
		try {
			SystemUser data =  systemUserService.execAddSystemUser(bean,userRoles);
			R = FeignResultVo.initSuc(JsonUtil.toJSON(data));
		}catch (BusiException e){
			R = FeignResultVo.initErr(e.getMessage());
		}catch (Exception e){
			log.error(ExceptionUtil.getExDetail(e));
		}
		return R;

	}

	 /**
	  * 用户管理-新修改用户 获取原数据
	  * @param id
	  * @return
	  */
	 @RequestMapping(value = "/preExecOperSystemUser",method = RequestMethod.POST)
	 public FeignResultVo preExecOperSystemUser(@RequestParam("id") String id){
		 FeignResultVo R = FeignResultVo.initErr();
		 try {
			 SystemUserVo data =  systemUserService.preExecOperSystemUser(id);
			 R = FeignResultVo.initSuc(JsonUtil.toJSON(data));
		 }catch (BusiException e){
			 R = FeignResultVo.initErr(e.getMessage());
		 }catch (Exception e){
			 log.error(ExceptionUtil.getExDetail(e));
		 }
		 return R;
	 }


	/**
	 * 用户管理-重置密码
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/resetSystemUserPwd",method = RequestMethod.POST)
	public FeignResultVo resetSystemUserPwd(@RequestParam("id") String id){
		FeignResultVo R = FeignResultVo.initErr();
		try {
			Boolean data = systemUserService.resetSystemUserPwd(id);
			R = FeignResultVo.initSuc(JsonUtil.toJSON(data));
		}catch (BusiException e){
			R = FeignResultVo.initErr(e.getMessage());
		}catch (Exception e){
			log.error(ExceptionUtil.getExDetail(e));
		}
		return R;
	}


	/**
	 * 更新bean
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateBean",method = RequestMethod.POST)
	public FeignResultVo updateBean(@RequestBody SystemUser bean){
		FeignResultVo R = FeignResultVo.initErr();
		try {
			Integer data =  systemUserService.updateBean(bean);
			R = FeignResultVo.initSuc(JsonUtil.toJSON(data));
		}catch (BusiException e){
			R = FeignResultVo.initErr(e.getMessage());
		}catch (Exception e){
			log.error(ExceptionUtil.getExDetail(e));
		}
		return R;
	}

}
