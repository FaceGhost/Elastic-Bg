package com.faceghost.elasticbg.mapper;

import com.faceghost.elasticbg.base.model.SystemUser;
import com.faceghost.elasticbg.base.vo.ExtjsCheckTreeVo;
import com.faceghost.elasticbg.base.vo.SystemUserVo;
import com.faceghost.elasticbg.util.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;


public interface SystemUserMapper extends BaseMapper<SystemUser>{

    /**
     * 根据name获取bean
     * @param name
     * @return
     */
    SystemUser getSystemUserByUserName(@Param("name") String name );

    /**
     * 登录成功或失败更新
     * @param status
     * @param retryNumber
     * @param lastRetryTime
     * @param name
     * @return
     */
    int updateByLogin(@Param("status") Byte status,
                      @Param("retryNumber")Integer retryNumber,
                      @Param("lastRetryTime") Date lastRetryTime,
                      @Param("name") String name
                      );

    /**
     * 用户管理-分页显示
     * @param searchVo
     * @return
     */
    List<SystemUserVo> getSystemUserPageVo(@Param("searchVo") SystemUserVo searchVo);

    /**
     * 用户管理-获取角色
     * @param uid
     * @param orgId
     * @return
     */
    List<ExtjsCheckTreeVo> getSystemUserRole(@Param("uid") String uid,
                                             @Param("orgId") Integer orgId
                                            );
    /**
     * 用户管理-新修改用户 获取原数据
     * @param uid
     * @return
     */
    SystemUserVo preExecOperSystemUser(@Param("uid") String uid);
}
