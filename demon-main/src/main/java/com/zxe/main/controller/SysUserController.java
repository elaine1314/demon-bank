package com.zxe.main.controller;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import com.mysql.cj.util.StringUtils;
import com.zxe.admin.entity.SysUserEntity;
import com.zxe.admin.service.SysUserService;
import com.zxe.admin.utils.JwtUtils;
import com.zxe.common.Result;
import com.zxe.common.utils.PasswordUtils;
import io.jsonwebtoken.Claims;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    public Result login(String username, String password,HttpServletResponse response) throws Exception {
        System.out.println("eeeeeeeee");
        SysUserEntity user = sysUserService.findUserInfo(username);
        Assert.notNull(user, "用户不存在");

        if (!user.getPassword().equals(PasswordUtils.encrypt(password,username))) {
            return Result.fail("密码不正确");
        }

        String jwt = jwtUtils.generateToken(user.getId());
        response.setHeader("Authorization", jwt);
        response.setHeader("Access-control-Expose-Headers", "Authorization");

        Map<Object,Object> map  = MapUtil.builder()
                .put("id", user.getId())
                .put("token", jwt)
                .put("username", user.getUsername())
                .put("avatar", user.getAvatar())
                .put("email", user.getEmail()).map();

        return Result.succ(map);
    }

    @RequiresAuthentication
    @GetMapping("/getUserInfo")
    public Result getUserInfo(HttpServletRequest request, HttpServletResponse response) {

        String userid = "";
        String token = request.getHeader("authorization");//从头部获取JWT字符串信息

        if(!StringUtils.isNullOrEmpty(token)){
            Claims claim = jwtUtils.getClaimByToken(token);
            userid = claim.getSubject();
        }


        SysUserEntity user = sysUserService.findUserInfoById(userid);

        return Result.succ(MapUtil.builder()
                .put("id", user.getId())
                .put("username", user.getUsername())
                .put("avatar", user.getAvatar())
                .put("email", user.getEmail())
                .map());

    }

    @RequiresAuthentication
    @PostMapping("/logout")
    public Result logout() {
        SecurityUtils.getSubject().logout();
        return Result.succ(null);
    }

    @RequiresAuthentication
    @GetMapping("/info")
    @RequiresPermissions(value = "user")
    public Result info() {
        return Result.succ("dfasdfasdfasdf");
    }

}