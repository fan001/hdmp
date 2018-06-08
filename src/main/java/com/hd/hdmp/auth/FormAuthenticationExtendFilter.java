package com.hd.hdmp.auth;

import com.google.gson.Gson;
import com.hd.hdmp.common.bean.ResultBean;
import com.hd.hdmp.common.util.HttpContextUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author fanzhenxing
 * @create 2018/6/8 2:43 PM
 */
public class FormAuthenticationExtendFilter extends FormAuthenticationFilter {
    private static final Logger log = LoggerFactory.getLogger(FormAuthenticationFilter.class);

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if (this.isLoginRequest(request, response)) {
            if (this.isLoginSubmission(request, response)) {
                if (log.isTraceEnabled()) {
                    log.trace("Login submission detected. Attempting to execute login.");
                }
                return this.executeLogin(request, response);
            } else {
                if (log.isTraceEnabled()) {
                    log.trace("Login page view.");
                }
                return true;
            }
        } else {
            if (log.isTraceEnabled()) {
                log.trace("Attempting to access a path which requires authentication. Forwarding to the Authentication url [" + this.getLoginUrl() + "]");
            }
            ResultBean<String> resultBean = new ResultBean<String>("请先完成登录");
            resultBean.setCode(ResultBean.FAIL);
            printResponse(resultBean, response);
            return false;
        }

    }

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        ResultBean<String> resultBean = new ResultBean<>("login success");
        printResponse(resultBean, response);
        return false;
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        System.out.println("login failure........." + e);
        return super.onLoginFailure(token, e, request, response);
    }


    /**
     * 处理返回结果
     *
     * @param resultBean
     * @param response
     * @return
     */

    private void printResponse(ResultBean<String> resultBean, ServletResponse response) {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setContentType("application/json;charset=utf-8");
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
        System.out.println("aaaaaaaaa:" + HttpContextUtils.getOrigin() );
        httpResponse.setHeader("Access-Control-Allow-Origin", HttpContextUtils.getOrigin());
        String json = new Gson().toJson(resultBean);
        try {
            PrintWriter out = httpResponse.getWriter();
            out.println(json);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
