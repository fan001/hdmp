package com.hd.hdmp.service.wework;


import com.hd.hdmp.common.util.wework.CommonUtil;
import com.hd.hdmp.entity.wework.WeworkAgentModel;
import com.hd.hdmp.entity.wework.WeworkTokenModel;
import com.hd.hdmp.repository.wework.WeworkTokenModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class WeworkTokenService {
    @Autowired
    WeworkTokenModelRepository weworkTokenModelRepository;
    @Autowired
    WeworkService weworkService;

    @Transactional(rollbackFor = {RuntimeException.class,Exception.class})
    public Boolean saveWeworkTokenModel(WeworkTokenModel weworkTokenModel){
        weworkTokenModelRepository.save(weworkTokenModel);
        return true;
    }

    @Cacheable("tokenModelActiveCache")
    public WeworkTokenModel findByAccountidAndAgentid(String accountid, String agentid){
        WeworkTokenModel weworkTokenModel = weworkTokenModelRepository.findByAccountidAndAgentid(accountid, agentid);
        return weworkTokenModel;
    }

    public WeworkTokenModel doTokenService(String accountid, String agentid){
        WeworkTokenModel weworkTokenModel = findByAccountidAndAgentid(accountid, agentid);
        Long time_version = weworkTokenModel.getTime_version();
        Long currentTime = System.currentTimeMillis();
        if(currentTime - time_version >= 7200000){
            WeworkAgentModel weworkAgentModel = weworkService.loadWeworkAgentModelByAccountidAndAgentid(accountid, agentid);
            String corpid = weworkAgentModel.getCorpid();
            String secret = weworkAgentModel.getSecret();
            System.out.println("corpid="+corpid+",secret="+secret);
            String token = CommonUtil.getToken(corpid, secret).getAccessToken();
            String ticket = CommonUtil.getTicket(token);
            System.out.println("token="+token);
            weworkTokenModel.setToken(token);
            weworkTokenModel.setTicket(ticket);
            weworkTokenModel.setTime_version(currentTime);
            saveWeworkTokenModel(weworkTokenModel);
        }else{
            System.out.println("未到更新时间 !!!!!!!"+accountid+",,agentid::"+agentid);
        }
        return weworkTokenModel;
    }
}