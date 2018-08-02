package com.hd.hdmp.entity.model.event;

/**
 * User: admin
 * Date: 16-11-14
 * Time: ����1:53
 * ɨ���ά���¼�
 */
public class QRCodeEvent extends BaseEvent {
    private String EventKey;
    private String Ticket;

    public String getEventKey() {
        return EventKey;
    }

    public void setEventKey(String eventKey) {
        EventKey = eventKey;
    }

    public String getTicket() {
        return Ticket;
    }

    public void setTicket(String ticket) {
        Ticket = ticket;
    }
}
