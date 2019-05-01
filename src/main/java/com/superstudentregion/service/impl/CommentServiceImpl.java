package com.superstudentregion.service.impl;

import com.github.pagehelper.PageInfo;
import com.superstudentregion.bean.ArticleComment;
import com.superstudentregion.exception.CommentException;
import com.superstudentregion.mapper.CommentMapper;
import com.superstudentregion.result.ArticleCommentResult;
import com.superstudentregion.result.ArticleResult;
import com.superstudentregion.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentMapper commentMapper;

    @Override
    @Transactional
    public void createComment(ArticleComment articleComment) {
        int i = commentMapper.insertComment(articleComment);
        System.out.println(articleComment);
        if(i==0){
            throw new CommentException("添加评论失败");
        }
    }

    @Override
    @Transactional
    public void delComment(Integer commentId) {
        int i = commentMapper.delComment(commentId);
        if(i==0){
            throw new CommentException("删除评论失败");
        }
    }

    @Override
    public PageInfo<ArticleCommentResult> allComment(Integer articleId,Integer currentPage,Integer pageSize) {

        List<ArticleCommentResult> allComment = commentMapper.allCommentByArticle(articleId);
        PageInfo<ArticleCommentResult> commentInfo = new PageInfo<>(allComment);
        return commentInfo;

    }
}
