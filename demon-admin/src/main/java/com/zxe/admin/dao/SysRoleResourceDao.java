package com.zxe.admin.dao;

import com.zxe.admin.entity.SysRoleResourceEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author:Elaine
 * @Description:
 * @Date: Created in 9:10 PM 2020/10/29
 * @Version: 1.0
 */
@Mapper
@Component
public interface SysRoleResourceDao {
    /**
     * 通过role_id获取资源
     */
    List<SysRoleResourceEntity> queryResourcesListWithSelected(@Param("rid") Long rid);
    /**
     * 插入用户与资源对应关系
     */
    Integer insertRoleResourceInfo(@Param("roleRes") List<SysRoleResourceEntity> roleRes);
    /**
     * 更新时间
     */
    Integer  updateRoleResourceInfo(@Param("roleRes") SysRoleResourceEntity roleRes);
    /**
    * 删除信息通过roleId
    */
    Integer deleteRoleResourceInfoByRoleId(@Param("roleId") Long roleId);
}
