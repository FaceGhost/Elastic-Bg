package com.faceghost.elasticbg.base.service;


import com.anoyi.grpc.annotation.GrpcService;
import com.faceghost.elasticbg.base.BaseApp;
import com.faceghost.elasticbg.base.model.SystemUser;
import com.faceghost.elasticbg.base.vo.ExtjsCheckTreeVo;
import com.faceghost.elasticbg.base.vo.PageVo;
import com.faceghost.elasticbg.base.vo.SystemUserVo;

import java.util.Date;
import java.util.List;

@GrpcService(server = BaseApp.RPC_SERVER)
public interface SystemUserService {
	
	/**
	 * 根据name获取bean
	 * 
	 * @param name
	 * @return
	 */
	 SystemUser getSystemUserByUserName(String name);

	/**
	 * 登录成功或失败更新 
	 * 
	 * @param username
	 * @param status
	 * @param retryNumber
	 * @param lastRetryTime
	 * @return
	 */
	 int updateByLogin(String username, int status, int retryNumber, Date lastRetryTime);

	/**
	 * 用户管理-分页显示
	 * @param
	 * @return
	 */
	PageVo getSystemUserPageVo(SystemUserVo searchVo) throws Exception;

	/**
	 * 用户管理-获取角色
	 * @param orgId
	 * @param uid
	 * @return
	 */
	 List<ExtjsCheckTreeVo> getSystemUserRole(Integer orgId, String  uid);

	/**
	 * 用户管理-新增,更新用户
	 * @param bean
	 * @param userRoles
	 * @return
	 */
	SystemUser execAddSystemUser(SystemUser bean, String userRoles) throws Exception;
	 
	 /**
	  * 用户管理-新修改用户 获取原数据
	  * @param id
	  * @return
	  */
	 SystemUserVo preExecOperSystemUser(String id) throws Exception;


	/**
	 * 用户管理-重置密码
	 * @param id
	 * @return
	 */
	Boolean resetSystemUserPwd(String id) throws Exception;


	/**
	 * 更新bean
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	int updateBean(SystemUser bean) throws  Exception;

}
