package com.hd.hdmp.entity.model.request;

/**
 * User: admin
 * Date: 16-11-14
 * Time: ����10:38
 * �ı���Ϣ
 */
public class LinkMessage extends BaseMessage {
    private String Title; //��Ϣ����
    private String Description; //��Ϣ����
    private String Url; //��Ϣ���ӵ�ַ

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
}
