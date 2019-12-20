package com.faceghost.elasticbg.controller;

import com.faceghost.elasticbg.base.model.SystemRolePermission;
import com.faceghost.elasticbg.service.SystemRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SystemRolePermissionServiceController {

	@Autowired
	private SystemRolePermissionService systemRolePermissionService;

	/**
	 * 角色管理->更新角色权限
	 * @param datas
	 * @return
	 */
	@RequestMapping(value = "/execAddSystemRolePerm",method = RequestMethod.POST)
	public int execAddSystemRolePerm(@RequestParam("roleId") Integer roleId, @RequestBody List<SystemRolePermission> datas) throws Exception{
		return systemRolePermissionService.execAddSystemRolePerm(roleId,datas);
	}

}
