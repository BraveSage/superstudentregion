package com.superstudentregion.result;

import com.superstudentregion.bean.ArticleInfo;

public class ArticleResult extends ArticleInfo {
    private String userName;

    private String avatarPath;

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

    @Override
    public String toString() {
        return "ArticleResult{" +
                "userName='" + userName + '\'' +
                ", avatarPath='" + avatarPath + '\'' +
                '}';
    }
}
