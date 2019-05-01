package com.superstudentregion.controller;

import com.github.pagehelper.PageInfo;
import com.superstudentregion.bean.ArticleComment;
import com.superstudentregion.result.ArticleCommentResult;
import com.superstudentregion.service.CommentService;
import com.superstudentregion.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/comment")
public class ArticleCommentController {

    @Autowired
    CommentService commentService;

    @RequestMapping(value = "/allCommentByArticle",method = RequestMethod.POST)
    public Result allCommentByArticleId(Integer articleId,Integer currentPage,Integer pageSize){
        PageInfo<ArticleCommentResult> comments = commentService.allComment(articleId,currentPage,pageSize);

        return Result.success(comments);
    }

    @RequestMapping(value = "/delComment",method = RequestMethod.POST)
    public Result delComment(Integer commentId){
        commentService.delComment(commentId);
        return Result.success("删除评论成功");
    }

    @RequestMapping(value = "/createComment",method = RequestMethod.POST)
    public Result createComment(ArticleComment articleComment){
        commentService.createComment(articleComment);
        return Result.success("添加评论成功");
    }


}
