package com.zxe.main.controller;

import com.zxe.admin.entity.SysUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.zxe.admin.service.*;

/**
 * @Author:Elaine
 * @Description:
 * @Date: Created in 2:10 PM 2020/10/30
 * @Version: 1.0
 */
@RestController
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;

    @GetMapping("/user")
    public String login(String username){
        SysUserEntity user = sysUserService.findUserInfo(username);
        return user.getNickname();
    }

// AND status = 1
}
