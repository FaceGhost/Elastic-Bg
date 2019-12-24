package com.faceghost.elasticbg.controller;

import com.faceghost.elasticbg.base.exception.BusiException;
import com.faceghost.elasticbg.base.utils.ExceptionUtil;
import com.faceghost.elasticbg.base.utils.JsonUtil;
import com.faceghost.elasticbg.base.vo.FeignResultVo;
import com.faceghost.elasticbg.base.vo.PageVo;
import com.faceghost.elasticbg.base.vo.SystemLogVo;
import com.faceghost.elasticbg.service.SystemLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/systemLog")
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
	FeignResultVo getSystemLogPageVo(@RequestBody SystemLogVo searchvo) {
		FeignResultVo R = FeignResultVo.initErr();
		try {
			PageVo data =  systemLogService.getSystemLogPageVo(searchvo);
			R = FeignResultVo.initSuc(JsonUtil.toJSON(data));
		}catch (BusiException e){
			R = FeignResultVo.initErr(e.getMessage());
		}catch (Exception e){
			log.error(ExceptionUtil.getExDetail(e));
		}
		return R;
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
	FeignResultVo saveLog(@RequestParam("logType") String logType,
                @RequestParam("uId") String uId,
                @RequestParam("oper") String oper,
                @RequestParam("refId") String refId,
                @RequestParam("clazzName") String clazzName,
                @RequestParam("methodName") String methodName,
                @RequestParam("ip1Str") String ip1Str,
                @RequestParam("ip2Str") String ip2Str) {

		FeignResultVo R = FeignResultVo.initErr();
		try {
			Integer data =  systemLogService.saveLog(logType,uId,oper,refId,clazzName,methodName,ip1Str,ip2Str);
			R = FeignResultVo.initSuc(JsonUtil.toJSON(data));
		}catch (BusiException e){
			R = FeignResultVo.initErr(e.getMessage());
		}catch (Exception e){
			log.error(ExceptionUtil.getExDetail(e));
		}
		return R;

	}


}
