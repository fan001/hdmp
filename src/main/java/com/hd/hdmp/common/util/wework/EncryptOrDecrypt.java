package com.hd.hdmp.common.util.wework;


import com.hd.hdmp.common.util.wework.aes.WXBizMsgCrypt;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by admin on 2017/4/10.
 */
@Slf4j
public class EncryptOrDecrypt {
    public final static String token = "qdhd";//公众平台上面自己填写的Token
    public final static String EncodingAESKey = "zJmd2pOvHh3u2DgRdEJaDDPuUqJrHkEbTQkFjrf7Nvg";//公众平台上面自己填写的43位EncodingAESKey
    /**
     * 对需要回复的原文进行加密重新封装
     * @param corpid 企业微信号
     * @param msg_signature 加密信息
     * @param timestamp 时间戳
     * @param nonce 随机吗
     * @param echostr 带解密字符串
     * @return 验签微信的信息及返回明文
     */
    public static String  checksignature(String corpid,String msg_signature,String timestamp, String nonce, String echostr){
        String sEchoStr; //需要返回的明文
        log.info("corpid:::"+corpid);
        log.info("msg_signature="+msg_signature+",time="+timestamp+",nonce="+nonce+",echostr="+echostr);
        try{
            WXBizMsgCrypt wxcpt = getWXBizMsgCrypt(corpid);
            sEchoStr = wxcpt.VerifyURL(msg_signature, timestamp, nonce, echostr);
            log.info("verifyurl echostr: " + sEchoStr);
            return sEchoStr;
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    public static String  decryptMsg(String corpid, String replyXml, String msg_signature, String timestamp, String nonce) {
        try {
            WXBizMsgCrypt pc = getWXBizMsgCrypt(corpid);
            return pc.DecryptMsg(msg_signature, timestamp, nonce, replyXml);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static String  encryptMsg(String corpid, String replyXml, String timestamp, String nonce) {
        try {
            WXBizMsgCrypt pc = getWXBizMsgCrypt(corpid);
            return pc.EncryptMsg(replyXml, timestamp, nonce);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }



    public static WXBizMsgCrypt getWXBizMsgCrypt(String corpid){
        System.out.println("encrypt:::appid="+corpid);
        try {
            WXBizMsgCrypt pc = new WXBizMsgCrypt(token, EncodingAESKey, corpid);
            return pc;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
