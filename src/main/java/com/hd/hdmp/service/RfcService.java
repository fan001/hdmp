package com.hd.hdmp.service;

import com.hd.hdmp.common.rfc.RfcCommon;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class RfcService {

    public Map<String, List<Map<String, Object>>> validatePhone(String phone) throws Exception{
        log.error("validatePhone......");
        List<Map<String, Map<String ,Object>>> list = new ArrayList<>();
        Map<String, Map<String ,Object>> map = new HashMap();
        Map<String ,Object> map_detail = new HashMap();
        Map<String,  List<Map<String, Object>>> map_back = new HashMap();
        map_detail.put("ZTELE",phone);
        map.put("IV_INFO",map_detail);
        list.add(map);
        List<Map<String, Object>> listT = RfcCommon.jcoTableToList(RfcCommon.getRfcData(null,null,list,"ZWT_ZHUCE_RFC","A002").getTable("EV_INFO"));
        log.info("返回手机号查询结果：："+listT);
        map_back.put("EV_INFO",listT);
        return map_back;
    }

    public Map<String, Object> validateLicense(String license) throws Exception{
        List<Map<String, Map<String ,Object>>> list = new ArrayList<>();
        Map<String, Map<String ,Object>> map = new HashMap();
        Map<String ,Object> map_detail = new HashMap();
        Map<String, Object> map_back = new HashMap();
        map_detail.put("ZZLICENCE",license);
        map.put("IV_INFO",map_detail);
        list.add(map);
        List<Map<String, Object>> listT = RfcCommon.jcoTableToList(RfcCommon.getRfcData(null,null,list,"ZWT_ZHUCE_RFC","A001").getTable("EV_INFO"));
        log.info("查询是否注册：："+listT);
        map_back.put("EV_INFO",listT);
        return map_back;
    }
}