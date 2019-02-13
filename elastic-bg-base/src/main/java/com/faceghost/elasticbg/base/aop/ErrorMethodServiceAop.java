package com.faceghost.elasticbg.base.aop;

import com.faceghost.elasticbg.base.annotation.ErrorMethodService;
import com.faceghost.elasticbg.base.exception.BusiException;
import com.faceghost.elasticbg.base.utils.ExceptionUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
/**
 * servcie 注解异常切面
 * 
 * @author faceghost.com/profile/java_world
 */
@Scope
@Aspect
@Component
public class ErrorMethodServiceAop {

	private static  final Logger log = LoggerFactory.getLogger(ErrorMethodServiceAop.class);


	@AfterThrowing(value="@annotation(sea)",throwing="e")
	public void doAfterThrowing(JoinPoint joinPoint,ErrorMethodService sea,Throwable e)  throws Exception{
			// 获得目标方法名
			String method = joinPoint.getSignature().getDeclaringTypeName() + joinPoint.getSignature().getName();

			/**
			 * 业务类异常，忽略
			 */
			if (e instanceof BusiException) {
				return;
			}
			
			/**
			 * 获取参数值
			 */
			StringBuffer params = new StringBuffer();
			if (joinPoint.getArgs() != null) {
				int len = joinPoint.getArgs().length;
				for (int i = 0; i < len; i++) {
					params.append(joinPoint.getArgs()[i]);
					if(i != len - 1) {
						params.append(",");
					}
				}
			}
			
			/**
			 * 描述
			 */
			String desc = sea.desc();
			
			log.info(String.format("%s方法抛出异常,描述:%s", method,desc));
			log.info(String.format("%s方法抛出异常,参数值:%s", method,params.toString()));
			log.info(String.format("%s方法抛出异常,异常:%s", method,ExceptionUtil.getExDetail(e)));
			
			/**
			 * 是否发送邮件
			 */
			if(sea.isSendEmail()) {
				String sendTo = sea.sendTo();
				String sendCc = sea.sendCc();
				log.info(String.format("%s方法抛出异常,发送邮件,收件人:%s,抄送:%s", method,sendTo,sendCc));
				
				/**
				 * 调用发送邮件
				 */
			}
			
	}
}
