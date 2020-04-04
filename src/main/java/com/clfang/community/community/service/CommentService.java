package com.clfang.community.community.service;

import com.clfang.community.community.enums.CommentTypeEnum;
import com.clfang.community.community.exception.CustomizeErrorCode;
import com.clfang.community.community.exception.CustomizeException;
import com.clfang.community.community.mapper.CommentMapper;
import com.clfang.community.community.mapper.QuestionExtMapper;
import com.clfang.community.community.mapper.QuestionMapper;
import com.clfang.community.community.model.Comment;
import com.clfang.community.community.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ClassName:CommentService
 * Package:com.clfang.community.community.service
 * Description:
 *
 * @Date:2020/4/3 20:41
 * @Author:CLFang
 */
@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionExtMapper questionExtMapper;

    @Transactional//整个方法体全部加事物
    public void insert(Comment comment) {
        if (comment.getParentId() == null||comment.getParentId() == 0){
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        if(comment.getType()==null||!CommentTypeEnum.isExist(comment.getType())){
            throw new CustomizeException(CustomizeErrorCode.TYPE_TARAM_WRONG);
        }
        if (comment.getType()==CommentTypeEnum.COMMENT.getType()){//回复评论
            Comment dbComment = commentMapper.selectByPrimaryKey(comment.getParentId());
            if (dbComment==null){//此问题不存在
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUNT);
            }
            commentMapper.insert(comment);
        }else {//回复问题
            Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
            if (question == null){
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            commentMapper.insert(comment);
            question.setCommentCount(1);
            questionExtMapper.incCommentCount(question);
        }
    }
}
