package com.faceghost.elasticbg.controller;

import com.alibaba.fastjson.JSONObject;
import com.faceghost.elasticbg.base.model.SystemParams;
import com.faceghost.elasticbg.base.statics.ErrorMsgConst;
import com.faceghost.elasticbg.base.statics.LogType;
import com.faceghost.elasticbg.base.utils.ExceptionUtil;
import com.faceghost.elasticbg.base.utils.ValidateUtil;
import com.faceghost.elasticbg.base.vo.BaseVo;
import com.faceghost.elasticbg.base.vo.FeignResultVo;
import com.faceghost.elasticbg.base.vo.PageVo;
import com.faceghost.elasticbg.base.vo.SystemParamsVo;
import com.faceghost.elasticbg.controller.base.BaseController;
import com.faceghost.elasticbg.service.SystemLogService;
import com.faceghost.elasticbg.service.SystemParamsService;
import com.faceghost.elasticbg.utils.IPUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * 系统参数管理
 */
@RequestMapping("/systemParams")
@Controller
@Slf4j
public class SystemParamsController extends BaseController {

	@Autowired
	private SystemParamsService systemParamsService;
	
	@Autowired
	private SystemLogService systemLogService;

	private static final String clazzName = SystemParamsController.class.getSimpleName();

	
	/**
	 * 系统参数管理-分页显示
	 * @return
	 */
	@RequiresPermissions("system:params:view")
	@RequestMapping(value="getSystemParamsPageVo",method=RequestMethod.POST)
	@ResponseBody
	public Object getSystemParamsPageVo(SystemParamsVo searchVo){
		PageVo pageList = new PageVo();
		try {
			FeignResultVo R = systemParamsService.getSystemParamsPageVo(searchVo);
			if(R.getSuccess()){
				pageList = JSONObject.parseObject(R.getData(),PageVo.class);
			}
		} catch (Exception e) {
			log.error(String.format("执行：系统参数管理-分页显示，异常：%s",ExceptionUtil.getExDetail(e)));
		}
		return pageList;
	}



	/**
	 * 系统参数管理-新增
	 * @param bean
	 * @param statusStr 状态
	 * @return
	 */
	@RequiresPermissions("system:params:add")
	@RequestMapping(value = "/execAddSystemParams")
	@ResponseBody
	public BaseVo execAddSystemParams(SystemParams bean, String statusStr){
		String methodName = "execAddSystemParams";
		String operName = "系统参数管理-新增";

		BaseVo vo = new BaseVo();
		vo.setSuccess(Boolean.FALSE);
		vo.setMsg(ErrorMsgConst.errOperFail);

		try {

			if(ValidateUtil.validateBlank(bean.getType())
					|| ValidateUtil.validateBlank(bean.getParam())
					|| ValidateUtil.validateBlank(bean.getValue())) {
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

			FeignResultVo R = systemParamsService.execAddSystemParams(bean);
			if(!R.getSuccess()){
			    vo.setMsg(R.getMsg());
				return vo;
			}
            bean = JSONObject.parseObject(R.getData(),SystemParams.class);
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
	 * 系统参数管理-更新
	 * @param bean
	 * @param statusStr 状态
	 * @return
	 */
	@RequiresPermissions("system:params:update")
	@RequestMapping(value = "/execUpdateSystemParams")
	@ResponseBody
	public BaseVo execUpdateSystemParams(SystemParams bean, String statusStr){
		String methodName = "execUpdateSystemParams";
		String operName = "角色管理-更新";
		BaseVo vo = new BaseVo();
		vo.setSuccess(Boolean.FALSE);
		vo.setMsg(ErrorMsgConst.errOperFail);
		try {
			if(bean.getId() == null
					|| ValidateUtil.validateBlank(bean.getType())
					|| ValidateUtil.validateBlank(bean.getParam())
					|| ValidateUtil.validateBlank(bean.getValue())) {
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

            FeignResultVo R = systemParamsService.execAddSystemParams(bean);
			if(!R.getSuccess()){
			    vo.setMsg(R.getMsg());
			    return vo;
            }

            bean = JSONObject.parseObject(R.getData(),SystemParams.class);
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
	 * 系统参数管理-修改获取原数据
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/preExecAddSystemParams")
	@ResponseBody
	public BaseVo preExecAddSystemParams(SystemParamsVo bean){
		BaseVo vo = new BaseVo();
		vo.setSuccess(Boolean.FALSE);
		vo.setMsg(ErrorMsgConst.errOperFail);
		try {
			if(bean.getId()== null) {
				vo.setMsg(ErrorMsgConst.errParam);
				return vo;
			}
			FeignResultVo R = systemParamsService.preExecAddSystemParams(bean.getId());
			if(!R.getSuccess()){
			    vo.setMsg(R.getMsg());
			    return vo;
            }
			vo.setData(JSONObject.parseObject(R.getData(),SystemParamsVo.class));
			vo.setSuccess(Boolean.TRUE);
			vo.setMsg("");
		}catch (Exception e) {
			log.error(String.format("执行：系统参数管理-修改获取原数据，异常：%s",ExceptionUtil.getExDetail(e)));
		}
		return vo;
    }
	
}
