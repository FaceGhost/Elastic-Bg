package com.faceghost.elasticbg.service;

import com.faceghost.elasticbg.base.exception.BusiException;
import com.faceghost.elasticbg.base.exception.RootException;
import com.faceghost.elasticbg.base.model.SystemParams;
import com.faceghost.elasticbg.base.service.SystemParamsService;
import com.faceghost.elasticbg.base.utils.ValidateUtil;
import com.faceghost.elasticbg.base.vo.PageVo;
import com.faceghost.elasticbg.base.vo.SystemParamsVo;
import com.faceghost.elasticbg.mapper.SystemParamsMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class SystemParamsServiceImpl implements SystemParamsService {

	@Autowired
	private SystemParamsMapper systemParamsMapper;

	/**
	 * 查询某一类型的最大值
	 * 
	 * @param type
	 * @return
	 * @throws RootException
	 */
	public Integer getMaxKeyByType(String type) throws Exception {
		if (ValidateUtil.validateBlank(type))
			throw new RootException("查询某一类型的最大值时，类型为空");
		SystemParams key = systemParamsMapper.getMaxKeyByType(type);
		if (key == null) {
			throw new BusiException("类型[" + type + "]的值为空");
		} else {
			return Integer.valueOf(key.getValue());
		}
	}

	/**
	 * 自动新增某一类型的值
	 * 
	 * @param type
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW , rollbackFor =  Exception.class)
	public Integer autoIncKeyByType(String type) {
		return systemParamsMapper.autoIncKeyByType(type);
	}

	/**
	 * 系统参数管理-分页显示
	 * @param searchVo
	 * @return
	 * @throws Exception
	 */
	@Override
	public PageVo getSystemParamsPageVo(SystemParamsVo searchVo) throws Exception {
		PageVo pageList = new PageVo();
		PageHelper.startPage(searchVo.getPage(), searchVo.getLimit());
		List<SystemParamsVo> list = systemParamsMapper.getSystemParamsPageVo(searchVo);
		PageInfo<SystemParamsVo> pageInfo = new PageInfo<SystemParamsVo>(list);
		pageList.setList(pageInfo.getList());
		pageList.setTotal(pageInfo.getTotal());
		return pageList;
	}

	/**
	 * 系统参数管理-新增,更新
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional
	public SystemParams execAddSystemParams(SystemParams bean) throws Exception {
		//是否新增
		if(bean.getId() != null) {
			  systemParamsMapper.updateByPrimaryKeySelective(bean);
		}else {
			 systemParamsMapper.insertSelective(bean);
		}
		return bean;
	}

	/**
	 * 系统参数管理-修改获取原数据
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	public SystemParamsVo preExecAddSystemParams(Integer id) throws Exception {
		return systemParamsMapper.preExecAddSystemParams(id);
	}
}
