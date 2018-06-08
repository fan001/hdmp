package com.hd.hdmp.service;

import com.hd.hdmp.entity.HdmpSysLog;
import com.hd.hdmp.repository.SysLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author fanzhenxing
 * @create 2018/5/29 11:32 AM
 */
@Component
public class SysLogService {

    @Autowired
    SysLogRepository sysLogRepository;


    public HdmpSysLog saveSysLog(HdmpSysLog sysLog) {
        return sysLogRepository.save(sysLog);
    }
}
