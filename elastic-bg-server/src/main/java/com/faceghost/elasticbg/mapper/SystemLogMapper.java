package com.faceghost.elasticbg.mapper;

import com.faceghost.elasticbg.base.model.SystemLog;
import com.faceghost.elasticbg.base.vo.SystemLogVo;
import com.faceghost.elasticbg.util.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SystemLogMapper  extends BaseMapper<SystemLog> {

    /**
     * 日志管理-分页显示
     * @return
     */
    List<SystemLogVo> getSystemLogPageVo(@Param("searchVo") SystemLogVo searchVo);

    /**
     * 日志管理-保存
     * @param record
     * @return
     */
    int saveBean(SystemLogVo record);

}