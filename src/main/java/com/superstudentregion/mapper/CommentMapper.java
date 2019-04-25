package com.superstudentregion.mapper;

import com.superstudentregion.bean.ArticleComment;
import com.superstudentregion.result.ArticleCommentResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    /**
     * 添加文章一级评论
     * @param articleComment 评论信息
     * @return 返回主键
     */
    int insertComment(ArticleComment articleComment);

    /**
     * 某篇文章的所有一级评论么
     * @param articleId 文章ID
     * @return 返回文章一级评论的所有内容
     */
    List<ArticleCommentResult> allCommentByArticle(Integer articleId);

    /**
     * 删除评论
     * @param commentId 评论ID
     * @return 删除是否成功
     */
    int delComment(Integer commentId);
}
