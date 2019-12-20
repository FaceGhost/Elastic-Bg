package com.faceghost.elasticbg.controller;

import com.faceghost.elasticbg.base.utils.ExceptionUtil;
import com.faceghost.elasticbg.base.vo.PageVo;
import com.faceghost.elasticbg.base.vo.SystemLogVo;
import com.faceghost.elasticbg.controller.base.BaseController;
import com.faceghost.elasticbg.service.SystemLogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 日志管理
 */
@RequestMapping("/systemLog")
@Controller
@Slf4j
public class SystemLogController extends BaseController {

	@Autowired
	private SystemLogService systemLogService;

	
	/**
	 * 日志管理-分页显示
	 * @return
	 */
	@RequiresPermissions("system:log:view")
	@RequestMapping(value="getSystemLogPageVo",method=RequestMethod.POST)
	@ResponseBody
	public Object getSystemLogPageVo(SystemLogVo searchVo){
		PageVo pageList = new PageVo();
		try {
			pageList  = systemLogService.getSystemLogPageVo(searchVo);
		} catch (Exception e) {
			log.error(String.format("执行：日志管理-分页显示，异常：%s",ExceptionUtil.getExDetail(e)));
			e.printStackTrace();
		}
		return pageList;
	}

	
}
