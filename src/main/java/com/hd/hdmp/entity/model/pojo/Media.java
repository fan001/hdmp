package com.hd.hdmp.entity.model.pojo;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 16-11-30
 * Time: ����2:56
 * ��ý��
 */
public class Media {
    private String type;//ý���ļ�����
    private String mediaId;//ý���ļ���ʶ
    private Long createAt;//ý���ϴ�ʱ��
    private String url;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public Long getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Long createAt) {
        this.createAt = createAt;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
