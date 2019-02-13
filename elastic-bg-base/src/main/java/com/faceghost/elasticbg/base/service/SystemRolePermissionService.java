package com.faceghost.elasticbg.base.service;

import com.anoyi.grpc.annotation.GrpcService;
import com.faceghost.elasticbg.base.BaseApp;
import com.faceghost.elasticbg.base.model.SystemRolePermission;

import java.util.List;

@GrpcService(server = BaseApp.RPC_SERVER)
public interface SystemRolePermissionService {

	/**
	 * 角色管理->更新角色权限
	 * @param datas
	 * @return
	 */
	int execAddSystemRolePerm(Integer roleId, List<SystemRolePermission> datas) throws Exception;
	
}
