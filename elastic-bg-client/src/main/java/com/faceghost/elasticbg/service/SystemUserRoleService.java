package com.faceghost.elasticbg.service;

import com.faceghost.elasticbg.base.model.SystemUserRole;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "${elasticbg.remote.server-name}",path = "${elasticbg.remote.server-path}")
public interface SystemUserRoleService {

	/**
	 * 更新用户角色权限
	 * @param datas
	 * @return
	 */
	@RequestMapping(value = "/execAddSystemUseRole",method = RequestMethod.POST)
	int execAddSystemUseRole(@RequestParam("uid") String uid, @RequestParam("datas") List<SystemUserRole> datas) throws Exception;

}
