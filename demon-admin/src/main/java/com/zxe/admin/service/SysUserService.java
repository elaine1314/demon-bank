package com.zxe.admin.service;

import com.zxe.admin.dao.SysUserDao;
import com.zxe.admin.entity.SysUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author:Elaine
 * @Description:
 * @Date: Created in 9:20 PM 2020/10/29
 * @Version: 1.0
 */
@Service
public class SysUserService {
    @Autowired
    private SysUserDao sysUserDao;

    /**
     * 查询用户
     * @param username
     * @return
     */
    public SysUserEntity findUserInfo(String username){
        return sysUserDao.findUser(username);
    }

    /**
     * 查询用户
     * @param userid
     * @return
     */
    public SysUserEntity findUserInfoById(String userid){
        return sysUserDao.findUser(userid);
    }
}
