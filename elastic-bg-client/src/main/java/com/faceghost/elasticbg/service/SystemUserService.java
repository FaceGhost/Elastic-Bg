package com.faceghost.elasticbg.service;


import com.faceghost.elasticbg.base.model.SystemUser;
import com.faceghost.elasticbg.base.vo.ExtjsCheckTreeVo;
import com.faceghost.elasticbg.base.vo.PageVo;
import com.faceghost.elasticbg.base.vo.SystemUserVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@FeignClient(name = "${elasticbg.remote.server-name}",path = "${elasticbg.remote.server-path}")
public interface SystemUserService {
	
	/**
	 * 根据name获取bean
	 * 
	 * @param name
	 * @return
	 */
	 @RequestMapping(value = "/getSystemUserByUserName",method = RequestMethod.POST)
	 SystemUser getSystemUserByUserName(@RequestParam("name") String name);

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
	 int updateByLogin(@RequestParam("username") String username,
                       @RequestParam("status") int status,
                       @RequestParam("retryNumber") int retryNumber,
                       @RequestParam("lastRetryTime") Date lastRetryTime);

	/**
	 * 用户管理-分页显示
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/getSystemUserPageVo",method = RequestMethod.POST)
	PageVo getSystemUserPageVo(@RequestBody SystemUserVo searchVo) throws Exception;

	/**
	 * 用户管理-获取角色
	 * @param orgId
	 * @param uid
	 * @return
	 */
	 @RequestMapping(value = "/getSystemUserRole",method = RequestMethod.POST)
	 List<ExtjsCheckTreeVo> getSystemUserRole(@RequestParam("orgId") Integer orgId,
                                              @RequestParam("uid") String uid);

	/**
	 * 用户管理-新增,更新用户
	 * @param bean
	 * @param userRoles
	 * @return
	 */
	@RequestMapping(value = "/execAddSystemUser",method = RequestMethod.POST)
	SystemUser execAddSystemUser(@RequestBody SystemUser bean,
                                 @RequestParam("userRoles") String userRoles) throws Exception;

	 /**
	  * 用户管理-新修改用户 获取原数据
	  * @param id
	  * @return
	  */
	 @RequestMapping(value = "/preExecOperSystemUser",method = RequestMethod.POST)
	 SystemUserVo preExecOperSystemUser(@RequestParam("id") String id) throws Exception;


	/**
	 * 用户管理-重置密码
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/resetSystemUserPwd",method = RequestMethod.POST)
	Boolean resetSystemUserPwd(@RequestParam("Id") String id) throws Exception;


	/**
	 * 更新bean
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateBean",method = RequestMethod.POST)
	int updateBean(@RequestBody SystemUser bean) throws  Exception;

}
