package com.faceghost.elasticbg.service;

import com.faceghost.elasticbg.base.model.SystemPermission;
import com.faceghost.elasticbg.base.vo.ExtjsTreeVo;
import com.faceghost.elasticbg.base.vo.PageVo;
import com.faceghost.elasticbg.base.vo.SystemPermissionVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "${elasticbg.remote.server-name}",path = "${elasticbg.remote.server-path}")
public interface SystemPermissionService {

	/**
	 * 获取角色下的所属权限
	 * @param rolesIds
	 * @return
	 */
	@RequestMapping(value = "/findPermissionBySystemRoleIds",method = RequestMethod.POST)
	List<SystemPermissionVo> findPermissionBySystemRoleIds(@RequestParam("rolesIds") List<Integer> rolesIds);

	
	/**
	 * 获取用户权限
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/getSystemUserPermission",method = RequestMethod.POST)
	List<ExtjsTreeVo> getSystemUserPermission(@RequestParam("uid") String uid) throws Exception;

	/**
	 * 权限配置-分页显示
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getSystemPermissionPageVo",method = RequestMethod.POST)
	PageVo getSystemPermissionPageVo(@RequestBody SystemPermissionVo searchVo) throws Exception;


	/**
	 * 权限配置-分页显示搜索; 新增，添加，上层权限 树形显示
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getSystemPermissionTreeVoForNotLow",method = RequestMethod.POST)
    List<ExtjsTreeVo> getSystemPermissionTreeVoForNotLow() throws Exception;


	/**
	 * 权限配置-新增,更新权限
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/execAddSystemPerm",method = RequestMethod.POST)
	SystemPermission execAddSystemPerm(@RequestBody SystemPermission bean) throws Exception;

	
	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getSystemPermssionById",method = RequestMethod.POST)
	SystemPermission getSystemPermssionById(@RequestParam("id") Integer id);


	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/preExecAddSystemOrg",method = RequestMethod.POST)
	SystemPermissionVo preExecAddSystemOrg(@RequestParam("id") Integer id);

	/**
	 * 根据pId查询
	 * @param pId
	 * @return
	 */
	@RequestMapping(value = "/getBeanVoByPid",method = RequestMethod.POST)
	List<SystemPermissionVo> getBeanVoByPid(@RequestParam("pId") String pId);

	
}
