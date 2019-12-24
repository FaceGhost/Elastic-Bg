package com.faceghost.elasticbg.controller;

import com.faceghost.elasticbg.base.exception.BusiException;
import com.faceghost.elasticbg.base.model.SystemUserRole;
import com.faceghost.elasticbg.base.utils.ExceptionUtil;
import com.faceghost.elasticbg.base.utils.JsonUtil;
import com.faceghost.elasticbg.base.vo.FeignResultVo;
import com.faceghost.elasticbg.service.SystemUserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Slf4j
@RequestMapping("/systemUserRole")
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
	public FeignResultVo execAddSystemUseRole(@RequestParam("uid") String uid, @RequestParam("datas") List<SystemUserRole> datas) throws Exception{
		FeignResultVo R = FeignResultVo.initErr();
		try {
			Integer data = systemUserRoleService.execAddSystemUseRole(uid,datas);
			R = FeignResultVo.initSuc(JsonUtil.toJSON(data));
		}catch (BusiException e){
			R = FeignResultVo.initErr(e.getMessage());
		}catch (Exception e){
			log.error(ExceptionUtil.getExDetail(e));
		}
		return R;


	}

}
