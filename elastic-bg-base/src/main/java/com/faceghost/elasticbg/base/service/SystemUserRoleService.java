package com.faceghost.elasticbg.base.service;

import com.anoyi.grpc.annotation.GrpcService;
import com.faceghost.elasticbg.base.BaseApp;
import com.faceghost.elasticbg.base.model.SystemUserRole;

import java.util.List;

@GrpcService(server = BaseApp.RPC_SERVER)
public interface SystemUserRoleService {

	/**
	 * 更新用户角色权限
	 * @param datas
	 * @return
	 */
	int execAddSystemUseRole(String uid, List<SystemUserRole> datas) throws Exception;

}
