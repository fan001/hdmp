package com.hd.hdmp.entity.model.response;

/**
 * User: admin
 * Date: 16-11-14
 * Time: ����10:38
 * ������Ϣ
 */
public class VoiceMessage extends BaseMessage {
    private Voice Voice; //��Ϣ����

    public Voice getImage() {
        return Voice;
    }

    public void setImage(Voice voice) {
        this.Voice = voice;
    }
}
