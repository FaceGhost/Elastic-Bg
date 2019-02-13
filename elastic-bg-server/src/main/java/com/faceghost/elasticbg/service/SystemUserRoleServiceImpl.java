package com.faceghost.elasticbg.service;

import com.faceghost.elasticbg.base.model.SystemUserRole;
import com.faceghost.elasticbg.base.service.SystemUserRoleService;
import com.faceghost.elasticbg.mapper.SystemUserRoleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class SystemUserRoleServiceImpl implements SystemUserRoleService {

	@Autowired
	private SystemUserRoleMapper systemUserRoleMapper;

	/**
	 * 更新用户角色权限
	 * @param uid
	 * @param datas
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional
	public int execAddSystemUseRole(String uid, List<SystemUserRole> datas) throws Exception {
		this.deleteSystemUserRole(uid);
		if(datas != null && !datas.isEmpty()) {
			return systemUserRoleMapper.insertList(datas);
		}
		return 1;
	}
	


	/**
	 * 删除用户的全部角色
	 * @param uid
	 */
	@Transactional
	private void deleteSystemUserRole(String uid) {
		systemUserRoleMapper.deleteSystemUserRole(uid);
	}
	

	
}
