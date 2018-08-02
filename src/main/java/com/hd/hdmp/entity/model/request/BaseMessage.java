package com.hd.hdmp.entity.model.request;

/**
 * User: admin
 * Date: 16-11-14
 * Time: 上午10:32
 * 消息基类（公众账号->普通用户）
 */
public class BaseMessage {
    private String ToUserName;       //接收方账号（一个OpenId）
    private String FromUserName;     //开法者微信号
    private Long CreateTime;         //消息创建时间（整型）
    private String MsgType;          //消息类型（text/music/news）

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public Long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Long createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }
}
