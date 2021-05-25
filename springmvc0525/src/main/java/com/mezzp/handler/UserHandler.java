package com.mezzp.handler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.Map;

/**
 * User
 *
 * @author zhaozhipeng
 * @version 1.0
 * @date 2021/5/25
 */
@Controller
@RequestMapping("/user")
@SessionAttributes(value = {"username","password"})
public class UserHandler {

    @RequestMapping("/handle1")
    public String handle1() {
        return "success";
    }

    /**
     * 处理模型数据
     *
     * @return 页面
     */
    @RequestMapping("/handle2")
    public ModelAndView handle2() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("success");
        modelAndView.addObject("username", "tom");
        modelAndView.addObject("age", "23");
        return modelAndView;
    }

    /**
     * 封装map，map数据会自动封装到request中
     *
     * @param map map
     * @return 成功页
     */
    @RequestMapping("/handle3")
    public String handle3(Map<String, Object> map) {
        map.put("username", "haha");
        map.put("password", "111111");
        return "success";
    }

    @RequestMapping("/handle4")
    public String handle4(Model model) {
        model.addAttribute("user", "haha");
        return "success";
    }

}
