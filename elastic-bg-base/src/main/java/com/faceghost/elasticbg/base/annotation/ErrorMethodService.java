package com.faceghost.elasticbg.base.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解 拦截service异常信息
 * @author faceghost.com/profile/java_world
 *
 */
@Target({ElementType.PARAMETER,ElementType.METHOD})//使用于方法参数，及方法上
@Retention(RetentionPolicy.RUNTIME)//会在class字节码文件中存在，在运行时可以通过反射获取到
@Documented  //将被包含在javadoc中
public @interface ErrorMethodService {

	/**
	 * 描述
	 */
	String desc();
	
	/**
	 * 是否发送邮件
	 */
	boolean isSendEmail();
	
	/**
	 * 邮件收件人
	 * @return
	 */
	String sendTo() default "";
	
	/**
	 * 邮件抄送人
	 * @return
	 */
	String sendCc() default "";
	
	
}
