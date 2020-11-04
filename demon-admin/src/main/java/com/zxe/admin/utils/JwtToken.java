package com.zxe.admin.utils;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @Author:Elaine
 * @Description:
 * @Date: Created in 8:58 PM 2020/11/3
 * @Version: 1.0
 */
public class JwtToken implements AuthenticationToken {
    private String token;

    public JwtToken(String jwt){
        this.token = jwt;
    }
    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
