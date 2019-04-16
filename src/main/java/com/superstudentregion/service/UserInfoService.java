package com.superstudentregion.service;

import com.superstudentregion.bean.BasicUserInfo;
import com.superstudentregion.bean.UserInfo;
import com.superstudentregion.bean.UserInfoManager;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface UserInfoService {
    int insertUser(UserInfo userInfo);

    int updateAvatar(UserInfo userInfo, MultipartFile avatar);

    UserInfo selectInfoById(Integer userId);

    int updateInfo(UserInfo userInfo);

    UserInfo loginUser(UserInfo userInfo);

    void forgetPwd(String email, String password);

    void updatePwd(String email);

    UserInfoManager insertThirdParty(BasicUserInfo basicUserInfo);

    UserInfo bindEmail(String openId, Integer loginType, String email, String password);

    BasicUserInfo bindThirtyParty(BasicUserInfo basicUserInfo, Integer userId);
}

