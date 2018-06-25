package com.hd.hdmp.common.filter;

import com.google.common.base.Strings;
import com.hd.hdmp.auth.ShiroUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.PathMatchingFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author fanzhenxing
 * @create 2018/6/22 6:53 PM
 */
@Slf4j
public class WechatFilter extends PathMatchingFilter {
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        log.info("excute wechatFilter ,check wheather the user wheather login.....");
        Subject currentUser = SecurityUtils.getSubject();
        Object pricipal = currentUser.getPrincipal();
        if(pricipal != null){
            log.info("the user has bean login ,and the login user is:" + ((ShiroUser)pricipal).getUsername() );
            return true;
        }

        String accountId = request.getParameter("accountid");
        String code = request.getParameter("code");
        if(!Strings.isNullOrEmpty(accountId) && !Strings.isNullOrEmpty(code)){

        }

        return super.preHandle(request, response);
    }
}
