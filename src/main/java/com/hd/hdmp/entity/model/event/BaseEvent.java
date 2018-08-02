package com.hd.hdmp.entity.model.event;

/**
 * User: admin
 * Date: 16-11-14
 * Time: ����10:32
 * �¼�����
 */
public class BaseEvent {
    private String ToUesrName;       //������΢�ź�
    private String FromUesrName;     //���ͷ��˺ţ�һ��OpenId��
    private Long CreateTime;         //��Ϣ����ʱ�䣨���ͣ�
    private String MsgType;          //��Ϣ���ͣ�event��
    private String Event;           //�¼����ͣ���

    public String getToUesrName() {
        return ToUesrName;
    }

    public void setToUesrName(String toUesrName) {
        ToUesrName = toUesrName;
    }

    public String getFromUesrName() {
        return FromUesrName;
    }

    public void setFromUesrName(String fromUesrName) {
        FromUesrName = fromUesrName;
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

    public String getEvent() {
        return Event;
    }

    public void setEvent(String event) {
        Event = event;
    }
}
