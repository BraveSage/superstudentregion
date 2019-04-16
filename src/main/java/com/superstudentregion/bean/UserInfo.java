package com.superstudentregion.bean;

public class UserInfo extends BasicUserInfo {
    private Integer userId;
    private String password;
    private String userName;
    private Integer sex;
    private String school;
    private String major;
    private String signature;
    private String avatarPath;
    private String email;
    private String tel;
    private Integer role;
    private Integer stateFlag;
    private String codeUrl;
    private Integer delFlag;
    private String userToken;
    private String qqOpenId;
    private String wechatOpenId;
    private BasicUserInfo qqUserInfo;
    private BasicUserInfo wechatUserInfo;
    private Integer followingCount;
    private Integer followersCount;

    @Override
    public Integer getUserId() {
        return userId;
    }

    @Override
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public Integer getSex() {
        return sex;
    }

    @Override
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

    @Override
    public String getSignature() {
        return signature;
    }

    @Override
    public void setSignature(String signature) {
        this.signature = signature;
    }

    @Override
    public String getAvatarPath() {
        return avatarPath;
    }

    @Override
    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
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

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Integer getStateFlag() {
        return stateFlag;
    }

    public void setStateFlag(Integer stateFlag) {
        this.stateFlag = stateFlag;
    }

    public String getCodeUrl() {
        return codeUrl;
    }

    public void setCodeUrl(String codeUrl) {
        this.codeUrl = codeUrl;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public String getQqOpenId() {
        return qqOpenId;
    }

    public void setQqOpenId(String qqOpenId) {
        this.qqOpenId = qqOpenId;
    }

    public String getWechatOpenId() {
        return wechatOpenId;
    }

    public void setWechatOpenId(String wechatOpenId) {
        this.wechatOpenId = wechatOpenId;
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
    public Integer getFollowingCount() {
        return followingCount;
    }

    @Override
    public void setFollowingCount(Integer followingCount) {
        this.followingCount = followingCount;
    }

    @Override
    public Integer getFollowersCount() {
        return followersCount;
    }

    @Override
    public void setFollowersCount(Integer followersCount) {
        this.followersCount = followersCount;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userId=" + userId +
                ", password='" + password + '\'' +
                ", userName='" + userName + '\'' +
                ", sex=" + sex +
                ", school='" + school + '\'' +
                ", major='" + major + '\'' +
                ", signature='" + signature + '\'' +
                ", avatarPath='" + avatarPath + '\'' +
                ", email='" + email + '\'' +
                ", tel='" + tel + '\'' +
                ", role=" + role +
                ", stateFlag=" + stateFlag +
                ", codeUrl='" + codeUrl + '\'' +
                ", delFlag=" + delFlag +
                ", userToken='" + userToken + '\'' +
                ", qqOpenId='" + qqOpenId + '\'' +
                ", wechatOpenId='" + wechatOpenId + '\'' +
                ", qqUserInfo=" + qqUserInfo +
                ", wechatUserInfo=" + wechatUserInfo +
                ", followingCount=" + followingCount +
                ", followersCount=" + followersCount +
                '}';
    }
}
