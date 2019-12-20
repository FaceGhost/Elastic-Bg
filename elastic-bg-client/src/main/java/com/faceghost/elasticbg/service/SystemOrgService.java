package com.faceghost.elasticbg.service;

import com.faceghost.elasticbg.base.model.SystemOrg;
import com.faceghost.elasticbg.base.vo.ExtjsTreeVo;
import com.faceghost.elasticbg.base.vo.PageVo;
import com.faceghost.elasticbg.base.vo.SystemOrgVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "${elasticbg.remote.server-name}",path = "${elasticbg.remote.server-path}")
public interface SystemOrgService {

	/**
	 * 组织管理树形显示
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getSystemOrgTreeVoListBean",method = RequestMethod.POST)
	List<ExtjsTreeVo> getSystemOrgTreeVoListBean() throws Exception;

	
	/**
	 * 组织管理分页显示
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/getSystemOrgPageVo",method = RequestMethod.POST)
	PageVo getSystemOrgPageVo(@RequestBody SystemOrgVo searchvo) throws Exception;


	/**
	 * 新增或修改-上级组织管理树形显示
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getSystemOrgTreeVoForNotLow",method = RequestMethod.POST)
	List<ExtjsTreeVo> getSystemOrgTreeVoForNotLow() throws Exception;


	/**
	 * 新增,更新组织
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/execAddSystemOrg",method = RequestMethod.POST)
	SystemOrg execAddSystemOrg(@RequestBody SystemOrg bean) throws Exception;
	

	/**
	 * 修改组织 获取原数据
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getPreExecAddSystemOrgData",method = RequestMethod.POST)
	SystemOrgVo getPreExecAddSystemOrgData(@RequestParam("id") Integer id) throws Exception;

}
