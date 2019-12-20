package com.faceghost.elasticbg.controller;

import com.faceghost.elasticbg.base.model.SystemPermission;
import com.faceghost.elasticbg.base.utils.ValidateUtil;
import com.faceghost.elasticbg.base.vo.ExtjsTreeVo;
import com.faceghost.elasticbg.base.vo.PageVo;
import com.faceghost.elasticbg.base.vo.SystemPermissionVo;
import com.faceghost.elasticbg.cons.AppCons;
import com.faceghost.elasticbg.service.SystemPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SystemPermissionServiceController {

	@Autowired
	private SystemPermissionService systemPermissionService;

	/**
	 * 获取角色下的所属权限
	 * @param rolesIds
	 * @return
	 */
	@RequestMapping(value = "/findPermissionBySystemRoleIds",method = RequestMethod.POST)
	public List<SystemPermissionVo> findPermissionBySystemRoleIds(@RequestParam("rolesIds") List<Integer> rolesIds){
		return systemPermissionService.findPermissionBySystemRoleIds(rolesIds);
	}

	
	/**
	 * 获取用户权限
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/getSystemUserPermission",method = RequestMethod.POST)
	public List<ExtjsTreeVo> getSystemUserPermission(@RequestParam("uid") String uid) throws Exception{
		List<ExtjsTreeVo>  R = systemPermissionService.getSystemUserPermission(uid);
		if(R != null && !R.isEmpty()){
			for(ExtjsTreeVo bean : R){
				if(ValidateUtil.validateBlank(bean.getIcon())){
					bean.setIcon(AppCons.EXT_ICON_PREFIX + AppCons.EXT_ICON_DEFAULT);
				}else{
					bean.setIcon(AppCons.EXT_ICON_PREFIX + bean.getIcon());
				}
				if("1".equals(bean.getIsAutoExpand())){
					bean.setExpanded(true);
				}else{
					bean.setExpanded(false);
				}
			}
		}
		return R;
	}

	/**
	 * 权限配置-分页显示
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getSystemPermissionPageVo",method = RequestMethod.POST)
	public PageVo getSystemPermissionPageVo(@RequestBody SystemPermissionVo searchVo) throws Exception{
		return systemPermissionService.getSystemPermissionPageVo(searchVo);
	}


	/**
	 * 权限配置-分页显示搜索; 新增，添加，上层权限 树形显示
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getSystemPermissionTreeVoForNotLow",method = RequestMethod.POST)
	public List<ExtjsTreeVo> getSystemPermissionTreeVoForNotLow() throws Exception{
		List<ExtjsTreeVo>  R =  systemPermissionService.getSystemPermissionTreeVoForNotLow();

		if(R != null && !R.isEmpty()){
			for(ExtjsTreeVo bean : R){
				if(ValidateUtil.validateBlank(bean.getIcon())){
					bean.setIcon(AppCons.EXT_ICON_PREFIX + AppCons.EXT_ICON_DEFAULT);
				}else{
					bean.setIcon(AppCons.EXT_ICON_PREFIX + bean.getIcon());
				}
				if("1".equals(bean.getIsAutoExpand())){
					bean.setExpanded(true);
				}else{
					bean.setExpanded(false);
				}
			}
		}
		return R;

	}


	/**
	 * 权限配置-新增,更新权限
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/execAddSystemPerm",method = RequestMethod.POST)
	public SystemPermission execAddSystemPerm(@RequestBody SystemPermission bean) throws Exception{
		return systemPermissionService.execAddSystemPerm(bean);
	}

	
	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getSystemPermssionById",method = RequestMethod.POST)
	public SystemPermission getSystemPermssionById(@RequestParam("id") Integer id){
		return systemPermissionService.getSystemPermssionById(id);
	}


	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/preExecAddSystemOrg",method = RequestMethod.POST)
	public SystemPermissionVo preExecAddSystemOrg(@RequestParam("id") Integer id){
		return systemPermissionService.preExecAddSystemOrg(id);
	}

	/**
	 * 根据pId查询
	 * @param pId
	 * @return
	 */
	@RequestMapping(value = "/getBeanVoByPid",method = RequestMethod.POST)
	public List<SystemPermissionVo> getBeanVoByPid(@RequestParam("pId") String pId){
		return systemPermissionService.getBeanVoByPid(pId);
	}

	
}
