package com.zxe.admin.service;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.zxe.admin.dao.SysResourceDao;
import com.zxe.admin.dao.SysRoleResourceDao;
import com.zxe.admin.entity.SysResourceEntity;
import com.zxe.admin.entity.SysRoleResourceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
    @Autowired
    private SysRoleResourceDao sysRoleResourceDao;

    /**
     * 查询用户
     * @param userId
     * @return
     */
    public List<SysResourceEntity> listByUserId(Long userId){
        return sysResourceDao.listByUserId(userId);
    }
    /**
     * 获取资源类型为menu的可用的值
     */
    public List<SysResourceEntity> listAllAvailableMenu(){
        return sysResourceDao.listAllAvailableMenu();
    };
    /**
     * 通过Parent_id获取数据
     */
    public List<SysResourceEntity> listMenuResourceByPid(Long pId){
        return sysResourceDao.listMenuResourceByPid(pId);
    };
    /**
     * list更改类型为前端tree准备
     * @return
     */
    public JSONArray getAllResourceInfo(){
        // 全部Menu列表
        List<SysResourceEntity> listAllAvailableMenu = listAllAvailableMenu();
//        // 通过userId获取权限
//        List<SysResourceEntity> listByUserId = sysResourceDao.listByUserId(rid);
//        // 获取所有信息
//        List<SysResourceEntity> getAllResourceInfo = sysResourceDao.getResourcesInfo();

//        for(SysResourceEntity aEntity:getAllResourceInfo){
//            for(SysResourceEntity sEntity:listByUserId){
//                if(aEntity.getId() == sEntity.getId()){
//                    aEntity.setChecked("selected");
//                }
//            }
//        }

        JSONArray jsonArray = new JSONArray();
        for(SysResourceEntity availableMenu : listAllAvailableMenu){
            JSONObject item = checkJsonContailsEntity(availableMenu,jsonArray);
            JSONObject jsonObject = new JSONObject();
            // 第一层孩子
            JSONArray childrenArr = new JSONArray();
            JSONObject childrenObject = new JSONObject();
            System.out.println(availableMenu.getNodeId());
            List<SysResourceEntity> chList = listMenuResourceByPid(availableMenu.getNodeId());
            JSONArray secChildrenArr = new JSONArray();

            for (SysResourceEntity entity : chList) {
                JSONObject entityObject = new JSONObject();
                entityObject.put("id", entity.getId());
//                entityObject.put("checked", getCheckboxState(getAllResourceInfo, entity.getId()));
                entityObject.put("name", entity.getName());
                secChildrenArr.add(entityObject);
            }

            childrenObject.put("id", availableMenu.getNodeId().toString());
//            childrenObject.put("checked", getCheckboxState(getAllResourceInfo, availableMenu.getNodeId()));
            childrenObject.put("name", availableMenu.getNodeName());
            childrenObject.put("children", secChildrenArr);
            childrenArr.put(childrenObject);

            if(item.size() != 0){
                childrenArr.addAll(item.getJSONArray("children"));
                jsonArray.remove(item);

            }
            // 第一层
            jsonObject.put("id", availableMenu.getId().toString());
//            jsonObject.put("checked", getCheckboxState(getAllResourceInfo, availableMenu.getId()));
            jsonObject.put("name", availableMenu.getName());
            jsonObject.put("children", childrenArr);
            jsonArray.put(jsonObject);
        }

        return jsonArray;
    }

//    public String getCheckboxState(List<SysResourceEntity> getAllResourceInfo,Long id){
//        for(SysResourceEntity aEntity:getAllResourceInfo){
//            if(aEntity.getId() == id){
//                return aEntity.getChecked();
//            }
//        }
//        return "";
//    }

    public JSONObject checkJsonContailsEntity(SysResourceEntity resourceEntities,JSONArray jsonArray){
        for(Object jsonObject:jsonArray){
            JSONObject item = new JSONObject(jsonObject);
            if(item.getLong("id") == resourceEntities.getId()){
                return item;
            }
        }

        return new JSONObject();
    }

    /**
     * 用户有的权限Id
     * @return
     */
    public List<Long> getUserResourceId(Long rid){
        List<SysResourceEntity> listByUserId = sysResourceDao.listByUserId(rid);
        List<Long> result = new ArrayList<>();
        for(SysResourceEntity entity:listByUserId){
            result.add(entity.getId());
        }

        return result;
    }

}
