package com.example.springcloud.common.oauth2server.controller;

import com.example.springclod.common.util.http.CookieUtils;
import com.example.springclod.common.util.validatecode.IVerifyCodeGen;
import com.example.springclod.common.util.validatecode.SimpleCharVerifyCodeGenImpl;
import com.example.springclod.common.util.validatecode.VerifyCode;
import com.example.springcloud.common.oauth2server.util.ImageCodeCaheMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;

/**
 * @author jbj
 * @create 2019-05-10 10:24
 */
@Controller
@Slf4j
public class LoginController {

    @RequestMapping("/userLogin")
    public String userLogin() {
        return "/login.html";
    }

    @GetMapping("/imageCode")
    public void imageCode(HttpServletResponse response, HttpServletRequest request) {
        IVerifyCodeGen iVerifyCodeGen = new SimpleCharVerifyCodeGenImpl();
        try {
            VerifyCode verifyCode = iVerifyCodeGen.generate(80, 28);

            String cookieValue = CookieUtils.getCookieValueIfNullThenSetCookie(request, response);
            ImageCodeCaheMap.addImageCode(cookieValue, verifyCode.getCode());

            request.getSession().setAttribute("VerifyCode", verifyCode.getCode());
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/jpeg");
            response.getOutputStream().write(verifyCode.getImgBytes());
            response.getOutputStream().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/admin")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String printAdmin() {
        return "如果你看见这句话，说明你有ROLE_ADMIN角色";
    }
    @RequestMapping("/user")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_USER')")
    public String printUser() {
        return "如果你看见这句话，说明你有ROLE_USER角色";
    }

    @RequestMapping("403")
    public String forbidden(){
        return "403";
    }


}
