package com.faceghost.elasticbg.controller;

import com.faceghost.elasticbg.base.controller.BaseController;
import com.faceghost.elasticbg.base.model.SystemOrg;
import com.faceghost.elasticbg.base.service.SystemLogService;
import com.faceghost.elasticbg.base.service.SystemOrgService;
import com.faceghost.elasticbg.base.statics.ErrorMsgConst;
import com.faceghost.elasticbg.base.statics.LogType;
import com.faceghost.elasticbg.base.utils.ExceptionUtil;
import com.faceghost.elasticbg.base.utils.IPUtil;
import com.faceghost.elasticbg.base.utils.ValidateUtil;
import com.faceghost.elasticbg.base.vo.BaseVo;
import com.faceghost.elasticbg.base.vo.ExtjsTreeVo;
import com.faceghost.elasticbg.base.vo.PageVo;
import com.faceghost.elasticbg.base.vo.SystemOrgVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 组织管理
 */
@RequestMapping("/systemOrg")
@Controller
@Slf4j
public class SystemOrgController extends BaseController {

	@Autowired
	private SystemOrgService systemOrgService;

	@Autowired
	private SystemLogService systemLogService;

	private static final String clazzName = SystemOrgController.class.getSimpleName();

	/**
	 * 组织管理树形显示
	 * @return
	 */
	@RequestMapping(value="getSystemOrgTreeVo")
	@ResponseBody
	public Object getSystemOrgTreeVo(String showAll){
		List<ExtjsTreeVo> root = new ArrayList<ExtjsTreeVo>();
		if("true".equals(showAll)) {
			ExtjsTreeVo all = new ExtjsTreeVo();
			all.setText("全部");
			root.add(all);
		}
		try {
			List<ExtjsTreeVo> listBean = systemOrgService.getSystemOrgTreeVoListBean();
			if(listBean != null){
				/**
				 * 菜单
				 */
				for(ExtjsTreeVo bean: listBean){
					if("0".equals(bean.getParentId())) {
						root.add(bean);
					}
				}
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
		} catch (Exception e) {
			log.error(String.format("执行：组织管理树形显示，异常：%s", ExceptionUtil.getExDetail(e)));
		}
		return root;
	}
	
	
	
	/**
	 * 组织管理分页显示
	 * @return
	 */
	@RequiresPermissions("system:org:view")
	@RequestMapping(value="getSystemOrgPageVo",method=RequestMethod.POST)
	@ResponseBody
	public Object getSystemOrgPageVo(SystemOrgVo searchVo){
		PageVo pageList = new PageVo();
		try {
			pageList  = systemOrgService.getSystemOrgPageVo(searchVo);
		} catch (Exception e) {
			log.error(String.format("执行：组织管理分页显示，异常：%s",ExceptionUtil.getExDetail(e)));
		}
		return pageList;
	}


	/**
	 * 新增或修改-上级组织管理树形显示,不含最低节点
	 * @param showAll 是否含有全部
	 * @return
	 */
	@RequestMapping(value="getSystemOrgTreeVoForNotLow")
	@ResponseBody
	public Object getSystemOrgTreeVoForNotLow(String showAll){
		List<ExtjsTreeVo> root = new ArrayList<ExtjsTreeVo>();
		if("true".equals(showAll)) {
			ExtjsTreeVo all = new ExtjsTreeVo();
			all.setText("全部");
			root.add(all);
		}
		try {
			List<ExtjsTreeVo> listBean = systemOrgService.getSystemOrgTreeVoForNotLow();
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
								}
							}
							firstLeaf.setChildren(secondTmp);
						}
					}
					
					bean.setChildren(firstTmp);
				}
			}
		} catch (Exception e) {
			log.error(String.format("执行：新增或修改-上级组织管理树形显示，异常：%s",ExceptionUtil.getExDetail(e)));
		}
		return root;
	}


	/**
	 * 组织管理-新增
	 * @param bean
	 * @param statusStr 状态
	 * @return
	 */
	@RequiresPermissions("system:org:add")
	@RequestMapping(value = "/execAddSystemOrg")
	@ResponseBody
	public BaseVo execAddSystemOrg(SystemOrg bean,String statusStr){
		String methodName = "execAddSystemOrg";
		String operName = "组织管理-新增";
		BaseVo vo = new BaseVo();
		vo.setSuccess(Boolean.FALSE);
		vo.setMsg(ErrorMsgConst.errOperFail);
		try {
			if(bean.getPId() == null
					|| ValidateUtil.validateBlank(bean.getName())
					|| ValidateUtil.validateBlank(bean.getLinkMan())) {
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
			//默认全部不展开
			bean.setIsAutoExpand("1");
			bean = systemOrgService.execAddSystemOrg(bean);
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
				log.error(String.format("执行：%s，异常：%s",methodName,ExceptionUtil.getExDetail(e)));
				systemLogService.saveLog
						(
								LogType.EX.getType(),
								getLoginUser().getId(),
								ExceptionUtil.getExDetail_log(e),
								String.valueOf(bean.getId()),
								clazzName,
								operName,
								IPUtil.getInnerIpAddress(request),
								IPUtil.getOuterIpAddress(request)
						);

			}
		}
		return vo;
    }

	/**
	 * 组织管理-更新
	 * @param bean
	 * @param statusStr 状态
	 * @return
	 */
	@RequiresPermissions("system:org:update")
	@RequestMapping(value = "/execUpdateSystemOrg")
	@ResponseBody
	public BaseVo execUpdateSystemOrg(SystemOrg bean,String statusStr){
		String methodName = "execUpdateSystemOrg";
		String operName = "组织管理-更新";
		BaseVo vo = new BaseVo();
		vo.setSuccess(Boolean.FALSE);
		vo.setMsg(ErrorMsgConst.errOperFail);
		try {
			if(bean.getId() == null
					|| bean.getPId() == null
					|| ValidateUtil.validateBlank(bean.getName())
					|| ValidateUtil.validateBlank(bean.getLinkMan())) {
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
			//级默认全部不展开
			bean.setIsAutoExpand("1");
			bean = systemOrgService.execAddSystemOrg(bean);
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
				log.error(String.format("执行：%s，异常：%s",operName,ExceptionUtil.getExDetail(e)));
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
	 * 修改组织 获取原数据
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/preExecAddSystemOrg")
	@ResponseBody
	public BaseVo preExecAddSystemOrg(SystemOrg bean){
		BaseVo vo = new BaseVo();
		vo.setSuccess(Boolean.FALSE);
		vo.setMsg(ErrorMsgConst.errOperFail);
		try {
			if(bean.getId()== null) {
				vo.setMsg(ErrorMsgConst.errParam);
				return vo;
			}
			SystemOrgVo data = systemOrgService.getPreExecAddSystemOrgData(bean.getId());
			vo.setData(data);
			vo.setSuccess(Boolean.TRUE);
			vo.setMsg("");
		}catch (Exception e) {
			log.error(String.format("执行：修改组织 获取原数据，异常：%s",ExceptionUtil.getExDetail(e)));
		}
		return vo;
    }
	
}
