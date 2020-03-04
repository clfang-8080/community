package com.clfang.community.community.provider;

import com.alibaba.fastjson.JSON;
import com.clfang.community.community.dto.AccessTokenDto;
import com.clfang.community.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * ClassName:GithubProvider
 * Package:com.clfang.community.community.provider
 * Description:
 *
 * @Date:2020/3/4 23:02
 * @Author:CLFang
 */
@Component
public class GithubProvider {

    public String getAccessToken(AccessTokenDto token) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(token));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string=response.body().string();
            String token1 = string.split("&")[0].split("=")[1];
            return token1;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public GithubUser getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+accessToken)
                .build();
        try {
            Response response1 = client.newCall(request).execute();
            String string1=response1.body().string();
            GithubUser githubUser = JSON.parseObject(string1, GithubUser.class);
            return githubUser;
        } catch (IOException e) {
        }
        return null;

    }




}
