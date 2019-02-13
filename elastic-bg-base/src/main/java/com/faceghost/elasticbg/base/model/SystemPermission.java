package com.faceghost.elasticbg.base.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import java.util.Date;
/**
 * system_permission
 * @author
 */
@Getter
@Setter
public class SystemPermission  extends BaseModel{
    /**
     * 主键
     */
    @Id
    private Integer id;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 权限级别，1：一级，2：二级，3：三级，4：四级，5：五级
     */
    private String type;

    /**
     * 访问URL
     */
    private String url;

    /**
     * 状态，1：正常，0：冻结
     */
    private Byte status;

    /**
     * 父权限ID
     */
    private Integer pId;

    /**
     * 权限
     */
    private String permission;

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
     * 备注
     */
    private String ps;

    /**
     * 排序
     */
    private Integer position;

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

}