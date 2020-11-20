package com.zxe.admin.dao;

import com.zxe.admin.entity.SysUserRoleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * @Author:Elaine
 * @Description:
 * @Date: Created in 9:10 PM 2020/10/29
 * @Version: 1.0
 */
@Mapper
@Component
public interface SysUserRoleDao {
    /**
     * 查询角色
     * @param roleId
     * @return
     */
    Integer findUserIdByRoleId(@Param("roleId") Long roleId);
    /**
     * 查询角色
     * @param userId
     * @return
     */
    SysUserRoleEntity findUserRoledId(@Param("userId") Long userId);
    /**
     * 插入角色信息
     * @param role
     * @return
     */
    Integer insertRolesInfo(@Param("role") SysUserRoleEntity role);
    /**
     * 更新角色信息
     * @param role
     * @return
     */
    Integer updateRolesInfo(@Param("role") SysUserRoleEntity role);
}
