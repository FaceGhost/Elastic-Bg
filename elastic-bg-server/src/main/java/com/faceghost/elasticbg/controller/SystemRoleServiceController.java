package com.faceghost.elasticbg.controller;

import com.faceghost.elasticbg.base.model.SystemRole;
import com.faceghost.elasticbg.base.utils.ValidateUtil;
import com.faceghost.elasticbg.base.vo.ExtjsCheckTreeVo;
import com.faceghost.elasticbg.base.vo.PageVo;
import com.faceghost.elasticbg.base.vo.SystemRoleVo;
import com.faceghost.elasticbg.cons.AppCons;
import com.faceghost.elasticbg.service.SystemRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
	List<SystemRoleVo> findRolesBySystemUserId(@RequestParam("userId") String userId){
		return systemRoleService.findRolesBySystemUserId(userId);
	}


	/**
	 * 角色管理-分页显示
	 * @param searchVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getSystemRolePageVo",method = RequestMethod.POST)
	PageVo getSystemRolePageVo(@RequestBody SystemRoleVo searchVo) throws Exception{
		return systemRoleService.getSystemRolePageVo(searchVo);
	}



	/**
	 * 角色管理-新增,更新角色
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/execAddSystemRole",method = RequestMethod.POST)
	SystemRole execAddSystemRole(@RequestBody SystemRole bean) throws Exception{
		return  systemRoleService.execAddSystemRole(bean);
	}

	
	/**
	 * 根据ID查询
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/getSystemRoleById",method = RequestMethod.POST)
	SystemRole getSystemRoleById(@RequestParam("id") Integer id){
		return systemRoleService.getSystemRoleById(id);
	}

	/**
	 * 根据ID查询
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/preExecAddSystemRole",method = RequestMethod.POST)
	SystemRoleVo preExecAddSystemRole(@RequestParam("id") Integer id){
		return  systemRoleService.preExecAddSystemRole(id);
	}
	
	/**
	 * 角色管理->权限配置
	 * @param roleId
	 * @return
	 */
	@RequestMapping(value = "/systemRolePermOperPre",method = RequestMethod.POST)
	List<ExtjsCheckTreeVo> systemRolePermOperPre(@RequestParam("roleId") Integer roleId) throws Exception{
		List<ExtjsCheckTreeVo>	R  = systemRoleService.systemRolePermOperPre(roleId);

		if(R != null && !R.isEmpty()){
			for(ExtjsCheckTreeVo bean : R){
				if(ValidateUtil.validateBlank(bean.getIcon())){
					bean.setIcon(AppCons.EXT_ICON_PREFIX + AppCons.EXT_ICON_DEFAULT);
				}else{
					bean.setIcon(AppCons.EXT_ICON_PREFIX + bean.getIcon());
				}
			}
		}

		return R;
	}


	

}
