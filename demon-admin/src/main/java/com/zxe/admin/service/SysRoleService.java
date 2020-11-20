package com.zxe.admin.service;

import com.zxe.admin.dao.SysRoleDao;
import com.zxe.admin.dao.SysUserRoleDao;
import com.zxe.admin.entity.SysRoleEntity;
import com.zxe.admin.entity.SysUserRoleEntity;
import org.apache.ibatis.annotations.Param;
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
public class SysRoleService {
    @Autowired
    private SysRoleDao sysRoleDao;
    @Autowired
    private SysUserRoleDao sysUserRoleDao;

    /**
     * 查询用户
     * @param userId
     * @return
     */
    public List<SysRoleEntity> listRolesByUserId(Long userId){
        return sysRoleDao.listRolesByUserId(userId);
    }
    /**
     * 获取所有角色名称和描述
     * @return
     */
    public List<SysRoleEntity> getAllRoles(){
        return sysRoleDao.searchAllRoles();
    }

    /**
     * 获取所有角色名称和描述
     * @return
     */
    public Integer insertRolesInfo(SysUserRoleEntity role){
        return sysUserRoleDao.insertRolesInfo(role);
    }
    /**
     * 更新role id
     * @return
     */
    public Integer updateRolesInfo(SysUserRoleEntity role){
        return sysUserRoleDao.updateRolesInfo(role);
    }
    /**
     * 更新role id
     * @return
     */
    public SysUserRoleEntity findUserRoledId(@Param("userId") Long userId){
        return sysUserRoleDao.findUserRoledId(userId);
    }

}
