package com.faceghost.elasticbg.base.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池工具类
 *
 */
public class ThreadPoolUtil {

	private ExecutorService service;
	
	private ThreadPoolUtil(){
		int num = Runtime.getRuntime().availableProcessors();
		service = Executors.newFixedThreadPool(num + 1);
	}
	
	private static final ThreadPoolUtil pool = new ThreadPoolUtil();
	
	public static ThreadPoolUtil getInstance(){
		return pool;
	}
	
	/**
	 * 子线程执行
	 * @param runnable
	 */
	public void execTask(Runnable runnable){
		service.execute(runnable);
	}
	
}
