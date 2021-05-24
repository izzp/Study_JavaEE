package com.mezzp.handler;

import com.mezzp.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * User
 *
 * @author zhaozhipeng
 * @version 1.0
 * @date 2021/5/24
 */
@Controller
@RequestMapping("/user")
public class UserHandler {
    @RequestMapping("/login1")
    /**
     *  ServletAPI
     */
    public String login1Handle(HttpServletRequest request){
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        System.out.println("username:"+username+"age:"+age);
        return "success";
    }
//    @RequestMapping("/login2")
//    /**
//     * login2 RequestParam处理映射对象，参数名与形参名一致的情况
//     */
//    public String login2Handle(String username , Integer age){
//        System.out.println("username:"+username+"age:"+age);
//        return "success";
//    }
    @RequestMapping("/login2")
    /**
     * login2 RequestParam处理映射对象
     */
    public String login2Handle(@RequestParam("username") String name , Integer age){
        System.out.println("username:"+name+"age:"+age);
        return "success";
    }
    @RequestMapping("/login3")
    /**
     * login3 RequestHeader来获取对象值
     */
    public String login3Handle(@RequestHeader("Accept-Encoding")String str){
        System.out.println("Accept-Encoding:"+str);
        return "success";
    }
    @RequestMapping("/login4")
    /**
     * 使用CookieValue来获取Cookie
     */
    public String login4Handle(@CookieValue("JSESSIONID") String str){
        System.out.println("JSESSIONID:"+str);
        return "success";
    }
    @RequestMapping("/login5")
    /**
     *  pojo映射
     */
    public String login5Handle(User user){
        System.out.println(user);
        return "success";
    }

}
