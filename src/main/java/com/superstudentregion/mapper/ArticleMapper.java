package com.superstudentregion.mapper;

import com.superstudentregion.bean.ArticleInfo;

import java.util.List;

public interface ArticleMapper {
    int insertArticle(ArticleInfo articleInfo);

    int updateArticle(ArticleInfo articleInfo);

    List<ArticleInfo> selectAllArticleByUser(Integer userId);

    ArticleInfo selectArticleById(Integer articleId);

    int deleteArticleById(Integer articleId);
}
