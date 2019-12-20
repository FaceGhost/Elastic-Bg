package com.faceghost.elasticbg.controller;

import com.faceghost.elasticbg.base.model.SystemUserRole;
import com.faceghost.elasticbg.service.SystemUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SystemUserRoleServiceController {

	@Autowired
	private SystemUserRoleService systemUserRoleService;

	/**
	 * 更新用户角色权限
	 * @param datas
	 * @return
	 */
	@RequestMapping(value = "/execAddSystemUseRole",method = RequestMethod.POST)
	public int execAddSystemUseRole(@RequestParam("uid") String uid, @RequestParam("datas") List<SystemUserRole> datas) throws Exception{
		return systemUserRoleService.execAddSystemUseRole(uid,datas);
	}

}
