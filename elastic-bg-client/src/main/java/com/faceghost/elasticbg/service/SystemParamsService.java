package com.faceghost.elasticbg.service;

import com.faceghost.elasticbg.base.model.SystemParams;
import com.faceghost.elasticbg.base.vo.FeignResultVo;
import com.faceghost.elasticbg.base.vo.SystemParamsVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "${elasticbg.remote.server-name}",path = "${elasticbg.remote.server-path}/systemParams")
public interface SystemParamsService {

	/**
	 * 系统参数管理-分页显示
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/getSystemParamsPageVo",method = RequestMethod.POST)
	FeignResultVo getSystemParamsPageVo(@RequestBody SystemParamsVo searchVo);


	/**
	 * 系统参数管理-新增,更新
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/execAddSystemParams",method = RequestMethod.POST)
	FeignResultVo execAddSystemParams(@RequestBody SystemParams bean);


	/**
	 * 系统参数管理-修改获取原数据
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/preExecAddSystemParams",method = RequestMethod.POST)
	FeignResultVo preExecAddSystemParams(@RequestParam("id") Integer id);
	 

}