package com.faceghost.elasticbg.service;

import com.faceghost.elasticbg.base.exception.RootException;
import com.faceghost.elasticbg.base.model.SystemParams;
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
import java.util.Objects;

@Slf4j
@Service
public class SystemParamsService  {

	@Autowired
	private SystemParamsMapper systemParamsMapper;

	/**
	 * 查询某一类型的最大值
	 * @param param
	 * @param machine
	 * @return
	 * @throws Exception
	 */
	public Long getMaxKeyByParam(String param, String machine) throws Exception {
		if(ValidateUtil.validateBlank(param)) throw new RootException("查询某一类型的最大值时，类型为空");
		Long value = systemParamsMapper.getValueByParam(param);
		if(Objects.isNull(value)){
			throw new RootException(String.format("查询type[%s],machine[%s]为空", param, machine));
		}else{
			return value;
		}
	}

	/**
	 *  自动新增某一类型的值
	 * @param param
	 * @param machine
	 * @param oldValue
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW , rollbackFor =  Exception.class)
	public Integer autoIncKeyByParam(String param, String machine, Long oldValue) {
		return systemParamsMapper.autoIncKeyByParamAndOldValue(param, oldValue);
	}
	/**
	 * 系统参数管理-分页显示
	 * @param searchVo
	 * @return
	 * @throws Exception
	 */
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
	public SystemParamsVo preExecAddSystemParams(Integer id) throws Exception {
		return systemParamsMapper.preExecAddSystemParams(id);
	}
}
