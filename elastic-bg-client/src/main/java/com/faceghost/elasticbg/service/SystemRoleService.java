package com.faceghost.elasticbg.service;

import com.faceghost.elasticbg.base.model.SystemRole;
import com.faceghost.elasticbg.base.vo.FeignResultVo;
import com.faceghost.elasticbg.base.vo.SystemRoleVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "${elasticbg.remote.server-name}",path = "${elasticbg.remote.server-path}/systemRole")
public interface SystemRoleService {

	/**
	 * 角色管理-获取某一用户下的所属角色
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/findRolesBySystemUserId",method = RequestMethod.POST)
	FeignResultVo findRolesBySystemUserId(@RequestParam("userId") String userId);


	/**
	 * 角色管理-分页显示
	 * @param searchVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getSystemRolePageVo",method = RequestMethod.POST)
	FeignResultVo getSystemRolePageVo(@RequestBody SystemRoleVo searchVo) ;



	/**
	 * 角色管理-新增,更新角色
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/execAddSystemRole",method = RequestMethod.POST)
	FeignResultVo execAddSystemRole(@RequestBody SystemRole bean) ;

	
	/**
	 * 根据ID查询
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/getSystemRoleById",method = RequestMethod.POST)
	FeignResultVo getSystemRoleById(@RequestParam("id") Integer id);

	/**
	 * 根据ID查询
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/preExecAddSystemRole",method = RequestMethod.POST)
	FeignResultVo preExecAddSystemRole(@RequestParam("id") Integer id);
	
	/**
	 * 角色管理->权限配置
	 * @param roleId
	 * @return
	 */
	@RequestMapping(value = "/systemRolePermOperPre",method = RequestMethod.POST)
	FeignResultVo systemRolePermOperPre(@RequestParam("roleId") Integer roleId) ;


}
