package com.faceghost.elasticbg.service;

import com.faceghost.elasticbg.base.model.SystemPermission;
import com.faceghost.elasticbg.base.service.SystemPermissionService;
import com.faceghost.elasticbg.base.vo.ExtjsTreeVo;
import com.faceghost.elasticbg.base.vo.PageVo;
import com.faceghost.elasticbg.base.vo.SystemPermissionVo;
import com.faceghost.elasticbg.mapper.SystemPermissionMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class SystemPermissionServiceImpl implements  SystemPermissionService{

	@Autowired
	private SystemPermissionMapper systemPermissionMapper;

	/**
	 * 获取角色下的所属权限
	 * @param rolesIds
	 * @return
	 */
	@Override
	public List<SystemPermissionVo> findPermissionBySystemRoleIds(List<Integer> rolesIds) {
		return systemPermissionMapper.findPermissionBySystemRoleIds(rolesIds);
	}

	/**
	 * 获取用户权限
	 * @param uid
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<ExtjsTreeVo> getSystemUserPermission(String uid) throws Exception {
		return  systemPermissionMapper.getSystemUserPermission(uid);
	}

	/**
	 * 权限配置-分页显示
	 * @param searchVo
	 * @return
	 * @throws Exception
	 */
	@Override
	public PageVo getSystemPermissionPageVo(SystemPermissionVo searchVo) throws Exception {
		PageVo pageList = new PageVo();
		PageHelper.startPage(searchVo.getPage(), searchVo.getLimit());
		List<SystemPermissionVo> list = systemPermissionMapper.getSystemPermissionPageVo(searchVo);
		PageInfo<SystemPermissionVo> pageInfo = new PageInfo<SystemPermissionVo>(list);
		pageList.setList(pageInfo.getList());
		pageList.setTotal(pageInfo.getTotal());
		return pageList;

	}

	/**
	 * 权限配置-分页显示搜索; 新增，添加，上层权限 树形显示
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<ExtjsTreeVo> getSystemPermissionTreeVoForNotLow() throws Exception {
			return systemPermissionMapper.getSystemPermissionTreeVoForNotLow();
	}

	/**
	 * 权限配置-新增,更新权限
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional
	public SystemPermission execAddSystemPerm(SystemPermission bean) throws Exception {
		if(bean.getId() != null) {
			 systemPermissionMapper.updateByPrimaryKeySelective(bean);
		}else {
			 systemPermissionMapper.insertSelective(bean);
		}
		return bean;
	}

	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	@Override
	public SystemPermission getSystemPermssionById(Integer id) {
		return systemPermissionMapper.selectByPrimaryKey(id);
	}

	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	@Override
	public SystemPermissionVo preExecAddSystemOrg(Integer id) {
		return systemPermissionMapper.preExecAddSystemOrg(id);
	}

	/**
	 * 根据pId查询
	 * @param pId
	 * @return
	 */
	@Override
	public List<SystemPermissionVo> getBeanVoByPid(String pId) {
		return systemPermissionMapper.getBeanVoByPid(pId);
	}
}
