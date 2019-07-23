package com.example.springclod.common.util.http;

import com.example.springclod.common.util.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.UUID;


public class CookieUtils {

    private static final String COOKIE_NAME = "OneId";

    /**
     * 设置Cookie值，用传入的Cookie Value
     *
     * @param request
     * @param response
     * @param cookieValue
     * @throws IOException
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieValue) throws IOException {
        Cookie cookie = getCookie(request, COOKIE_NAME);
        if (cookie == null) {
            cookie = new Cookie(COOKIE_NAME, cookieValue);
        }
        cookie.setValue(cookieValue);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 24);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
    }

    /**
     * 设置Cookie值，随机生成一个Cookie
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    public static String setCookie(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String cookieValue = UUID.randomUUID().toString();
        setCookie(request, response, cookieValue);
        return cookieValue;
    }

    /**
     * 获取Cookie，如果为空则设置Cookie，并返回Cookie值
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    public static String getCookieValueIfNullThenSetCookie(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String cookieValue = getCookieValue(request);
        if (StringUtils.isEmpty(cookieValue)) {
            cookieValue = setCookie(request, response);
        }
        return cookieValue;
    }

    /**
     * 获取Cookie值
     * @param request
     * @return
     */
    public static String getCookieValue(HttpServletRequest request) {
        Cookie cookie = getCookie(request, COOKIE_NAME);
        if (cookie != null) {
            return cookie.getValue();
        } else {
            return null;
        }
    }

    /**
     * 移除Cookie值
     * @param request
     * @param response
     */
    public static void removeCookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = getCookie(request, COOKIE_NAME);
        if (cookie!=null){
            cookie.setValue(null);
            cookie.setMaxAge(0);
            cookie.setPath("/");
            response.addCookie(cookie);
        }
    }

    /**
     * 获取Cookie对象
     * @param request
     * @param cookieName
     * @return
     */
    private static Cookie getCookie(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        if (CollectionUtils.isEmpty(cookies)) {
            return null;
        }

        Cookie ticket = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(cookieName)) {
                ticket = cookie;
                break;
            }
        }
        return ticket;
    }

    /**
     * 获取返回给前端的cookies
     * @param response
     * @return
     */
    public static String getCookieKeyAndName(HttpServletResponse response){
        //Set-Cookie: WEBID85ea1457-ae3c-487b-ab0f-472ac00db15c=684a69c3-900b-4512-ba2e-8b64aea8fb69; Path=/; HttpOnly
        Collection<String> header = response.getHeaders("Set-Cookie");
        if (header != null){
            for (String keyAndValue: header){
                if (keyAndValue.contains("WEBID")) {
                    String[] idStr = keyAndValue.split(";");
                    return idStr[0];
                }
            }
        }
        return null;
    }

}
