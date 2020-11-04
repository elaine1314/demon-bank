package com.zxe.admin.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Author:Elaine
 * @Description:
 * @Date: Created in 6:13 PM 2020/10/28
 * @Version: 1.0
 */
@Data
public class SysRoleResourceEntity {
    private static final long serialVersionUID = 5088697673359856350L;
    private String roleId;
    private String resourcesId;
    private Long id;
    private Date createTime;
    private Date updateTime;
}
