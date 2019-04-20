package com.superstudentregion.service;

import com.superstudentregion.bean.ArticleInfo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface ArticleService {
    int insertArticle(ArticleInfo articleInfo, String articleByHtml, String articleByXml);

    int updateArticle(ArticleInfo articleInfo, String articleByHtml, String articleByXml);

    int updateArticle(ArticleInfo articleInfo);

    void likeArticle(Integer articleId);

    ArticleInfo browseArticle(Integer articleId);

    int deleteArticle(ArticleInfo articleInfo);

    List<ArticleInfo> allArticleByUser(Integer userId);

    List<String> uploadArticlePic(MultipartFile[] pictures, Integer userId);

}
