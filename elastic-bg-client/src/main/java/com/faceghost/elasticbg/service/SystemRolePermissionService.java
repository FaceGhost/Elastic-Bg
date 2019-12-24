package com.faceghost.elasticbg.service;

import com.faceghost.elasticbg.base.model.SystemRolePermission;
import com.faceghost.elasticbg.base.vo.FeignResultVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "${elasticbg.remote.server-name}",path = "${elasticbg.remote.server-path}/systemRolePermission")
public interface SystemRolePermissionService {

	/**
	 * 角色管理->更新角色权限
	 * @param datas
	 * @return
	 */
	@RequestMapping(value = "/execAddSystemRolePerm",method = RequestMethod.POST)
	FeignResultVo execAddSystemRolePerm(@RequestParam("roleId") Integer roleId, @RequestBody List<SystemRolePermission> datas) throws Exception;

}
