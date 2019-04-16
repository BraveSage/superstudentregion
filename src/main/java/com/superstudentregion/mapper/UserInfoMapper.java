package com.superstudentregion.mapper;

import com.superstudentregion.bean.BasicUserInfo;
import com.superstudentregion.bean.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserInfoMapper {
    List<UserInfo> selectAll();

    List<Integer> selectRoleIdByUid(Integer roleId);

    String findPwdByUserName(String userName);

    Integer findRoleByUserName(String userName);

    int insertUser(UserInfo user);

    UserInfo selectInfoById(@Param("userId") Integer userId);

    UserInfo ifExistEmail(@Param("email") String email);

    UserInfo ifExistUserName(@Param("userName") String userName);

    UserInfo loginUser(UserInfo userInfo);

    int updateInfo(UserInfo userInfo);

    int updatePwd(UserInfo userInfo);

    BasicUserInfo selectInfoByQQOpenid(@Param("openId") String openId);

    BasicUserInfo selectInfoByWechatOpenid(@Param("openId") String openId);

    int insertQQUser(BasicUserInfo basicUserInfo);

    int insertWechatUser(BasicUserInfo basicUserInfo);

    int updateQQUserInfo(BasicUserInfo qqUserInfo);

    int updateWechatInfo(BasicUserInfo wechatUserInfo);
}