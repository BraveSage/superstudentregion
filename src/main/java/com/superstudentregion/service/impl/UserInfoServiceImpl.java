package com.superstudentregion.service.impl;

import com.superstudentregion.bean.BasicUserInfo;
import com.superstudentregion.bean.UserInfo;
import com.superstudentregion.bean.UserInfoManager;
import com.superstudentregion.constant.RedisConstant;
import com.superstudentregion.constant.TokenConstant;
import com.superstudentregion.exception.UserException;
import com.superstudentregion.mapper.UserInfoMapper;
import com.superstudentregion.service.UserInfoService;
import com.superstudentregion.stuenum.DelEnum;
import com.superstudentregion.stuenum.StateEnum;
import com.superstudentregion.stuenum.ThirdPartyType;
import com.superstudentregion.util.ExamineUtil;
import com.superstudentregion.util.SendMail;
import com.superstudentregion.util.ThirdPartyNickNameUtil;
import com.superstudentregion.util.TokenUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    UserInfoMapper userInfoMapper;
    @Autowired
    RedisClientSingle redisTemplate;
    private Logger log = LoggerFactory.getLogger(UserInfoServiceImpl.class);

    public UserInfoServiceImpl() {
    }

    public UserInfo loginUser(UserInfo userInfo) {
        return this.userInfoMapper.loginUser(userInfo);
    }

    public void updatePwd(String email) {
        Object pwd = this.redisTemplate.get(email);
        if (pwd.equals((Object)null)) {
            throw new UserException(500, "该链接已经过期，请重新发送邮件");
        } else {
            UserInfo userInfo = new UserInfo();
            userInfo.setEmail(email);
            userInfo.setPassword(pwd.toString());
            int i = this.userInfoMapper.updatePwd(userInfo);
            if (i == 0) {
                throw new UserException(500, "修改密码失败");
            }
        }
    }

    public void forgetPwd(String email, String password) {
        UserInfo userInfo = this.userInfoMapper.ifExistEmail(email);
        if (userInfo == null) {
            throw new UserException(500, "用户邮箱不存在");
        } else if (password.length() >= 6 && password.length() <= 16) {
            Long ttl = this.redisTemplate.ttl(email);
            if (ttl < 0L) {
                SendMail.forgetPwd(email);
                this.redisTemplate.set(email, password, 600L);
            } else {
                throw new UserException(500, "密码修改确认邮件有效期为 10 分钟，您可以在" + (ttl + 1L) + "分钟之后再进行该操作");
            }
        } else {
            throw new UserException(500, "密码设置不符合6-16位的要求");
        }
    }

    public UserInfo selectInfoById(Integer userId) {
        if (this.redisTemplate.get(RedisConstant.USER_INFO_PREFIX + userId) != null) {
            return (UserInfo)this.redisTemplate.get(RedisConstant.USER_INFO_PREFIX + userId);
        } else {
            UserInfo userInfo = this.userInfoMapper.selectInfoById(userId);
            this.redisTemplate.set(RedisConstant.USER_INFO_PREFIX + userId, userInfo);
            return userInfo;
        }
    }

    @Transactional
    public int updateInfo(UserInfo userInfo) {
        if (userInfo.getTel() != null && !ExamineUtil.examineUserPhone(userInfo.getTel())) {
        }

        if (userInfo.getEmail() != null && ExamineUtil.examineUserEmail(userInfo.getEmail())) {
            UserInfo userInfo1 = this.userInfoMapper.ifExistEmail(userInfo.getEmail());
            if (userInfo1 != null) {
                ExamineUtil.examinExistEmail(userInfo1.getStateFlag());
            }
        }

        return this.userInfoMapper.updateInfo(userInfo);
    }

    @Transactional
    public int insertUser(UserInfo userInfo) {
        String userName = ThirdPartyNickNameUtil.createName(ThirdPartyType.EMAIL.getName());
        userInfo.setUserName(userName);
        userInfo.setAvatarPath("http://193.112.79.70/sturegion/user/avatar/ROOT.jpg");
        String userEmail = userInfo.getEmail();
        if (userEmail == null) {
            throw new UserException("邮箱不能为空");
        } else {
            UserInfo userInfo1 = this.userInfoMapper.ifExistEmail(userInfo.getEmail());
            if (userInfo1 != null) {
                if (userInfo1.getStateFlag().equals(StateEnum.ACTIVE.getValue())) {
                    if (this.redisTemplate.get(userEmail + 1) != null) {
                        Long ttl = this.redisTemplate.ttl(userEmail);
                        throw new UserException(500, "邮箱注册确认邮件有效期为 10 分钟，您可以在" + (ttl + 1L) + "分钟之后再进行该操作");
                    } else {
                        this.redisTemplate.set(userEmail + 1, "nondue", 600L);
                        SendMail.registerCode(userInfo);
                        throw new UserException(0, "已经重新发送激活邮件，请进入邮箱激活用户");
                    }
                } else {
                    throw new UserException(500, "该邮箱账号已经被注册, 您可以去登录或者更换邮箱账号");
                }
            } else {
                UserInfo userInfo2 = this.userInfoMapper.ifExistUserName(userName);
                if (userInfo2 != null) {
                    throw new UserException(500, "该用户名已经被使用, 请更换");
                } else {
                    userInfo.setStateFlag(StateEnum.ACTIVE.getValue());
                    int i = this.userInfoMapper.insertUser(userInfo);
                    SendMail.registerCode(userInfo);
                    return i;
                }
            }
        }
    }

    @Transactional
    public int updateAvatar(UserInfo userInfo, MultipartFile avatar) {
        try {
            String fileName = avatar.getOriginalFilename();
            String filePath = "/home/sturegion/userInfo/avatar/" + userInfo.getUserId() + "/" + System.currentTimeMillis() + fileName;
            String avatarPath = "http://193.112.79.70/sturegion/user/avatar/" + userInfo.getUserId() + "/" + System.currentTimeMillis() + fileName;
            File uploadFile = new File(filePath);
            if (!uploadFile.getParentFile().exists()) {
                uploadFile.getParentFile().mkdirs();
            }

            avatar.transferTo(uploadFile);
            uploadFile.createNewFile();
            userInfo.setAvatarPath(avatarPath);
            this.userInfoMapper.updateInfo(userInfo);
            return 0;
        } catch (IOException var7) {
            this.log.error("上传文件失败", var7);
            throw new UserException(500, "更换头像失败");
        }
    }

    @Transactional
    public UserInfoManager insertThirdParty(BasicUserInfo basicUserInfo) {
        UserInfoManager manager = new UserInfoManager();
        BasicUserInfo wechatuserInfo;
        UserInfo userInfo;
        String token;
        String userName;
        int i;
        if (basicUserInfo.getLoginType().equals(ThirdPartyType.QQ_TYPE.getValue())) {
            wechatuserInfo = this.userInfoMapper.selectInfoByQQOpenid(basicUserInfo.getOpenId());
            if (wechatuserInfo == null) {
                userName = ThirdPartyNickNameUtil.createName(ThirdPartyType.QQ_TYPE.getName());
                basicUserInfo.setUserName(userName);
                i = this.userInfoMapper.insertQQUser(basicUserInfo);
                if (i < 1) {
                    throw new UserException(500, "创建QQ用户失败");
                } else {
                    basicUserInfo.setLoginType(ThirdPartyType.QQ_TYPE.getValue());
                    manager.setQqUserInfo(basicUserInfo);
                    return manager;
                }
            } else if (wechatuserInfo.getUserId() == null) {
                wechatuserInfo.setLoginType(ThirdPartyType.QQ_TYPE.getValue());
                manager.setQqUserInfo(wechatuserInfo);
                return manager;
            } else {
                userInfo = this.userInfoMapper.selectInfoById(wechatuserInfo.getUserId());
                if (!userInfo.getStateFlag().equals(StateEnum.NORMAL.getValue()) && !userInfo.getDelFlag().equals(DelEnum.DEL.getValue())) {
                    wechatuserInfo.setLoginType(ThirdPartyType.QQ_TYPE.getValue());
                    manager.setQqUserInfo(wechatuserInfo);
                    return manager;
                } else {
                    userInfo.setQqUserInfo(wechatuserInfo);
                    token = (String)this.redisTemplate.get(TokenConstant.TOKEN_USER_PREFIX + userInfo.getUserId());
                    userInfo.setUserToken(token);
                    manager.setEmailUserInfo(userInfo);
                    return manager;
                }
            }
        } else {
            wechatuserInfo = this.userInfoMapper.selectInfoByWechatOpenid(basicUserInfo.getOpenId());
            if (wechatuserInfo == null) {
                userName = ThirdPartyNickNameUtil.createName(ThirdPartyType.WECHAT_TYPE.getName());
                basicUserInfo.setUserName(userName);
                i = this.userInfoMapper.insertWechatUser(basicUserInfo);
                if (i < 1) {
                    throw new UserException(500, "创建微信用户失败");
                } else {
                    basicUserInfo.setLoginType(ThirdPartyType.WECHAT_TYPE.getValue());
                    manager.setWechatUserInfo(basicUserInfo);
                    return manager;
                }
            } else if (wechatuserInfo.getUserId() == null) {
                wechatuserInfo.setLoginType(ThirdPartyType.WECHAT_TYPE.getValue());
                manager.setWechatUserInfo(wechatuserInfo);
                return manager;
            } else {
                userInfo = this.userInfoMapper.selectInfoById(wechatuserInfo.getUserId());
                if (!userInfo.getStateFlag().equals(StateEnum.NORMAL.getValue()) && !userInfo.getDelFlag().equals(DelEnum.DEL.getValue())) {
                    wechatuserInfo.setLoginType(ThirdPartyType.WECHAT_TYPE.getValue());
                    manager.setWechatUserInfo(wechatuserInfo);
                    return manager;
                } else {
                    userInfo.setWechatUserInfo(wechatuserInfo);
                    token = (String)this.redisTemplate.get(TokenConstant.TOKEN_USER_PREFIX + userInfo.getUserId());
                    userInfo.setUserToken(token);
                    manager.setEmailUserInfo(userInfo);
                    return manager;
                }
            }
        }
    }

    @Transactional
    public UserInfo bindEmail(String openId, Integer loginType, String email, String password) {
        UserInfo userInfo = new UserInfo();
        int i;
        int i2;
        String token;
        if (loginType.equals(ThirdPartyType.QQ_TYPE.getValue())) {
            BasicUserInfo qqInfo = this.userInfoMapper.selectInfoByQQOpenid(openId);
            UserInfo info = this.userInfoMapper.ifExistEmail(email);
            if (info != null) {
                if (!StringUtils.isBlank(info.getQqOpenId())) {
                    this.validate(info, openId, loginType, qqInfo, password);
                }

                userInfo.setQqOpenId(openId);
                userInfo.setUserId(info.getUserId());
                if (!info.getPassword().equals(password)) {
                    throw new UserException(500, "邮箱账号和密码不匹配");
                } else {
                    i = this.userInfoMapper.updateInfo(userInfo);
                    qqInfo.setUserId(userInfo.getUserId());
                    i2 = this.userInfoMapper.updateQQUserInfo(qqInfo);
                    if (i != 1 && i2 != 1) {
                        throw new UserException(500, "绑定邮箱失败");
                    } else {
                        info.setQqUserInfo(qqInfo);
                        return info;
                    }
                }
            } else {
                userInfo.setEmail(email);
                userInfo.setAvatarPath(qqInfo.getAvatarPath());
                userInfo.setSex(qqInfo.getSex());
                userInfo.setPassword(password);
                userInfo.setQqOpenId(qqInfo.getOpenId());
                userInfo.setUserName(qqInfo.getUserName());
                i = this.insertUser(userInfo);
                qqInfo.setUserId(userInfo.getUserId());
                i2 = this.userInfoMapper.updateQQUserInfo(qqInfo);
                if (i != 1 && i2 != 1) {
                    throw new UserException(500, "绑定邮箱失败");
                } else {
                    token = TokenUtils.generateToken(userInfo.getUserId(), email);
                    userInfo.setUserToken(token);
                    userInfo.setQqUserInfo(qqInfo);
                    return userInfo;
                }
            }
        } else if (loginType.equals(ThirdPartyType.WECHAT_TYPE.getValue())) {
            UserInfo info = this.userInfoMapper.ifExistEmail(email);
            BasicUserInfo wechatInfo = this.userInfoMapper.selectInfoByWechatOpenid(openId);
            if (info != null) {
                if (!StringUtils.isBlank(info.getWechatOpenId())) {
                    this.validate(info, openId, loginType, wechatInfo, password);
                }

                if (!info.getPassword().equals(password)) {
                    throw new UserException(500, "邮箱账号和密码不匹配");
                } else {
                    userInfo.setWechatOpenId(openId);
                    userInfo.setUserId(info.getUserId());
                    i = this.userInfoMapper.updateInfo(info);
                    i2 = this.userInfoMapper.updateWechatInfo(wechatInfo);
                    if (i != 1 && i2 != 1) {
                        throw new UserException(500, "绑定邮箱失败");
                    } else {
                        info.setWechatUserInfo(wechatInfo);
                        return info;
                    }
                }
            } else {
                userInfo.setEmail(email);
                userInfo.setAvatarPath(wechatInfo.getAvatarPath());
                userInfo.setSex(wechatInfo.getSex());
                userInfo.setPassword(password);
                userInfo.setWechatOpenId(wechatInfo.getOpenId());
                userInfo.setUserName(wechatInfo.getUserName());
                i = this.insertUser(userInfo);
                i2 = this.userInfoMapper.updateWechatInfo(wechatInfo);
                if (i != 1 && i2 != 1) {
                    throw new UserException(500, "绑定邮箱失败");
                } else {
                    token = TokenUtils.generateToken(userInfo.getUserId(), email);
                    this.redisTemplate.set(TokenConstant.TOKEN_USER_PREFIX + userInfo.getUserId(), token);
                    userInfo.setUserToken(token);
                    userInfo.setWechatUserInfo(wechatInfo);
                    return userInfo;
                }
            }
        } else {
            throw new UserException(500, "传入的绑定类型是错误的");
        }
    }

    public BasicUserInfo bindThirtyParty(BasicUserInfo basicUserInfo, Integer userId) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(userId);
        BasicUserInfo wechatUserInfo;
        String name;
        if (basicUserInfo.getLoginType().equals(ThirdPartyType.QQ_TYPE.getValue())) {
            userInfo.setQqOpenId(basicUserInfo.getOpenId());
            wechatUserInfo = this.userInfoMapper.selectInfoByQQOpenid(basicUserInfo.getOpenId());
            basicUserInfo.setUserId(userId);
            if (wechatUserInfo == null) {
                name = ThirdPartyNickNameUtil.createName(ThirdPartyType.QQ_TYPE.getName());
                basicUserInfo.setUserName(name);
                this.userInfoMapper.insertQQUser(basicUserInfo);
            } else {
                if (wechatUserInfo.getUserId() != null) {
                    throw new UserException(500, "该用户已经绑定了邮箱");
                }

                this.userInfoMapper.updateQQUserInfo(basicUserInfo);
            }

            this.userInfoMapper.updateInfo(userInfo);
            return basicUserInfo;
        } else {
            userInfo.setQqOpenId(basicUserInfo.getOpenId());
            wechatUserInfo = this.userInfoMapper.selectInfoByWechatOpenid(basicUserInfo.getOpenId());
            basicUserInfo.setUserId(userId);
            if (wechatUserInfo == null) {
                name = ThirdPartyNickNameUtil.createName(ThirdPartyType.WECHAT_TYPE.getName());
                this.userInfoMapper.updateWechatInfo(basicUserInfo);
            } else {
                if (wechatUserInfo.getUserId() != null) {
                    throw new UserException(500, "该用户已经绑定了邮箱");
                }

                this.userInfoMapper.updateWechatInfo(basicUserInfo);
            }

            this.userInfoMapper.updateInfo(userInfo);
            return basicUserInfo;
        }
    }

    private void validate(UserInfo info, String openId, Integer loginType, BasicUserInfo thirdInfo, String password) {
        if (info.getStateFlag().equals(StateEnum.ACTIVE.getValue())) {
            Long ttl;
            if (loginType.equals(ThirdPartyType.QQ_TYPE.getValue())) {
                if (openId.equals(info.getQqOpenId())) {
                    ttl = this.redisTemplate.ttl(info.getEmail() + 1);
                    if (ttl != -1L) {
                        throw new UserException(500, "邮箱注册确认邮件有效期为 10 分钟，您可以在" + (ttl + 1L) + "分钟之后再进行该操作");
                    }

                    SendMail.registerCode(info);
                    throw new UserException(0, "邮件已经发送，请点击激活该账户来进行绑定该邮箱账户");
                }

                throw new UserException(500, "该邮箱已经被绑定，且处于未激活状态，请填写其他邮箱");
            }

            if (loginType.equals(ThirdPartyType.WECHAT_TYPE.getValue())) {
                if (openId.equals(info.getWechatOpenId())) {
                    ttl = this.redisTemplate.ttl(info.getEmail() + 1);
                    if (ttl != -1L) {
                        throw new UserException(500, "邮箱注册确认邮件有效期为 10 分钟，您可以在" + (ttl + 1L) + "分钟之后再进行该操作");
                    }

                    SendMail.registerCode(info);
                    throw new UserException(0, "邮件已经发送，请点击激活该账户来进行绑定该邮箱账户");
                }

                throw new UserException(500, "该邮箱已经被绑定，且处于未激活状态，请填写其他邮箱");
            }
        } else if (info.getStateFlag().equals(StateEnum.FROZEN.getValue())) {
            throw new UserException(500, "该邮箱处于冻结状态，请填写其他邮箱-");
        }

    }
}
