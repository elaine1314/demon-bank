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
}
