package com.mezzp.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    @RequestMapping(value="/order/1001" ,method = RequestMethod.DELETE)
    public String deleteHandle() {
        System.out.println("delete order");
        return "success";
    }
    @RequestMapping(value="/order" ,method = RequestMethod.PUT)
    public String updateHandle() {
        System.out.println("update order");
        return "success";
    }
    @RequestMapping(value="/order/1001" ,method = RequestMethod.GET)
    public String getHandle() {
        System.out.println("get order");
        return "success";
    }
}
