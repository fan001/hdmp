package com.hd.hdmp.controller;

import com.hd.hdmp.auth.ShiroUser;
import org.apache.shiro.SecurityUtils;

/**
 * @author fanzhenxing
 * @create 2018/6/8 1:50 PM
 */
public abstract class BaseController {

    public ShiroUser getShiroUser(){
       return  (ShiroUser)SecurityUtils.getSubject().getPrincipal();
    }

    /**
     * 返回ID
     * @return
     */
    public Integer getId(){
        return getShiroUser().getId();
    }

    /**
     * 返回用户名
     * @return
     */
    public String getUserName(){
       return getShiroUser().getUsername();
    }



}
