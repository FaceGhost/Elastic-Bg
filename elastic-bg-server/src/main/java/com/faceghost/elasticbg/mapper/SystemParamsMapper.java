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
     * @param type
     * @param machine
     * @return
     */
    @Select({"select value from qa_no where type = #{type} and machine = #{machine}"})
    Long getValueByTypeAndMachine(@Param("type") String type, @Param("machine") String machine);

    /**
     * 自动新增某一类型的值
     * @param type
     * @param machine
     * @param oldValue
     * @return
     */
    @Update({"update qa_no set value = value + 1 where type = #{type} and machine = #{machine} and value = #{oldValue}"})
    Integer autoIncKeyByType(@Param("type")String type,@Param("machine") String machine,@Param("oldValue") Long oldValue);


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
