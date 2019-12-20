package com.faceghost.elasticbg.controller;

import com.faceghost.elasticbg.base.exception.RootException;
import com.faceghost.elasticbg.base.statics.BaseSysConst;
import com.faceghost.elasticbg.base.utils.CheckImageUtil;
import com.faceghost.elasticbg.controller.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;


/**
 * 系统controller
 *
 */
@RequestMapping("/sys")
@Controller("system")
public class SystemController extends BaseController {
	
	
	
	private ByteArrayInputStream inputStream;
	
	
	public ByteArrayInputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(ByteArrayInputStream inputStream) {
		this.inputStream = inputStream;
	}
	
	
	/**
	 * 生成验证码
	 * @throws RootException
	 */
	@RequestMapping("/checkCode")
	public void checkCode() throws RootException {
		try {
			
			CheckImageUtil rdnu = CheckImageUtil.Instance();
			request.getSession().setAttribute(BaseSysConst.systemCheckCode, rdnu.getString());
			InputStream in = rdnu.getImage();
	    	OutputStream out = response.getOutputStream();
	    	byte[]  data = new byte[in.available()];
	    	in.read(data);
	    	response.setContentType("image/png");
	    	out.write(data);
	    	out.flush();
	    	in.close();
	    	out.close();
	    	
		} catch (Exception e) {
			throw new RootException(e.getMessage());
		}
	}
	
	
	
}
