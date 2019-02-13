package com.faceghost.elasticbg.base.vo;

import com.faceghost.elasticbg.base.utils.ValidateUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class SystemPermissionVo implements Serializable{

	/**
	 * 主键
	 */
	private Integer id;

	/**
	 * 权限名称
	 */
	private String name;

	/**
	 * 父级权限名称
	 */
	@JsonProperty("pName")
	private String pName;
	@JsonIgnore
	private String pname;

	/**
	 * 权限级别，1：一级，2：二级，3：三级，4：四级，5：五级
	 */
	private String type;

	/**
	 * 访问URL
	 */
	private String url;

	/**
	 * 状态，1：正常，0：冻结
	 */
	private Byte status;

	/**
	 * 父权限ID
	 */
	@JsonProperty("pId")
	private Integer pId;
	@JsonIgnore
	private Integer pid;

	/**
	 * 权限
	 */
	private String permission;

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
	 * 备注
	 */
	private String ps;

	/**
	 * 排序
	 */
	private Integer position;

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

	public String getIcon() {
		if(ValidateUtil.validateBlank(icon)){
			return "icon152.png";
		}
		return icon;
	}
}