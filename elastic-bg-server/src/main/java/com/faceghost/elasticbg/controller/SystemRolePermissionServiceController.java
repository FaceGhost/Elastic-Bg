package com.faceghost.elasticbg.controller;

import com.faceghost.elasticbg.base.exception.BusiException;
import com.faceghost.elasticbg.base.model.SystemRolePermission;
import com.faceghost.elasticbg.base.utils.ExceptionUtil;
import com.faceghost.elasticbg.base.utils.JsonUtil;
import com.faceghost.elasticbg.base.vo.FeignResultVo;
import com.faceghost.elasticbg.service.SystemRolePermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RequestMapping("/systemRolePermission")
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
	FeignResultVo execAddSystemRolePerm(@RequestParam("roleId") Integer roleId, @RequestBody List<SystemRolePermission> datas) throws Exception{
		FeignResultVo R = FeignResultVo.initErr();
		try {
			Integer data = systemRolePermissionService.execAddSystemRolePerm(roleId,datas);
			R = FeignResultVo.initSuc(JsonUtil.toJSON(data));
		}catch (BusiException e){
			R = FeignResultVo.initErr(e.getMessage());
		}catch (Exception e){
			log.error(ExceptionUtil.getExDetail(e));
		}
		return R;
	}

}
