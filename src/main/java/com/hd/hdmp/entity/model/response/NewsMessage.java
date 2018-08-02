package com.hd.hdmp.entity.model.response;

import java.util.List;

/**
 * User: admin
 * Date: 16-11-14
 * Time: ����10:38
 * �ı���Ϣ
 */
public class NewsMessage extends BaseMessage {
    private int ArticleCount; //ͼ����Ϣ����������Ϊ10��֮��
    private List<Article> Articles;//Ĭ�ϵ�һ��Ϊ��ͼ

    public int getArticleCount() {
        return ArticleCount;
    }

    public void setArticleCount(int articleCount) {
        ArticleCount = articleCount;
    }

    public List<Article> getArticles() {
        return Articles;
    }

    public void setArticles(List<Article> articles) {
        Articles = articles;
    }
}
