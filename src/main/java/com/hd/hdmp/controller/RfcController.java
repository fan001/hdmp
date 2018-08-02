package com.hd.hdmp.controller;

import com.hd.hdmp.common.bean.ResultBean;
import com.hd.hdmp.service.RfcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping(value = "/api")
public class RfcController {
    @Autowired
    RfcService rfcService;

    @RequestMapping(value = "/phone/{phoneNumber}",method = RequestMethod.GET)
    public ResultBean<?> loadMobileBind(@PathVariable String phoneNumber) throws Exception{
        System.out.println("收到的参数===>>"+phoneNumber);
        ResultBean<Map<String, List<Map<String, Object>>>> resultBean = new ResultBean<>(rfcService.validatePhone(phoneNumber));
        return resultBean;
    }

    @RequestMapping(value = "/license/{phoneNumber}",method = RequestMethod.GET)
    public ResultBean<?> validateLicense(@PathVariable String phoneNumber) throws Exception{
        System.out.println("收到的参数===>>"+phoneNumber);
        ResultBean<Map<String, Object>> resultBean = new ResultBean<>(rfcService.validateLicense(phoneNumber));
        return resultBean;
    }
}