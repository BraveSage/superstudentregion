package com.superstudentregion.bean;

import java.util.Date;

public class ArticleInfo {
    private Integer articleId;
    private Integer userId;
    private String articleTitle;
    private Date submitTime;
    private String articleHtmlPath;
    private String articleMdPath;
    private Integer likeCount;
    private Long collectorCount;
    private Integer readPermission;
    private Integer typeId;
    private Integer delFlag;
    private Integer stateFlag;
    private Date releaseTime;
    private Date preReleaseTime;
    private Date sufReleaseTime;
    private Integer collectionState;
    private Long browseCount;

    public ArticleInfo() {
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public Date getPreReleaseTime() {
        return preReleaseTime;
    }

    public void setPreReleaseTime(Date preReleaseTime) {
        this.preReleaseTime = preReleaseTime;
    }

    public Date getSufReleaseTime() {
        return sufReleaseTime;
    }

    public void setSufReleaseTime(Date sufReleaseTime) {
        this.sufReleaseTime = sufReleaseTime;
    }

    public Integer getTypeId() {
        return this.typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public void setCollectorCount(Long collectorCount) {
        this.collectorCount = collectorCount;
    }

    public Long getCollectorCount() {
        return collectorCount;
    }

    public Integer getCollectionState() {
        return collectionState;
    }

    public void setCollectionState(Integer collectionState) {
        this.collectionState = collectionState;
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

    public String getArticleMdPath() {
        return articleMdPath;
    }

    public void setArticleMdPath(String articleMdPath) {
        this.articleMdPath = articleMdPath;
    }

    public Integer getLikeCount() {
        return this.likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Long getBrowseCount() {
        return browseCount;
    }

    public void setBrowseCount(Long browseCount) {
        this.browseCount = browseCount;
    }

    @Override
    public String toString() {
        return "ArticleInfo{" +
                "articleId=" + articleId +
                ", userId=" + userId +
                ", articleTitle='" + articleTitle + '\'' +
                ", submitTime=" + submitTime +
                ", articleHtmlPath='" + articleHtmlPath + '\'' +
                ", articleMdPath='" + articleMdPath + '\'' +
                ", likeCount=" + likeCount +
                ", collectorCount=" + collectorCount +
                ", readPermission=" + readPermission +
                ", typeId=" + typeId +
                ", delFlag=" + delFlag +
                ", stateFlag=" + stateFlag +
                ", releaseTime=" + releaseTime +
                ", preReleaseTime=" + preReleaseTime +
                ", sufReleaseTime=" + sufReleaseTime +
                ", collectionState=" + collectionState +
                ", browseCount=" + browseCount +
                '}';
    }
}
