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
     * 获取所有资源
     * @return
     */
    public JSONArray getAllResourceInfo(Long rid){
        // 全部资源列表
        List<SysResourceEntity> resourceEntities = sysResourceDao.getResourcesInfo();
        // 通过ID获取的资源列表
        List<SysRoleResourceEntity> resourceEntitiesByRid = sysRoleResourceDao.queryResourcesListWithSelected(rid);

        for (SysResourceEntity rentity: resourceEntities){
            Long rId = rentity.getId();
            for(SysRoleResourceEntity selectEntity : resourceEntitiesByRid){
                Long selectedId = selectEntity.getId();
                if(rId == selectedId){

                }

            }
        }



        Map<Long, List<SysResourceEntity>>  map = resourceEntities.stream().collect(Collectors.groupingBy(SysResourceEntity::getParentId));
        System.out.println(map.toString());
        List<SysResourceEntity> firstMenu = map.get(0L);
        System.out.println(firstMenu.toString());
        JSONArray firstMenuJson = new JSONArray(firstMenu);
        System.out.printf("==",firstMenuJson);

        JSONArray resultArray = new JSONArray();

        for(int i = 0; i < firstMenuJson.size();i++) {
             JSONObject entity = new JSONObject(firstMenuJson.get(i));
             Long id = entity.getLong("id");
            if(map.containsKey(id)) {
                Object conetent = map.get(id);
                System.out.println("+++++=="+conetent);
                entity.append("children",conetent);
                resultArray.put(entity);
            }
        }

        // 最终结果
        JSONArray finalArray = new JSONArray();

        for(int i = 0; i < resultArray.size();i++) {
            JSONObject entity = new JSONObject(resultArray.get(i));
            if(entity.containsKey("children")) {
                JSONArray children = new JSONArray(new JSONArray(entity.get("children")).get(0));
                for(int j = 0; j <  children.size(); j++){
                    JSONObject entityValue = new JSONObject(children.get(j));
                    Long id = entityValue.getLong("id");
                    Object conetent = map.get(id);
                    entityValue.append("children",conetent);
                    children.put(entityValue);
                    finalArray.put(entity);
                }
            }
        }

        System.out.printf("==",finalArray);
        return firstMenuJson;
    }
}
