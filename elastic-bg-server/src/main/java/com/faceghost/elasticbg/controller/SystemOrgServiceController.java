package com.faceghost.elasticbg.controller;

import com.faceghost.elasticbg.base.exception.BusiException;
import com.faceghost.elasticbg.base.model.SystemOrg;
import com.faceghost.elasticbg.base.utils.ExceptionUtil;
import com.faceghost.elasticbg.base.utils.JsonUtil;
import com.faceghost.elasticbg.base.utils.ValidateUtil;
import com.faceghost.elasticbg.base.vo.*;
import com.faceghost.elasticbg.cons.AppCons;
import com.faceghost.elasticbg.service.SystemOrgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/systemOrg")
@RestController
public class SystemOrgServiceController {

	@Autowired
	private SystemOrgService systemOrgService;

	/**
	 * 组织管理树形显示
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getSystemOrgTreeVoListBean",method = RequestMethod.POST)
	FeignResultVo getSystemOrgTreeVoListBean(){
		FeignResultVo R = FeignResultVo.initErr();
		try {
			List<ExtjsTreeVo>  data =   systemOrgService.getSystemOrgTreeVoListBean();
			if(data != null && !data.isEmpty()){
				for(ExtjsTreeVo bean : data){
					if(ValidateUtil.validateBlank(bean.getIcon())){
						bean.setIcon(AppCons.EXT_ICON_PREFIX + AppCons.EXT_ICON_DEFAULT);
					}else{
						bean.setIcon(AppCons.EXT_ICON_PREFIX + bean.getIcon());
					}
					if("1".equals(bean.getIsAutoExpand())){
						bean.setExpanded(true);
					}else{
						bean.setExpanded(false);
					}
				}
			}
			R = FeignResultVo.initSuc(JsonUtil.toJSON(data));
		}catch (BusiException e){
			R = FeignResultVo.initErr(e.getMessage());
		}catch (Exception e){
			log.error(ExceptionUtil.getExDetail(e));
		}
		return R;
	}

	
	/**
	 * 组织管理分页显示
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/getSystemOrgPageVo",method = RequestMethod.POST)
	FeignResultVo getSystemOrgPageVo(@RequestBody SystemOrgVo searchvo){
		FeignResultVo R = FeignResultVo.initErr();
		try {
			PageVo data =  systemOrgService.getSystemOrgPageVo(searchvo);
			R = FeignResultVo.initSuc(JsonUtil.toJSON(data));
		}catch (BusiException e){
			R = FeignResultVo.initErr(e.getMessage());
		}catch (Exception e){
			log.error(ExceptionUtil.getExDetail(e));
		}
		return R;
	}


	/**
	 * 新增或修改-上级组织管理树形显示
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getSystemOrgTreeVoForNotLow",method = RequestMethod.POST)
	FeignResultVo getSystemOrgTreeVoForNotLow() {
		FeignResultVo R = FeignResultVo.initErr();
		try {
			List<ExtjsTreeVo>  data =    systemOrgService.getSystemOrgTreeVoForNotLow();
			if(data != null && !data.isEmpty()){
				for(ExtjsTreeVo bean : data){
					if(ValidateUtil.validateBlank(bean.getIcon())){
						bean.setIcon(AppCons.EXT_ICON_PREFIX + AppCons.EXT_ICON_DEFAULT);
					}else{
						bean.setIcon(AppCons.EXT_ICON_PREFIX + bean.getIcon());
					}
					if("1".equals(bean.getIsAutoExpand())){
						bean.setExpanded(true);
					}else{
						bean.setExpanded(false);
					}
				}
			}
			R = FeignResultVo.initSuc(JsonUtil.toJSON(data));
		}catch (BusiException e){
			R = FeignResultVo.initErr(e.getMessage());
		}catch (Exception e){
			log.error(ExceptionUtil.getExDetail(e));
		}
		return R;
	}


	/**
	 * 新增,更新组织
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/execAddSystemOrg",method = RequestMethod.POST)
	FeignResultVo  execAddSystemOrg(@RequestBody SystemOrg bean){
		FeignResultVo R = FeignResultVo.initErr();
		try {
			SystemOrg data =  systemOrgService.execAddSystemOrg(bean);
			R = FeignResultVo.initSuc(JsonUtil.toJSON(data));
		}catch (BusiException e){
			R = FeignResultVo.initErr(e.getMessage());
		}catch (Exception e){
			log.error(ExceptionUtil.getExDetail(e));
		}
		return R;
	}
	

	/**
	 * 修改组织 获取原数据
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getPreExecAddSystemOrgData",method = RequestMethod.POST)
	FeignResultVo  getPreExecAddSystemOrgData(@RequestParam("id") Integer id) {
		FeignResultVo R = FeignResultVo.initErr();
		try {
			SystemOrgVo data =  systemOrgService.getPreExecAddSystemOrgData(id);
			R = FeignResultVo.initSuc(JsonUtil.toJSON(data));
		}catch (BusiException e){
			R = FeignResultVo.initErr(e.getMessage());
		}catch (Exception e){
			log.error(ExceptionUtil.getExDetail(e));
		}
		return R;
	}

}
