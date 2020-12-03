package com.zxe.admin.dao;

import com.zxe.admin.entity.SysRoleEntity;
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
public interface SysRoleDao {

    /**
     * 通过ID获取角色
     */
    List<SysRoleEntity> listRolesByUserId(@Param("userId") Long userId);
    /**
     * 获取所有可用的角色描述和名称
     */
    List<SysRoleEntity> searchAvailabeRoles();
    /**
     * 获取所有角色描述和名称
     */
    List<SysRoleEntity> searchAllRoles();
    /**
     * 通过ID获取角色
     */
    SysRoleEntity searchOneRoleByUseId(@Param("roleId") Long roleId);
    /**
     * 插入用户角色
     */
    Integer insertRoles(@Param("roles") SysRoleEntity roles);
    /**
     * 查找用户角色通过name
     */
    SysRoleEntity searchOneRoleByName(@Param("name") String name);
    /**
     * 编辑用户角色sys_role
     */
    Integer editRoleInfo(@Param("roles") SysRoleEntity roles);
}
