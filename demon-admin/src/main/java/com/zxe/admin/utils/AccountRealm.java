package com.zxe.admin.utils;

import com.zxe.admin.entity.SysResourceEntity;
import com.zxe.admin.entity.SysRoleEntity;
import com.zxe.admin.entity.SysUserEntity;
import com.zxe.admin.service.SysResourceService;
import com.zxe.admin.service.SysRoleService;
import com.zxe.admin.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @Author:Elaine
 * @Description:
 * @Date: Created in 9:24 PM 2020/11/3
 * @Version: 1.0
 */
@Component
public class AccountRealm extends AuthorizingRealm{
    @Autowired
    SysUserService sysUserService;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    SysRoleService sysRoleService;
    @Autowired
    SysResourceService sysResourceService;

    @Override
    public boolean supports(AuthenticationToken token){
        return token instanceof JwtToken;
    }

    /**
     * 权限认证，为当前登录的Subject授予角色和权限（角色的权限信息集合）
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        SysUserEntity userEntity = (SysUserEntity) SecurityUtils.getSubject().getPrincipal();
        Long userId = userEntity.getId();

        // 赋予角色
        List<SysRoleEntity> roleEntityList =  sysRoleService.listRolesByUserId(userId);
        for(SysRoleEntity entity : roleEntityList){
            info.addRole(entity.getName());
        }

        // 赋予权限
        List<SysResourceEntity> resourceEntityList = sysResourceService.listByUserId(userId);
        if(!CollectionUtils.isEmpty(resourceEntityList)){
            for (SysResourceEntity entity : resourceEntityList){
                String permission = entity.getPermission();
                if(!StringUtils.isEmpty(permission)){
                    info.addStringPermission(permission);
                }
            }
        }

        return info;
    }

    /**
     * 提供账户信息返回认证信息（用户的角色信息集合）
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        JwtToken jwt = (JwtToken) authenticationToken;
        String userId = jwtUtils.getClaimByToken((String) jwt.getPrincipal()).getSubject();

        SysUserEntity user = sysUserService.findUserInfoById(userId);
        if (user == null) {
            throw new UnknownAccountException("账户不存在！");
        }
        if (user.getStatus() != 1) {
            throw new LockedAccountException("账户已被锁定！");
        }

//        AccountProfile profile = new AccountProfile();
//        BeanUtil.copyProperties(user,profile);
        AuthenticationInfo info = new SimpleAuthenticationInfo(user, jwt.getCredentials(), getName());
        return info;
    }
}
