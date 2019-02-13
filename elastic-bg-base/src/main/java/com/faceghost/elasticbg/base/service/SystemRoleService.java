package com.faceghost.elasticbg.base.service;

import com.anoyi.grpc.annotation.GrpcService;
import com.faceghost.elasticbg.base.BaseApp;
import com.faceghost.elasticbg.base.model.SystemRole;
import com.faceghost.elasticbg.base.vo.ExtjsCheckTreeVo;
import com.faceghost.elasticbg.base.vo.PageVo;
import com.faceghost.elasticbg.base.vo.SystemRoleVo;

import java.util.List;

@GrpcService(server = BaseApp.RPC_SERVER)
public interface SystemRoleService {

	/**
	 * 角色管理-获取某一用户下的所属角色
	 * @param userId
	 * @return
	 */
	List<SystemRoleVo> findRolesBySystemUserId(String userId);

	/**
	 * 角色管理-分页显示
	 * @param searchVo
	 * @return
	 * @throws Exception
	 */
	PageVo getSystemRolePageVo( SystemRoleVo searchVo) throws Exception;



	/**
	 * 角色管理-新增,更新角色
	 * @param bean
	 * @return
	 */
	SystemRole execAddSystemRole(SystemRole bean) throws Exception;

	
	/**
	 * 根据ID查询
	 * @param
	 * @return
	 */
	SystemRole getSystemRoleById(Integer id);

	/**
	 * 根据ID查询
	 * @param
	 * @return
	 */
	SystemRoleVo preExecAddSystemRole(Integer id);
	
	/**
	 * 角色管理->权限配置
	 * @param roleId
	 * @return
	 */
	List<ExtjsCheckTreeVo> systemRolePermOperPre(Integer roleId) throws Exception;


	

}
