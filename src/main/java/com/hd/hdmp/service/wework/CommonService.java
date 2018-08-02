package com.hd.hdmp.service.wework;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.hd.hdmp.common.util.wework.CommonUtil;
import com.hd.hdmp.common.util.wework.MD5Util;
import com.hd.hdmp.common.util.wework.RandomUtil;
import com.hd.hdmp.entity.wework.WeworkAgentModel;
import com.hd.hdmp.entity.wework.WeworkLogModel;
import com.hd.hdmp.entity.wework.WeworkTokenModel;
import com.hd.hdmp.entity.wework.WeworkUserModel;
import com.hd.hdmp.repository.wework.WeworkAgentModelRepository;
import com.hd.hdmp.repository.wework.WeworkLogModelRepository;
import com.hd.hdmp.repository.wework.WeworkTokenModelRepository;
import com.hd.hdmp.repository.wework.WeworkUserModelRepository;
import lombok.extern.log4j.Log4j;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
@Slf4j
public class CommonService {
    @Autowired
    WeworkLogModelRepository weworkLogModelRepository;
    @Autowired
    WeworkTokenModelRepository weworkTokenModelRepository;
    @Autowired
    WeworkAgentModelRepository weworkAgentModelRepository;
    @Autowired
    WeworkTokenService weworkTokenService;
    @Autowired
    WeworkUserModelRepository weworkUserModelRepository;
    @Autowired
    WeworkLogService weworkLogService;

    //通过code获取用户id
    public String getUseridByCode(String access_token, String code){
        log.info("根据用户code获取userid,,code=="+code);
        return CommonUtil.getUserIDFromOauth2(access_token, code);
    }

    //通用保存企业微信日志信息
    @Transactional(rollbackFor = {RuntimeException.class,Exception.class})
    public boolean saveWeworkLog(WeworkLogModel weworkLogModel){
        log.info("保存企业微信日志，cznr=="+weworkLogModel.getCznr());
        weworkLogModelRepository.save(weworkLogModel);
        return true;
    }

    //获取微信config基础信息
    public Map<String, String> getWeworkConfig(String accountid, String url){
        Map map = new HashMap<String, String>();
        WeworkTokenModel weworkTokenModel = weworkTokenService.doTokenService(accountid,"");
        WeworkAgentModel weworkAgentModel = weworkAgentModelRepository.findByAccountidAndAgentid(accountid,"");
        SortedMap<Object,Object> params = new TreeMap<Object,Object>();
        String timeStamp = Long.toString(new Date().getTime()/1000);
        String nonce = RandomUtil.generateString(32);
        params.put("timeStamp",timeStamp);
        params.put("nonceStr", nonce);
        params.put("url",url);
        params.put("jsapi_ticket",weworkTokenModel.getTicket());
        String string1 = "jsapi_ticket="+weworkTokenModel.getTicket()+"&noncestr="+nonce+"&timestamp="+timeStamp+"&url="+url;
        String signature = MD5Util.sha1(string1);
        log.info("jsapi_ticket===="+weworkTokenModel.getTicket());
        log.info("signature===="+signature);
        map.put("timeStamp",timeStamp);
        map.put("nonceStr", nonce);
        map.put("appid",weworkAgentModel.getCorpid());
        map.put("signature",signature);
        return map;
    }

    //企业微信发送卡片消息
    public Boolean sendMessage(String userid){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        WeworkAgentModel agentModel = weworkAgentModelRepository.findByGrgsbh("");
        String cznr=userid+"于"+sdf.format(new Date())+"收到一条任务";

        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+agentModel.getCorpid()+"&redirect_uri=http%3a%2f%2f"+agentModel.getYm()+"%2findex.html%23%2fredTask%3faccountid%3d"
                +agentModel.getAccountid()+"%26agentid%3d"+agentModel.getAgentid()+"%26dbid%3d"+"XXXXXXXXXXXXXXXXXXXXX"+"&response_type=code&scope=snsapi_base&agentid="+agentModel.getAgentid()+"&state=qdhd#wechat_redirect";
        String message = "<div class=\\\"gray\\\">"+sdf.format(new Date())+"</div>收到1条新<>任务;\n任务编号;\n点击消息进入应用查询详细信息!";

        return commonSendMessage(agentModel.getAccountid(),agentModel.getAgentid(),userid,url,message,cznr);
    }

    //企业微信发送卡片消息::通过电话查询用户然后发送
    public Boolean sendMessageByMobile(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        WeworkAgentModel agentModel = weworkAgentModelRepository.findByGrgsbh("");
        WeworkUserModel weworkUserModel = weworkUserModelRepository.findByMobile("");
        if(weworkUserModel ==null){
            System.out.println("userid==nulllllllll");
            return false;
        }
        String userid = weworkUserModel.getUserid();
        String cznr="系统自动发送新任务提醒至"+userid+"，发送时间："+sdf.format(new Date());
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+agentModel.getCorpid()+"&redirect_uri=http%3a%2f%2f"+agentModel.getYm()+"%2findex.html%23%2ftodoTask%3faccountid%3d"
                +agentModel.getAccountid()+"%26agentid%3d"+agentModel.getAgentid()+"%26&response_type=code&scope=snsapi_base&agentid="+agentModel.getAgentid()+"&state=qdhd#wechat_redirect";
        String message = "<div class=\\\"gray\\\">"+sdf.format(new Date())+"</div>收到新的任务;\n点击消息进入应用!";

        return commonSendMessage(agentModel.getAccountid(), agentModel.getAgentid(), userid, url, message, cznr);
    }

    public Boolean commonSendMessage(String accountid, String agentid,String userid, String url, String message, String cznr){
        System.out.println("accountid:==>"+accountid+",,agentid::=>"+agentid);
        WeworkTokenModel weworkTokenModel = weworkTokenService.doTokenService(accountid,agentid);
        String czjg = "1";boolean flag = false;String bz="";
        try{
            String string = "{\"touser\":\""+userid+"\", \"msgtype\":\"textcard\", \"agentid\":\""+agentid+"\", \"textcard\": {\"title\":\"任务通知\",\"description\":\""+message
                    +"\",\"url\":\""+url+"\",\"btntxt\":\"进入应用\"}}";
            String str = CommonUtil.sendMessage(weworkTokenModel.getToken(), string);
            ObjectMapper mapper = new ObjectMapper();
            Map map = mapper.readValue(str, Map.class);
            if((int)map.get("errcode")==0){
                flag = true;
            }else{
                //发送失败
                czjg = "0";
            }
            bz = str;
        }catch (Exception e){
            e.printStackTrace();
            bz = e.getMessage();
            //出现异常
            czjg = "2";
        }finally {
            weworkLogService.saveLog(accountid,agentid,cznr,bz,czjg);
            return flag;
        }
    }

    public Map<String, String> getJssdk(String accountid , String agentid, String url){
        //获取应用信息
        WeworkAgentModel agentModel = weworkAgentModelRepository.findByAccountidAndAgentid(accountid, agentid);
        //根据accountid 获取当前access_token tickit
        WeworkTokenModel tokenModel = weworkTokenService.doTokenService(accountid,agentid);
        //签名
        String appid = agentModel.getCorpid();
        String timestamp = Long.toString(System.currentTimeMillis()/1000);
        String nonceStr = RandomUtil.generateString(15);
        String string1 = "jsapi_ticket="+tokenModel.getTicket()+"&noncestr="+nonceStr+"&timestamp="+timestamp+"&url=" + url;
        String signature = "";
        //签名
        try{
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
            log.info("signature=====>"+signature);
        }
        catch (Exception e){
            log.error("WeChatController.makeWXTicket=====Start");
            log.error(e.getMessage(),e);
            log.error("WeChatController.makeWXTicket=====End");
        }
        //结果集返回
        Map<String, String> map = new HashMap<>();
        map.put("appid",appid);
        map.put("timestamp",timestamp);
        map.put("nonceStr",nonceStr);
        map.put("signature",signature);
        map.put("jsapi_ticket",tokenModel.getTicket());
        map.put("url",url);
        System.out.println("map::==>>"+map);
        return map;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash){
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        System.out.println(result);
        return result;
    }


    public String getTranslate(Float latitude, Float longitude){
        return CommonUtil.translate(latitude, longitude);
    }

}