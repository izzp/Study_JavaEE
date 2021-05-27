package com.mezzp.mapper;

import com.mezzp.bean.Employee;
import java.util.List;

/**
 * EmployeeMapper接口
 *
 * @author zhaozhipeng
 * @version 1.0
 * @date 2021/5/27
 */
public interface EmployeeMapper {
    /**
     * 获取所有的Employee信息
     * @return
     */
    List<Employee> getALLEmps();
}
