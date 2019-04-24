package com.superstudentregion.bean;

import java.util.Date;

/**
 * 评论一级
 */
public class ArticleComment {
    /**
     * 评论Id
     */
    private Integer commentId;

    /**
     * 评论人ID
     */
    private Integer submitterId;

    /**
     * 文章ID
     */
    private Integer articleId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 提交时间
     */
    private Date submitTime;

    /**
     * 点赞数
     */
    private Integer likeCount;

    /**
     * 删除标识
     */
    private Integer delFlag;

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getSubmitterId() {
        return submitterId;
    }

    public void setSubmitterId(Integer submitterId) {
        this.submitterId = submitterId;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    @Override
    public String toString() {
        return "ArticleComment{" +
                "commentId=" + commentId +
                ", submitterId=" + submitterId +
                ", articleId=" + articleId +
                ", content='" + content + '\'' +
                ", submitTime=" + submitTime +
                ", likeCount=" + likeCount +
                ", delFlag=" + delFlag +
                '}';
    }
}
