package com.mezzp.service.impl;

import com.mezzp.bean.Employee;
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

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employees = employeeMapper.getAllEmps();
        return employees;
    }
}
