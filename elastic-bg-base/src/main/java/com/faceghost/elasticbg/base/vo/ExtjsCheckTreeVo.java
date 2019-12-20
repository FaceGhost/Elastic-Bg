package com.faceghost.elasticbg.base.vo;

import com.faceghost.elasticbg.base.utils.ValidateUtil;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ExtjsCheckTreeVo implements Serializable{
	
	private String  id;
	private String 	parentId;
	private String  text;
	private String  icon;
	private String  path;
	private Byte 	status;
	private Boolean expanded = Boolean.FALSE;
	private String  isAutoExpand;
	private Boolean checked = Boolean.FALSE;
	private Integer  checked_ = 0 ;
	private String iconCls = "task";
	private List<ExtjsCheckTreeVo> children = new ArrayList<ExtjsCheckTreeVo>();
	
	public Boolean getExpanded() {
		if(!ValidateUtil.validateBlank(isAutoExpand) && "1".equals(isAutoExpand)) return Boolean.TRUE;
		return expanded;
	}

	public Boolean getChecked() {
		if(checked_ != null && checked_ > 0) return Boolean.TRUE;
		return checked;
	}

	
	
	
}
