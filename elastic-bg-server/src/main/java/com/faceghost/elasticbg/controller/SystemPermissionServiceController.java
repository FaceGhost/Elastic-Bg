package com.faceghost.elasticbg.controller;

import com.faceghost.elasticbg.base.exception.BusiException;
import com.faceghost.elasticbg.base.model.SystemPermission;
import com.faceghost.elasticbg.base.utils.ExceptionUtil;
import com.faceghost.elasticbg.base.utils.JsonUtil;
import com.faceghost.elasticbg.base.utils.ValidateUtil;
import com.faceghost.elasticbg.base.vo.ExtjsTreeVo;
import com.faceghost.elasticbg.base.vo.FeignResultVo;
import com.faceghost.elasticbg.base.vo.PageVo;
import com.faceghost.elasticbg.base.vo.SystemPermissionVo;
import com.faceghost.elasticbg.cons.AppCons;
import com.faceghost.elasticbg.service.SystemPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RequestMapping("/systemPermission")
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
	FeignResultVo findPermissionBySystemRoleIds(@RequestParam("rolesIds") List<Integer> rolesIds){
		FeignResultVo R = FeignResultVo.initErr();
		try {
			List<SystemPermissionVo> data =  systemPermissionService.findPermissionBySystemRoleIds(rolesIds);
			R = FeignResultVo.initSuc(JsonUtil.toJSON(data));
		}catch (BusiException e){
			R = FeignResultVo.initErr(e.getMessage());
		}catch (Exception e){
			log.error(ExceptionUtil.getExDetail(e));
		}
		return R;

	}

	
	/**
	 * 获取用户权限
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/getSystemUserPermission",method = RequestMethod.POST)
	FeignResultVo getSystemUserPermission(@RequestParam("uid") String uid){
		FeignResultVo R = FeignResultVo.initErr();
		try {
			List<ExtjsTreeVo>  data = systemPermissionService.getSystemUserPermission(uid);
			if(data != null && !data.isEmpty()){
				for(ExtjsTreeVo bean : data){
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
			R = FeignResultVo.initSuc(JsonUtil.toJSON(data));
		}catch (BusiException e){
			R = FeignResultVo.initErr(e.getMessage());
		}catch (Exception e){
			log.error(ExceptionUtil.getExDetail(e));
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
	FeignResultVo  getSystemPermissionPageVo(@RequestBody SystemPermissionVo searchVo) {
		FeignResultVo R = FeignResultVo.initErr();
		try {
			PageVo data =  systemPermissionService.getSystemPermissionPageVo(searchVo);
			R = FeignResultVo.initSuc(JsonUtil.toJSON(data));
		}catch (BusiException e){
			R = FeignResultVo.initErr(e.getMessage());
		}catch (Exception e){
			log.error(ExceptionUtil.getExDetail(e));
		}
		return R;
	}


	/**
	 * 权限配置-分页显示搜索; 新增，添加，上层权限 树形显示
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getSystemPermissionTreeVoForNotLow",method = RequestMethod.POST)
	FeignResultVo getSystemPermissionTreeVoForNotLow() {
		FeignResultVo R = FeignResultVo.initErr();
		try {
			List<ExtjsTreeVo>  data =  systemPermissionService.getSystemPermissionTreeVoForNotLow();

			if(data != null && !data.isEmpty()){
				for(ExtjsTreeVo bean : data){
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
			R = FeignResultVo.initSuc(JsonUtil.toJSON(data));
		}catch (BusiException e){
			R = FeignResultVo.initErr(e.getMessage());
		}catch (Exception e){
			log.error(ExceptionUtil.getExDetail(e));
		}
		return R;



	}


	/**
	 * 权限配置-新增,更新权限
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/execAddSystemPerm",method = RequestMethod.POST)
	FeignResultVo execAddSystemPerm(@RequestBody SystemPermission bean){
		FeignResultVo R = FeignResultVo.initErr();
		try {
			SystemPermission data = systemPermissionService.execAddSystemPerm(bean);
			R = FeignResultVo.initSuc(JsonUtil.toJSON(data));
		}catch (BusiException e){
			R = FeignResultVo.initErr(e.getMessage());
		}catch (Exception e){
			log.error(ExceptionUtil.getExDetail(e));
		}
		return R;

	}

	
	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getSystemPermssionById",method = RequestMethod.POST)
	FeignResultVo getSystemPermssionById(@RequestParam("id") Integer id){
		FeignResultVo R = FeignResultVo.initErr();
		try {
			SystemPermission data =  systemPermissionService.getSystemPermssionById(id);
			R = FeignResultVo.initSuc(JsonUtil.toJSON(data));
		}catch (BusiException e){
			R = FeignResultVo.initErr(e.getMessage());
		}catch (Exception e){
			log.error(ExceptionUtil.getExDetail(e));
		}
		return R;
	}


	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/preExecAddSystemOrg",method = RequestMethod.POST)
	FeignResultVo preExecAddSystemOrg(@RequestParam("id") Integer id){
		FeignResultVo R = FeignResultVo.initErr();
		try {
			SystemPermissionVo data = systemPermissionService.preExecAddSystemOrg(id);
			R = FeignResultVo.initSuc(JsonUtil.toJSON(data));
		}catch (BusiException e){
			R = FeignResultVo.initErr(e.getMessage());
		}catch (Exception e){
			log.error(ExceptionUtil.getExDetail(e));
		}
		return R;
	}

	/**
	 * 根据pId查询
	 * @param pId
	 * @return
	 */
	@RequestMapping(value = "/getBeanVoByPid",method = RequestMethod.POST)
	FeignResultVo getBeanVoByPid(@RequestParam("pId") String pId){

		FeignResultVo R = FeignResultVo.initErr();
		try {
			List<SystemPermissionVo> data = systemPermissionService.getBeanVoByPid(pId);
			R = FeignResultVo.initSuc(JsonUtil.toJSON(data));
		}catch (BusiException e){
			R = FeignResultVo.initErr(e.getMessage());
		}catch (Exception e){
			log.error(ExceptionUtil.getExDetail(e));
		}
		return R;

	}

	
}
