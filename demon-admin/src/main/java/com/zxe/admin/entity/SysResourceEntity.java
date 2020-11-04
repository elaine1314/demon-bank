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
public class SysResourceEntity {
    private static final long serialVersionUID = 5088697673359856350L;
    private Long id;
    private String name;
    private String type;
    private String url;
    private String permission;
    private Long parentId;
    private Integer sort;
    private Boolean external;
    private Boolean available;
    private String icon;
    private Date createTime;
    private Date updateTime;
}
