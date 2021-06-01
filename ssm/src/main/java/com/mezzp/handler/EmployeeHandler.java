package com.mezzp.handler;

import com.mezzp.bean.Department;
import com.mezzp.bean.Employee;
import com.mezzp.service.EmployeeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhaozhipeng
 * @version 1.0
 * @date 2021/5/27
 */
@Controller
public class EmployeeHandler {
    static Logger logger = Logger.getLogger(EmployeeHandler.class);
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/emps", method = RequestMethod.GET)
    public String listEmps(Map<String, Object> map) {
        List<Employee> employees = employeeService.getAllEmployees();
        map.put("employees", employees);
        logger.info("listEmps--200");
        return "listemps";
    }

    @RequestMapping("/toAddEmp")
    public String toAddEmp(Map<String, Object> map) {
        Map<String, String> genders = new HashMap<>();
        genders.put("0", "女");
        genders.put("1", "男");
        genders.put("2", "保密");
        map.put("genders", genders);

        List<Department> departments = employeeService.getAllDeps();
        map.put("departments", departments);
        Employee employee = new Employee();
        //设置默认回显值
        employee.setEmpName("xxx");
        employee.setGender(1);
        employee.setEmail("xxx@sina.com");
        Department department=new Department();
        department.setDepId(102);
        employee.setDepartment(department);

        map.put("command", employee);
        return "addemp";
    }
    @RequestMapping("/emp")
    public String addEmp(Employee employee){
        employeeService.addEmp(employee);
        return "redirect:/emps";
    }
}
