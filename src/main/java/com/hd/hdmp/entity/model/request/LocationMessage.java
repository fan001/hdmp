package com.hd.hdmp.entity.model.request;

/**
 * User: admin
 * Date: 16-11-14
 * Time: ����10:38
 * ����λ����Ϣ
 */
public class LocationMessage extends BaseMessage {
    private String Location_X; //����λ��ά��
    private String Location_Y; //����λ�þ���
    private String Scale; //��ͼ���Ŵ�С
    private String Label; //����λ����Ϣ

    public String getLocation_X() {
        return Location_X;
    }

    public void setLocation_X(String location_X) {
        Location_X = location_X;
    }

    public String getLocation_Y() {
        return Location_Y;
    }

    public void setLocation_Y(String location_Y) {
        Location_Y = location_Y;
    }

    public String getScale() {
        return Scale;
    }

    public void setScale(String scale) {
        Scale = scale;
    }

    public String getLabel() {
        return Label;
    }

    public void setLabel(String label) {
        Label = label;
    }
}
