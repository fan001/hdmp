package com.hd.hdmp.entity.model.response;

/**
 * User: admin
 * Date: 16-11-14
 * Time: ����2:47
 * ͼƬ model
 */
public class Article {
    private String Title;//ͼ����Ϣ����
    private String Description;//ͼ����Ϣ����
    private String PicUrl;//��������
    private String Url;//���ͼ����Ϣ��ת��ַ

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
}
