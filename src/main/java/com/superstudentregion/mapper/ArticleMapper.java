package com.superstudentregion.mapper;

import com.superstudentregion.result.ArticleResult;
import com.superstudentregion.bean.ArticleInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleMapper {
    int insertArticle(ArticleInfo articleInfo);

    int updateArticle(ArticleInfo articleInfo);

    List<ArticleResult> selectAllArticleByUser(ArticleResult articleInfo);

    ArticleInfo selectArticleById(@Param(value="articleId")Integer articleId);

    int deleteArticleById(Integer articleId);
}
