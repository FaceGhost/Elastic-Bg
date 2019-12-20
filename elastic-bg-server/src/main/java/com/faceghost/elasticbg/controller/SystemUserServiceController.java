package com.faceghost.elasticbg.controller;


import com.faceghost.elasticbg.base.model.SystemUser;
import com.faceghost.elasticbg.base.vo.ExtjsCheckTreeVo;
import com.faceghost.elasticbg.base.vo.PageVo;
import com.faceghost.elasticbg.base.vo.SystemUserVo;
import com.faceghost.elasticbg.service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
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
	 public SystemUser getSystemUserByUserName(@RequestParam("name") String name){
	 	return systemUserService.getSystemUserByUserName(name);
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
	 public int updateByLogin(@RequestParam("username") String username,
                       @RequestParam("status") int status,
                       @RequestParam("retryNumber") int retryNumber,
                       @RequestParam("lastRetryTime") Date lastRetryTime){
	 	return  systemUserService.updateByLogin(username,status,retryNumber,lastRetryTime);
	 }

	/**
	 * 用户管理-分页显示
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/getSystemUserPageVo",method = RequestMethod.POST)
	public PageVo getSystemUserPageVo(@RequestBody SystemUserVo searchVo) throws Exception{
		return systemUserService.getSystemUserPageVo(searchVo);
	}

	/**
	 * 用户管理-获取角色
	 * @param orgId
	 * @param uid
	 * @return
	 */
	 @RequestMapping(value = "/getSystemUserRole",method = RequestMethod.POST)
	 public List<ExtjsCheckTreeVo> getSystemUserRole(@RequestParam("orgId") Integer orgId,
                                              @RequestParam("uid") String uid){
	 	return
				systemUserService.getSystemUserRole(orgId,uid);
	 }

	/**
	 * 用户管理-新增,更新用户
	 * @param bean
	 * @param userRoles
	 * @return
	 */
	@RequestMapping(value = "/execAddSystemUser",method = RequestMethod.POST)
	public SystemUser execAddSystemUser(@RequestBody SystemUser bean,
                                 @RequestParam("userRoles") String userRoles) throws Exception{
		return systemUserService.execAddSystemUser(bean,userRoles);
	}

	 /**
	  * 用户管理-新修改用户 获取原数据
	  * @param id
	  * @return
	  */
	 @RequestMapping(value = "/preExecOperSystemUser",method = RequestMethod.POST)
	 public SystemUserVo preExecOperSystemUser(@RequestParam("id") String id) throws Exception{
	 	return systemUserService.preExecOperSystemUser(id);
	 }


	/**
	 * 用户管理-重置密码
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/resetSystemUserPwd",method = RequestMethod.POST)
	public Boolean resetSystemUserPwd(@RequestParam("Id") String id) throws Exception{
		return systemUserService.resetSystemUserPwd(id);
	}


	/**
	 * 更新bean
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateBean",method = RequestMethod.POST)
	public int updateBean(@RequestBody SystemUser bean) throws  Exception{
		return systemUserService.updateBean(bean);
	}

}
