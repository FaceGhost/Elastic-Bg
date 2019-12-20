package com.faceghost.elasticbg.service;

import com.faceghost.elasticbg.base.model.SystemOrg;
import com.faceghost.elasticbg.base.vo.ExtjsTreeVo;
import com.faceghost.elasticbg.base.vo.PageVo;
import com.faceghost.elasticbg.base.vo.SystemOrgVo;
import com.faceghost.elasticbg.mapper.SystemOrgMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Slf4j
@Service
public class SystemOrgService {

	@Autowired
	private SystemOrgMapper systemOrgMapper;


	/**
	 * 组织管理树形显示
	 * @return
	 * @throws Exception
	 */
	public List<ExtjsTreeVo> getSystemOrgTreeVoListBean() throws Exception {
		List<ExtjsTreeVo> data =  systemOrgMapper.getSystemOrgTreeVoListBean();
		return data;
	}

	/**
	 * 组织管理分页显示
	 * @param searchVo
	 * @return
	 * @throws Exception
	 */
	public PageVo getSystemOrgPageVo( SystemOrgVo searchVo) throws Exception {
		PageVo pageList = new PageVo();
		PageHelper.startPage(searchVo.getPage(), searchVo.getLimit());
		List<SystemOrgVo> list = systemOrgMapper.getSystemOrgPageVo(searchVo);
		PageInfo<SystemOrgVo> pageInfo = new PageInfo<SystemOrgVo>(list);
		pageList.setList(pageInfo.getList());
		pageList.setTotal(pageInfo.getTotal());
		return pageList;
	}

	/**
	 * 新增或修改-上级组织管理树形显示
	 * @return
	 * @throws Exception
	 */
	public List<ExtjsTreeVo> getSystemOrgTreeVoForNotLow() throws Exception {
		List<ExtjsTreeVo> data =  systemOrgMapper.getSystemOrgTreeVoForNotLow();
		return data;
	}


	/**
	 * 新增,更新组织
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public SystemOrg execAddSystemOrg(SystemOrg bean) throws Exception {
		//是否新增
		if(bean.getId() != null) {
			  systemOrgMapper.updateByPrimaryKeySelective(bean);
		}else {
			 systemOrgMapper.insertSelective(bean);
		}
		return bean;
	}

	/**
	 * 修改组织 获取原数据
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public SystemOrgVo getPreExecAddSystemOrgData(Integer id) throws Exception {
		return systemOrgMapper.getPreExecAddSystemOrgData(id);
	}
	
	
}
