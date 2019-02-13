package com.faceghost.elasticbg.base.statics;

import org.springframework.context.ApplicationContext;

public class BaseSysConst {

	/**
	 * 该值会在web容器启动时由WebAppContextListener初始化
	 */
	public static ApplicationContext WEB_APP_CONTEXT = null;
	
	public static final String systemCheckCode = "systemCheckCode";
	
	public static final String systemSessionUser = "shiroUser";
	
	

}

