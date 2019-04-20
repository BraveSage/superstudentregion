package com.superstudentregion.bean;

import java.util.Date;

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
     * 作者ID
     */
    private Integer authorId;

    /**
     * 评论内容
     */
    private String comment;

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

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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
                ", authorId=" + authorId +
                ", comment='" + comment + '\'' +
                ", submitTime=" + submitTime +
                ", likeCount=" + likeCount +
                '}';
    }
}
