package com.zxe.admin.service;

import com.zxe.admin.dao.SysRoleDao;
import com.zxe.admin.dao.SysUserRoleDao;
import com.zxe.admin.entity.SysRoleEntity;
import com.zxe.admin.entity.SysUserRoleEntity;
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
     * 获取所有可用的角色描述和名称
     */
    public List<SysRoleEntity> getAvailabeRoles(){
        return sysRoleDao.searchAvailabeRoles();
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
    public SysUserRoleEntity findUserRoledId(Long userId){
        return sysUserRoleDao.findUserRoledId(userId);
    }
    /**
     * 获取roles通过roles id
     * @return
     */
    public SysRoleEntity searchOneRoleByUseId(Long roleId){
        return sysRoleDao.searchOneRoleByUseId(roleId);
    }
    /**
     * 删除用户角色
     * @return
     */
    public Integer deleteRolesInfo(Long userId){
        return sysUserRoleDao.deleteRolesInfo(userId);
    }
    /**
     * 插入用户角色
     * @return
     */
    public Integer insertRoles(SysRoleEntity roleEntity){
        return sysRoleDao.insertRoles(roleEntity);
    }
    /**
     * 查找用户角色通过name
     *
     * @return
     */
    public SysRoleEntity searchOneRoleByName(String name) {
        return sysRoleDao.searchOneRoleByName(name);
    }
    /**
     * 编辑用户角色sys_role
     */
    public Integer editRoleInfo(SysRoleEntity roleEntity) {
        return sysRoleDao.editRoleInfo(roleEntity);
    }
}
