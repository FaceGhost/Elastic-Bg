package com.faceghost.elasticbg.service;

import com.faceghost.elasticbg.base.model.SystemRolePermission;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "${elasticbg.remote.server-name}",path = "${elasticbg.remote.server-path}")
public interface SystemRolePermissionService {

	/**
	 * 角色管理->更新角色权限
	 * @param datas
	 * @return
	 */
	@RequestMapping(value = "/execAddSystemRolePerm",method = RequestMethod.POST)
	int execAddSystemRolePerm(@RequestParam("roleId") Integer roleId, @RequestBody List<SystemRolePermission> datas) throws Exception;

}
