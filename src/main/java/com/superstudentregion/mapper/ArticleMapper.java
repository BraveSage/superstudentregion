package com.superstudentregion.mapper;

import com.superstudentregion.bean.ArticleInfo;

import java.util.List;

public interface ArticleMapper {
    int insertArticle(ArticleInfo var1);

    int updateArticle(ArticleInfo var1);

    List<ArticleInfo> selectAllArticleByUser(Integer var1);

    ArticleInfo selectArticleById(Integer var1);

    int deleteArticleById(Integer articleId);
}
