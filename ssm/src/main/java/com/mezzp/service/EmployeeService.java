package com.mezzp.service;

import com.mezzp.bean.Department;
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
     * @return List<Employee>
     */
    List<Employee> getAllEmployees();

    /**
     * 获取所有部门信息
     * @return List<Department>
     */
    List<Department> getAllDeps();

    /**
     * 添加员工得Service方法
     * @param employee employee信息
     */
    void addEmp(Employee employee);

    /**
     * 删除员工
     * @param empId empId
     */
    void delEmp(Integer empId);

    /**
     * 查询员工信息
     * @param empId empId
     * @return Employee
     */
    Employee getEmp(Integer empId);

    /**
     * 修改员工信息
     * @param employee employee
     */
    void updateEmp(Employee employee);
}
