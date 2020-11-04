package com.zxe.main.controller;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.zxe.admin.entity.SysUserEntity;
import com.zxe.admin.service.SysUserService;
import com.zxe.admin.utils.JwtUtils;
import com.zxe.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author:Elaine
 * @Description:
 * @Date: Created in 2:10 PM 2020/10/30
 * @Version: 1.0
 */
@RestController
@RequestMapping("/user")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/login")
    public Result login(String username, String password){
        System.out.println("eeeeeeeee");
        SysUserEntity user = sysUserService.findUserInfo(username);
        Assert.notNull(user, "用户不存在");
        if (!user.getPassword().equals(SecureUtil.md5(password))) {
            return Result.fail("密码不正确");
        }

        String jwt = jwtUtils.generateToken(user.getId());
        //        response.setHeader("Authorization", jwt);
        //        response.setHeader("Access-control-Expose-Headers", "Authorization");

        Map<Object,Object> map  = MapUtil.builder()
                .put("id", user.getId())
                .put("token", jwt)
                .put("username", user.getUsername())
                .put("avatar", user.getAvatar())
                .put("email", user.getEmail()).map();

        return Result.succ(map);
    }

    @PostMapping("/gggg")
    public String ggg(){
         String ff = "dfgsdfgsdf";
        return ff;
    }
}