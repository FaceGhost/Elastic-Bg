package com.faceghost.elasticbg.base.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;

/**
 * system_user_role
 * @author
 */
@Getter
@Setter
public class SystemUserRole  extends BaseModel{
    /**
     * 主键
     */
    @Id
    private Integer id;

    /**
     * 所属用户
     */
    private String systemUserId;

    /**
     * 所属角色
     */
    private Integer systemRoleId;
}