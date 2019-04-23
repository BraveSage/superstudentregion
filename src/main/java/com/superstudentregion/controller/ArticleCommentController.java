package com.superstudentregion.controller;

import com.superstudentregion.bean.ArticleComment;
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

    @RequestMapping(value = "allComment",method = RequestMethod.POST)
    public Result allCommentByArticleId(Integer articleId){
        List<ArticleComment> comments = commentService.allComment(articleId);

        return Result.success(comments);
    }

    @RequestMapping(value = "/delComment",method = RequestMethod.POST)
    public Result delComment(Integer commentId){
        commentService.delComement(commentId);
        return Result.success("删除评论成功");
    }


}
