package com.clfang.community.community.controller;

import com.clfang.community.community.dto.CommentCreateDto;
import com.clfang.community.community.dto.CommentDto;
import com.clfang.community.community.dto.ResultDto;
import com.clfang.community.community.enums.CommentTypeEnum;
import com.clfang.community.community.exception.CustomizeErrorCode;
import com.clfang.community.community.model.Comment;
import com.clfang.community.community.model.User;
import com.clfang.community.community.service.CommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    public Object post(@RequestBody CommentCreateDto commentCreateDto,
                       HttpServletRequest request){//@RequestBody:把传入的json转成对象
        User user = (User) request.getSession().getAttribute("user");
        if (user == null){//当前未登录
            return ResultDto.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
                                    //StringUtils.isBlank():apach.commons.lang3下的判空方法
        if (commentCreateDto == null|| StringUtils.isBlank(commentCreateDto.getContent())){//输入内容为空
            return ResultDto.errorOf(CustomizeErrorCode.CONTENT_IS_EMPTY);
        }
        Comment comment = new Comment();
        comment.setParentId(commentCreateDto.getParentId());
        comment.setContent(commentCreateDto.getContent());
        comment.setType(commentCreateDto.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setCommentator(user.getId());
        comment.setLikeCount(0L);//long类型，0后面加L
        comment.setCommentCount(0L);
        commentService.insert(comment);
        return ResultDto.okOF();
    }

    //二级评论
    @ResponseBody
    @RequestMapping(value = "/comment/{id}",method = RequestMethod.GET)
    public ResultDto<List<CommentDto>> comments(@PathVariable(name = "id") Integer id){
        List<CommentDto> commentDtos = commentService.listByTargetId(id.longValue(), CommentTypeEnum.COMMENT);
        return ResultDto.okOF(commentDtos);
    }
}
