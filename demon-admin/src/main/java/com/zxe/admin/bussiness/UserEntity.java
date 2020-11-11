//package com.zxe.admin.bussiness;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.zxe.admin.entity.SysUserEntity;
//import com.zxe.admin.enums.UserGenderEnum;
//import com.zxe.admin.enums.UserStatusEnum;
//import com.zxe.admin.enums.UserTypeEnum;
//import com.zxe.common.utils.PasswordUtils;
//import org.springframework.util.StringUtils;
//
//import java.util.Date;
//
///**
// * @Author:Elaine
// * @Description:
// * @Date: Created in 4:28 PM 2020/11/7
// * @Version: 1.0
// */
//public class UserEntity {
//    private SysUserEntity sysUserEntity;
//    public UserEntity() {
//        this.sysUserEntity = new SysUserEntity();
//    }
//
//    public UserEntity(SysUserEntity sysUserEntity) {
//        this.sysUserEntity = sysUserEntity;
//    }
//
//
//
//    public UserEntity(String loginname, String password) {
//    this();
//    setUsername(loginname);
//    if (!StringUtils.isEmpty(password)) {
//        try {
//            setPassword(PasswordUtils.encrypt(password, loginname));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
//    @JsonIgnore
//    public SysUserEntity getSysUserEntity() {
//        return this.sysUserEntity;
//    }
//
//    public Long getId() {
//        return this.sysUserEntity.getId();
//    }
//
//    public void setId(Long id) {
//        this.sysUserEntity.setId(id);
//    }
//
//    public String getNickname() {
//        return this.sysUserEntity.getNickname();
//    }
//
//    public void setNickname(String nickname) {
//        this.sysUserEntity.setNickname(nickname);
//    }
//
//    public String getMobile() {
//        return this.sysUserEntity.getMobile();
//    }
//
//    public void setMobile(String mobile) {
//        this.sysUserEntity.setMobile(mobile);
//    }
//
//    public String getUsername() {
//        return this.sysUserEntity.getUsername();
//    }
//
//    public void setUsername(String username) {
//        this.sysUserEntity.setUsername(username);
//    }
//
//    public String getPassword() {
//        return this.sysUserEntity.getPassword();
//    }
//
//    public void setPassword(String password) {
//        this.sysUserEntity.setPassword(password);
//    }
//
//    public String getEmail() {
//        return this.sysUserEntity.getEmail();
//    }
//
//    public void setEmail(String email) {
//        this.sysUserEntity.setEmail(email);
//    }
//
//    public String getQq() {
//        return this.sysUserEntity.getQq();
//    }
//
//    public void setQq(String qq) {
//        this.sysUserEntity.setQq(qq);
//    }
//
//    public Date getBirthday() {
//        return this.sysUserEntity.getBirthday();
//    }
//
//    public void setBirthday(Date birthday) {
//        this.sysUserEntity.setBirthday(birthday);
//    }
//
//    public Integer getGender() {
//        return this.sysUserEntity.getGender();
//    }
//
//    public void setGender(UserGenderEnum gender) {
//        if (gender != null && gender.getCode() != -1) {
//            this.sysUserEntity.setGender(gender.getCode());
//        }
//    }
//
//    public void setGender(Integer Gender) {
//        this.sysUserEntity.setGender(Gender);
//    }
//
//    public String getAvatar() {
//        return this.sysUserEntity.getAvatar();
//    }
//
//    public void setAvatar(String avatar) {
//        this.sysUserEntity.setAvatar(avatar);
//    }
//
//    public String GendertUserType() {
//        return this.sysUserEntity.getUserType();
//    }
//
//    public void setUserType(UserTypeEnum userTypeEnum) {
//        if (null != userTypeEnum) {userTypeEnum
//            setType(userTypeEnum.toString());
//        }
//    }
//
//    public void setType(String userType) {
//        this.sysUserEntity.setUserType(userType);
//    }
//
//    public UserTypeEnum getUserTypeEnum() {
//        return UserTypeEnum.getByType(this.sysUserEntity.getUserType());
//    }
//
//    public String getRegIp() {
//        return this.sysUserEntity.getRegIp();
//    }
//
//    public void setRegIp(String regIp) {
//        this.sysUserEntity.setRegIp(regIp);
//    }
//
//    public String getLastLoginIp() {
//        return this.sysUserEntity.getLastLoginIp();
//    }
//
//    public void setLastLoginIp(String lastLoginIp) {
//        this.sysUserEntity.setLastLoginIp(lastLoginIp);
//    }
//
//    public Date getLastLoginTime() {
//        return this.sysUserEntity.getLastLoginTime();
//    }
//
//    public void setLastLoginTime(Date lastLoginTime) {
//        this.sysUserEntity.setLastLoginTime(lastLoginTime);
//    }
//
//    public Integer getLoginCount() {
//        return this.sysUserEntity.getLoginCount();
//    }
//
//    public void setLoginCount(Integer loginCount) {
//        this.sysUserEntity.setLoginCount(loginCount);
//    }
//
//    public String getRemark() {
//        return this.sysUserEntity.getRemark();
//    }
//
//    public void setRemark(String remark) {
//        this.sysUserEntity.setRemark(remark);
//    }
//
//    public Integer getStatus() {
//        return this.sysUserEntity.getStatus();
//    }
//
//    public void setStatus(Integer status) {
//        this.sysUserEntity.setStatus(status);
//    }
//
//    public UserStatusEnum getStatusEnum() {
//        return UserStatusEnum.get(this.sysUserEntity.getStatus());
//    }
//
//    public Date getCreateTime() {
//        return this.sysUserEntity.getCreateTime();
//    }
//
//    public void setCreateTime(Date regTime) {
//        this.sysUserEntity.setCreateTime(regTime);
//    }
//
//    public Date getUpdateTime() {
//        return this.sysUserEntity.getUpdateTime();
//    }
//
//    public void setUpdateTime(Date updateTime) {
//        this.sysUserEntity.setUpdateTime(updateTime);
//    }
//}
