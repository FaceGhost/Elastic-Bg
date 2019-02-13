package com.faceghost.elasticbg.base.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class SystemRoleVo implements Serializable{
	/**
	 * 主键
	 */
	private Integer id;

	/**
	 * 角色名称
	 */
	private String name;

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
	 * 所属组织
	 */
	private Integer systemOrgId;

	/**
	 * 备注
	 */
	private String ps;

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

	/**
	 * 组织ID
	 */
	private Integer orgId;

	/**
	 * 组织名称
	 */
	private String orgName;

	/**
	 * 父级组织名称
	 */
	@JsonProperty("pOrgName")
	private String pOrgName;
	@JsonIgnore
	private String porgName;

}