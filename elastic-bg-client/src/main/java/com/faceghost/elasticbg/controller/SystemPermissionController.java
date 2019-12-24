package com.faceghost.elasticbg.controller;

import com.alibaba.fastjson.JSONObject;
import com.faceghost.elasticbg.base.model.SystemOrg;
import com.faceghost.elasticbg.base.model.SystemPermission;
import com.faceghost.elasticbg.base.statics.ErrorMsgConst;
import com.faceghost.elasticbg.base.statics.LogType;
import com.faceghost.elasticbg.base.utils.ExceptionUtil;
import com.faceghost.elasticbg.base.utils.ValidateUtil;
import com.faceghost.elasticbg.base.vo.*;
import com.faceghost.elasticbg.controller.base.BaseController;
import com.faceghost.elasticbg.service.SystemLogService;
import com.faceghost.elasticbg.service.SystemPermissionService;
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
 * 权限管理
 */
@Slf4j
@Controller
@RequestMapping("/systemPermission")
public class SystemPermissionController  extends BaseController {

	@Autowired
	private SystemPermissionService systemPermissionService;

	@Autowired
	private SystemLogService systemLogService;

	private static final String clazzName = SystemPermissionController.class.getSimpleName();

	/**
	 * 获取用户权限
	 * @return
	 */
	@RequestMapping(value="getSystemUserPermission")
	@ResponseBody
	public Object getSystemUserPermission(){
		List<ExtjsTreeVo> root = new ArrayList<ExtjsTreeVo>();
		try {
			FeignResultVo R = systemPermissionService.getSystemUserPermission(getLoginUser().getId());
			if(R.getSuccess()){
				List<ExtjsTreeVo> listBean = JSONObject.parseArray(R.getData(),ExtjsTreeVo.class);
				if(listBean != null){
					for(ExtjsTreeVo bean: listBean){
						if("1".equals(bean.getParentId())) {
							root.add(bean);
						}
					}
					/**
					 * 菜单
					 */
					for(ExtjsTreeVo bean : root ) {
						List<ExtjsTreeVo> firstTmp = new ArrayList<ExtjsTreeVo>();
						for(ExtjsTreeVo firstLeaf : listBean){
							if(firstLeaf.getParentId().equals(bean.getId())) {
								firstTmp.add(firstLeaf);
								List<ExtjsTreeVo> secondTmp = new ArrayList<ExtjsTreeVo>();
								for(ExtjsTreeVo secondLeaf : listBean ) {
									if(secondLeaf.getParentId().equals(firstLeaf.getId())) {
										secondTmp.add(secondLeaf);
									}
								}
								firstLeaf.setChildren(secondTmp);
							}
						}
						bean.setChildren(firstTmp);
					}
				}
			}else{
				log.error(String.format("执行：获取用户权限，异常：%s", R.getMsg()));
			}
		} catch (Exception e) {
			log.error(String.format("执行：获取用户权限，异常：%s", ExceptionUtil.getExDetail(e)));
			e.printStackTrace();
		}
		return root;
	}


	/**
	 * 权限配置-分页显示
	 * @param searchVo
	 * @return
	 */
	@RequiresPermissions("system:perm:view")
	@RequestMapping(value="getSystemPermissionPageVo")
	@ResponseBody
	public Object getSystemPermissionPageVo(SystemPermissionVo searchVo){
		PageVo pageList = new PageVo();
		try {
			FeignResultVo R = systemPermissionService.getSystemPermissionPageVo(searchVo);
			if(R.getSuccess()){
				pageList = JSONObject.parseObject(R.getData(),PageVo.class);
			}else{
				log.error(String.format("执行：权限配置-分页显示，异常：%s", R.getMsg()));
			}
		} catch (Exception e) {
			log.error(String.format("执行：权限配置-分页显示，异常：%s", ExceptionUtil.getExDetail(e)));
		}
		return pageList;
	}

	/**
	 * 权限配置-分页显示搜索; 新增，添加，上层权限 树形显示
	 * @return
	 */
	@RequestMapping(value="getSystemPermissionTreeVoForNotLow")
	@ResponseBody
	public Object getSystemPermissionTreeVoForNotLow(String showAll){
		List<ExtjsTreeVo> root = new ArrayList<ExtjsTreeVo>();
		if("true".equals(showAll)) {
			ExtjsTreeVo all = new ExtjsTreeVo();
			all.setText("全部");
			root.add(all);
		}
		try {
			FeignResultVo  R = systemPermissionService.getSystemPermissionTreeVoForNotLow();
			if(R.getSuccess()){
				List<ExtjsTreeVo> listBean = JSONObject.parseArray(R.getData(),ExtjsTreeVo.class);
				if(listBean != null){
					for(ExtjsTreeVo bean: listBean){
						if( "0".equals(bean.getParentId())) {
							root.add(bean);
						}
					}
					/**
					 * 菜单
					 */
					for(ExtjsTreeVo bean : root ) {
						List<ExtjsTreeVo> firstTmp = new ArrayList<ExtjsTreeVo>();
						for(ExtjsTreeVo firstLeaf : listBean){
							if(firstLeaf.getParentId().equals(bean.getId())) {
								firstTmp.add(firstLeaf);
								List<ExtjsTreeVo> secondTmp = new ArrayList<ExtjsTreeVo>();
								for(ExtjsTreeVo secondLeaf : listBean ) {
									if(secondLeaf.getParentId().equals(firstLeaf.getId())) {
										secondTmp.add(secondLeaf);
										List<ExtjsTreeVo> thirdTmp = new ArrayList<ExtjsTreeVo>();
										for(ExtjsTreeVo thirdLeft : listBean) {
											if(thirdLeft.getParentId().equals(secondLeaf.getId())) {
												thirdTmp.add(thirdLeft);
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
			}else{
				log.error(String.format("执行：权限配置-分页显示搜索; 新增，添加，上层权限 树形显示，异常：:%s", R.getMsg()));
			}
		} catch (Exception e) {
			log.error(String.format("执行：权限配置-分页显示搜索; 新增，添加，上层权限 树形显示，异常：:%s", ExceptionUtil.getExDetail(e)));
		}
		return root;
	}

	/**
	 * 权限配置-新增
	 * @param bean
	 * @return
	 */
	@RequiresPermissions("system:perm:add")
	@RequestMapping(value = "/execAddSystemPerm")
	@ResponseBody
	public BaseVo execAddSystemPerm(SystemPermission bean,String statusStr){
		String methodName = "execAddSystemPerm";
		String operName = "权限配置-新增";
		BaseVo vo = new BaseVo();
		vo.setSuccess(Boolean.FALSE);
		vo.setMsg(ErrorMsgConst.errOperFail);
		try {
			if(bean.getPId() == null
					|| ValidateUtil.validateBlank(bean.getName())) {
				vo.setMsg(ErrorMsgConst.errParam);
				return vo;
			}
			FeignResultVo R = systemPermissionService.getSystemPermssionById(bean.getPId());
			if(!R.getSuccess()){
				vo.setMsg(R.getMsg());
				return vo;
			}
			SystemPermission tmp = JSONObject.parseObject(R.getData(),SystemPermission.class);
			if(tmp == null) {
				vo.setMsg("上层权限不能不存在，请确认");
				return vo;
			}
			bean.setType((Integer.valueOf(tmp.getType()) + 1 ) +  "");
			if(!ValidateUtil.validateBlank(bean.getIcon())) {
				//trim
				bean.setIcon(bean.getIcon().trim());
			}
			bean.setId(null);
			bean.setInsertU(getLoginUser().getId());
			bean.setInsertT(new Date());
			if("on".equals(statusStr)) {
				bean.setStatus((byte)1);
			}else {
				bean.setStatus((byte)0);
			}

			if(bean.getPId() == 1) {
				bean.setIsAutoExpand("1");
			}else {
				bean.setIsAutoExpand("0");
			}
			FeignResultVo R2 = systemPermissionService.execAddSystemPerm(bean);
			if(!R2.getSuccess()){
				vo.setMsg(R2.getMsg());
				return vo;
			}
			bean = JSONObject.parseObject(R2.getData(),SystemPermission.class);
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
		return vo;
    }

	/**
	 * 权限配置-更新
	 * @param bean
	 * @return
	 */
	@RequiresPermissions("system:perm:update")
	@RequestMapping(value = "/execUpdateSystemPerm")
	@ResponseBody
	public BaseVo execUpdateSystemPerm(SystemPermission bean,String statusStr){
		String methodName = "execUpdateSystemPerm";
		String operName = "权限配置-更新";
		BaseVo vo = new BaseVo();
		vo.setSuccess(Boolean.FALSE);
		vo.setMsg(ErrorMsgConst.errOperFail);
		try {
			if(bean.getId() == null
					|| bean.getPId() == null
					|| ValidateUtil.validateBlank(bean.getName())) {
				vo.setMsg(ErrorMsgConst.errParam);
				return vo;
			}

			FeignResultVo R = systemPermissionService.getSystemPermssionById(bean.getPId());
			if(!R.getSuccess()){
				vo.setMsg(R.getMsg());
				return vo;
			}
			SystemPermission tmp = JSONObject.parseObject(R.getData(),SystemPermission.class);
			if(tmp == null) {
				vo.setMsg("上层权限不能不存在，请确认");
				return vo;
			}
			bean.setType((Integer.valueOf(tmp.getType()) + 1 ) +  "");
			if(!ValidateUtil.validateBlank(bean.getIcon())) {
				//trim
				bean.setIcon(bean.getIcon().trim());
			}
			bean.setUpdateT(new Date());
			bean.setUpdateU(getLoginUser().getId());
			if("on".equals(statusStr)) {
				bean.setStatus((byte)1);
			}else {
				bean.setStatus((byte)0);
			}

			if(bean.getPId() == 1) {
				bean.setIsAutoExpand("1");
			}else {
				bean.setIsAutoExpand("0");
			}
			FeignResultVo R2  = systemPermissionService.execAddSystemPerm(bean);
			if(!R2.getSuccess()){
				vo.setMsg(R2.getMsg());
				return vo;
			}
			bean = JSONObject.parseObject(R2.getData(),SystemPermission.class);
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
		return vo;
	}


	/**
	 * 权限配置-修改权限 获取原数据
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/preExecAddSystemPerm")
	@ResponseBody
	public BaseVo preExecAddSystemPerm(SystemOrg bean){
		BaseVo vo = new BaseVo();
		vo.setSuccess(Boolean.FALSE);
		vo.setMsg(ErrorMsgConst.errOperFail);
		try {
			if(bean.getId()== null) {
				vo.setMsg(ErrorMsgConst.errParam);
				return vo;
			}
			FeignResultVo R  = systemPermissionService.preExecAddSystemOrg(bean.getId());
			if(!R.getSuccess()){
				vo.setMsg(R.getMsg());
				return vo;
			}
			vo.setData(JSONObject.parseObject(R.getData(),SystemPermissionVo.class));
			vo.setSuccess(Boolean.TRUE);
			vo.setMsg("");
		}catch (Exception e) {
			log.error(String.format("执行：权限配置-修改权限 获取原数据，异常：%s", ExceptionUtil.getExDetail(e)));
			e.printStackTrace();
		}
		return vo;
    }


	/**
	 * 权限配置-获取当前页面的权限
	 * @param pId
	 * @return
	 */
	@RequestMapping(value = "/getPermByPage")
	@ResponseBody
	public BaseVo getPermByPage(String pId){
		BaseVo vo = new BaseVo();
		vo.setSuccess(Boolean.FALSE);
		vo.setMsg(ErrorMsgConst.errOperFail);
		try {
			if(ValidateUtil.validateBlank(pId)) {
				vo.setMsg(ErrorMsgConst.errParam);
				return vo;
			}
			FeignResultVo  R = systemPermissionService.getBeanVoByPid(pId);
			if(!R.getSuccess()){
				vo.setMsg(R.getMsg());
				return  vo;
			}
			vo.setData(JSONObject.parseArray(R.getData(),SystemPermissionVo.class));
			vo.setSuccess(Boolean.TRUE);
			vo.setMsg("");
		}catch (Exception e) {
			log.error(String.format("执行：权限配置-获取当前页面的权限，异常：%s", ExceptionUtil.getExDetail(e)));
			e.printStackTrace();
		}
		return vo;
	}


}
