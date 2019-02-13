package com.faceghost.elasticbg.base.service;

import com.anoyi.grpc.annotation.GrpcService;
import com.faceghost.elasticbg.base.BaseApp;
import com.faceghost.elasticbg.base.vo.PageVo;
import com.faceghost.elasticbg.base.vo.SystemLogVo;

@GrpcService(server = BaseApp.RPC_SERVER)
public interface SystemLogService {


	
	/**
	 * 日志管理-分页显示
	 * @param
	 * @return
	 */
	PageVo getSystemLogPageVo(SystemLogVo searchvo) throws Exception;


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
	int saveLog(String logType, String uId, String oper,String refId,String clazzName, String methodName,String ip1Str,String ip2Str) ;


}
