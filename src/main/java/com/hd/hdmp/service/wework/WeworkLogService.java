package com.hd.hdmp.service.wework;

import com.hd.hdmp.entity.wework.WeworkLogModel;
import com.hd.hdmp.repository.wework.WeworkLogModelRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Component
@Slf4j
public class WeworkLogService {
    @Autowired
    WeworkLogModelRepository weworkLogModelRepository;

    @Transactional(rollbackFor = {RuntimeException.class,Exception.class})
    public boolean saveLog(String accountid, String agentid, String cznr, String bz, String czjg){
        WeworkLogModel weworkLogModel = new WeworkLogModel();
        weworkLogModel.setAccountid(accountid);
        weworkLogModel.setAgentid(agentid);
        weworkLogModel.setCznr(cznr);
        weworkLogModel.setBz(bz);
        weworkLogModel.setCzjg(czjg);
        weworkLogModel.setCreate_date(new Date());
        weworkLogModelRepository.save(weworkLogModel);
        return true;
    }
}