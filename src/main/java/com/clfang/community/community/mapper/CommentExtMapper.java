package com.clfang.community.community.mapper;

import com.clfang.community.community.model.Comment;
import com.clfang.community.community.model.CommentExample;
import com.clfang.community.community.model.Question;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface CommentExtMapper {
    int incCommentCount(Comment record);
}