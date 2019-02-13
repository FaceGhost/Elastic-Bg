package com.faceghost.elasticbg.base.vo;

import com.faceghost.elasticbg.base.model.BaseModel;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * system_log
 * @author 
 */
@Getter
@Setter
public class SystemLogVo extends  BaseModel{
    /**
     * 主键
     */
    private Integer id;

    /**
     * 操作时间
     */
    private Date insertT;

    /**
     * 操作时间
     */
    private String insertTStr;

    /**
     * 操作人员
     */
    private String insertU;

    /**
     * 类名（含包名）
     */
    private String className;

    /**
     * 执行方法
     */
    private String method;

    /**
     * 操作类型，自定义，以便分类
     */
    private String type;

    /**
     * 外键ID
     */
    private String refId;

    /**
     * 操作内容
     */
    private String oper;

    /**
     * 操作IP，内网
     */
    private Integer ip1;

    /**
     * 操作IP，内网
     */
    private String ip1Str;

    /**
     * 操作IP，外网
     */
    private Integer ip2;

    /**
     * 操作IP，外网
     */
    private String ip2Str;


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
}