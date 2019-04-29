package com.superstudentregion.service;

import com.superstudentregion.bean.CollectorArticle;
import com.superstudentregion.result.ArticleResult;
import com.superstudentregion.bean.ArticleInfo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface ArticleService {
    int insertArticle(ArticleInfo articleInfo, String articleByHtml, String articleByXml);

    int updateArticle(ArticleInfo articleInfo, String articleByHtml, String articleByXml);

    int updateArticle(ArticleInfo articleInfo);

    @Deprecated
    void likeArticle(Integer articleId);

    ArticleInfo browseArticle(Integer articleId,Integer browseUserId);

    int deleteArticle(ArticleInfo articleInfo);

    List<ArticleResult> allArticleByUser(ArticleResult articleInfo,Integer browserId);

    List<String> uploadArticlePic(MultipartFile[] pictures, Integer userId);

    void browseCount(Integer articleId);

    String collectorCount(CollectorArticle collectorArticle);

    List<ArticleResult> allCollectorArticleByUser(ArticleResult articleResult,Integer browserId);

    String thumbUpOrDown(Integer userId,Integer articleId,Integer type);

}
