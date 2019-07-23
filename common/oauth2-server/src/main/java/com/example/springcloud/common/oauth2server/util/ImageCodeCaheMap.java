package com.example.springcloud.common.oauth2server.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author jbj
 * @create 2019-05-10 15:52
 */
public class ImageCodeCaheMap {

    public static Map<String, String> imageCodeCaheMap = new ConcurrentHashMap<>();


    /**
     * 根据用户名称获取验证码
     * @param key
     * @return
     */
    public static String getImageCode(String key) {
        return imageCodeCaheMap.get(key);
    }

    public static void addImageCode(String key, String value) {
        imageCodeCaheMap.put(key, value);
    }

}
