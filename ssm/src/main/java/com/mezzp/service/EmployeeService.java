package com.mezzp.service;

import com.mezzp.bean.Employee;

import java.util.List;

/**
 * EmployeeService
 *
 * @author zhaozhipeng
 * @version 1.0
 * @date 2021/5/27
 */
public interface EmployeeService {
    /**
     * 获取所有的Employee信息
     *
     * @return
     */
    List<Employee> getAllEmployees();
}
