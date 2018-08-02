package com.hd.hdmp.config;

import com.google.common.base.Strings;
import com.hd.hdmp.auth.ShiroUser;
import com.hd.hdmp.entity.wework.WeworkUserModel;
import com.hd.hdmp.service.wework.WeworkUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author fanzhenxing
 * @create 2018/6/22 6:53 PM
 */
@Slf4j
public class WeworkFilter extends PathMatchingFilter {

    @Autowired
    WeworkUserService userService;

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        log.info("excute wechatFilter ,check wheather the user wheather login.....");
        System.out.println("***********************************************");
        Subject currentUser = SecurityUtils.getSubject();
        Object pricipal = currentUser.getPrincipal();
        if(pricipal != null){
            log.info("the user has bean login ,and the login user is:" + ((ShiroUser)pricipal).getUsername() );
            return true;
        }
        String accountId = request.getParameter("accountid");
        String agentid = request.getParameter("agentid");
        String code = request.getParameter("code");
        System.out.println("accountid==>"+accountId+",,agentid==>"+agentid+",,code=>"+code);
        if (!Strings.isNullOrEmpty(accountId) && !Strings.isNullOrEmpty(agentid) && !Strings.isNullOrEmpty(code)) {
            WeworkUserModel userModel = userService.findByCode(accountId, agentid, code);
            log.error("the user u query is:" +userModel == null?"无此用户" : userModel.getName());
            if (userModel != null) {
                String userId = userModel.getUserid();
                Subject subject = SecurityUtils.getSubject();
                UsernamePasswordToken token = new UsernamePasswordToken(userId, "123");
                try {
                    subject.login(token);
                    log.error("get userName is:" + userId);
                } catch (AuthenticationException e) {
                    e.printStackTrace();
                    log.error(userId + ",longin failure exception:", e);
                }
            }
        }
        return super.preHandle(request, response);
    }
}
