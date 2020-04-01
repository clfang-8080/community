package com.clfang.community.community.controller;

import com.clfang.community.community.dto.AccessTokenDto;
import com.clfang.community.community.dto.GithubUser;
import com.clfang.community.community.mapper.UserMapper;
import com.clfang.community.community.model.User;
import com.clfang.community.community.provider.GithubProvider;
import com.clfang.community.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * ClassName:AuthorizeController
 * Package:com.clfang.community.community.controller
 * Description:
 *
 * @Date:2020/3/4 22:41
 * @Author:CLFang
 */
@Controller
public class AuthorizeController {
    @Autowired//不用new对象，跳过创建
    private GithubProvider githubProvider;

    @Value("${github.client.id}")//自动读取application.properties中的key，并赋值给下面的变量
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;

    @Autowired
    private UserService userService;

    @GetMapping("/callback")
    public String callbck(@RequestParam(name="code") String code,
                          @RequestParam(name="state") String state,
                          HttpServletResponse response){
        AccessTokenDto token = new AccessTokenDto();
        token.setCode(code);
        token.setState(state);
        token.setClient_id(clientId);
        token.setClient_secret(clientSecret);
        token.setRedirect_uri(redirectUri);
        String accessToken = githubProvider.getAccessToken(token);
        GithubUser githubUser = githubProvider.getUser(accessToken);
        if (githubUser != null ){
            User user = new User();
            String token1 = UUID.randomUUID().toString();
            user.setToken(token1);
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            //user.setGmtCreate(System.currentTimeMillis());时间操作放到判断用户是否为空后操作
            //user.setGmtModified(user.getGmtCreate());
            user.setAvatarUrl(githubUser.getAvatar_url());
            userService.createOrUpdate(user);
            //userMapper.insert(user);
            //写入cookie
            response.addCookie(new Cookie("token",token1));
            return "redirect:/";
        }else{//登录失败。重新登录
            return "redirect:/";
        }
    }

    //退出登录
    @GetMapping("/logout")
    public String logout(
            HttpServletRequest request,
            HttpServletResponse response
    ){
        request.getSession().removeAttribute("user");//移除user
        //删除cookie
        Cookie cookie = new Cookie("token",null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }

}
