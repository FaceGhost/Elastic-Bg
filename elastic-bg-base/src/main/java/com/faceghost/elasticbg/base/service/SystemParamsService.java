package com.faceghost.elasticbg.base.service;

import com.anoyi.grpc.annotation.GrpcService;
import com.faceghost.elasticbg.base.BaseApp;
import com.faceghost.elasticbg.base.model.SystemParams;
import com.faceghost.elasticbg.base.vo.PageVo;
import com.faceghost.elasticbg.base.vo.SystemParamsVo;

@GrpcService(server = BaseApp.RPC_SERVER)
public interface SystemParamsService {

	/**
	 * 查询某一类型的最大值
	 * 
	 * @param type
	 * @return
	 * @throws Exception
	 */
	Integer getMaxKeyByType(String type) throws Exception;

	/**
	 * 自动新增某一类型的值
	 * 
	 * @param type
	 * @return
	 */
	 Integer autoIncKeyByType(String type) throws Exception;


	/**
	 * 系统参数管理-分页显示
	 * @param
	 * @return
	 */
	PageVo getSystemParamsPageVo(SystemParamsVo searchVo) throws Exception;


	/**
	 * 系统参数管理-新增,更新
	 * @param bean
	 * @return
	 */
	SystemParams execAddSystemParams(SystemParams bean) throws Exception;


	/**
	 * 系统参数管理-修改获取原数据
	 * @param id
	 * @return
	 */
	SystemParamsVo preExecAddSystemParams(Integer id) throws Exception;
	 

}