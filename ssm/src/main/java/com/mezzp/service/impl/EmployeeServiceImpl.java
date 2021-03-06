package com.mezzp.service.impl;

import com.mezzp.bean.Department;
import com.mezzp.bean.Employee;
import com.mezzp.mapper.DepartmentMapper;
import com.mezzp.mapper.EmployeeMapper;
import com.mezzp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * EmployeeServiceImpl
 *
 * @author zhaozhipeng
 * @version 1.0
 * @date 2021/5/27
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
    /**
     * 获取mapper代理实现类
     */
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    DepartmentMapper departmentMapper;

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employees = employeeMapper.getAllEmps();
        return employees;
    }

    @Override
    public List<Department> getAllDeps() {
        List<Department> departments= departmentMapper.getAllDeps();
        return departments;
    }

    @Override
    public void addEmp(Employee employee) {
        employeeMapper.addEmp(employee);
    }

    @Override
    public void delEmp(Integer empId) {
        employeeMapper.delEmpById(empId);
    }
    @Override
    public Employee getEmp(Integer empId) {
        Employee employee = employeeMapper.getEmpById(empId);
        return employee;
    }
    @Override
    public void updateEmp(Employee employee) {
        employeeMapper.updateEmp(employee);
    }
}
