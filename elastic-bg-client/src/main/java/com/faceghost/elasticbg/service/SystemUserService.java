package com.faceghost.elasticbg.service;


import com.faceghost.elasticbg.base.model.SystemUser;
import com.faceghost.elasticbg.base.vo.FeignResultVo;
import com.faceghost.elasticbg.base.vo.SystemUserVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@FeignClient(name = "${elasticbg.remote.server-name}",path = "${elasticbg.remote.server-path}/systemUser" )
public interface SystemUserService {
	
	/**
	 * 根据name获取bean
	 * 
	 * @param name
	 * @return
	 */
	 @RequestMapping(value = "/getSystemUserByUserName",method = RequestMethod.POST)
	 FeignResultVo getSystemUserByUserName(@RequestParam("name") String name);

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
	 FeignResultVo updateByLogin(@RequestParam("username") String username,
                       @RequestParam("status") int status,
                       @RequestParam("retryNumber") int retryNumber,
                       @RequestParam("lastRetryTime") Date lastRetryTime);

	/**
	 * 用户管理-分页显示
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/getSystemUserPageVo",method = RequestMethod.POST)
	FeignResultVo getSystemUserPageVo(@RequestBody SystemUserVo searchVo) ;

	/**
	 * 用户管理-获取角色
	 * @param orgId
	 * @param uid
	 * @return
	 */
	 @RequestMapping(value = "/getSystemUserRole",method = RequestMethod.POST)
	 FeignResultVo getSystemUserRole(@RequestParam("orgId") Integer orgId,
                                              @RequestParam("uid") String uid);

	/**
	 * 用户管理-新增,更新用户
	 * @param bean
	 * @param userRoles
	 * @return
	 */
	@RequestMapping(value = "/execAddSystemUser",method = RequestMethod.POST)
	FeignResultVo execAddSystemUser(@RequestBody SystemUser bean,
                                 @RequestParam("userRoles") String userRoles) ;

	 /**
	  * 用户管理-新修改用户 获取原数据
	  * @param id
	  * @return
	  */
	 @RequestMapping(value = "/preExecOperSystemUser",method = RequestMethod.POST)
	 FeignResultVo preExecOperSystemUser(@RequestParam("id") String id) ;


	/**
	 * 用户管理-重置密码
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/resetSystemUserPwd",method = RequestMethod.POST)
	FeignResultVo resetSystemUserPwd(@RequestParam("id") String id) ;


	/**
	 * 更新bean
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateBean",method = RequestMethod.POST)
	FeignResultVo updateBean(@RequestBody SystemUser bean) ;

}
