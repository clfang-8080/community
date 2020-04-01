package com.clfang.community.community.service;

import com.clfang.community.community.mapper.UserMapper;
import com.clfang.community.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        User dbUser = userMapper.findByAccountId(user.getAccountId());
        //判断当前用户是否为空
        if (dbUser == null){//无此用户，插入
            user.setGmtCreate(System.currentTimeMillis());//插入新用户需要确定时间，更新不需要
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        }else {//有此用户，更新
            dbUser.setGmtModified(System.currentTimeMillis());
            dbUser.setAvatarUrl(user.getAvatarUrl());
            dbUser.setName(user.getName());
            dbUser.setToken(user.getToken());
            userMapper.update(dbUser);
        }
    }
}
