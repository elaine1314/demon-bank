package com.zxe.admin.service;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.zxe.admin.dao.SysResourceDao;
import com.zxe.admin.dao.SysRoleResourceDao;
import com.zxe.admin.entity.SysResourceEntity;
import com.zxe.admin.entity.SysRoleResourceEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
                entityObject.put("name", entity.getName());
                secChildrenArr.add(entityObject);
            }

            childrenObject.put("id", availableMenu.getNodeId().toString());
            childrenObject.put("name", availableMenu.getNodeName());
            childrenObject.put("children", secChildrenArr);
            childrenArr.put(childrenObject);

            if(item.size() != 0){
                childrenArr.addAll(item.getJSONArray("children"));
                jsonArray.remove(item);

            }
            // 第一层
            jsonObject.put("id", availableMenu.getId().toString());
            jsonObject.put("name", availableMenu.getName());
            jsonObject.put("children", childrenArr);
            jsonArray.put(jsonObject);
        }

        return jsonArray;
    }


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
            if(!entity.getType().equals("menu")) {
                result.add(entity.getId());
            }
        }

        return result;
    }

    /**
     * 插入用户与资源对应关系
     */
    public Integer insertRoleResourceInfo(List<SysRoleResourceEntity> roleRes){
        return sysRoleResourceDao.insertRoleResourceInfo(roleRes);
    }

    /**
     * 更新时间
     */
    public Integer  updateRoleResourceInfo(SysRoleResourceEntity roleRes){
        return sysRoleResourceDao.updateRoleResourceInfo(roleRes);
    }

    /**
     * 保存用户与资源对应关系逻辑
     */
    public Integer saveRoleResourrceRelation(Long roleId,List<Long> nodeIds){
        //获取所有权限
        List<SysResourceEntity> listByUserId = listByUserId(roleId);
        List<Long> result = new ArrayList<>();
        for(SysResourceEntity entity:listByUserId) {
            result.add(entity.getId());
        }

        // 通过节点id查询父亲节点
        List<SysResourceEntity> listAllAvailableMenu = listAllAvailableMenu();
        List<Long> allNodeList = new ArrayList<>();
        for(Long item : nodeIds){
            Long parentId = sysResourceDao.getParentNode(item).getParentId();
            allNodeList.add(parentId);
        }

        List<Long> noDouble = allNodeList.stream().distinct().collect(Collectors.toList());
        for(Long item : noDouble){
            for(SysResourceEntity entity:listAllAvailableMenu){
                if(item == entity.getNodeId()){
                    nodeIds.add(entity.getId());
                }
            }
        }
        nodeIds.addAll(allNodeList);
        System.out.println(allNodeList);

        // get common list
        Integer del = sysRoleResourceDao.deleteRoleResourceInfoByRoleId(roleId);
        if(del == 0){
            return 0;
        }else{
            List<SysRoleResourceEntity> insertInfoList = new ArrayList<>();
            for(Long item:nodeIds){
                SysRoleResourceEntity entity = new SysRoleResourceEntity();
                entity.setRoleId(String.valueOf(roleId));
                entity.setResourcesId(String.valueOf(item));
                insertInfoList.add(entity);
            }
            Integer update = sysRoleResourceDao.insertRoleResourceInfo(insertInfoList);
            return update;
        }

    }


}
