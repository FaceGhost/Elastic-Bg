package com.faceghost.elasticbg.mapper;

import com.faceghost.elasticbg.base.model.SystemPermission;
import com.faceghost.elasticbg.base.vo.ExtjsTreeVo;
import com.faceghost.elasticbg.base.vo.SystemPermissionVo;
import com.faceghost.elasticbg.util.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SystemPermissionMapper  extends BaseMapper<SystemPermission>{

    /**
     * 获取用户权限
     * @param uid
     * @return
     */
    List<ExtjsTreeVo> getSystemUserPermission(@Param("uid") String uid);


    /**
     * 权限配置-分页显示
     * @param searchVo
     * @return
     */
    List<SystemPermissionVo> getSystemPermissionPageVo (@Param("searchVo") SystemPermissionVo searchVo);


    /**
     * 权限配置-分页显示搜索; 新增，添加，上层权限 树形显示
     * @return
     */
    List<ExtjsTreeVo> getSystemPermissionTreeVoForNotLow();


    /**
     * 修改权限 获取原数据
     * @param id
     * @return
     */
    SystemPermissionVo preExecAddSystemOrg(@Param("id") Integer id);


    /**
     * 获取角色下的所属权限
     */
    List<SystemPermissionVo> findPermissionBySystemRoleIds(@Param("rids") List<Integer> rids);

    /**
     * 根据pId查询
     * @param pId
     * @return
     */
    List<SystemPermissionVo> getBeanVoByPid(@Param("pId") String pId);
}
