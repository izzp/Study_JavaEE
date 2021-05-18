package com.mezzp.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User bean
 *
 * @author zhaozhipeng
 * @version 1.0
 * @date 2021/5/18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;
    private String userName;
    private Integer age;
    private Address address;
}
