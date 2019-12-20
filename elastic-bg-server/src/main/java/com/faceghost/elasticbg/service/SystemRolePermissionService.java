package com.faceghost.elasticbg.service;

import com.faceghost.elasticbg.base.model.SystemRolePermission;
import com.faceghost.elasticbg.mapper.SystemRolePermissionMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class SystemRolePermissionService  {

	
	@Autowired
	private SystemRolePermissionMapper systemRolePermissionMapper;

	/**
	 * 角色管理->更新角色权限
	 * @param roleId
	 * @param datas
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public int execAddSystemRolePerm(Integer roleId,List<SystemRolePermission> datas) throws Exception {
		this.deleteSystemRolePerm(roleId);
		if(datas != null && !datas.isEmpty()) {
			return systemRolePermissionMapper.insertList(datas);
		}
		return 1;
	}


	/**
	 * 删除某一角色的全部权限
	 * @param roleId
	 */
	@Transactional
	private void deleteSystemRolePerm(Integer roleId) {
		systemRolePermissionMapper.deleteSystemRolePerm(roleId);
	}
	
}
