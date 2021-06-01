package com.mezzp.mapper;

import com.mezzp.bean.Department;

import java.util.List;

/**
 * DepartmentMapper
 *
 * @author zhaozhipeng
 * @version 1.0
 * @date 2021/5/27
 */
public interface DepartmentMapper {
    /**
     * 根据dep_id查询
     * @param id id号
     * @return Department
     */
    Department getDepById(Integer id);

    /**
     * 获取所有部门被信息
     * @return List<Department>
     */
    List<Department> getAllDeps();
}
