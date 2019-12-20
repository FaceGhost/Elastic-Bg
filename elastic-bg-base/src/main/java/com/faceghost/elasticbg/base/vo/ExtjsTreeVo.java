package com.faceghost.elasticbg.base.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * ExtJS 树形model
 */
@Getter
@Setter
@ToString
public class ExtjsTreeVo implements Serializable{

	/**
	 * ID
	 */
	private String  id;
	/**
	 * 父ID
	 */
	private String 	parentId;
	/**
	 * 图标
	 */
	private String  icon;
	/**
	 * 名称
	 */
	private String  text;
	/**
	 * URL
	 */
	private String  path;
	/**
	 * 状态
	 */
	private Byte 	status;
	/**
	 * 节点是否展开
	 */
	private Boolean expanded = Boolean.FALSE;
	/**
	 * 节点是否展开，过渡字段
	 */
	private String  isAutoExpand;

	/**
	 * 孩子节点
	 */
	private List<ExtjsTreeVo> children = new ArrayList<ExtjsTreeVo>();


	
}
