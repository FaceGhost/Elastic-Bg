package com.faceghost.elasticbg.base.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;

/**
 * system_role_permission
 * @author
 */
@Getter
@Setter
public class SystemRolePermission  extends BaseModel{
    /**
     * 主键
     */
    @Id
    private Integer id;

    /**
     * 所属角色
     */
    private Integer systemRoleId;

    /**
     * 所属权限
     */
    private Integer systemPermissionId;
}