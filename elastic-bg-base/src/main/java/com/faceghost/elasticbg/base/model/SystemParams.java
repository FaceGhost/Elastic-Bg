package com.faceghost.elasticbg.base.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import java.util.Date;

/**
 * system_params
 * @author
 */
@Getter
@Setter
public class SystemParams extends BaseModel {
    /**
     * 主键
     */
    @Id
    private Integer id;

    /**
     * 登记时间
     */
    private Date insertT;

    /**
     * 登记人员
     */
    private String insertU;

    /**
     * 更新时间
     */
    private Date updateT;

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
    private Byte status;

    /**
     * 备注
     */
    private String ps;

}