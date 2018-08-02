package com.hd.hdmp.entity.model.response;

/**
 * User: admin
 * Date: 16-11-14
 * Time: ����2:51
 * ��Ƶ model
 */
public class Video {
    private String MediaId;//ý���ļ�ID
    private String ThumbMediaId;//����ͼ��ý��ID

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
