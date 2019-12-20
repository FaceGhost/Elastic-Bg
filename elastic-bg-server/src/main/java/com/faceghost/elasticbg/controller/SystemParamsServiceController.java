package com.faceghost.elasticbg.controller;

import com.faceghost.elasticbg.base.model.SystemParams;
import com.faceghost.elasticbg.base.vo.PageVo;
import com.faceghost.elasticbg.base.vo.SystemParamsVo;
import com.faceghost.elasticbg.service.SystemParamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SystemParamsServiceController {

	@Autowired
	private SystemParamsService systemParamsService;

	/**
	 * 查询某一类型的最大值
	 * 
	 * @param type
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getMaxKeyByType",method = RequestMethod.POST)
	public Integer getMaxKeyByType(@RequestParam("type") String type) throws Exception{
		return systemParamsService.getMaxKeyByType(type);
	}

	/**
	 * 自动新增某一类型的值
	 * 
	 * @param type
	 * @return
	 */
	@RequestMapping(value = "/autoIncKeyByType",method = RequestMethod.POST)
	public Integer autoIncKeyByType(@RequestParam("type") String type) throws Exception{
		return systemParamsService.autoIncKeyByType(type);
	}


	/**
	 * 系统参数管理-分页显示
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/getSystemParamsPageVo",method = RequestMethod.POST)
	public PageVo getSystemParamsPageVo(@RequestBody SystemParamsVo searchVo) throws Exception{
		return systemParamsService.getSystemParamsPageVo(searchVo);
	}


	/**
	 * 系统参数管理-新增,更新
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/execAddSystemParams",method = RequestMethod.POST)
	public SystemParams execAddSystemParams(@RequestBody SystemParams bean) throws Exception{
		return systemParamsService.execAddSystemParams(bean);
	}


	/**
	 * 系统参数管理-修改获取原数据
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/preExecAddSystemParams",method = RequestMethod.POST)
	public SystemParamsVo preExecAddSystemParams(@RequestParam("id") Integer id) throws Exception{
		return systemParamsService.preExecAddSystemParams(id);
	}
	 

}