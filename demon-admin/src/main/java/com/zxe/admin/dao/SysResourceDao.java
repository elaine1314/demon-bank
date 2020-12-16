package com.zxe.admin.dao;

import com.zxe.admin.entity.SysResourceEntity;
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
public interface SysResourceDao {
    /**
     * 通过ID获取权限
     */
    List<SysResourceEntity> listByUserId(@Param("userId") Long userId);
    /**
     * 获取所有资源
     */
    List<SysResourceEntity> getResourcesInfo();
    /**
     * 获取资源类型为menu的可用的值
     */
    List<SysResourceEntity> listAllAvailableMenu();
    /**
     * 通过Parent_id获取数据
     */
    List<SysResourceEntity> listMenuResourceByPid(@Param("pid") Long pid);
}
