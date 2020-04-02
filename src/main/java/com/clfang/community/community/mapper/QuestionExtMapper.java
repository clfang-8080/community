package com.clfang.community.community.mapper;

import com.clfang.community.community.model.Question;
import com.clfang.community.community.model.QuestionExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface QuestionExtMapper {

    int incView(Question record);
}