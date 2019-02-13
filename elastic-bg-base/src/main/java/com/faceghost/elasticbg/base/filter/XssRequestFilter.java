package com.faceghost.elasticbg.base.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class XssRequestFilter implements Filter {

	@Override
	public void destroy() {
		
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper((HttpServletRequest) request);
		  chain.doFilter(xssRequest, response);
	}
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}
	

}
