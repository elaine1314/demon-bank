package com.zxe.admin.service;

import com.zxe.admin.dao.SysResourceDao;
import com.zxe.admin.entity.SysResourceEntity;
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
public class SysResourceService {
    @Autowired
    private SysResourceDao sysResourceDao;

    /**
     * 查询用户
     * @param userId
     * @return
     */
    public List<SysResourceEntity> listByUserId(Long userId){
        return sysResourceDao.listByUserId(userId);
    }
    /**
     * 获取所有资源
     * @return
     */
    public List<SysResourceEntity> getAllResourceInfo(){

    }


}
