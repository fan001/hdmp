package com.hd.hdmp.entity.model.event;

/**
 * User: admin
 * Date: 16-11-14
 * Time: ����1:53
 * �ϱ�����λ���¼�
 */
public class LocationEvent extends BaseEvent {
    private String Latitude; //����λ��ά��
    private String Longitude;//����λ�þ���
    private String Precision;//����λ�þ���

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public String getPrecision() {
        return Precision;
    }

    public void setPrecision(String precision) {
        Precision = precision;
    }
}
