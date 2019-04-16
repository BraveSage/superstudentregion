package com.superstudentregion.bean;

import java.io.Serializable;

public class BasicUserInfo implements Serializable {
    private Integer id;
    private String openId;
    private Integer userId;
    private String userName;
    private String nickname;
    private String signature;
    private String avatarPath;
    private Integer sex;
    private Integer loginType;
    private Integer followingCount;
    private Integer followersCount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
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

    public Integer getLoginType() {
        return loginType;
    }

    public void setLoginType(Integer loginType) {
        this.loginType = loginType;
    }

    public Integer getFollowingCount() {
        return followingCount;
    }

    public void setFollowingCount(Integer followingCount) {
        this.followingCount = followingCount;
    }

    public Integer getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(Integer followersCount) {
        this.followersCount = followersCount;
    }

    @Override
    public String toString() {
        return "BasicUserInfo{" +
                "id=" + id +
                ", openId='" + openId + '\'' +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", nickname='" + nickname + '\'' +
                ", signature='" + signature + '\'' +
                ", avatarPath='" + avatarPath + '\'' +
                ", sex=" + sex +
                ", loginType=" + loginType +
                ", followingCount=" + followingCount +
                ", followersCount=" + followersCount +
                '}';
    }
}
