package com.zxe.admin.utils;

import com.zxe.admin.service.SysUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author:Elaine
 * @Description:
 * @Date: Created in 9:24 PM 2020/11/3
 * @Version: 1.0
 */
@Component
public class AccountRealm extends AuthorizingRealm{
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    SysUserService sysUserService;

    @Override
    public boolean supports(AuthenticationToken token){
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        JwtToken jwt = (JwtToken) authenticationToken;
        String userId = jwtUtils.getClaimByToken((String) jwt.getPrincipal()).getSubject();

//        SysUserEntity user = sysUserService.findUserInfo(userId);
//        if(user == null){
//            throw new UnknownAccountException("账户不存在！");
//        }
//        if(user.getStatus() != 1){
//            throw new LockedAccountException("账户已被锁定！");
//        }

        AccountProfile profile = new AccountProfile();
//        BeanUtil.copyProperties(user,profile);
        return new SimpleAuthenticationInfo(profile,jwt.getCredentials(),getName());
    }
}
