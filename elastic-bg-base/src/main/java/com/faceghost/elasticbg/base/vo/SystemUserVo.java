package com.faceghost.elasticbg.base.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class SystemUserVo implements Serializable{
	/**
	 * 主键
	 */
	private String id;

	/**
	 * 用户账号
	 */
	private String name;

	/**
	 * 用户密码，暗文，不可逆
	 */
	private String password;

	/**
	 * 初始、重置密码，明文
	 */
	private String initPassword;

	/**
	 * 加密盐
	 */
	private String salt;


	/**
	 * 状态，1：正常，0：冻结
	 */
	private Byte status;

	/**
	 * 登记时间
	 */
	private String insertT;

	/**
	 * 更新时间
	 */
	private String updateT;

	/**
	 * 登记人员
	 */
	private String insertU;

	/**
	 * 更新人员
	 */
	private String updateU;

	/**
	 * 手机
	 */
	private String telPhone;

	/**
	 * 固话
	 */
	private String fixedPhone;

	/**
	 * 真实姓名
	 */
	private String realName;

	/**
	 * 电子邮箱
	 */
	private String email;

	/**
	 * QQ
	 */
	private Integer qq;

	/**
	 * 地址
	 */
	private String address;

	/**
	 * 备注
	 */
	private String ps;

	/**
	 * 最后一次尝试登记系统时间
	 */
	private Date lastRetryTime;

	/**
	 * 登录系统失败次数
	 */
	private Integer retryNumber;

	/**
	 * 组织ID
	 */
	private Integer orgId;

	/**
	 * 组织名称
	 */
	private String orgName;

	/**
	 * 所属角色ID集合
	 */
	private String roleIds;

	/**
	 * 所属角色名称集合
	 */
	private String roleNames;

	/**
	 * ExtJS，查询搜索起始日期
	 */
	private String searchTS;

	/**
	 * ExtJS，查询搜索终止日期
	 */
	private String searchTE;

	/**
	 * ExtJS，分页，页码
	 */
	private Integer page  = 0;
	/**
	 * ExtJs，分页，页大小
	 */
	private Integer limit = 20;


    
}