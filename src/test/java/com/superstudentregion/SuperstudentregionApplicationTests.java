package com.superstudentregion;

import com.superstudentregion.bean.ArticleComment;
import com.superstudentregion.bean.CollectorArticle;
import com.superstudentregion.bean.UserInfo;
import com.superstudentregion.controller.ArticleCommentController;
import com.superstudentregion.controller.ArticleController;
import com.superstudentregion.controller.UserInfoController;
import com.superstudentregion.mapper.ArticleMapper;
import com.superstudentregion.result.ArticleResult;
import com.superstudentregion.util.JsonUtil;
import com.superstudentregion.util.Result;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SuperstudentregionApplicationTests {

    @Autowired
    UserInfoController userInfoController;

    @Autowired
    ArticleController articleController;

    @Autowired
    ArticleCommentController articleCommentController;

    @Autowired
    ArticleMapper articleMapper;

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
//        Result result = articleController.allArticleByUser(articleResult, 1,1,10);
//        System.out.println(JsonUtil.toJson(result));
//    }
//
//    @Test
//    public void insertComment(){
//        ArticleComment articleComment = new ArticleComment();
//        articleComment.setArticleId(10);
//        articleComment.setContent("你好啊啊啊 ");
//        articleComment.setSubmitterId(1);
//        Result result = articleCommentController.createComment(articleComment);
//        System.out.println(JsonUtil.toJson(result));
//    }
//    @Test
//    public void delComment(){
//        Result result = articleCommentController.delComment(3);
//        System.out.println(JsonUtil.toJson(result));
//    }
    @Test
    public void allComment(){
        Result result = articleCommentController.allCommentByArticleId(2,1,10);
        System.out.println(JsonUtil.toJson(result));
    }

//    @Test
//    public void collectorArticle(){
//        CollectorArticle collectorArticle = new CollectorArticle();
//        collectorArticle.setArticleId(11);
//        collectorArticle.setCollectorUserId(2);
//        Result result = articleController.collectorArticle(collectorArticle);
//        System.out.println(JsonUtil.toJson(result));
//    }

    @Test
    public void allCollectorArticle(){
        ArticleResult articleResult = new ArticleResult();
        articleResult.setCollectorUserId(1);
        Result result = articleController.allCollectorArticleByUser(articleResult,2,1,10);

        System.out.println(JsonUtil.toJson(result));
    }

    @Test
    public void bindEmail(){
        Result result = userInfoController.bindUser0("979612783@qq.com", 1);
        System.out.println(JsonUtil.toJson(result));
    }


    @Test
    public void cloudMusicInfo(){
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse httpResponse = null;
        try {
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(10000).setSocketTimeout(10000)
                    .setConnectionRequestTimeout(10000).build();
            httpClient = HttpClients.createDefault();
            HttpGet get = new HttpGet("https://music.163.com/#/playlist?id=401615519");

            get.setConfig(requestConfig);

            get.setHeader("Host", "music.163.com");
            get.setHeader("Referer", "http://music.163.com/");
            get.setHeader("User-Agent",
                    "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.101 Safari/537.36");
            httpResponse = httpClient.execute(get);
            String musicName = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
            System.out.println(musicName);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


//    @Test
//    public void insertUser(){
//        userInfoController.register("a123456","1120891211@qq.com");
//    }


    @Test
    public void active(){
//        userInfoController.activeAccount("http://193.112.79.70:8080/sturegion/user/active?yuercd=用户_51815760462&dsjs=66a0e3c83945c395b1078b0810c2e51c&jgssd=1576046282439");
    }
}
