package com.zxe.admin.service;

import com.zxe.admin.dao.SysRoleDao;
import com.zxe.admin.entity.SysRoleEntity;
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

    /**
     * 查询用户
     * @param userId
     * @return
     */
    public List<SysRoleEntity> listRolesByUserId(Long userId){
        return sysRoleDao.listRolesByUserId(userId);
    }
}
