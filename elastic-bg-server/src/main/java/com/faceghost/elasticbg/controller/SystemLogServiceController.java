package com.faceghost.elasticbg.controller;

import com.faceghost.elasticbg.base.vo.PageVo;
import com.faceghost.elasticbg.base.vo.SystemLogVo;
import com.faceghost.elasticbg.service.SystemLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SystemLogServiceController {

	@Autowired
	private SystemLogService systemLogService;


	/**
	 * 日志管理-分页显示
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/getSystemLogPageVo",method = RequestMethod.POST)
	public PageVo getSystemLogPageVo(@RequestBody SystemLogVo searchvo) throws Exception{
		return systemLogService.getSystemLogPageVo(searchvo);
	}


	/**
	 * 日志管理-记录日志
	 * @param logType
	 * @param uId
	 * @param oper
	 * @param clazzName
	 * @param methodName
	 * @param ip1Str
	 * @param ip2Str
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/saveLog",method = RequestMethod.POST)
	public int saveLog(@RequestParam("logType") String logType,
                @RequestParam("uId") String uId,
                @RequestParam("oper") String oper,
                @RequestParam("refId") String refId,
                @RequestParam("clazzName") String clazzName,
                @RequestParam("methodName") String methodName,
                @RequestParam("ip1Str") String ip1Str,
                @RequestParam("ip2Str") String ip2Str) {
		return systemLogService.saveLog(logType,uId,oper,refId,clazzName,methodName,ip1Str,ip2Str);
	}


}
