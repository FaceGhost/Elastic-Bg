package com.faceghost.elasticbg.service.statics;

import com.faceghost.elasticbg.base.exception.BusiException;
import com.faceghost.elasticbg.base.utils.RandomKeyUtil;
import com.faceghost.elasticbg.base.utils.ValidateUtil;
import com.faceghost.elasticbg.conf.PropConf;
import com.faceghost.elasticbg.service.SystemParamsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class SequenceServiceStatic implements InitializingBean{

	private static SystemParamsService systemParamsService;
	private static PropConf propertiesConf;

	@Resource
	public  void setSystemParamsService(SystemParamsService systemParamsService) {
		SequenceServiceStatic.systemParamsService = systemParamsService;
	}

	@Resource
	public  void setPropertiesConf(PropConf propertiesConf) {
		SequenceServiceStatic.propertiesConf = propertiesConf;
	}


	/**
	 * 获取机器码
	 * @return
	 */
	public static String getMachine() {
		return propertiesConf.getMachine();
	}

	@Override
	public void afterPropertiesSet() throws Exception {

		if(ValidateUtil.validateBlank(getMachine())){
			throw new Exception("machine is null");
		}
		log.info("init machine successful，machine is " + getMachine() + "...");
		
	}
	
	private static final String SEQ_SYSTEM_USER_ID = "system_user_id";
	private static final Integer SEQ_SYSTEM_USER_ID_LEN = 2;
	
	
	
	/**
	 * 查询type的最大值
	 * @param type
	 * @return
	 */
	public static Long getMaxKeyByType(String type) throws Exception{
		return systemParamsService.getMaxKeyByType(type, getMachine());
	}

	/**
	 * 创建系统用户ID
	 * @return
	 * @throws Exception
	 */
	public static String createSystemUserId()throws Exception{
		StringBuffer  id =  new StringBuffer();
		Long max = getMaxKeyByType(SEQ_SYSTEM_USER_ID);
		id.append(RandomKeyUtil.getRandomDigitStrNotZero(SEQ_SYSTEM_USER_ID_LEN));
		id.append(max + 1);
		id.append(getMachine());
		int count = systemParamsService.autoIncKeyByType(SEQ_SYSTEM_USER_ID, getMachine(), max);
		if(count <= 0){
			String err  = "after get Key cannot autoIncrement";
			log.error(err);
			throw new BusiException(err);
		}
		return id.toString();
	}
	
	

}
