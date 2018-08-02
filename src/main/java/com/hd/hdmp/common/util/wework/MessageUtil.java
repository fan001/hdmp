package com.hd.hdmp.common.util.wework;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: admin
 * Date: 16-11-14
 * Time: 下午3:52
 *
 */
public class MessageUtil {

    public static final String REQ_MESSAGE_TYPE_TEXT = "text";              //请求消息类型：文本
    public static final String REQ_MESSAGE_TYPE_IMAGE = "image";            //请求消息类型：图片
    public static final String REQ_MESSAGE_TYPE_VOICE = "voice";            //请求消息类型：音频
    public static final String REQ_MESSAGE_TYPE_VIDEO = "video";            //请求消息类型：视频
    public static final String REQ_MESSAGE_TYPE_SHORTVIDEO = "shortvideo"; //请求消息类型：小视频
    public static final String REQ_MESSAGE_TYPE_LOCATION = "location";      //请求消息类型：地理位置
    public static final String REQ_MESSAGE_TYPE_LINK = "link";              //请求消息类型：链接
    public static final String REQ_MESSAGE_TYPE_EVENT = "event";            //请求消息类型：事件推送

    public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";          //事件类型：关注
    public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";      //事件类型：取消关注
    public static final String EVENT_TYPE_SCAN = "SCAN";                    //事件类型：扫描带参数二维码
    public static final String EVENT_TYPE_LOCATION = "LOCATION";            //事件类型：上报地理位置
    public static final String EVENT_TYPE_CLICK = "click";                  //事件类型：自定义菜单
    public static final String EVENT_TYPE_VIEW = "view";                    //事件类型：自定义菜单直接跳转

    public static final String RESP_MESSAGE_TYPE_TEXT = "text";             //响应消息类型：文本
    public static final String RESP_MESSAGE_TYPE_IMAGE = "image";           //响应消息类型：图片
    public static final String RESP_MESSAGE_TYPE_VOICE = "voice";           //响应消息类型：音频
    public static final String RESP_MESSAGE_TYPE_VIDEO = "video";           //响应消息类型：视频
    public static final String RESP_MESSAGE_TYPE_MUSIC = "music";           //响应消息类型：音乐
    public static final String RESP_MESSAGE_TYPE_NEWS = "news";             //响应消息类型：图文

    /**
     * 解析收到的请求(XML)
     * @param xml
     * @return Map<String, String>--解密后的内容
     * @throws Exception
     */
    public static Map<String ,String> parseXml(String xml) throws Exception {
        Map<String ,String> map = new HashMap<String, String>();
        Document document;
        //解密微信发送的
        document = DocumentHelper.parseText(xml);
        //得到XML根节点
        Element root = document.getRootElement();
        //得到根节点的全部子节点
        List<Element> elementList = root.elements();
        for(Element e : elementList) {
            map.put(e.getName(), e.getText());
        }
        return map;
    }
    /**
     * 解析收到的请求(XML)
     * @param request
     * @return String 接受的内容
     * @throws Exception
     */
    public static String parseString(HttpServletRequest request) throws Exception {
        //从request中取得输入流
        InputStream is = request.getInputStream();
        //采用加密
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line;
        StringBuffer buf = new StringBuffer();
        while ((line = reader.readLine()) != null) {
            buf.append(line);
        }
        System.out.println("line::buf:::=" + buf.toString());
        return buf.toString();
    }

//    /**
//     * 扩展xstream支持其他CDATA
//     */
//    private static XStream xstream = new XStream(new XppDriver(){
//        public HierarchicalStreamWriter createWriter(Writer out){
//            return new PrettyPrintWriter(out) {
//                boolean cdata = true;
//                public void startNode(String name, Class clazz){
//                    super.startNode(name, clazz);
//                }
//                protected void writeText(QuickWriter writer, String text){
//                    if(cdata){
//                        writer.write("<![CDATA[");
//                        writer.write(text);
//                        writer.write("]]>");
//                    }else {
//                        writer.write(text);
//                    }
//                }
//            };
//        }
//    });
//
//    public static String messageToXml(BaseMessage baseMessage){
//        xstream.alias("xml",baseMessage.getClass());
//        return xstream.toXML(baseMessage);
//    }
//    /**
//     * 文本消息对象转化为XML
//     */
//    public static String messageToXml(TextMessage textMessage){
//        xstream.alias("xml",textMessage.getClass());
//        return xstream.toXML(textMessage);
//    }
//
//    /**
//     * 图片消息对象转化为XML
//     */
//    public static String messageToXml(ImageMessage imageMessage){
//        xstream.alias("xml",imageMessage.getClass());
//        return xstream.toXML(imageMessage);
//    }
//
//    /**
//     * 音频消息对象转化为XML
//     */
//    public static String messageToXml(VoiceMessage voiceMessage){
//        xstream.alias("xml",voiceMessage.getClass());
//        return xstream.toXML(voiceMessage);
//    }
//
//    /**
//     * 文本消息对象转化为XML
//     */
//    public static String messageToXml(VideoMessage videoMessage){
//        xstream.alias("xml",videoMessage.getClass());
//        return xstream.toXML(videoMessage);
//    }
//
//    /**
//     * 音乐消息对象转化为XML
//     */
//    public static String messageToXml(MusicMessage musicMessage){
//        xstream.alias("xml",musicMessage.getClass());
//        return xstream.toXML(musicMessage);
//    }
//
//    /**
//     * 图文消息对象转化为XML
//     */
//    public static String messageToXml(NewsMessage newsMessagew){
//        xstream.alias("xml",newsMessagew.getClass());
//        xstream.alias("item",new Article().getClass());
//        return xstream.toXML(newsMessagew);
//    }

    public static String checkSignature(String corpid,String msg_signature,String timestamp, String nonce, String echostr){
        return EncryptOrDecrypt.checksignature(corpid,msg_signature,timestamp,nonce,echostr);
    }

    public static String doSignature(String corpid,String xml, String msg_signature,String timestamp, String nonce) throws Exception{
        //解密收到的报文请求
        String respXml =  EncryptOrDecrypt.decryptMsg(corpid,xml,msg_signature,timestamp,nonce);
        System.out.println("fromWeworkXml::"+respXml);
        //将xml转为map
        Map<String, String> requestMap = MessageUtil.parseXml(respXml);
        //返回的明文xml
        //String backXml = new WeworkAccounts().weworkAccount(requestMap);
        String backXml = "";
        System.out.println("backXml::"+backXml);
        //加密返回的报文
        return EncryptOrDecrypt.encryptMsg(corpid,backXml,timestamp,nonce);
    }

    public static String checkSuiteSignature(String suiteid,String msg_signature,String timestamp, String nonce, String echostr){
        return EncryptOrDecrypt.checksignature(suiteid,msg_signature,timestamp,nonce,echostr);
    }
}
