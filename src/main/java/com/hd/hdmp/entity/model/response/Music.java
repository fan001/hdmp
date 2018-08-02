package com.hd.hdmp.entity.model.response;

/**
 * User: admin
 * Date: 16-11-14
 * Time: ����2:51
 * ���� model
 */
public class Music {
    private String Title;//���ֱ���
    private String Description;//��������
    private String MusicUrl;//��������
    private String HQMusicUrl;//�������������ӣ�Wi-Fi��������ʹ�ø����Ӳ�������
    private String ThumbMediaId;//����ͼ��ý��ID

    public String getThumbMediaId() {
        return ThumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        ThumbMediaId = thumbMediaId;
    }

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

    public String getMusicUrl() {
        return MusicUrl;
    }

    public void setMusicUrl(String musicUrl) {
        MusicUrl = musicUrl;
    }

    public String getHQMusicUrl() {
        return HQMusicUrl;
    }

    public void setHQMusicUrl(String HQMusicUrl) {
        this.HQMusicUrl = HQMusicUrl;
    }
}
