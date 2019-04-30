package com.superstudentregion.controller;

import com.superstudentregion.bean.CollectorArticle;
import com.superstudentregion.result.ArticleResult;
import com.superstudentregion.bean.ArticleInfo;
import com.superstudentregion.constant.Constants;
import com.superstudentregion.exception.ArticleException;
import com.superstudentregion.mapper.ArticleMapper;
import com.superstudentregion.service.ArticleService;
import com.superstudentregion.service.impl.RedisClientSingle;
import com.superstudentregion.stuenum.ReadPermissionEnum;
import com.superstudentregion.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping({"/article"})
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    RedisClientSingle redisClient;

    public ArticleController() {
    }

    /**
     * 创建文章
     * @param articleInfo 文章信息
     * @param articleByHtml html格式文章
     * @param articleByMd  md文章
     * @return
     */
    @RequestMapping(
            value = {"/createArticle"},
            method = {RequestMethod.POST}
    )
    public Result insertArticle(ArticleInfo articleInfo, String articleByHtml, String articleByMd) {
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

    /**
     * 修改文章信息
     * @param articleInfo 文章信息
     * @param articleByHtml html文章
     * @param articleByMd   md文章
     * @return
     */
    @RequestMapping(
            value = {"/modifyArticle/"},
            method = {RequestMethod.POST}
    )
    public Result updateArticle(ArticleInfo articleInfo, String articleByHtml, String articleByMd) {
        int i = this.articleService.updateArticle(articleInfo, articleByHtml,articleByMd);
        return i == 0 ? Result.failure(Constants.RESP_STATUS_INTERNAL_ERROR, "修改文章失败") : Result.success("修改文章成功");
    }

    @RequestMapping(
            value = {"/delArticle"},
            method = {RequestMethod.POST}
    )
    public Result deleteArticle(@Valid Integer articleId) {
        ArticleInfo articleInfo = articleMapper.selectArticleById(articleId);
        int i = this.articleService.deleteArticle(articleInfo);
        return i != 1 ? Result.failure(Constants.RESP_STATUS_INTERNAL_ERROR, "删除文章失败") : Result.success("删除文章成功");
    }

    @RequestMapping(
            value = {"/uploadPic"},
            method = {RequestMethod.POST}
    )
    public Result uploadPicture(Integer userId, MultipartFile... pictures) {
//        System.out.println(System.currentTimeMillis());
        List<String> pics = this.articleService.uploadArticlePic(pictures, userId);
//        System.out.println(System.currentTimeMillis());
        return Result.success("上传图片成功", pics);
    }

    /**
     * 用户所有文章接口
     * @param articleInfo
     * @param browserUserId 
     * @return
     */
    @RequestMapping(
            value = {"/userAllArticle"},
            method = {RequestMethod.POST}
    )
    public Result userArticle(@Valid ArticleResult articleInfo,@Valid Integer browserUserId ) {
        if(!articleInfo.getUserId().equals(browserUserId )){
            articleInfo.setReadPermission(ReadPermissionEnum.PUBLIC.getValue());
        }
        List<ArticleResult> allArticleByUser = this.articleService.allArticleByUser(articleInfo,browserUserId );
        return Result.success("返回用户所有文章信息成功", allArticleByUser);
    }

    @RequestMapping(
            value = {"/userArticle"},
            method = {RequestMethod.POST}
    )
    public Result browseArticle(@Valid Integer articleId,@Valid Integer browserUserId) {
        ArticleInfo articleInfo = this.articleService.browseArticle(articleId,browserUserId);
        return Result.success("返回文章信息成功", articleInfo);
    }

    @RequestMapping(value = "/modifyReadPermission",method = RequestMethod.POST)
    public Result updateReadPermission(@Valid Integer userId,@Valid Integer articleId,@Valid Integer readPermission){
        ArticleInfo articleInfo = new ArticleInfo();
        if (articleId != null) {
            articleInfo.setArticleId(articleId);
        }
        articleInfo.setUserId(userId);
        articleInfo.setReadPermission(readPermission);
        int i = articleService.updateArticle(articleInfo);

        return i!=0? Result.success("修改阅读权限成功"): Result.success("修改阅读权限失败");
    }

    @RequestMapping(value = "/allArticle",method = RequestMethod.POST)
    public Result allArticle(ArticleResult articleInfo ,@Valid Integer browserUserId ){

        List<ArticleResult> articleInfos = articleService.allArticleByUser(articleInfo,browserUserId );
        return Result.success(articleInfos);
    }

    @RequestMapping(value = "/collector",method = RequestMethod.POST)
    public Result collectorArticle(@Valid CollectorArticle collectorArticle){
        String info = articleService.collectorCount(collectorArticle);
        return Result.success(info);
    }

    @RequestMapping(value = "/allCollectorArticleByUser",method = RequestMethod.POST)
    public Result allCollectorArticleByUser(ArticleResult articleResult,@Valid Integer browserId){
        List<ArticleResult> articleResults = articleService.allCollectorArticleByUser(articleResult,browserId);
        return Result.success(articleResults);
    }

    @RequestMapping(value = "/browseCount",method = RequestMethod.POST)
    public void browseCount(@Valid Integer articleId){
        articleService.browseCount(articleId);
    }

    /**
     * 点赞或者踩
     * @param userId 点赞的用户id
     * @param articleId 被点赞的文章
     * @param type 1赞 0取消 赞或者踩 -1踩
     * @return
     */
    @RequestMapping(value="/thumb",method = RequestMethod.POST)
    public Result thumbUpOrDown(@Valid Integer userId,@Valid Integer articleId,@Valid Integer type){
        String info = articleService.thumbUpOrDown(userId, articleId, type);
        return Result.success(info);
    }

}
