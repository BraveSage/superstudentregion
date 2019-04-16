package com.superstudentregion.bean;

public class UserInfoManager {

    private UserInfo emailUserInfo;
    private BasicUserInfo qqUserInfo;
    private BasicUserInfo wechatUserInfo;

    public UserInfo getEmailUserInfo() {
        return emailUserInfo;
    }

    public void setEmailUserInfo(UserInfo emailUserInfo) {
        this.emailUserInfo = emailUserInfo;
    }

    public BasicUserInfo getQqUserInfo() {
        return qqUserInfo;
    }

    public void setQqUserInfo(BasicUserInfo qqUserInfo) {
        this.qqUserInfo = qqUserInfo;
    }

    public BasicUserInfo getWechatUserInfo() {
        return wechatUserInfo;
    }

    public void setWechatUserInfo(BasicUserInfo wechatUserInfo) {
        this.wechatUserInfo = wechatUserInfo;
    }

    @Override
    public String toString() {
        return "UserInfoManager{" +
                "emailUserInfo=" + emailUserInfo +
                ", qqUserInfo=" + qqUserInfo +
                ", wechatUserInfo=" + wechatUserInfo +
                '}';
    }
}
