package com.hd.hdmp.entity.model.request;

/**
 * User: admin
 * Date: 16-11-14
 * Time: ����10:38
 * ��Ƶ��Ϣ
 */
public class VideoMessage extends BaseMessage {
    private String MediaId; //��Ƶ��Ϣý��ID
    private String ThumbMediaId;//��Ƶ��Ϣ����ͼ��ID

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    public String getThumbMediaId() {
        return ThumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        ThumbMediaId = thumbMediaId;
    }
}
