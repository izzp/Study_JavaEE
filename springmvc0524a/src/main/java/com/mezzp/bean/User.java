package com.mezzp.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User
 *
 * @author zhaozhipeng
 * @version 1.0
 * @date 2021/5/24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer uid;
    private String username;
    private Integer age;
    private Address address;
}
