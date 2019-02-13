package com.faceghost.elasticbg.mapper;

import com.faceghost.elasticbg.base.model.SystemUserRole;
import com.faceghost.elasticbg.util.BaseMapper;
import org.apache.ibatis.annotations.Param;


public interface SystemUserRoleMapper extends BaseMapper<SystemUserRole>{

    /**
     * 删除用户的全部角色
     * @param uid
     * @return
     */
    int deleteSystemUserRole(@Param("uid") String uid);
 
}
