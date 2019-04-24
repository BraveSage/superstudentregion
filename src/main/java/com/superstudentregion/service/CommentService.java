package com.superstudentregion.service;

import com.superstudentregion.bean.ArticleComment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {
    public void createComment(ArticleComment articleComment);

    public void delComment(Integer commentId);

    public List<ArticleComment> allComment(Integer articleId);
}
