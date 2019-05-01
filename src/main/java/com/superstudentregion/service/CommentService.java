package com.superstudentregion.service;

import com.github.pagehelper.PageInfo;
import com.superstudentregion.bean.ArticleComment;
import com.superstudentregion.result.ArticleCommentResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {
    void createComment(ArticleComment articleComment);

    void delComment(Integer commentId);

    PageInfo<ArticleCommentResult> allComment(Integer articleId,Integer currentPage,Integer pageSize);
}
