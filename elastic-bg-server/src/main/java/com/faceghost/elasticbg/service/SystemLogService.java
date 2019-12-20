package com.faceghost.elasticbg.service;

import com.faceghost.elasticbg.base.utils.ExceptionUtil;
import com.faceghost.elasticbg.base.utils.ValidateUtil;
import com.faceghost.elasticbg.base.utils.ip.IPSearchUtil;
import com.faceghost.elasticbg.base.vo.PageVo;
import com.faceghost.elasticbg.base.vo.SystemLogVo;
import com.faceghost.elasticbg.mapper.SystemLogMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Slf4j
@Service
public class SystemLogService {

	@Autowired
	private SystemLogMapper systemLogMapper;

	private static final String ipdbPath =  SystemLogService.class.getResource("/ip2region.db").getPath();



	/**
	 * 日志管理-分页显示
	 * @param searchVo
	 * @return
	 * @throws Exception
	 */
	public PageVo getSystemLogPageVo( SystemLogVo searchVo) throws Exception {
		PageVo pageList = new PageVo();
		PageHelper.startPage(searchVo.getPage(), searchVo.getLimit());
		List<SystemLogVo> list = systemLogMapper.getSystemLogPageVo(searchVo);
		PageInfo<SystemLogVo> pageInfo = new PageInfo<SystemLogVo>(list);
		pageList.setList(pageInfo.getList());
		pageList.setTotal(pageInfo.getTotal());
		return pageList;
	}

	/**
	 * 日志管理-记录日志
	 * @param logType
	 * @param uId
	 * @param oper
	 * @param clazzName
	 * @param methodName
	 * @param ip1Str
	 * @param ip2Str
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public int saveLog(String logType, String uId, String oper,String refId, String clazzName, String methodName,String ip1Str,String ip2Str)  {
		SystemLogVo bean  = new SystemLogVo();
		bean.setInsertT(new Date());
		bean.setInsertU(uId);
		bean.setClassName(clazzName);
		bean.setMethod(methodName);
		if(!ValidateUtil.validateBlank(refId) && !"null".equals(refId)){
			bean.setRefId(refId);
		}
		bean.setType(logType);
		bean.setOper(oper);
		bean.setIp1Str(ip1Str);
		bean.setIp2Str(ip2Str);

		try{

			String city = "";
			if(!ValidateUtil.validateBlank(ip2Str) && ip2Str.indexOf(".") > 0){
				city = IPSearchUtil.getCityInfo(ip2Str,ipdbPath);
			}
			if(city.length() > 25 ){
				city  = city.substring(0,25);
			}
			bean.setCity(city);
		}catch (Exception e){
				log.info("根据IP[{}]获取城市失败,错误:{}",ip2Str, ExceptionUtil.getExDetail(e));
		}


		return systemLogMapper.saveBean(bean);
	}


}
