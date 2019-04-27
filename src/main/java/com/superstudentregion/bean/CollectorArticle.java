package com.superstudentregion.bean;

public class CollectorArticle {
    private Integer articleId;

    private Integer collectorUserId;

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getCollectorUserId() {
        return collectorUserId;
    }

    public void setCollectorUserId(Integer collectorUserId) {
        this.collectorUserId = collectorUserId;
    }

    @Override
    public String toString() {
        return "CollectorArticle{" +
                "articleId=" + articleId +
                ", collectorUserId=" + collectorUserId +
                '}';
    }
}
