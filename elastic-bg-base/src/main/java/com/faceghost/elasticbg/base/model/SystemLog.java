package com.faceghost.elasticbg.base.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import java.util.Date;

/**
 * system_log
 * @author 
 */
@Getter
@Setter
public class SystemLog extends  BaseModel{
    /**
     * 主键
     */
    @Id
    private Integer id;

    /**
     * 操作时间
     */
    private Date insertT;

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
     * 操作IP，外网
     */
    private Integer ip2;

    /**
     * 备注
     */
    private String ps;
}