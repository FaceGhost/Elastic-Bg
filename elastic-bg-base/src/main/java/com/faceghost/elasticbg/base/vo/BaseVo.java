package com.faceghost.elasticbg.base.vo;

import com.faceghost.elasticbg.base.statics.ErrorMsgConst;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
public class BaseVo implements Serializable {
	
	private Boolean success = Boolean.FALSE;
	private String msg = ErrorMsgConst.errSys;
	private String tip;
	private Object data;
	

	
	
	
	
	
	
}
