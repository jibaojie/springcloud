package com.example.springcloud.consumer.consumer1.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.springcloud.consumer.consumer1.entity.GithubUserInfo;
import com.example.springcloud.consumer.consumer1.entity.TokenInfo;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author jbj
 * @create 2019-05-08 15:45
 */
@RestController
@RequestMapping("oauth")
@Slf4j
public class GithubOAuthController {

    @GetMapping("redirect")
    public void redirect(HttpServletResponse response, @RequestParam String code) {
        //获取code，向GitHub后台请求获取用户信息
        TokenInfo tokenInfo = getToken(code);

        //使用token获取用户信息
        getUserInfo(tokenInfo.getAccess_token());

        //重定向到其他页面
        try {
            response.sendRedirect("/index.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getUserInfo(String token) {

        GithubUserInfo githubUserInfo = null;
        String result = null;
        String url = "https://api.github.com/user";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Accept", "application/json")
                .addHeader("Authorization", "token " + token)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                result = response.body().string();
                log.info(result);
                githubUserInfo = JSONObject.parseObject(result, GithubUserInfo.class);
                //TODO 将token与用户信息缓存
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            response.close();
        }

    }


    private TokenInfo getToken(String code) {

        String clientId = "a39ae9c868a7536d0c57";
        String secret = "81743ab9d2e4be2ad7a11570de7699c8e07a1c3a";
        String authorizeUri = "https://github.com/login/oauth/access_token";

        String uri =authorizeUri + "?client_id=" + clientId + "&client_secret=" + secret + "&code=" + code;

        //发送请求 获取token

        TokenInfo tokenInfo = null;
        String result = null;

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(uri)
                .addHeader("Accept", "application/json")
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                result = response.body().string();
                log.info(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            response.close();
        }

        if (result != null) {
            tokenInfo = JSONObject.parseObject(result, TokenInfo.class);
        }

        return tokenInfo;
    }
}
