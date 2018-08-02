package com.hd.hdmp.entity.model.response;

/**
 * User: admin
 * Date: 16-11-14
 * Time: ����10:38
 * ������Ϣ
 */
public class MusicMessage extends BaseMessage {
    private Music Music; //��Ϣ����

    public Music getImage() {
        return Music;
    }

    public void setImage(Music music) {
        this.Music = music;
    }
}
