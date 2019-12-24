package com.faceghost.elasticbg.controller;

import com.faceghost.elasticbg.base.exception.BusiException;
import com.faceghost.elasticbg.base.model.SystemParams;
import com.faceghost.elasticbg.base.utils.ExceptionUtil;
import com.faceghost.elasticbg.base.utils.JsonUtil;
import com.faceghost.elasticbg.base.vo.FeignResultVo;
import com.faceghost.elasticbg.base.vo.PageVo;
import com.faceghost.elasticbg.base.vo.SystemParamsVo;
import com.faceghost.elasticbg.service.SystemParamsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/systemParams")
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
	FeignResultVo getMaxKeyByType(@RequestParam("type") String type){
		FeignResultVo R = FeignResultVo.initErr();
		try {
			Integer data =  systemParamsService.getMaxKeyByType(type);
			R = FeignResultVo.initSuc(JsonUtil.toJSON(data));
		}catch (BusiException e){
			R = FeignResultVo.initErr(e.getMessage());
		}catch (Exception e){
			log.error(ExceptionUtil.getExDetail(e));
		}
		return R;
	}

	/**
	 * 自动新增某一类型的值
	 * 
	 * @param type
	 * @return
	 */
	@RequestMapping(value = "/autoIncKeyByType",method = RequestMethod.POST)
	FeignResultVo autoIncKeyByType(@RequestParam("type") String type) throws Exception{
		FeignResultVo R = FeignResultVo.initErr();
		try {
			Integer data =  systemParamsService.autoIncKeyByType(type);
			R = FeignResultVo.initSuc(JsonUtil.toJSON(data));
		}catch (BusiException e){
			R = FeignResultVo.initErr(e.getMessage());
		}catch (Exception e){
			log.error(ExceptionUtil.getExDetail(e));
		}
		return R;
	}


	/**
	 * 系统参数管理-分页显示
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/getSystemParamsPageVo",method = RequestMethod.POST)
	FeignResultVo getSystemParamsPageVo(@RequestBody SystemParamsVo searchVo){
		FeignResultVo R = FeignResultVo.initErr();
		try {
			PageVo data =  systemParamsService.getSystemParamsPageVo(searchVo);
			R = FeignResultVo.initSuc(JsonUtil.toJSON(data));
		}catch (BusiException e){
			R = FeignResultVo.initErr(e.getMessage());
		}catch (Exception e){
			log.error(ExceptionUtil.getExDetail(e));
		}
		return R;

	}


	/**
	 * 系统参数管理-新增,更新
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/execAddSystemParams",method = RequestMethod.POST)
	FeignResultVo execAddSystemParams(@RequestBody SystemParams bean) {
		FeignResultVo R = FeignResultVo.initErr();
		try {
			SystemParams data =  systemParamsService.execAddSystemParams(bean);
			R = FeignResultVo.initSuc(JsonUtil.toJSON(data));
		}catch (BusiException e){
			R = FeignResultVo.initErr(e.getMessage());
		}catch (Exception e){
			log.error(ExceptionUtil.getExDetail(e));
		}
		return R;
	}


	/**
	 * 系统参数管理-修改获取原数据
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/preExecAddSystemParams",method = RequestMethod.POST)
	FeignResultVo  preExecAddSystemParams(@RequestParam("id") Integer id) throws Exception{
		FeignResultVo R = FeignResultVo.initErr();
		try {
			SystemParamsVo data =  systemParamsService.preExecAddSystemParams(id);
			R = FeignResultVo.initSuc(JsonUtil.toJSON(data));
		}catch (BusiException e){
			R = FeignResultVo.initErr(e.getMessage());
		}catch (Exception e){
			log.error(ExceptionUtil.getExDetail(e));
		}
		return R;
	}
	 

}