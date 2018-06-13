package com.hd.hdmp.auth;

import com.google.common.collect.Sets;
import com.hd.hdmp.entity.StaffinfoEntity;
import com.hd.hdmp.service.StaffinfoService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * @author fanzhenxing
 * @create 2018/5/29 9:55 PM
 */

public class UserRealm extends AuthorizingRealm {

    @Autowired
    StaffinfoService staffinfoService;


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        StaffinfoEntity staffinfoEntity = staffinfoService.findByUsername(username);

        if (staffinfoEntity == null) {
            throw new UnknownAccountException();//没找到帐号
        }

        ShiroUser shiroUser = new ShiroUser(staffinfoEntity.getId(),staffinfoEntity.getUsername()
                ,staffinfoEntity.getName(), Sets.newHashSet(),Sets.newHashSet());

        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                shiroUser, //用户名
                staffinfoEntity.getPassword(), //短信验证码
                ByteSource.Util.bytes(staffinfoEntity.getCredentialsSalt()),
                getName()  //realm name
        );

        return authenticationInfo;
    }
}
