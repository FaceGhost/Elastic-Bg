package com.faceghost.elasticbg.base.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class SystemOrgVo  implements Serializable {
	/**
	 * 主键
	 */
	private Integer id;

	/**
	 * 组织名称
	 */
	private String name;

	/**
	 * 上层组织名称，忽略pname，前端转json生成pName
	 */
	@JsonProperty("pName")
	private String pName;
	@JsonIgnore
	private String pname;

	/**
	 * 备注
	 */
	private String ps;

	/**
	 * 上层组织ID，忽略pid，前端转json生成pId
	 */
	@JsonProperty("pId")
	private Integer pId;
	@JsonIgnore
	private Integer pid;

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
	 * 联系人
	 */
	private String linkMan;

	/**
	 * 组织名称拼音
	 */
	private String pinYin;

	/**
	 * 地址
	 */
	private String address;

	/**
	 * 固话
	 */
	private String fixedPhone;

	/**
	 * 传真
	 */
	private String fax;

	/**
	 * 树形菜单是否展开，1：是，0：否
	 */
	private String isAutoExpand;

	/**
	 * 树形菜单是否为叶子节点，1：是，0：否
	 */
	private String isLeaf;

	/**
	 * 树形菜单图标
	 */
	private String icon;

	/**
	 * 状态，1：正常，0：冻结
	 */
	private Integer status;

	/**
	 * 状态，ExtJS页面
	 */
	private String statusStr;

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
	 * ExtJS，树形节点是否展开，过渡字段
	 */
	private Boolean expanded = Boolean.FALSE;


	public Boolean getExpanded() {
		if("1".equals(isAutoExpand)){
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}


}
