package com.faceghost.elasticbg.controller;

import com.faceghost.elasticbg.base.exception.BusiException;
import com.faceghost.elasticbg.base.model.SystemRole;
import com.faceghost.elasticbg.base.utils.ExceptionUtil;
import com.faceghost.elasticbg.base.utils.JsonUtil;
import com.faceghost.elasticbg.base.utils.ValidateUtil;
import com.faceghost.elasticbg.base.vo.ExtjsCheckTreeVo;
import com.faceghost.elasticbg.base.vo.FeignResultVo;
import com.faceghost.elasticbg.base.vo.PageVo;
import com.faceghost.elasticbg.base.vo.SystemRoleVo;
import com.faceghost.elasticbg.cons.AppCons;
import com.faceghost.elasticbg.service.SystemRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/systemRole")
@RestController
public class SystemRoleServiceController {

	@Autowired
	private SystemRoleService systemRoleService;

	/**
	 * 角色管理-获取某一用户下的所属角色
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/findRolesBySystemUserId",method = RequestMethod.POST)
	FeignResultVo findRolesBySystemUserId(@RequestParam("userId") String userId){
		FeignResultVo R = FeignResultVo.initErr();
		try {
			List<SystemRoleVo> data = systemRoleService.findRolesBySystemUserId(userId);
			R = FeignResultVo.initSuc(JsonUtil.toJSON(data));
		}catch (BusiException e){
			R = FeignResultVo.initErr(e.getMessage());
		}catch (Exception e){
			log.error(ExceptionUtil.getExDetail(e));
		}
		return R;

	}


	/**
	 * 角色管理-分页显示
	 * @param searchVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getSystemRolePageVo",method = RequestMethod.POST)
	FeignResultVo getSystemRolePageVo(@RequestBody SystemRoleVo searchVo) throws Exception{
		FeignResultVo R = FeignResultVo.initErr();
		try {
			PageVo data =  systemRoleService.getSystemRolePageVo(searchVo);
			R = FeignResultVo.initSuc(JsonUtil.toJSON(data));
		}catch (BusiException e){
			R = FeignResultVo.initErr(e.getMessage());
		}catch (Exception e){
			log.error(ExceptionUtil.getExDetail(e));
		}
		return R;

	}



	/**
	 * 角色管理-新增,更新角色
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/execAddSystemRole",method = RequestMethod.POST)
	FeignResultVo execAddSystemRole(@RequestBody SystemRole bean) throws Exception{
		FeignResultVo R = FeignResultVo.initErr();
		try {
			SystemRole data = systemRoleService.execAddSystemRole(bean);
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
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/getSystemRoleById",method = RequestMethod.POST)
	FeignResultVo getSystemRoleById(@RequestParam("id") Integer id){
		FeignResultVo R = FeignResultVo.initErr();
		try {
			SystemRole data =  systemRoleService.getSystemRoleById(id);
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
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/preExecAddSystemRole",method = RequestMethod.POST)
	FeignResultVo preExecAddSystemRole(@RequestParam("id") Integer id){
		FeignResultVo R = FeignResultVo.initErr();
		try {
			SystemRoleVo data =   systemRoleService.preExecAddSystemRole(id);
			R = FeignResultVo.initSuc(JsonUtil.toJSON(data));
		}catch (BusiException e){
			R = FeignResultVo.initErr(e.getMessage());
		}catch (Exception e){
			log.error(ExceptionUtil.getExDetail(e));
		}
		return R;

	}
	
	/**
	 * 角色管理->权限配置
	 * @param roleId
	 * @return
	 */
	@RequestMapping(value = "/systemRolePermOperPre",method = RequestMethod.POST)
	FeignResultVo systemRolePermOperPre(@RequestParam("roleId") Integer roleId) throws Exception{
		FeignResultVo R = FeignResultVo.initErr();
		try {
			List<ExtjsCheckTreeVo>	data  = systemRoleService.systemRolePermOperPre(roleId);

			if(data != null && !data.isEmpty()){
				for(ExtjsCheckTreeVo bean : data){
					if(ValidateUtil.validateBlank(bean.getIcon())){
						bean.setIcon(AppCons.EXT_ICON_PREFIX + AppCons.EXT_ICON_DEFAULT);
					}else{
						bean.setIcon(AppCons.EXT_ICON_PREFIX + bean.getIcon());
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

}
