package com.faceghost.elasticbg.base.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import java.util.Date;

/**
 * system_role
 * @author
 */
@Getter
@Setter
public class SystemRole  extends BaseModel{
    /**
     * 主键
     */
    @Id
    private Integer id;

    /**
     * 角色名称
     */
    private String name;

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
     * 所属组织
     */
    private Integer systemOrgId;

    /**
     * 备注
     */
    private String ps;

}