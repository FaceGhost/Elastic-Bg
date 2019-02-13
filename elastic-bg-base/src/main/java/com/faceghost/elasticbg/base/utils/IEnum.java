package com.faceghost.elasticbg.base.utils;

import java.io.Serializable;

/**
 * <p>
 * 定义所有枚举接口
 * </p>
 * 
 */
public interface IEnum {

	/**
	 * 定义枚举值
	 */
	public Serializable key();


	/**
	 * 定义枚举描述
	 */
	public Serializable value();

}
