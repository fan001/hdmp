package com.hd.hdmp.entity.model.news;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 16-12-5
 * Time: ����5:12
 * To change this template use File | Settings | File Templates.
 */
public class Article {
    private String thumb_media_id;       //ͼ����Ϣ����ͼ��media_id
    private String author;               //����
    private String title;                //����
    private String content_source_url;   //�Ķ�ԭ�ĵĵ�ַ
    private String content;              //ͼ����Ϣҳ������ݣ�֧��HTML��ǩ������΢��֧��Ȩ�޵Ĺ��ںţ�����ʹ��a��ǩ
    private String digest;               //ͼ����Ϣ������
    private String show_cover_pic;       //�Ƿ���ʾ���棬1Ϊ��ʾ��0����ʾ

    public String getThumb_media_id() {
        return thumb_media_id;
    }

    public void setThumb_media_id(String thumb_media_id) {
        this.thumb_media_id = thumb_media_id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent_source_url() {
        return content_source_url;
    }

    public void setContent_source_url(String content_source_url) {
        this.content_source_url = content_source_url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getShow_cover_pic() {
        return show_cover_pic;
    }

    public void setShow_cover_pic(String show_cover_pic) {
        this.show_cover_pic = show_cover_pic;
    }
}
