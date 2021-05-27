package com.mezzp.handler;

import com.mezzp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zhaozhipeng
 * @version 1.0
 * @date 2021/5/27
 */
@Controller
public class EmployeeHandler {
    @Autowired
    EmployeeService employeeService;
    @RequestMapping("/emps")
    public String listEmps(){

        return "listemps";
    }
}
