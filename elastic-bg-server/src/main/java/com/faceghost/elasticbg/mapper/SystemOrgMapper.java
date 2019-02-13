package com.faceghost.elasticbg.mapper;

import com.faceghost.elasticbg.base.model.SystemOrg;
import com.faceghost.elasticbg.base.vo.ExtjsTreeVo;
import com.faceghost.elasticbg.base.vo.SystemOrgVo;
import com.faceghost.elasticbg.util.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface SystemOrgMapper extends BaseMapper<SystemOrg> {


    /**
     * 组织管理树形显示
     * @return
     */
     List<ExtjsTreeVo> getSystemOrgTreeVoListBean();


    /**
     * 组织管理分页显示
     * @return
     */
     List<SystemOrgVo> getSystemOrgPageVo(@Param("searchVo") SystemOrgVo searchVo);

    /**
     * 新增或修改-上级组织管理树形显示
     * @return
     */
     List<ExtjsTreeVo> getSystemOrgTreeVoForNotLow();

    /**
     * 修改组织 获取原数据
     * @param id
     * @return
     */
     SystemOrgVo getPreExecAddSystemOrgData(@Param("id") Integer id);
}
