package com.faceghost.elasticbg.mapper;

import com.faceghost.elasticbg.base.model.SystemParams;
import com.faceghost.elasticbg.base.vo.SystemParamsVo;
import com.faceghost.elasticbg.util.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface SystemParamsMapper extends BaseMapper<SystemParams> {

    /**
     * 查询某一类型的最大值
     * @param param
     * @return
     */
    @Select({"select value from system_params where param = #{param}"})
    Long getValueByParam(@Param("param") String param);

    /**
     * 自动新增某一类型的值
     * @param param
     * @param oldValue
     * @return
     */
    @Update({"update system_params set value = value + 1 where param = #{param} and value = #{oldValue}"})
    Integer autoIncKeyByParamAndOldValue(@Param("param")String param,@Param("oldValue") Long oldValue);


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
