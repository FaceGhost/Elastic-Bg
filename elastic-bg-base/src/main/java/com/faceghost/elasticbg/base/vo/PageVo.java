package com.faceghost.elasticbg.base.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;


@Getter
@Setter
public class PageVo  implements Serializable {

	private Long total;
	private List list;

	
}
