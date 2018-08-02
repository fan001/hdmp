package com.hd.hdmp.entity.model.response;

/**
 * User: admin
 * Date: 16-11-14
 * Time: ����10:38
 * ��Ƶ��Ϣ
 */
public class VideoMessage extends BaseMessage {
    private Video Video; //��Ϣ����

    public Video getImage() {
        return Video;
    }

    public void setImage(Video video) {
        this.Video = video;
    }
}
