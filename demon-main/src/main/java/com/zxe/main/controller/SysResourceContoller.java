package com.zxe.main.controller;

import cn.hutool.json.JSONArray;
import com.mysql.cj.util.StringUtils;
import com.zxe.admin.entity.SysResourceEntity;
import com.zxe.admin.service.SysResourceService;
import com.zxe.admin.utils.JwtUtils;
import com.zxe.common.Result;
import io.jsonwebtoken.Claims;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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
    @Autowired
    JwtUtils jwtUtils;

    @RequiresPermissions("resources")
    @GetMapping("/resourceList")
    public Result resourceList(){
        JSONArray result  = sysResourceService.getAllResourceInfo();
        return Result.succ(result);
    }

    @RequiresPermissions("resources")
    @GetMapping("/userOwnerPression")
    public Result userOwnerPression(@RequestParam Long rid){
        List<Long> result = sysResourceService.getUserResourceId(rid);
        return Result.succ(result);
    }


    @RequiresPermissions("resources")
    @GetMapping("/saveResource")
    public Result saveResource(@RequestParam Long rid, @RequestParam(value = "list") Long[] list){
        List<Long> formateList = new ArrayList<>();
        for(Long l:list) {
            formateList.add(l);
        }

        Integer flag = sysResourceService.saveRoleResourrceRelation(rid,formateList);
        return Result.succ(flag);
    }

    @RequiresPermissions("resources")
    @GetMapping("/permissionList")
    public Result permissionList(HttpServletRequest request){
        String userid = "";
        String token = request.getHeader("authorization");//从头部获取JWT字符串信息

        if(!StringUtils.isNullOrEmpty(token)){
            Claims claim = jwtUtils.getClaimByToken(token);
            userid = claim.getSubject();
        }

        List<SysResourceEntity> permissionList = sysResourceService.listByUserId(Long.parseLong(userid));

        String[] result = new String[permissionList.size()];
        for(int i = 0; i < permissionList.size();i++){
            result[i] = permissionList.get(i).getPermission();
        }

        return Result.succ(result);
    }
}
