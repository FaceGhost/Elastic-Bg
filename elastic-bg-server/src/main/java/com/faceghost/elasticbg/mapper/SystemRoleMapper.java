package com.faceghost.elasticbg.mapper;

import com.faceghost.elasticbg.base.model.SystemRole;
import com.faceghost.elasticbg.base.vo.ExtjsCheckTreeVo;
import com.faceghost.elasticbg.base.vo.SystemRoleVo;
import com.faceghost.elasticbg.util.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SystemRoleMapper  extends BaseMapper<SystemRole>{

    /**
     * 角色管理-分页显示
     * @param searchVo
     * @return
     */
    List<SystemRoleVo> getSystemRolePageVo(@Param("searchVo") SystemRoleVo searchVo);

    /**
     *  角色管理->权限配置
     * @param roleId
     * @return
     */
    List<ExtjsCheckTreeVo> systemRolePermOperPre(@Param("roleId") Integer roleId);

    /**
     * 修改角色 获取原数据
     * @param id
     * @return
     */
    SystemRoleVo preExecAddSystemRole(@Param("id") Integer id) ;


    /**
     * 角色管理-获取某一用户下的所属角色
     * @param userId
     * @return
     */
    List<SystemRoleVo> findRolesBySystemUserId(@Param("userId") String userId);
}
