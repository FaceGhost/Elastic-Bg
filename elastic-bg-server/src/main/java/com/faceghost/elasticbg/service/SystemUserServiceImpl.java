package com.faceghost.elasticbg.service;

import com.faceghost.elasticbg.base.exception.BusiException;
import com.faceghost.elasticbg.base.model.SystemUser;
import com.faceghost.elasticbg.base.model.SystemUserRole;
import com.faceghost.elasticbg.base.service.SystemUserRoleService;
import com.faceghost.elasticbg.base.service.SystemUserService;
import com.faceghost.elasticbg.base.utils.PasswordUtil;
import com.faceghost.elasticbg.base.utils.RandomKeyUtil;
import com.faceghost.elasticbg.base.utils.ValidateUtil;
import com.faceghost.elasticbg.base.vo.ExtjsCheckTreeVo;
import com.faceghost.elasticbg.base.vo.PageVo;
import com.faceghost.elasticbg.base.vo.SystemUserVo;
import com.faceghost.elasticbg.mapper.SystemUserMapper;
import com.faceghost.elasticbg.service.statics.SequenceServiceStatic;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class SystemUserServiceImpl implements SystemUserService {

	@Autowired
	private SystemUserRoleService systemUserRoleService;
	
	@Autowired
	private SystemUserMapper systemUserMapper;
	
	@Autowired
	private PasswordUtil passwordUtil;
	
	@Override
	public SystemUser getSystemUserByUserName(String name) {
		return systemUserMapper.getSystemUserByUserName(name);
	}

	@Override
	@Transactional
	public int updateByLogin(String username, int status, int retryNumber, Date lastRetryTime) {
		return  systemUserMapper.updateByLogin((byte)status,retryNumber,lastRetryTime,username);


	}

	/**
	 * 用户管理-分页显示
	 * @param searchVo
	 * @return
	 * @throws Exception
	 */
	@Override
	public PageVo getSystemUserPageVo(SystemUserVo searchVo) throws Exception {
		PageVo pageList = new PageVo();
		PageHelper.startPage(searchVo.getPage(), searchVo.getLimit());
		List<SystemUserVo> list = systemUserMapper.getSystemUserPageVo(searchVo);
		PageInfo<SystemUserVo> pageInfo = new PageInfo<SystemUserVo>(list);
		pageList.setList(pageInfo.getList());
		pageList.setTotal(pageInfo.getTotal());
		return pageList;
	}

	/**
	 * 用户管理-获取角色
	 * @param orgId
	 * @param uid
	 * @return
	 */
	@Override
	public List<ExtjsCheckTreeVo> getSystemUserRole(Integer orgId, String uid) {
		return systemUserMapper.getSystemUserRole(uid,orgId);
	}

	/**
	 * 用户管理-新增,更新用户
	 * @param bean
	 * @param userRoles
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional
	public SystemUser execAddSystemUser(SystemUser bean, String userRoles) throws Exception {
		boolean isAdd = Boolean.FALSE;
		//新增
		if(ValidateUtil.validateBlank(bean.getId())) {
			isAdd = Boolean.TRUE;
			bean.setId(SequenceServiceStatic.createSystemUserId());
			//新增用户，生产随机6位密码
			bean.setInitPassword(RandomKeyUtil.getRandomStr(6));
			bean.setPassword(bean.getInitPassword());
			passwordUtil.systemUserEncryptPassword(bean);
		}
		List<SystemUserRole> roles= new ArrayList<SystemUserRole>();
		if(!ValidateUtil.validateBlank(userRoles)) {
			for(String id:userRoles.split(",")) {
				SystemUserRole role = new SystemUserRole();
				role.setSystemRoleId(Integer.valueOf(id));
				role.setSystemUserId(bean.getId());
				roles.add(role);
				role = null;
			}
		}
		systemUserRoleService.execAddSystemUseRole(bean.getId(), roles);
		if(isAdd) {
			 systemUserMapper.insertSelective(bean);
		}else{
			systemUserMapper.updateByPrimaryKeySelective(bean);
		}
		return bean;
	}

	/**
	 * 用户管理-新修改用户 获取原数据
	 * @param uid
	 * @return
	 * @throws Exception
	 */
	@Override
	public SystemUserVo preExecOperSystemUser(String uid) throws Exception {
		return systemUserMapper.preExecOperSystemUser(uid);
	}

	/**
	 * 用户管理-重置密码
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional
	public Boolean resetSystemUserPwd(String id) throws Exception {
		SystemUser bean = systemUserMapper.selectByPrimaryKey(id);
		if(bean == null){
			throw  new BusiException("@@该用户可能不存在，请刷新后重试！");
		}
		//新增用户，生产随机6位密码
		bean.setInitPassword(RandomKeyUtil.getRandomStr(6));
		bean.setPassword(bean.getInitPassword());
		passwordUtil.systemUserEncryptPassword(bean);
		systemUserMapper.updateByPrimaryKeySelective(bean);
		return Boolean.TRUE;
	}

	/**
	 * 更新bean
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional
	public int updateBean(SystemUser bean) throws Exception {
		return systemUserMapper.updateByPrimaryKeySelective(bean);
	}
}
