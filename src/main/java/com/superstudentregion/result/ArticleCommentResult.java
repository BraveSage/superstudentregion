package com.superstudentregion.result;

import com.superstudentregion.bean.ArticleComment;

public class ArticleCommentResult extends ArticleComment {
    private String userName;

    private String avatarPath;

    private Integer sex;

    private String school;

    private String major;

    private String signature;

    private String email;

    private String tel;

    private Integer userStateFlag;

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

    @Override
    public String toString() {
        return "ArticleCommentResult{" +
                "userName='" + userName + '\'' +
                ", avatarPath='" + avatarPath + '\'' +
                ", sex=" + sex +
                ", school='" + school + '\'' +
                ", major='" + major + '\'' +
                ", signature='" + signature + '\'' +
                ", email='" + email + '\'' +
                ", tel='" + tel + '\'' +
                ", userStateFlag=" + userStateFlag +
                '}';
    }
}
