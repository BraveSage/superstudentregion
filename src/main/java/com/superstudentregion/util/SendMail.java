package com.superstudentregion.util;

import com.superstudentregion.bean.UserInfo;
import com.superstudentregion.exception.UserException;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

public class SendMail {
    Logger log = LoggerFactory.getLogger(this.getClass());
//    @Autowired
//    private PropertiesConstant propertiesConstant;
    @Value("${mail.port}")
    private String MAIL_PORT;
    private static String CHARSET = "UTF-8";

    public SendMail() {
    }

    public static HtmlEmail sendMail(String mail) {
        HtmlEmail email = new HtmlEmail();
        email.setHostName("smtp.qq.com");
        email.setCharset(CHARSET);
        email.setSmtpPort(587);

        try {
            email.setFrom("1532033525@qq.com", "superstudentregion官方");
            email.setAuthentication("1532033525@qq.com", "yupjhslnuirabaee");
            email.addTo(mail);
            email.setSubject("superstudentregion官方网站");
        } catch (EmailException var3) {
            var3.printStackTrace();
        }

        return email;
    }

    private static String getCode() {
        String num = "";

        for(int i = 0; i < 6; ++i) {
            num = num + String.valueOf((int)(Math.random() * 10.0D));
        }

        return num;
    }

    public static void forgetPwd(String mail) {
        HtmlEmail email = sendMail(mail);

        try {
            String message = "尊敬的用户：" + mail + ":\n 您收到这封这封电子邮件是因为您 (也可能是某人冒充您的名义) 申请了一个新的密码。假如这不是您本人所申请, 请不用理会这封电子邮件, 但是如果您持续收到这类的信件骚扰, 请您尽快联络管理员。\n 请点击下方链接即可更新密码。\n<a href='" + GenerateLinkUtil.generateActivateLink(mail) + "'>" + GenerateLinkUtil.generateActivateLink(mail) + "</a>";
            email.setMsg(message);
            email.send();
        } catch (EmailException var3) {
            throw new UserException(500, "发送邮件失败");
        }
    }

    public static String registerCode(UserInfo user) {
        HtmlEmail htmlEmail = sendMail(user.getEmail());

        try {
            String activeLink = GenerateLinkUtil.generateActivateLink(user);
            String message = "尊敬的" + user.getUserName() + ":\n 您好，感谢您注册XXX\n 请点击下方链接即可激活账号。\n<a href='" + activeLink + "'>" + activeLink + "</a>\n 链接有效时间为10分钟";
            System.out.println(message);
            htmlEmail.setMsg(message);
            htmlEmail.send();
        } catch (EmailException var4) {
            var4.printStackTrace();
        }

        return "";
    }

    public static void main(String[] args) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName("张三");
        String message = "尊敬的" + userInfo.getUserName() + ":\n 您好，感谢您注册XXX\n 请点击下方链接即可激活账号。\n<a href='" + GenerateLinkUtil.generateActivateLink(userInfo) + "'>" + GenerateLinkUtil.generateActivateLink(userInfo) + "</a>";
        System.out.println(message);
        HtmlEmail htmlEmail = sendMail("1120891211@qq.com");

        try {
            htmlEmail.setMsg(message);
            htmlEmail.send();
        } catch (EmailException var5) {
            var5.printStackTrace();
        }

    }
}
