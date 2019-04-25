package com.superstudentregion;

import com.superstudentregion.bean.ArticleComment;
import com.superstudentregion.bean.UserInfo;
import com.superstudentregion.controller.ArticleCommentController;
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

    @Autowired
    ArticleCommentController articleCommentController;

//    @Test
//    public void test() {
//        UserInfo userInfo = new UserInfo();
//        userInfo.setUserId(1);
//        Result result = userInfoController.displayInfo(userInfo);
//        System.out.printf(JsonUtil.toJson(result));
//    }
//    @Test
//    public void allArticle(){
//        ArticleResult articleResult = new ArticleResult();
//        articleResult.setTypeId(-1);
//        articleResult.setUserId(1);
//        Result result = articleController.userArticle(articleResult, 1);
//        System.out.println(JsonUtil.toJson(result));
//    }
//
    @Test
    public void insertComment(){
        ArticleComment articleComment = new ArticleComment();
        articleComment.setArticleId(10);
        articleComment.setContent("你好啊啊啊 ");
        articleComment.setSubmitterId(1);
        Result result = articleCommentController.createComment(articleComment);
        System.out.println(JsonUtil.toJson(result));
    }
//    @Test
//    public void delComment(){
//        Result result = articleCommentController.delComment(3);
//        System.out.println(JsonUtil.toJson(result));
//    }
    @Test
    public void allComment(){
        Result result = articleCommentController.allCommentByArticleId(10);
        System.out.println(JsonUtil.toJson(result));
    }
}
