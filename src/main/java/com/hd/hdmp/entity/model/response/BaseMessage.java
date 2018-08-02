package com.hd.hdmp.entity.model.response;

/**
 * User: admin
 * Date: 16-11-14
 * Time: ����10:32
 * ��Ϣ���ࣨ�����˺�->��ͨ�û���
 */
public class BaseMessage {
    private String ToUserName;       //���շ��˺ţ�һ��OpenId��
    private String FromUserName;     //������΢�ź�
    private Long CreateTime;         //��Ϣ����ʱ�䣨���ͣ�
    private String MsgType;          //��Ϣ���ͣ�text/music/news��

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
