package com.zxe.main.controller;

import cn.hutool.json.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zxe.admin.entity.SysUserEntity;
import com.zxe.admin.service.SysUserService;
import com.zxe.common.Result;
import com.zxe.common.utils.PasswordUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author:Elaine
 * @Description:
 * @Date: Created in 7:00 PM 2020/11/5
 * @Version: 1.0
 */
@RestController
public class SysUserContoller {

    @Autowired
    SysUserService sysUserService;

    @RequiresAuthentication
    @GetMapping("/userList")
    public Result userList(@RequestParam Integer pageNum, @RequestParam Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<SysUserEntity> list = sysUserService.searchAllUser();

        PageInfo<SysUserEntity> info = new PageInfo(list);

        return Result.succ(info);
    }

    @RequiresPermissions("user:edit")
    @PostMapping(value = "/edit")
    public Result edit(@RequestBody JSONObject data) {
        SysUserEntity user = new SysUserEntity();
        user.setUsername(data.getStr("username"));
        user.setPassword(data.getStr("password"));
        user.setNickname(data.getStr("nickname"));
        user.setMobile(data.getStr("mobile"));
        user.setEmail(data.getStr("email"));
        user.setId(Long.parseLong(data.getStr("id")));
        try {
            String password = user.getPassword();
            if(password.length() != 24){
                user.setPassword(PasswordUtils.encrypt(user.getPassword(),user.getUsername()));
            }
            sysUserService.updateSysUserInfo(user);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail("用户修改失败！");
        }
        return Result.succ("修改用户成功");
    }

    @RequiresPermissions("user:edit")
    @GetMapping(value = "/getUserInfo")
    public Result getUserInfo(@RequestParam String id) {
        SysUserEntity entity = sysUserService.findUserInfoById(id);

        return Result.succ(entity);
    }

    @RequiresPermissions(value = {"user:delete"}, logical = Logical.OR)
    @GetMapping(value = "/delete")
    public Result delete(@RequestParam String id) {
        SysUserEntity entity = sysUserService.findUserInfoById(id);
        Integer result = sysUserService.deleteUserInfo(id);
        if(result == 1){
            return Result.succ(entity.getUsername() + "用户被删除");
        }else {
            return Result.fail(entity.getUsername() + "用户无法被删除");
        }
    }

    @RequiresPermissions("user:add")
    @PostMapping(value = "/addUser")
    public Result addUser(@RequestBody JSONObject data){
        SysUserEntity user = new SysUserEntity();
        user.setUsername(data.getStr("username"));
        user.setPassword(data.getStr("password"));
        user.setNickname(data.getStr("nickname"));
        user.setMobile(data.getStr("mobile"));
        user.setEmail(data.getStr("email"));

        SysUserEntity userInfo = sysUserService.findUserInfo(data.getStr("username"));
        if (userInfo != null) {
            return Result.fail("该用户名[" + userInfo.getUsername() + "]已存在！请更改用户名");
        }else{
            try {
                user.setPassword(PasswordUtils.encrypt(user.getPassword(),user.getUsername()));
                sysUserService.inserUserInfo(user);
                return Result.succ(user);

            } catch (Exception e) {
                e.printStackTrace();
                return Result.succ("插入失败");
            }
        }

    }
}
