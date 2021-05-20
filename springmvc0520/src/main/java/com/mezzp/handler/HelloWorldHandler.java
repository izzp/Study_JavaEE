package com.mezzp.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * HelloWorldHandler
 *
 * @author zhaozhipeng
 * @version 1.0
 * @date 2021/5/20
 */
@Controller
public class HelloWorldHandler {
    @RequestMapping(value="/hello")
    public String helloHandle() {
        System.out.println("hello world");
        return "success";
    }
}
