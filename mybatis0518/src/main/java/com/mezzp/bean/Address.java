package com.mezzp.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 地址bean
 *
 * @author zhaozhipeng
 * @version 1.0
 * @date 2021/5/18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    private Integer id;
    private String province;
    private String city;
}
