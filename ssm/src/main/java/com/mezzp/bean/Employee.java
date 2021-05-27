package com.mezzp.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * employee
 *
 * @author zhaozhipeng
 * @version 1.0
 * @date 2021/5/27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private Integer empId;
    private String empName;
    private Integer gender;
    private String email;
    private Department department;
}
