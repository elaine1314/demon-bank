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
public class SysUserEntity {
    private static final long serialVersionUID = 5088697673359856350L;
    private String username;
    private String password;
    private String nickname;
    private String mobile;
    private String email;
    private String qq;
    private Date birthday;
    private Integer gender;
    private String avatar;
    private String userType;
    private String regIp;
    private String lastLoginIp;
    private Date lastLoginTime;
    private Integer loginCount;
    private String remark;
    private Integer status;
    private Long id;
    private Date createTime;
    private Date updateTime;
}
