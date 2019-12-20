package com.faceghost.elasticbg.service;

import com.faceghost.elasticbg.base.vo.PageVo;
import com.faceghost.elasticbg.base.vo.SystemLogVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "${elasticbg.remote.server-name}",path = "${elasticbg.remote.server-path}")
public interface SystemLogService {


	
	/**
	 * 日志管理-分页显示
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/getSystemLogPageVo",method = RequestMethod.POST)
	PageVo getSystemLogPageVo(@RequestBody SystemLogVo searchvo) throws Exception;


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
	int saveLog(@RequestParam("logType") String logType,
                @RequestParam("uId") String uId,
                @RequestParam("oper") String oper,
                @RequestParam("refId") String refId,
                @RequestParam("clazzName") String clazzName,
                @RequestParam("methodName") String methodName,
                @RequestParam("ip1Str") String ip1Str,
                @RequestParam("ip2Str") String ip2Str) ;


}
