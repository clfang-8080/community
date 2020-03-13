package com.clfang.community.community.mapper;

import com.clfang.community.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * ClassName:UserMapper
 * Package:com.clfang.community.community.mapper
 * Description:
 *
 * @Date:2020/3/11 15:45
 * @Author:CLFang
 */
@Mapper
public interface UserMapper {
    @Insert("insert into user (name,account_id,token,gmt_create,gmt_modified) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified})")
    void insert(User user);//形参是一个类，参数直接写里面

    @Select("select * from user where token = #{token}")//#{}表示运行时能把下面方法参数对应放进去
    User findByToken(@Param("token") String token);//形参不是一个类，用@Param("参数")
}
