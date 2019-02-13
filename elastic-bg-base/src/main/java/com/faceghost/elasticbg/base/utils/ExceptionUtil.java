package com.faceghost.elasticbg.base.utils;

import com.faceghost.elasticbg.base.statics.ErrorMsgConst;
import com.faceghost.elasticbg.base.vo.BaseVo;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
/**
 * 异常工具类
 * 
 * @author faceghost.com/profile/java_world
 */
public class ExceptionUtil {

	/**
	 * 获取异常的全部信息
	 * @param ex
	 * @return
	 */
	public static String getExDetail(Throwable ex) {
		
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		ex.printStackTrace(pw);

		try {
			pw.close();
			sw.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pw != null)
					pw.close();
				if (sw != null)
					sw.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return sw.toString();
	}

	/**
	 * 获取异常信息——字符截取
	 * @param ex
	 * @return
	 */
	public static String getExDetail_log(Throwable ex){
		String msg = getExDetail(ex);
		if(!ValidateUtil.validateBlank(msg) && msg.length() > 250){
			msg = msg.substring(0,250);
		}
		return msg;
	}

	/**
	 * 处理RPC异常
	 * @param e
	 * @return
	 */
	public static BaseVo dealRpcError(Exception e){
		BaseVo vo = new BaseVo();
		vo.setSuccess(Boolean.FALSE);
		String msg  = e.getMessage() == null ?  "" : e.getMessage();
		/**
		 * @see Server端 TransactionAop.java
		 */
		if(msg.split("@@").length == 2){
			vo.setMsg(msg.split("@@")[1]);
			vo.setSuccess(Boolean.TRUE);
			return vo;
		}else{
			vo.setMsg(ErrorMsgConst.errSys);
			e.printStackTrace();
		}
		return vo;
	}

}
