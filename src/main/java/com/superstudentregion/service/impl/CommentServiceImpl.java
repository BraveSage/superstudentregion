package com.superstudentregion.service.impl;

import com.superstudentregion.bean.ArticleComment;
import com.superstudentregion.mapper.CommentMapper;
import com.superstudentregion.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentMapper commentMapper;

    @Override
    public void createComment(ArticleComment articleComment) {
        int i = commentMapper.insertComment(articleComment);
    }

    @Override
    public void delComement(Integer commentId) {
        commentMapper.delComment(commentId);
    }

    @Override
    public List<ArticleComment> allComment(Integer articleId) {

        List<ArticleComment> allComment = commentMapper.allCommentByArticle(articleId);


    }
}
