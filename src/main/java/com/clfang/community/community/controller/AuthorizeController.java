package com.clfang.community.community.controller;

import com.clfang.community.community.dto.AccessTokenDto;
import com.clfang.community.community.dto.GithubUser;
import com.clfang.community.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/callback")
    public String callbck(@RequestParam(name="code") String code,
                          @RequestParam(name="state") String state){
        AccessTokenDto token = new AccessTokenDto();
        token.setCode(code);
        token.setState(state);
        token.setClient_id(clientId);
        token.setClient_secret(clientSecret);
        token.setRedirect_uri(redirectUri);
        String accessToken = githubProvider.getAccessToken(token);
        GithubUser user = githubProvider.getUser(accessToken);
        System.out.println(user.getName()+user.getId()+user.getBio());
        System.out.println(clientId+">>"+clientSecret+">>"+redirectUri);
        return "index";
    }



}
