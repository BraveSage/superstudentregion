package com.superstudentregion.controller;

import com.superstudentregion.bean.BasicUserInfo;
import com.superstudentregion.bean.UserInfo;
import com.superstudentregion.bean.UserInfoManager;
import com.superstudentregion.constant.Constants;
import com.superstudentregion.constant.RedisConstant;
import com.superstudentregion.constant.TokenConstant;
import com.superstudentregion.exception.UserException;
import com.superstudentregion.mapper.UserInfoMapper;
import com.superstudentregion.service.UserInfoService;
import com.superstudentregion.service.impl.RedisClientSingle;
import com.superstudentregion.stuenum.StateEnum;
import com.superstudentregion.stuenum.ThirdPartyType;
import com.superstudentregion.util.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/user")
public class UserInfoController {
    @Autowired
    RedisClientSingle redisClient;
    @Autowired
    UserInfoService userInfoService;
//    @Autowired
//    CurrentUserUtil currentUserUtil;
    @Autowired
    UserInfoMapper userInfoMapper;

    public UserInfoController() {
    }

    @RequestMapping(
            value = "/autoLogin",
            method = RequestMethod.POST
    )
    public Result autoLogin(String userName, String token) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(userName);
        userInfo = this.userInfoService.loginUser(userInfo);
        String rToken = (String)this.redisClient.get(TokenConstant.TOKEN_USER_PREFIX + userInfo.getUserId());
        if (!TokenUtils.isTokenValid(rToken, token)) {
            Result.failure(Constants.RESP_STATUS_INTERNAL_ERROR, "登录失败");
        }

        userInfo.setUserToken(rToken);
        return Result.success("登录成功", userInfo);
    }

    @RequestMapping(
            value = "/login",
            method = RequestMethod.POST
    )
    public Result login(String input, String password) {
        UserInfo userInfo = new UserInfo();
        if (!StringUtils.isBlank(input)) {
            if (input.matches(UserRegex.EMAIL_REGEX)) {
                userInfo.setEmail(input);
            } else {
                userInfo.setUserName(input);
            }
        } else {
            Result.failure(Constants.RESP_STATUS_INTERNAL_ERROR, "输入的邮箱/账号不能为空");
        }

        UserInfo user = this.userInfoService.loginUser(userInfo);
        if (user == null) {
            return Result.failure(Constants.RESP_STATUS_INTERNAL_ERROR, "您输入的账号不存在, 请重新输入");
        } else if (!user.getStateFlag().equals(StateEnum.ACTIVE.getValue()) && user.getStateFlag() != StateEnum.ACTIVE.getValue()) {
            if (!user.getStateFlag().equals(StateEnum.FROZEN.getValue()) && user.getStateFlag() != StateEnum.FROZEN.getValue()) {
                if (!password.equals(user.getPassword())) {
                    throw new UserException("账号和密码不匹配, 请重新输入");
                } else {
                    String token = TokenUtils.generateToken(user.getUserId(), user.getEmail());
                    this.redisClient.set(TokenConstant.TOKEN_USER_PREFIX + user.getUserId(), token);
                    user.setUserToken(token);
                    user.setPassword((String)null);
                    BasicUserInfo wechatUserInfo;
                    if (!StringUtils.isBlank(user.getQqOpenId())) {
                        wechatUserInfo = this.userInfoMapper.selectInfoByQQOpenid(user.getQqOpenId());
                        user.setQqUserInfo(wechatUserInfo);
                    }

                    if (!StringUtils.isBlank(user.getWechatOpenId())) {
                        wechatUserInfo = this.userInfoMapper.selectInfoByWechatOpenid(user.getWechatOpenId());
                        user.setWechatUserInfo(wechatUserInfo);
                    }

                    UserInfoManager manager = new UserInfoManager();
                    manager.setEmailUserInfo(user);
                    return Result.success("登录成功", manager);
                }
            } else {
                return Result.failure(Constants.RESP_STATUS_INTERNAL_ERROR, "用:该账号已被冻结, 无法登陆");
            }
        } else {
            return Result.failure(Constants.RESP_STATUS_INTERNAL_ERROR, "账号还未激活, 请单击激活邮件中的激活链接激活账号");
        }
    }

    @RequestMapping(
            value = "/register",
            method = RequestMethod.POST
    )
    public Result register(@RequestParam("password") String password, @RequestParam("email") String email) {
        UserInfo userInfo = new UserInfo();
        userInfo.setPassword(password);
        userInfo.setEmail(email);
        this.userInfoService.insertUser(userInfo);
        return Result.success("注册成功，请进入邮箱激活账号");
    }

    @RequestMapping(
            value = "/changeAvatar",
            method = RequestMethod.POST
    )
    public Result changeAvatar(MultipartFile avatar, Integer userId,String token) {
        String rToken = (String) this.redisClient.get(TokenConstant.TOKEN_USER_PREFIX + userId);

        if (!TokenUtils.isTokenValid(rToken, token)) {
            Result.failure(Constants.RESP_STATUS_INTERNAL_ERROR, "只有邮箱用户才能");
        }

        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(userId);
        this.userInfoService.updateAvatar(userInfo, avatar);
        this.redisClient.del(RedisConstant.USER_INFO_PREFIX + userInfo.getUserId());
        return Result.success("修改头像成功");
    }

    @RequestMapping(
            value = "/changeUserInfo",
            method = RequestMethod.POST
    )
    public Result changeUserInfo(@Valid UserInfo userInfo) {
        UserInfo info = this.userInfoMapper.ifExistUserName(userInfo.getUserName());
        if (info != null) {
            return Result.failure(Constants.RESP_STATUS_INTERNAL_ERROR, "账号已存在");
        } else {
            this.userInfoService.updateInfo(userInfo);
            return Result.success("修改信息成功");
        }
    }

    @RequestMapping(
            value = "/displayInfo",
            method = RequestMethod.POST
    )
    public Result displayInfo(@Valid UserInfo userInfo) {
        Integer userId = userInfo.getUserId();
        UserInfo u = this.userInfoService.selectInfoById(userId);

        return Result.success(null, u);
    }

    @RequestMapping(
            value = "/active",
            method = RequestMethod.GET
    )
    public String activeAccount(HttpServletRequest request) {
        String parameter = request.getParameter(GenerateLinkUtil.getSendTimeKey());
        long submitTime = Long.valueOf(parameter);
        long currentTimeMillis = System.currentTimeMillis();
        String userName = request.getParameter(GenerateLinkUtil.getUserNameKey());
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(userName);
        userInfo.setStateFlag(StateEnum.NORMAL.getValue());
        long duration = currentTimeMillis - submitTime;
        if (CurrentTimeUtil.duration(duration)) {
            throw new UserException("该链接已经失效");
        } else {
            int i = this.userInfoService.updateInfo(userInfo);
            if (i != 0) {
                return "账号激活成功";
            } else {
                throw new UserException("账号激活失败");
            }
        }
    }

    @RequestMapping(
            value = "/forgetPwd",
            method = {RequestMethod.POST}
    )
    public Result forgetPassword(String email, String password) {
        this.userInfoService.forgetPwd(email, password);
        return Result.success("发送邮件成功，请进入邮箱查看");
    }

    @RequestMapping(
            value = "/alterPwd",
            method = {RequestMethod.GET}
    )
    public Result alterPassword(HttpServletRequest request) {
        String email = request.getParameter(GenerateLinkUtil.getUserEmailKey());
        this.userInfoService.updatePwd(email);
        return Result.success("修改密码成功");
    }

    @RequestMapping(
            value = "/thirdPartyLogin",
            method = {RequestMethod.POST}
    )
    public Result thirdPartyLogin(BasicUserInfo userInfo) {
        UserInfoManager manager = this.userInfoService.insertThirdParty(userInfo);
        return manager == null ? Result.failure(Constants.RESP_STATUS_INTERNAL_ERROR, "登录失败") : Result.success("登录成功", manager);
    }

    @RequestMapping(
            value = "/bindUser0",
            method = RequestMethod.POST
    )
    public Result bindUser0(String email, Integer loginType) {
        UserInfo info = this.userInfoMapper.ifExistEmail(email);
        if (info != null) {
            BasicUserInfo wechatInfo;
            if (loginType.equals(ThirdPartyType.QQ_TYPE.getValue())) {
                if (!StringUtils.isBlank(info.getQqOpenId())) {
                    wechatInfo = this.userInfoMapper.selectInfoByQQOpenid(info.getQqOpenId());
                    info.setQqUserInfo(wechatInfo);
                    return Result.success("该邮箱用户已经被绑定，是否仍然绑定该邮箱（绑定后会将原邮箱覆盖）", info);
                } else {
                    return Result.success((String)null, info);
                }
            } else if (!StringUtils.isBlank(info.getWechatOpenId())) {
                wechatInfo = this.userInfoMapper.selectInfoByWechatOpenid(info.getWechatOpenId());
                info.setWechatUserInfo(wechatInfo);
                return Result.success("该邮箱用户已经被绑定，是否仍然绑定该邮箱（绑定后会将原邮箱覆盖）", info);
            } else {
                return Result.success((String)null, info);
            }
        } else {
            return Result.success("这是一个没有创建过的邮箱账户，可以绑定创建一个新的邮箱账号");
        }
    }

    @RequestMapping(
            value = "/bindUser",
            method = RequestMethod.POST
    )
    public Result bindUser(String openId, Integer loginType, String email, String password) {
        UserInfo userInfo = this.userInfoService.bindEmail(openId, loginType, email, password);
        return Result.success("绑定成功", userInfo);
    }

    @RequestMapping(
            value = "/bindThirdParty",
            method = RequestMethod.POST
    )
    public Result bindThird(BasicUserInfo basicUserInfo, Integer userId) {
        this.userInfoService.bindThirtyParty(basicUserInfo, userId);
        return basicUserInfo.getLoginType().equals(ThirdPartyType.QQ_TYPE.getValue()) ? Result.success("绑定QQ用户成功") : Result.success("绑定微信用户成功");
    }

    @RequestMapping(
            value = "getArticleImage",
            method = RequestMethod.POST
    )
    public Result getImage(MultipartFile articleImage) {
        return null;
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(){
        return "hello";
    }
}
