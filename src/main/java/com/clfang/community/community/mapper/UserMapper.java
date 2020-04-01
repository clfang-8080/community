package com.clfang.community.community.mapper;

import com.clfang.community.community.model.User;
import org.apache.ibatis.annotations.*;

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
    @Insert("insert into user (name,account_id,token,gmt_create,gmt_modified,avatar_url) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    void insert(User user);//形参是一个类，参数直接写里面

    @Select("select * from user where token = #{token}")//#{}表示运行时能把下面方法参数对应放进去
    User findByToken(@Param("token") String token);//形参不是一个类，用@Param("参数")

    @Select("select * from user where id = #{id}")
    User findById(@Param("id") Integer id);//参数id是发起者的creator字段

    @Select("select * from user where account_id = #{accountId}")
    User findByAccountId(@Param("accountId") String accountId);

    @Update("update user set name = #{name},token = #{token},gmt_modified = #{gmtModified},avatar_url = #{avatarUrl} where id = #{id}")
    void update(User user);
}
