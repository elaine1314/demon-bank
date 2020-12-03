package com.zxe.admin.dao;

import com.zxe.admin.entity.SysUserEntity;
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
public interface SysUserDao {
    /**
     * 查询用户
     * @param username
     * @return
     */
    SysUserEntity findUser(@Param("username") String username);
    /**
     * 查询用户
     * @param userid
     * @return
     */
    SysUserEntity findUserById(@Param("userid") String userid);
    /**
     * 查询所有用户
     * @return
     */
    List<SysUserEntity> searchAllUser();
    /**
     * 更新用户信息
     */
    Integer updateSysUserInfo(@Param("user") SysUserEntity user);
    /**
     * 删除用户
     */
    Integer deleteUserInfo(@Param("id") String id);
    /**
     * 插入用户
     */
    Integer insertUserInfo(@Param("user") SysUserEntity user);
    /**
     * 更新user_type
     */
    Integer updateSysUserType(@Param("user") SysUserEntity user,@Param("userType") String userType);
}
