package com.clfang.community.community.service;

import com.clfang.community.community.mapper.UserMapper;
import com.clfang.community.community.model.User;
import com.clfang.community.community.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName:UserService
 * Package:com.clfang.community.community.service
 * Description:
 *
 * @Date:2020/4/1 19:55
 * @Author:CLFang
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void createOrUpdate(User user) {
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andAccountIdEqualTo(user.getAccountId());
        List<User> users = userMapper.selectByExample(userExample);
        //判断当前用户是否为空
        if (users.size() == 0){//无此用户，插入
            user.setGmtCreate(System.currentTimeMillis());//插入新用户需要确定时间，更新不需要
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        }else {//有此用户，更新
            User dbUser = users.get(0);
            User updateUser = new User();
            updateUser.setGmtModified(System.currentTimeMillis());
            updateUser.setAvatarUrl(user.getAvatarUrl());
            updateUser.setName(user.getName());
            updateUser.setToken(user.getToken());
            //获取用户ID
            UserExample example = new UserExample();
            //返回用户
            example.createCriteria()
                    .andIdEqualTo(dbUser.getId());
            //传入更新信息，通过ID插更新据库
            userMapper.updateByExampleSelective(updateUser, example);
        }
    }
}
