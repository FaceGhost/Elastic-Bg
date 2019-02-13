package com.faceghost.elasticbg.base.interceptor;


import com.faceghost.elasticbg.base.BaseApp;
import com.faceghost.elasticbg.base.statics.BaseSysConst;
import org.springframework.web.context.ContextLoader;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class WebAppContextInit  implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		BaseSysConst.WEB_APP_CONTEXT =  ContextLoader.getCurrentWebApplicationContext();
		//init
		new BaseApp();
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
	}

}
