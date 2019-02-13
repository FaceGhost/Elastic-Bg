package com.faceghost.elasticbg.mapper;

import com.faceghost.elasticbg.base.model.SystemParams;
import com.faceghost.elasticbg.base.vo.SystemParamsVo;
import com.faceghost.elasticbg.util.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SystemParamsMapper extends BaseMapper<SystemParams> {

    /**
     * 查询某一类型的最大值
     * @param type
     * @return
     */
    SystemParams getMaxKeyByType(@Param("type") String type);

    /**
     * 自动新增某一类型的值
     * @param type
     * @return
     */
    int autoIncKeyByType(@Param("type") String type);


    /**
     * 系统参数管理-分页显示
     * @param
     * @return
     */
    List<SystemParamsVo> getSystemParamsPageVo(@Param("searchVo") SystemParamsVo searchVo) throws Exception;

    /**
     * 系统参数管理-修改获取原数据
     * @param id
     * @return
     */
    SystemParamsVo preExecAddSystemParams(@Param("id") Integer id);
}
