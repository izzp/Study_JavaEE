package com.mezzp.handler;

import com.mezzp.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
public class UserHandler {

    /**
     * 模拟从数据库根据id查询的User信息
     *
     * @param map
     */
    @ModelAttribute
    public void getUser(@RequestParam(value = "id", required = false) Integer id, Map<String, Object> map) {
        if (id != null) {
            System.out.println("模拟从数据库根据id查询的User信息");
            User user = new User(1001, "Tom", "123456", 20);
            map.put("user", user);
        }
    }

    @RequestMapping("/update")
    public String updateUser(User user) {
        System.out.println(user);
        return "success";
    }


}
