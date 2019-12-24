package com.faceghost.elasticbg.aop;

import com.faceghost.elasticbg.base.exception.BusiException;
import com.faceghost.elasticbg.conf.PropConf;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;
import java.util.ArrayList;

@Slf4j
@Aspect
@Component
public class TransactionAop {

    @Autowired
    private PropConf propConf;

    /**
     * 切入点
     */
    @Pointcut("execution( * com.faceghost.elasticbg.service..*.*(..))")
    private void cut(){}

    /**
     * service方法执行前，检查
     * @param joinPoint
     */
    @Before("cut()")
    public void doBefore(JoinPoint joinPoint) throws  Exception{
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature)signature;
        Method targetMethod = methodSignature.getMethod();
        Method realMethod =  joinPoint.getTarget().getClass().getDeclaredMethod(signature.getName(),targetMethod.getParameterTypes());

        if(propConf.getEnvReadonly()){
            /**
             * 是否需要事务
             */
            if(realMethod.isAnnotationPresent(Transactional.class)){
                String package_ = joinPoint.getSignature().getDeclaringTypeName() ;
                String method_ = joinPoint.getSignature().getName();
                ArrayList<String> ignore = new ArrayList<String>();
                ignore.add("updateByLogin");
                ignore.add("saveLog");
                if(!ignore.contains(method_)){
                    throw new BusiException("操作受限：当前系统正在以【只读】模式运行！");
                }

            }


        }


    }

}
