package com.superstudentregion.controller;

import com.superstudentregion.bean.ArticleInfo;
import com.superstudentregion.constant.Constants;
import com.superstudentregion.constant.TokenConstant;
import com.superstudentregion.exception.ArticleException;
import com.superstudentregion.service.ArticleService;
import com.superstudentregion.service.impl.RedisClientSingle;
import com.superstudentregion.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping({"/article"})
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @Autowired
    RedisClientSingle redisClient;

    public ArticleController() {
    }

    @RequestMapping(
            value = {"/createArticle"},
            method = {RequestMethod.POST}
    )
    public Result insertArticle(ArticleInfo articleInfo, MultipartFile articleByHtml, MultipartFile articleByMd) {
//        String token = (String) this.redisClient.get(TokenConstant.TOKEN_USER_PREFIX + articleInfo.getUserId());
//        if(token.isEmpty()){
//            throw new ArticleException("请绑定邮箱后");
//        }

        if(articleInfo.getUserId().equals(null)){
            throw new ArticleException("请绑定邮箱，只有邮箱用户能够创建文章");
        }

        int i = this.articleService.insertArticle(articleInfo, articleByHtml, articleByMd);
        return i == 0 ? Result.failure(Constants.RESP_STATUS_INTERNAL_ERROR, "创建文章失败") : Result.success("添加文章成功");
    }

    @RequestMapping(
            value = {"/modifyArticle/{userId}/{articleId}"},
            method = {RequestMethod.POST}
    )
    public Result updateArticle(ArticleInfo articleInfo, MultipartFile articleByHtml, MultipartFile articleByMd) {
        int i = this.articleService.updateArticle(articleInfo, articleByHtml,articleByMd);
        return i == 0 ? Result.failure(Constants.RESP_STATUS_INTERNAL_ERROR, "修改文章失败") : Result.success("修改文章成功");
    }

    @RequestMapping(
            value = {"/delArticle"},
            method = {RequestMethod.POST}
    )
    public Result deleteArticle(Integer articleId) {
        ArticleInfo info = new ArticleInfo();
        info.setArticleId(articleId);
        int i = this.articleService.deleteArticle(info);
        return i != 1 ? Result.failure(Constants.RESP_STATUS_INTERNAL_ERROR, "删除文章失败") : Result.success("删除文章成功");
    }

    @RequestMapping(
            value = {"/uploadPic"},
            method = {RequestMethod.POST}
    )
    public Result uploadPicture(Integer userId, MultipartFile... pictures) {
        List<String> pics = this.articleService.uploadArticlePic(pictures, userId);
        return Result.success("上传图片成功", pics);
    }

    @RequestMapping(
            value = {"/userArticle/{userId}"},
            method = {RequestMethod.POST}
    )
    public Result userArticle(Integer userId) {
        List<ArticleInfo> allArticleByUser = this.articleService.allArticleByUser(userId);
        return Result.success("返回用户所有文章信息成功", allArticleByUser);
    }

    @RequestMapping(
            value = {"/userArticle/{userId}/{articleId}"},
            method = {RequestMethod.POST}
    )
    public Result browseArticle(Integer articleId) {
        ArticleInfo articleInfo = this.articleService.browseArticle(articleId);
        return Result.success("返回文章信息成功", articleInfo);
    }

    @RequestMapping(value = "{userId}/modifyReadPermission")
    public Result updateReadPermission(Integer userId, Integer articleId,Integer readPermission){
        ArticleInfo articleInfo = new ArticleInfo();
        if (articleId != null) {
            articleInfo.setArticleId(articleId);
        }
        articleInfo.setUserId(userId);
        articleInfo.setReadPermission(readPermission);
        int i = articleService.updateArticle(articleInfo);

        return i!=0? Result.success("修改阅读权限成功"): Result.success("修改阅读权限失败");
    }
}
