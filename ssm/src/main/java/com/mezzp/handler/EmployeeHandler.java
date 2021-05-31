package com.mezzp.handler;

import com.mezzp.bean.Employee;
import com.mezzp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

/**
 * @author zhaozhipeng
 * @version 1.0
 * @date 2021/5/27
 */
@Controller
public class EmployeeHandler {
    @Autowired
    EmployeeService employeeService;

    @RequestMapping(value = "/emps", method = RequestMethod.GET)
    public String listEmps(Map<String, Object> map) {
        List<Employee> employees = employeeService.getAllEmployees();
        map.put("employees", employees);
        System.out.println("-----listEmps--200-----");
        return "listemps";
    }
}
