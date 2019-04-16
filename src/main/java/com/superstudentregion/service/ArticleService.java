package com.superstudentregion.service;

import com.superstudentregion.bean.ArticleInfo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface ArticleService {
    int insertArticle(ArticleInfo articleInfo, MultipartFile articleByHtml, MultipartFile articleByXml);

    int updateArticle(ArticleInfo articleInfo, MultipartFile articleByHtml, MultipartFile articleByXml);

    int updateArticle(ArticleInfo var1);

    void likeArticle(Integer var1);

    ArticleInfo browseArticle(Integer var1);

    int deleteArticle(ArticleInfo var1);

    List<ArticleInfo> allArticleByUser(Integer var1);

    List<String> uploadArticlePic(MultipartFile[] var1, Integer var2);

}
