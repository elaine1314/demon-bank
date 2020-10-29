package com.zxe.admin.dao;

import com.zxe.admin.entity.SysUserEntity;
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
public interface SysUserDao {

    /**
     * 查询用户
     * @param username
     * @return
     */
    SysUserEntity findUser(@Param("username") String username);

}
