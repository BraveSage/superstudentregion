package com.superstudentregion.bean;

import java.util.Date;

public class ArticleInfo {
    private Integer articleId;
    private Integer userId;
    private String articleTitle;
    private Date submitTime;
    private String articleHtmlPath;
    private String articleXmlPath;
    private Integer likeCount;
    private Integer collectionCount;
    private Integer readPermission;
    private Integer typeId;
    private Integer delFlag;
    private Integer stateFlag;

    public ArticleInfo() {
    }

    public Integer getTypeId() {
        return this.typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getCollectionCount() {
        return this.collectionCount;
    }

    public void setCollectionCount(Integer collectionCount) {
        this.collectionCount = collectionCount;
    }

    public Integer getReadPermission() {
        return this.readPermission;
    }

    public void setReadPermission(Integer readPermission) {
        this.readPermission = readPermission;
    }

    public Integer getDelFlag() {
        return this.delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public Integer getStateFlag() {
        return this.stateFlag;
    }

    public void setStateFlag(Integer stateFlag) {
        this.stateFlag = stateFlag;
    }

    public Integer getArticleId() {
        return this.articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getArticleTitle() {
        return this.articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public Date getSubmitTime() {
        return this.submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public String getArticleHtmlPath() {
        return this.articleHtmlPath;
    }

    public void setArticleHtmlPath(String articleHtmlPath) {
        this.articleHtmlPath = articleHtmlPath;
    }

    public String getArticleXmlPath() {
        return this.articleXmlPath;
    }

    public void setArticleXmlPath(String articleXmlPath) {
        this.articleXmlPath = articleXmlPath;
    }

    public Integer getLikeCount() {
        return this.likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public String toString() {
        return "ArticleInfo{articleId=" + this.articleId + ", userId=" + this.userId + ", articleTitle='" + this.articleTitle + '\'' + ", submitTime=" + this.submitTime + ", articleHtmlPath='" + this.articleHtmlPath + '\'' + ", articleXmlPath='" + this.articleXmlPath + '\'' + ", likeCount=" + this.likeCount + ", collectionCount=" + this.collectionCount + ", readPermission=" + this.readPermission + ", typeId=" + this.typeId + ", delFlag=" + this.delFlag + ", stateFlag=" + this.stateFlag + '}';
    }
}
