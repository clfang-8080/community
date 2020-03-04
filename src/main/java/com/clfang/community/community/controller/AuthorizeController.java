package com.clfang.community.community.controller;

import com.clfang.community.community.dto.AccessTokenDto;
import com.clfang.community.community.dto.GithubUser;
import com.clfang.community.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private GithubProvider githubProvider;
    @GetMapping("/callback")
    public String callbck(@RequestParam(name="code") String code,
                          @RequestParam(name="state") String state){
        AccessTokenDto token = new AccessTokenDto();
        token.setCode(code);
        token.setState(state);
        token.setClient_id("Iv1.19c33fd30ca3617d");
        token.setClient_secret("d1d04a66c18463af2bb775f444c99165dbc87db8");
        token.setRedirect_uri("http://localhost:8887/callback");
        String accessToken = githubProvider.getAccessToken(token);
        GithubUser user = githubProvider.getUser(accessToken);
        System.out.println(user.getName()+user.getId()+user.getBio());
        return "index";
    }



}
