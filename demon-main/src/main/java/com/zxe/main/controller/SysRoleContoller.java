package com.zxe.main.controller;

import cn.hutool.json.JSONObject;
import com.zxe.admin.entity.SysRoleEntity;
import com.zxe.admin.entity.SysUserRoleEntity;
import com.zxe.admin.service.SysRoleService;
import com.zxe.common.Result;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author:Elaine
 * @Description: Roles
 * @Date: Created in 7:00 PM 2020/11/5
 * @Version: 1.0
 */
@RestController
@RequestMapping("/roles")
public class SysRoleContoller {

    @Autowired
    SysRoleService sysRoleService;


    @RequiresPermissions("roles")
    @GetMapping("/rolesList")
    public Result rolesList(){
        List<SysRoleEntity> list = sysRoleService.getAllRoles();
        return Result.succ(list);
    }

    @RequiresPermissions("user:allotRole")
    @PostMapping("/saveUserRole")
    public Result saveUserRole(@RequestBody JSONObject data){
        SysUserRoleEntity roleEntity = new SysUserRoleEntity();
        roleEntity.setUserId(data.getLong("userId"));
        roleEntity.setRoleId(data.getLong("roleId"));

        SysUserRoleEntity userRoleEntity = sysRoleService.findUserRoledId(data.getLong("userId"));
        if(userRoleEntity != null){
            sysRoleService.updateRolesInfo(roleEntity);
            return Result.succ("更新成功");
        }else{
            sysRoleService.insertRolesInfo(roleEntity);
            return Result.succ("更新成功");
        }
    }
}
