package com.mezzp.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * User
 *
 * @author zhaozhipeng
 * @version 1.0
 * @date 2021/5/26
 */
@Controller
public class UserHandler {

    @RequestMapping("/testMyView")
    public String handle1() {
        return "myView";
    }

    @RequestMapping("/hello")
    public String handle2() {
        //return "success";
        //return "forward:/WEB-INF/pages/success.jsp";
        return "redirect:/jsps/ok.jsp";
    }
}
