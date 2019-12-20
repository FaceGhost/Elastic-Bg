package com.faceghost.elasticbg.service;

import com.faceghost.elasticbg.base.model.SystemParams;
import com.faceghost.elasticbg.base.vo.PageVo;
import com.faceghost.elasticbg.base.vo.SystemParamsVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "${elasticbg.remote.server-name}",path = "${elasticbg.remote.server-path}")
public interface SystemParamsService {

	/**
	 * 查询某一类型的最大值
	 * 
	 * @param type
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getMaxKeyByType",method = RequestMethod.POST)
	Integer getMaxKeyByType(@RequestParam("type") String type) throws Exception;

	/**
	 * 自动新增某一类型的值
	 * 
	 * @param type
	 * @return
	 */
	@RequestMapping(value = "/autoIncKeyByType",method = RequestMethod.POST)
	 Integer autoIncKeyByType(@RequestParam("type") String type) throws Exception;


	/**
	 * 系统参数管理-分页显示
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/getSystemParamsPageVo",method = RequestMethod.POST)
	PageVo getSystemParamsPageVo(@RequestBody SystemParamsVo searchVo) throws Exception;


	/**
	 * 系统参数管理-新增,更新
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/execAddSystemParams",method = RequestMethod.POST)
	SystemParams execAddSystemParams(@RequestBody SystemParams bean) throws Exception;


	/**
	 * 系统参数管理-修改获取原数据
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/preExecAddSystemParams",method = RequestMethod.POST)
	SystemParamsVo preExecAddSystemParams(@RequestParam("id") Integer id) throws Exception;
	 

}