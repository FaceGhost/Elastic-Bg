package com.faceghost.elasticbg.service;

import com.faceghost.elasticbg.base.model.SystemRole;
import com.faceghost.elasticbg.base.vo.ExtjsCheckTreeVo;
import com.faceghost.elasticbg.base.vo.PageVo;
import com.faceghost.elasticbg.base.vo.SystemRoleVo;
import com.faceghost.elasticbg.mapper.SystemRoleMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class SystemRoleService  {
	
	@Autowired
	private SystemRoleMapper systemRoleMapper;

	/**
	 * 角色管理-获取某一用户下的所属角色
	 * @param userId
	 * @return
	 */
	public List<SystemRoleVo> findRolesBySystemUserId(String userId) {
		return systemRoleMapper.findRolesBySystemUserId(userId);
	}

	/**
	 * 角色管理-分页显示
	 * @param searchVo
	 * @return
	 * @throws Exception
	 */
	public PageVo getSystemRolePageVo(SystemRoleVo searchVo) throws Exception {
		PageVo pageList = new PageVo();
		PageHelper.startPage(searchVo.getPage(), searchVo.getLimit());
		List<SystemRoleVo> list = systemRoleMapper.getSystemRolePageVo(searchVo);
		PageInfo<SystemRoleVo> pageInfo = new PageInfo<SystemRoleVo>(list);
		pageList.setList(pageInfo.getList());
		pageList.setTotal(pageInfo.getTotal());
		return pageList;
	}

	/**
	 * 角色管理-新增,更新角色
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public SystemRole execAddSystemRole(SystemRole bean) throws Exception {
		if(bean.getId() != null) {
			 systemRoleMapper.updateByPrimaryKeySelective(bean);
		}else {
			 systemRoleMapper.insertSelective(bean);
		}
		return bean;
	}

	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	public SystemRole getSystemRoleById(Integer id) {
		return systemRoleMapper.selectByPrimaryKey(id);
	}

	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	public SystemRoleVo preExecAddSystemRole(Integer id) {
		return systemRoleMapper.preExecAddSystemRole(id);
	}

	/**
	 * 角色管理->权限配置
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public List<ExtjsCheckTreeVo> systemRolePermOperPre(Integer roleId) throws Exception {
		return systemRoleMapper.systemRolePermOperPre(roleId);
	}

	

}
