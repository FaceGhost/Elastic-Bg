package com.faceghost.elasticbg.base.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import java.util.Date;

/**
 * system_org
 * @author
 */
@Getter
@Setter
public class SystemOrg extends BaseModel {
    /**
     * 主键
     */
    @Id
    private Integer id;

    /**
     * 组织名称
     */
    private String name;

    /**
     * 备注
     */
    private String ps;

    /**
     * 上层组织ID
     */
    private Integer pId;

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
     * 联系人
     */
    private String linkMan;

    /**
     * 组织名称拼音
     */
    private String pinYin;

    /**
     * 地址
     */
    private String address;

    /**
     * 固话
     */
    private String fixedPhone;

    /**
     * 传真
     */
    private String fax;

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
     * 状态，1：正常，0：冻结
     */
    private Byte status;
}