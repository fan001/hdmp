package com.hd.hdmp.entity.model.request;

/**
 * User: admin
 * Date: 16-11-14
 * Time: ����10:38
 * ͼƬ��Ϣ
 */
public class ImageMessage extends BaseMessage {
    private String PicUrl; //ͼƬ��ַ
    private String MediaId; //ý��ID

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }
}
