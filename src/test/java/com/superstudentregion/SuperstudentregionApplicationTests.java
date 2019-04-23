package com.superstudentregion;

import com.superstudentregion.bean.UserInfo;
import com.superstudentregion.controller.ArticleController;
import com.superstudentregion.controller.UserInfoController;
import com.superstudentregion.result.ArticleResult;
import com.superstudentregion.util.JsonUtil;
import com.superstudentregion.util.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SuperstudentregionApplicationTests {

    @Autowired
    UserInfoController userInfoController;

    @Autowired
    ArticleController articleController;

    @Test
    public void test() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(1);
        Result result = userInfoController.displayInfo(userInfo);
        System.out.printf(JsonUtil.toJson(result));
    }
    @Test
    public void allArticle(){
        ArticleResult articleResult = new ArticleResult();
        articleResult.setTypeId(-1);
        articleResult.setUserId(1);
        Result result = articleController.userArticle(articleResult, 1);
        System.out.println(JsonUtil.toJson(result));
    }
}
