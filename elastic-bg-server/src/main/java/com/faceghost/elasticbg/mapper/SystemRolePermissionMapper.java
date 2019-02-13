package com.faceghost.elasticbg.mapper;

import com.faceghost.elasticbg.base.model.SystemRolePermission;
import com.faceghost.elasticbg.util.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface SystemRolePermissionMapper extends BaseMapper<SystemRolePermission> {

    /**
     * 删除某一角色的全部权限
     * @param roleId
     * @return
     */
    int deleteSystemRolePerm(@Param("roleId") Integer roleId);
}
