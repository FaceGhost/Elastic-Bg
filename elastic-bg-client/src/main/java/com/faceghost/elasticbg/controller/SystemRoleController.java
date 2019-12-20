package com.faceghost.elasticbg.controller;

import com.faceghost.elasticbg.base.model.SystemOrg;
import com.faceghost.elasticbg.base.model.SystemRole;
import com.faceghost.elasticbg.base.model.SystemRolePermission;
import com.faceghost.elasticbg.base.statics.ErrorMsgConst;
import com.faceghost.elasticbg.base.statics.LogType;
import com.faceghost.elasticbg.base.utils.ExceptionUtil;
import com.faceghost.elasticbg.base.utils.ValidateUtil;
import com.faceghost.elasticbg.base.vo.BaseVo;
import com.faceghost.elasticbg.base.vo.ExtjsCheckTreeVo;
import com.faceghost.elasticbg.base.vo.PageVo;
import com.faceghost.elasticbg.base.vo.SystemRoleVo;
import com.faceghost.elasticbg.controller.base.BaseController;
import com.faceghost.elasticbg.service.SystemLogService;
import com.faceghost.elasticbg.service.SystemRolePermissionService;
import com.faceghost.elasticbg.service.SystemRoleService;
import com.faceghost.elasticbg.utils.IPUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 角色管理
 */
@Slf4j
@Controller
@RequestMapping("/systemRole")
public class SystemRoleController  extends BaseController {

	@Autowired
	private SystemRoleService systemRoleService;
	
	@Autowired
	private SystemRolePermissionService systemRolePermissionService;

	@Autowired
	private SystemLogService systemLogService;

	private static final String clazzName = SystemRoleController.class.getSimpleName();
	
	
	/**
	 * 角色管理-分页显示
	 * @param searchVo
	 * @return
	 */
	@RequiresPermissions("system:role:view")
	@RequestMapping(value="getSystemRolePageVo")
	@ResponseBody
	public Object getSystemRolePageVo(SystemRoleVo searchVo){
		PageVo pageList = new PageVo();
		try {
			pageList = systemRoleService.getSystemRolePageVo(searchVo);
		} catch (Exception e) {
			log.error(String.format("执行：角色管理-分页显示，异常：%s", ExceptionUtil.getExDetail(e)));
		}
		return pageList;
	}
	
	/**
	 * 角色管理-新增
	 * @param bean
	 * @return
	 */
	@RequiresPermissions("system:role:add")
	@RequestMapping(value = "/execAddSystemRole")
	@ResponseBody
	public BaseVo execAddSystemOrg(SystemRole bean,String statusStr){
		String methodName = "execAddSystemOrg";
		String operName = "角色管理-新增";
		BaseVo vo = new BaseVo();
		vo.setSuccess(Boolean.FALSE);
		vo.setMsg(ErrorMsgConst.errOperFail);
		try {
			if( ValidateUtil.validateBlank(bean.getName())) {
				vo.setMsg(ErrorMsgConst.errParam);
				return vo;
			}

			bean.setId(null);
			bean.setInsertU(getLoginUser().getId());
			bean.setInsertT(new Date());
			if("on".equals(statusStr)) {
				bean.setStatus((byte)1);
			}else {
				bean.setStatus((byte)0);
			}
			
			 bean = systemRoleService.execAddSystemRole(bean);
			if(bean.getId() != null) {
				vo.setSuccess(Boolean.TRUE);
				vo.setMsg("");
				systemLogService.saveLog
						(
								LogType.BUSI.getType(),
								getLoginUser().getId(),
								"执行：" + operName,
								String.valueOf(bean.getId()),
								clazzName,
								methodName,
								IPUtil.getInnerIpAddress(request),
								IPUtil.getOuterIpAddress(request)
						);
			}
		}catch (Exception e) {
			BaseVo eVo = ExceptionUtil.dealRpcError(e);
			vo.setMsg(eVo.getMsg());
			if(eVo.getSuccess()){
				return vo;
			}else{
				log.error(String.format("执行：%s，异常：%s",operName, ExceptionUtil.getExDetail(e)));
				systemLogService.saveLog
						(
								LogType.EX.getType(),
								getLoginUser().getId(),
								ExceptionUtil.getExDetail_log(e),
								String.valueOf(bean.getId()),
								clazzName,
								methodName,
								IPUtil.getInnerIpAddress(request),
								IPUtil.getOuterIpAddress(request)
						);

			}
		}
		return vo;
    }

	/**
	 * 角色管理-更新
	 * @param bean
	 * @return
	 */
	@RequiresPermissions("system:role:update")
	@RequestMapping(value = "/execUpdateSystemRole")
	@ResponseBody
	public BaseVo execUpdateSystemRole(SystemRole bean,String statusStr){
		String methodName = "execUpdateSystemRole";
		String operName = "角色管理-更新";
		BaseVo vo = new BaseVo();
		vo.setSuccess(Boolean.FALSE);
		vo.setMsg(ErrorMsgConst.errOperFail);
		try {
			if( ValidateUtil.validateBlank(bean.getName())) {
				vo.setMsg(ErrorMsgConst.errParam);
				return vo;
			}
			bean.setUpdateT(new Date());
			bean.setUpdateU(getLoginUser().getId());
			if("on".equals(statusStr)) {
				bean.setStatus((byte)1);
			}else {
				bean.setStatus((byte)0);
			}

			bean = systemRoleService.execAddSystemRole(bean);
			vo.setSuccess(Boolean.TRUE);
			vo.setMsg("");
			systemLogService.saveLog
					(
							LogType.BUSI.getType(),
							getLoginUser().getId(),
							"执行：" + operName,
						    String.valueOf(bean.getId()),
							clazzName,
							methodName,
							IPUtil.getInnerIpAddress(request),
							IPUtil.getOuterIpAddress(request)
					);
		}catch (Exception e) {
			BaseVo eVo = ExceptionUtil.dealRpcError(e);
			vo.setMsg(eVo.getMsg());
			if(eVo.getSuccess()){
				return vo;
			}else{
				log.error(String.format("执行：%s，异常：%s",operName, ExceptionUtil.getExDetail(e)));
				systemLogService.saveLog
						(
								LogType.EX.getType(),
								getLoginUser().getId(),
								ExceptionUtil.getExDetail_log(e),
								String.valueOf(bean.getId()),
								clazzName,
								methodName,
								IPUtil.getInnerIpAddress(request),
								IPUtil.getOuterIpAddress(request)
						);

			}
		}
		return vo;
	}

	
	/**
	 * 角色管理-修改角色 获取原数据
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/preExecAddSystemRole")
	@ResponseBody
	public BaseVo preExecAddSystemRole(SystemOrg bean){
		BaseVo vo = new BaseVo();
		vo.setSuccess(Boolean.FALSE);
		vo.setMsg(ErrorMsgConst.errOperFail);
		try {
			if(bean.getId()== null) {
				vo.setMsg(ErrorMsgConst.errParam);
				return vo;
			}
			SystemRoleVo data = systemRoleService.preExecAddSystemRole(bean.getId());
			vo.setData(data);
			vo.setSuccess(Boolean.TRUE);
			vo.setMsg("");
		}catch (Exception e) {
			log.error(String.format("执行：角色管理-修改角色 获取原数据，异常：%s", ExceptionUtil.getExDetail(e)));
		}
		return vo;
    }
	
	
	/**
	 * 角色管理-权限配置
	 * @return
	 */
	@RequestMapping(value="systemRolePermOperPre")
	@ResponseBody
	public Object systemRolePermOperPre(Integer roleId){
		List<ExtjsCheckTreeVo> root = new ArrayList<ExtjsCheckTreeVo>();
		try {
			List<ExtjsCheckTreeVo> listBean = systemRoleService.systemRolePermOperPre(roleId);
			if(listBean != null){
				for(ExtjsCheckTreeVo bean: listBean){
					if( "1".equals(bean.getParentId())) {
						root.add(bean);
					}
				}
				/**
				 * 菜单
				 */
				for(ExtjsCheckTreeVo bean : root ) {
					List<ExtjsCheckTreeVo> firstTmp = new ArrayList<ExtjsCheckTreeVo>();
					for(ExtjsCheckTreeVo firstLeaf : listBean){
						if(firstLeaf.getParentId().equals(bean.getId())) {
							firstTmp.add(firstLeaf);
							List<ExtjsCheckTreeVo> secondTmp = new ArrayList<ExtjsCheckTreeVo>();
							for(ExtjsCheckTreeVo secondLeaf : listBean ) {
								if(secondLeaf.getParentId().equals(firstLeaf.getId())) {
									secondTmp.add(secondLeaf);
									List<ExtjsCheckTreeVo> thirdTmp = new ArrayList<ExtjsCheckTreeVo>();
									for(ExtjsCheckTreeVo thirdLeaf : listBean) {
										if(thirdLeaf.getParentId().equals(secondLeaf.getId())) {
											thirdTmp.add(thirdLeaf);
										}
									}
									secondLeaf.setChildren(thirdTmp);
								}
							}
							firstLeaf.setChildren(secondTmp);
						}
					}
					bean.setChildren(firstTmp);
				}
			}
		} catch (Exception e) {
			log.error(String.format("执行：角色管理-权限配置，异常：%s", ExceptionUtil.getExDetail(e)));
		}
		return root;
	}
	

	/**
	 * 角色管理-权限配置
	 * @param bean
	 * @return
	 */
	@RequiresPermissions("system:role:settingPerm")
	@RequestMapping(value = "/execAddSystemRolePerm")
	@ResponseBody
	public BaseVo execAddSystemRolePerm(SystemRolePermission bean,String permIds){
		String methodName = "execAddSystemRolePerm";
		String operName = "角色管理-权限配置";
		BaseVo vo = new BaseVo();
		vo.setSuccess(Boolean.FALSE);
		vo.setMsg(ErrorMsgConst.errOperFail);
		try {
			if(bean.getSystemRoleId() == null) {
				vo.setMsg(ErrorMsgConst.errParam);
				return vo;
			}
			List<SystemRolePermission> datas = new ArrayList<SystemRolePermission>();
			if(!ValidateUtil.validateBlank(permIds)) {
				for(String id : permIds.split(",")) {
					SystemRolePermission data = new SystemRolePermission();
					data.setSystemPermissionId(Integer.valueOf(id));
					data.setSystemRoleId(bean.getSystemRoleId());
					datas.add(data);
				}
			}
			int eff = systemRolePermissionService.execAddSystemRolePerm(bean.getSystemRoleId(),datas);
			if(eff > 0) {
				vo.setSuccess(Boolean.TRUE);
				vo.setMsg("");
				systemLogService.saveLog
						(
								LogType.BUSI.getType(),
								getLoginUser().getId(),
								"执行：" + operName,
								String.valueOf(bean.getSystemRoleId()),
								clazzName,
								methodName,
								IPUtil.getInnerIpAddress(request),
								IPUtil.getOuterIpAddress(request)
						);
			}
		} catch (Exception e) {
			BaseVo eVo = ExceptionUtil.dealRpcError(e);
			vo.setMsg(eVo.getMsg());
			if(eVo.getSuccess()){
				return vo;
			}else{
				log.error(String.format("执行：%s，异常：%s",operName, ExceptionUtil.getExDetail(e)));
				systemLogService.saveLog
						(
								LogType.EX.getType(),
								getLoginUser().getId(),
								ExceptionUtil.getExDetail_log(e),
								String.valueOf(bean.getSystemRoleId()),
								clazzName,
								methodName,
								IPUtil.getInnerIpAddress(request),
								IPUtil.getOuterIpAddress(request)
						);

			}
		}
		return vo;
    }
	
}
