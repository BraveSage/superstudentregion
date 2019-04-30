package com.superstudentregion.result;

import com.superstudentregion.bean.ArticleInfo;

public class ArticleResult extends ArticleInfo {

//    private Integer userId;

    private String userName;

    private String avatarPath;

    private Integer sex;

    private String school;

    private String major;

    private String signature;

    private String email;

    private String tel;

    private Integer userStateFlag;

    private Integer commentCounts;

    private Integer collectorUserId;

    private Integer authorUserId;

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getUserStateFlag() {
        return userStateFlag;
    }

    public void setUserStateFlag(Integer userStateFlag) {
        this.userStateFlag = userStateFlag;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public Integer getCommentCounts() {
        return commentCounts;
    }

    public void setCommentCounts(Integer commentCounts) {
        this.commentCounts = commentCounts;
    }

    public Integer getCollectorUserId() {
        return collectorUserId;
    }

    public void setCollectorUserId(Integer collectorUserId) {
        this.collectorUserId = collectorUserId;
    }

    public Integer getAuthorUserId() {
        return authorUserId;
    }

    public void setAuthorUserId(Integer authorUserId) {
        this.authorUserId = authorUserId;
    }

    @Override
    public String toString() {
        return "ArticleResult{" +
                "userName='" + userName + '\'' +
                ", avatarPath='" + avatarPath + '\'' +
                ", sex=" + sex +
                ", school='" + school + '\'' +
                ", major='" + major + '\'' +
                ", signature='" + signature + '\'' +
                ", email='" + email + '\'' +
                ", tel='" + tel + '\'' +
                ", userStateFlag=" + userStateFlag +
                ", commentCounts=" + commentCounts +
                ", collectorUserId=" + collectorUserId +
                ", authorUserId=" + authorUserId +
                '}';
    }
}
