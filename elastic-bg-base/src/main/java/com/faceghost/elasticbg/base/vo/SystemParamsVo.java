package com.faceghost.elasticbg.base.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
public class SystemParamsVo implements Serializable{
    /**
     * 主键
     */
    private Integer id;

    /**
     * 登记时间
     */
    private String insertT;

    /**
     * 登记人员
     */
    private String insertU;

    /**
     * 更新时间
     */
    private String updateT;

    /**
     * 更新人员
     */
    private String updateU;

    /**
     * 类型，sequence：序列
     */
    private String type;

    /**
     * 参数
     */
    private String param;

    /**
     * 参数值
     */
    private String value;

    /**
     * 状态，1：正常，0：冻结
     */
    private Integer status;

    /**
     * 备注
     */
    private String ps;


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

}