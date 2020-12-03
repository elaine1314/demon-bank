package com.zxe.main.controller;

import cn.hutool.json.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zxe.admin.entity.SysRoleEntity;
import com.zxe.admin.entity.SysUserEntity;
import com.zxe.admin.entity.SysUserRoleEntity;
import com.zxe.admin.enums.UserTypeEnum;
import com.zxe.admin.service.SysRoleService;
import com.zxe.admin.service.SysUserService;
import com.zxe.common.Result;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    @Autowired
    SysUserService sysUserService;


    @RequiresPermissions("roles")
    @GetMapping("/rolesList")
    public Result rolesList(){
        List<SysRoleEntity> list = sysRoleService.getAvailabeRoles();
        return Result.succ(list);
    }

    @RequiresPermissions("roles")
    @GetMapping("/getRolesList")
    public Result getRolesList(@RequestParam Integer pageNum, @RequestParam Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<SysRoleEntity> list = sysRoleService.getAllRoles();
        PageInfo<SysRoleEntity> info = new PageInfo(list);
        return Result.succ(info);
    }

    @RequiresPermissions("user:allotRole")
    @PostMapping("/saveUserRole")
    public Result saveUserRole(@RequestBody JSONObject data){
        SysUserRoleEntity roleEntity = new SysUserRoleEntity();
        roleEntity.setUserId(data.getLong("userId"));
        roleEntity.setRoleId(data.getLong("roleId"));

        SysUserRoleEntity userRoleEntity = sysRoleService.findUserRoledId(data.getLong("userId"));

        SysRoleEntity roleEntity1 = sysRoleService.searchOneRoleByUseId(data.getLong("roleId"));
        UserTypeEnum userType = UserTypeEnum.getByType(roleEntity1.getDescription());
        System.out.println(userType.toString());
        int flag = 0;
        if (userRoleEntity != null) {
            flag = sysRoleService.updateRolesInfo(roleEntity);

        } else {
            flag = sysRoleService.insertRolesInfo(roleEntity);
        }

        if (flag == 1) {
            SysUserEntity userEntity = new SysUserEntity();
            String uerType;
            uerType = sysUserService.findUserInfoById(data.getStr("userId")).getUserType();
            userEntity.setUserType(userType.toString());
            userEntity.setId(roleEntity.getUserId());
            sysUserService.updateSysUserType(userEntity, uerType);
        }

        return Result.succ("更新成功");
    }

    @RequiresPermissions("role:add")
    @PostMapping("/addRole")
    public Result addRole(@RequestBody JSONObject data) throws ParseException {
        SysRoleEntity roleEntity = new SysRoleEntity();
        roleEntity.setName("role:"+data.getStr("name"));
        roleEntity.setDescription(data.getStr("description"));
        roleEntity.setAvailable(data.getInt("available"));

        //设置日期格式
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        roleEntity.setUpdateTime(df.parse(df.format(new Date())));

        SysRoleEntity existRoleEntity = sysRoleService.searchOneRoleByName("role:"+data.getStr("name"));
        if(existRoleEntity != null){
            return Result.fail("该角色[" + existRoleEntity.getName() + "]已存在！请更改角色名称");
        }else{
            int result = sysRoleService.insertRoles(roleEntity);
            if(result == 1){
                return Result.succ(roleEntity);
            }else{
                return Result.fail("该角色[" + roleEntity.getName() + "]插入数据失败,请联系管理人员");
            }
        }
    }

    @RequiresPermissions("role:edit")
    @GetMapping("/getOneRole")
    public Result getOneRole(@RequestParam String id){
        return Result.succ(sysRoleService.searchOneRoleByUseId(Long.parseLong(id)));
    }

    @RequiresPermissions("role:edit")
    @PostMapping("/editRole")
    public Result editRole(@RequestBody JSONObject data) throws ParseException {
        SysRoleEntity roleEntity = new SysRoleEntity();
        roleEntity.setName(data.getStr("name"));
        roleEntity.setDescription(data.getStr("description"));
        roleEntity.setId(data.getLong("id"));
        String availableValue = data.getStr("available");
        String finalAvailableValue = "";
        if(availableValue.equals("可用")){
            finalAvailableValue = "1";
        }else if(availableValue.equals("不可用")){
            finalAvailableValue = "0";
        }else{
            finalAvailableValue = availableValue;
        }
        roleEntity.setAvailable(Integer.parseInt(finalAvailableValue));

        // 获取当前日期
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        roleEntity.setUpdateTime(df.parse(df.format(new Date())));

        Integer result = sysRoleService.editRoleInfo(roleEntity);
        if(result == 1) {
            return Result.succ("["+roleEntity.getName()+"]用户角色更新成功");
        }else{
            return Result.fail("["+roleEntity.getName()+"]用户角色更新失败");
        }
    }


}
