package com.clfang.community.community.controller;

import com.clfang.community.community.dto.CommentDto;
import com.clfang.community.community.dto.ResultDto;
import com.clfang.community.community.exception.CustomizeErrorCode;
import com.clfang.community.community.mapper.CommentMapper;
import com.clfang.community.community.model.Comment;
import com.clfang.community.community.model.User;
import com.clfang.community.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * ClassName:CommentController
 * Package:com.clfang.community.community.controller
 * Description:
 *回复问题类
 * @Date:2020/4/3 14:34
 * @Author:CLFang
 */
@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;
    @ResponseBody//把返回的数据转成json
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public Object post(@RequestBody CommentDto commentDto,
                       HttpServletRequest request){//@RequestBody:把传入的json转成对象
        User user = (User) request.getSession().getAttribute("user");
        if (user == null){//当前未登录
            return ResultDto.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        Comment comment = new Comment();
        comment.setParentId(commentDto.getParentId());
        comment.setContent(commentDto.getContent());
        comment.setType(commentDto.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setCommentator(user.getId());
        comment.setLikeCount(0L);//long类型，0后面加L
        commentService.insert(comment);
        return ResultDto.okOF();
    }
}
