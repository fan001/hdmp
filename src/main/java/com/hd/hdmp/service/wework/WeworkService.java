package com.hd.hdmp.service.wework;

import com.hd.hdmp.common.util.wework.MessageUtil;
import com.hd.hdmp.entity.wework.WeworkAgentModel;
import com.hd.hdmp.entity.wework.WeworkCorpModel;
import com.hd.hdmp.repository.wework.WeworkAgentModelRepository;
import com.hd.hdmp.repository.wework.WeworkCorpModelRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@Slf4j
public class WeworkService {

    @Autowired
    WeworkAgentModelRepository weworkAgentModelRepository;
    @Autowired
    WeworkCorpModelRepository weworkCorpModelRepository;

    public String checkWeworkMessage(String accountid,String msg_signature,String timestamp,String nonce,String echostr){
        WeworkCorpModel weworkCorpModel = loadWeworkCorpModelByAccountid(accountid);
        log.info("^^^^^^^"+weworkCorpModel.getCorpid());
        return MessageUtil.checkSignature(weworkCorpModel.getCorpid(),msg_signature,timestamp,nonce,echostr);
    }

    @Cacheable("agentModelActiveCache")
    public WeworkAgentModel loadWeworkAgentModelByAccountid(String accountid){
        WeworkAgentModel weworkAgentModel = weworkAgentModelRepository.findByAccountid(accountid);
        return weworkAgentModel;
    }

    @Cacheable("agentModelActiveCache")
    public WeworkAgentModel loadWeworkAgentModelByAccountidAndAgentid(String accountid, String agentid){
        WeworkAgentModel weworkAgentModel = weworkAgentModelRepository.findByAccountidAndAgentid(accountid,agentid);
        return weworkAgentModel;
    }

    @Cacheable("agentModelActiveCache")
    public WeworkAgentModel loadWeworkAgentModelByGrgsbh(String grgsbh){
        WeworkAgentModel weworkAgentModel = weworkAgentModelRepository.findByGrgsbh(grgsbh);
        return weworkAgentModel;
    }

    @Cacheable("corpModelActiveCache")
    public WeworkCorpModel loadWeworkCorpModelByAccountid(String accountid){
        WeworkCorpModel weworkCorpModel = weworkCorpModelRepository.findByAccountid(accountid);
        return weworkCorpModel;
    }

    public String doWeworkMessage(HttpServletRequest request) throws Exception{
        String accountid = request.getParameter("accountid");//企业编号
        String msg_signature = request.getParameter("msg_signature");//加密信息
        String timestamp = request.getParameter("timestamp");//时间戳
        String nonce = request.getParameter("nonce");//随机数
        log.info("accountid=="+accountid+",msg_signature="+msg_signature+",timestamp="+timestamp+",nonce="+nonce);
        WeworkCorpModel weworkCorpModel = loadWeworkCorpModelByAccountid(accountid);
        log.info("corpid::"+weworkCorpModel.getCorpid());
        return MessageUtil.doSignature(weworkCorpModel.getCorpid(), MessageUtil.parseString(request),msg_signature,timestamp,nonce);
    }

}