package com.zxe.main.controller;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zxe.admin.entity.SysResourceEntity;
import com.zxe.admin.entity.SysUserEntity;
import com.zxe.admin.entity.SysUserRoleEntity;
import com.zxe.admin.service.SysResourceService;
import com.zxe.admin.service.SysRoleService;
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
@RequestMapping("/resources")
public class SysResourceContoller {

    @Autowired
    SysResourceService sysResourceService;

    @RequiresPermissions("resources")
    @GetMapping("/resourceList")
    public Result resourceList(){
        JSONArray result  = sysResourceService.getAllResourceInfo();

        return Result.succ(result);
    }
}
