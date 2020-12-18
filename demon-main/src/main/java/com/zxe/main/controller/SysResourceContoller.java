package com.zxe.main.controller;

import cn.hutool.json.JSONArray;
import com.zxe.admin.service.SysResourceService;
import com.zxe.common.Result;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        System.out.println(rid);
        List<Long> formateList = new ArrayList<>();
        for(Long l:list) {
            formateList.add(l);
        }
//        List<Long> list = (List<Long>) data.get("nodeIdsList");
////        System.out.println(Long.getLong(data.getStr("rid")));
        Integer flag = sysResourceService.saveRoleResourrceRelation(rid,formateList);
        return Result.succ("flag");
    }
}
