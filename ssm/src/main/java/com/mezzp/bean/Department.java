package com.mezzp.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * department
 *
 * @author zhaozhipeng
 * @version 1.0
 * @date 2021/5/27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {
    private Integer depId;
    private String depName;
}
