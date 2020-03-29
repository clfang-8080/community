package com.clfang.community.community.mapper;

import com.clfang.community.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * ClassName:QuestionMapper
 * Package:com.clfang.community.community.mapper
 * Description:
 *
 * @Date:2020/3/15 9:29
 * @Author:CLFang
 */
@Mapper
public interface QuestionMapper {
    //插入问题
    @Insert("insert into question (title,description,gmt_create,gmt_modified,creator,tag) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void create(Question question);
    //查询问题
    @Select("select * from question")
    List<Question> list();
}
