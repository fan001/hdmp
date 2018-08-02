package com.hd.hdmp.controller.wework;

import com.hd.hdmp.entity.wework.WeworkUserModel;
import com.hd.hdmp.service.wework.WeworkUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class WeworkUserController {
    @Autowired
    WeworkUserService userService;

    //根据code获取userid
    @RequestMapping(value = "/user/code", method = RequestMethod.GET)
    public WeworkUserModel getUserByCode(@RequestParam("accountid") String accountid, @RequestParam("agentid") String agentid, @RequestParam("code") String code){
        System.out.println("accountid:"+accountid+",,agentid::"+agentid+",,code;;"+code);
        WeworkUserModel weworkUserModel = userService.findByCode(accountid, agentid, code);
        System.out.println("this is over!!!");
        return weworkUserModel;
    }
}