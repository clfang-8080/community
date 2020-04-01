package com.clfang.community.community.mapper;

import com.clfang.community.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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
    @Select("select * from question limit #{offset} , #{size}")
    List<Question> list(@Param(value = "offset") Integer offset, @Param(value = "size") Integer size);
    //查询总记录条数
    @Select("select count(1) from question")
    Integer count();

    @Select("select * from question where creator = #{userId} limit #{offset} , #{size}")
    List<Question> listByUserId(@Param(value = "userId") Integer userId,@Param(value = "offset") Integer offset, @Param(value = "size") Integer size);
    //查询登录用户记录条数
    @Select("select count(1) from question where creator = #{userId}")
    Integer countByUserId(@Param(value = "userId") Integer userId);
    //查询单个用户记录
    @Select("select * from question where id = #{id}")
    Question getById(@Param(value = "id") Integer id);
}
