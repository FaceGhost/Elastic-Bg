package com.faceghost.elasticbg.service;

import com.faceghost.elasticbg.base.model.SystemRole;
import com.faceghost.elasticbg.base.vo.ExtjsCheckTreeVo;
import com.faceghost.elasticbg.base.vo.PageVo;
import com.faceghost.elasticbg.base.vo.SystemRoleVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "${elasticbg.remote.server-name}",path = "${elasticbg.remote.server-path}")
public interface SystemRoleService {

	/**
	 * 角色管理-获取某一用户下的所属角色
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/findRolesBySystemUserId",method = RequestMethod.POST)
	List<SystemRoleVo> findRolesBySystemUserId(@RequestParam("userId") String userId);


	/**
	 * 角色管理-分页显示
	 * @param searchVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getSystemRolePageVo",method = RequestMethod.POST)
	PageVo getSystemRolePageVo(@RequestBody SystemRoleVo searchVo) throws Exception;



	/**
	 * 角色管理-新增,更新角色
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/execAddSystemRole",method = RequestMethod.POST)
	SystemRole execAddSystemRole(@RequestBody SystemRole bean) throws Exception;

	
	/**
	 * 根据ID查询
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/getSystemRoleById",method = RequestMethod.POST)
	SystemRole getSystemRoleById(@RequestParam("id") Integer id);

	/**
	 * 根据ID查询
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/preExecAddSystemRole",method = RequestMethod.POST)
	SystemRoleVo preExecAddSystemRole(@RequestParam("id") Integer id);
	
	/**
	 * 角色管理->权限配置
	 * @param roleId
	 * @return
	 */
	@RequestMapping(value = "/systemRolePermOperPre",method = RequestMethod.POST)
	List<ExtjsCheckTreeVo> systemRolePermOperPre(@RequestParam("roleId") Integer roleId) throws Exception;


	

}
