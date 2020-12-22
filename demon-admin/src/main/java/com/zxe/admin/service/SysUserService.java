package com.zxe.admin.service;

import com.zxe.admin.dao.SysUserDao;
import com.zxe.admin.entity.SysUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return sysUserDao.findUserById(userid);
    }
    /**
     * 返回所有用户
     * @param
     * @return
     */
    public List<SysUserEntity> searchAllUser(){
        return sysUserDao.searchAllUser();
    }

    /**
     * 返回所有用户
     * @param
     * @return
     */
    public Integer updateSysUserInfo(SysUserEntity entity){
        return sysUserDao.updateSysUserInfo(entity);
    }

    /**
     * 删除用户
     * @param
     * @return
     */
    public Integer deleteUserInfo(String id){
        return sysUserDao.deleteUserInfo(id);
    }

    /**
     * 插入用户信息
     * @param
     * @return
     */
    public Integer inserUserInfo(SysUserEntity entity){
        return sysUserDao.insertUserInfo(entity);
    }
    /**
     * 更新用户user_type
     * @param
     * @return
     */
    public Integer updateSysUserType(SysUserEntity entity,String userType){
        return sysUserDao.updateSysUserType(entity,userType);
    }
    /**
     * 更新用户login info
     * @param
     * @return
     */
    public Integer updateSysUserLoginInfo(SysUserEntity entity) {
        return sysUserDao.updateSysUserLoginInfo(entity);
    }
}
