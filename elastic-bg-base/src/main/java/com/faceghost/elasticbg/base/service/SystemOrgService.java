package com.faceghost.elasticbg.base.service;

import com.anoyi.grpc.annotation.GrpcService;
import com.faceghost.elasticbg.base.BaseApp;
import com.faceghost.elasticbg.base.model.SystemOrg;
import com.faceghost.elasticbg.base.vo.ExtjsTreeVo;
import com.faceghost.elasticbg.base.vo.PageVo;
import com.faceghost.elasticbg.base.vo.SystemOrgVo;

import java.util.List;

@GrpcService(server = BaseApp.RPC_SERVER)
public interface SystemOrgService {

	/**
	 * 组织管理树形显示
	 * @return
	 * @throws Exception
	 */
	public List<ExtjsTreeVo> getSystemOrgTreeVoListBean() throws Exception;

	
	/**
	 * 组织管理分页显示
	 * @param
	 * @return
	 */
	PageVo getSystemOrgPageVo(SystemOrgVo searchvo) throws Exception;

	
	/**
	 * 新增或修改-上级组织管理树形显示
	 * @return
	 * @throws Exception
	 */
	List<ExtjsTreeVo> getSystemOrgTreeVoForNotLow() throws Exception;
	
	
	/**
	 * 新增,更新组织
	 * @param bean
	 * @return
	 */
	SystemOrg execAddSystemOrg(SystemOrg bean) throws Exception;
	

	/**
	 * 修改组织 获取原数据
	 * @param id
	 * @return
	 */
	SystemOrgVo getPreExecAddSystemOrgData(Integer id) throws Exception;

}
