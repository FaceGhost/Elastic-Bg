package com.faceghost.elasticbg.base.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import java.util.Date;

/**
 * system_user
 * @author
 */
@Getter
@Setter
public class SystemUser  extends BaseModel{
    /**
     * 主键
     */
    @Id
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
     * 所属组织
     */
    private Integer systemOrgId;

    /**
     * 状态，1：正常，0：冻结
     */
    private Byte status;

    /**
     * 登记时间
     */
    private Date insertT;

    /**
     * 更新时间
     */
    private Date updateT;

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
}