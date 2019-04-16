package com.superstudentregion.util;

public class UserRegex {
    public static final String EMAIL_REGEX = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
    public static final String TEL_REGEX = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,3,5-9]))\\d{8}$";
    public static final String USER_NAME_REGEX = "^\\w{6,18}$";
    public static final String PREV_PWD_REGEX = "^(\\w|[`_~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“'。，、？]){";
    public static final String END_PWD_REGEX = "}$";

    public UserRegex() {
    }

    public static void main(String[] args) {
        String email = "112@qq.com";
        String tel = "14518881288";
        String username = "张三";
        System.out.println(email.matches("^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$"));
        System.out.println(tel.matches("^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,3,5-9]))\\d{8}$"));
        System.out.println(username.matches("^\\w{6,18}$"));
        Integer minPwd = 6;
        Integer maxPwd = 18;
        StringBuffer pwdR = new StringBuffer();
        pwdR.append("^([A-Z]|[a-z]|\\d|[`_~!@#$%^&*()+=|':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“'。，、？]){");
        pwdR.append(minPwd);
        pwdR.append(",");
        pwdR.append(maxPwd);
        pwdR.append("}$");
        String pwdRegex = new String(pwdR);
        String pwd = "132112";
        String pwdRegex3 = "^(\\w|[`_~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“'。，、？]){6,20}$";
        String pwdRegex2 = "^([A-Z]|[a-z]|[0-9]|[`_~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“'。，、？]){6,20}$";
        System.out.println(pwdRegex);
        System.out.println(pwdRegex2);
        System.out.println(pwd.matches(pwdRegex3));
        System.out.println(pwd.matches(pwdRegex));
//        StringBuffer pwdR2 = new StringBuffer();
//        pwdR2.append("^(\\w|[`_~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“'。，、？]){");
//        pwdR2.append(6);
//        pwdR2.append(",");
//        pwdR2.append(18);
//        pwdR2.append("}$");
//        String pwdRegex4 = new String(pwdR2);
//        System.out.println(pwdRegex4);
//        System.out.println(pwd.matches(pwdRegex4));
        System.out.println(pwdRegex());
        System.out.println(pwd.matches(pwdRegex()));
        String userpwd = "liuzhouzhou";
        boolean b = userpwd.matches("^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$");
        if (userpwd != null || !userpwd.isEmpty()) {
            if (userpwd.matches("^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$")) {
                System.out.println("这个是邮箱");
            } else {
                System.out.println("这个是用户");
            }
        }

    }

    private static String pwdRegex() {
        StringBuffer pwdR = new StringBuffer();
        pwdR.append("^(\\w|[`_~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“'。，、？]){");
        pwdR.append(6);
        pwdR.append(",");
        pwdR.append(18);
        pwdR.append("}$");
        String pwdRegex = new String(pwdR);
        return pwdRegex;
    }
}
