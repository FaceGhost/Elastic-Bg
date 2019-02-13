package com.faceghost.elasticbg.base.service;

import com.anoyi.grpc.annotation.GrpcService;
import com.faceghost.elasticbg.base.BaseApp;
import com.faceghost.elasticbg.base.model.SystemPermission;
import com.faceghost.elasticbg.base.vo.ExtjsTreeVo;
import com.faceghost.elasticbg.base.vo.PageVo;
import com.faceghost.elasticbg.base.vo.SystemPermissionVo;

import java.util.List;

@GrpcService(server = BaseApp.RPC_SERVER)
public interface SystemPermissionService {

	/**
	 * 获取角色下的所属权限
	 * @param rolesIds
	 * @return
	 */
	List<SystemPermissionVo> findPermissionBySystemRoleIds(List<Integer> rolesIds);

	
	/**
	 * 获取用户权限
	 * @param
	 * @return
	 */
	List<ExtjsTreeVo> getSystemUserPermission(String uid) throws Exception;

	/**
	 * 权限配置-分页显示
	 * @param
	 * @return
	 * @throws Exception
	 */
	PageVo getSystemPermissionPageVo(SystemPermissionVo searchVo) throws Exception;


	/**
	 * 权限配置-分页显示搜索; 新增，添加，上层权限 树形显示
	 * @return
	 * @throws Exception
	 */
    List<ExtjsTreeVo> getSystemPermissionTreeVoForNotLow() throws Exception;


	/**
	 * 权限配置-新增,更新权限
	 * @param bean
	 * @return
	 */
	SystemPermission execAddSystemPerm(SystemPermission bean) throws Exception;

	
	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	SystemPermission getSystemPermssionById(Integer id);


	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	SystemPermissionVo preExecAddSystemOrg(Integer id);

	/**
	 * 根据pId查询
	 * @param pId
	 * @return
	 */
	List<SystemPermissionVo> getBeanVoByPid(String pId);

	
}
