package com.mezzp.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * address
 *
 * @author zhaozhipeng
 * @version 1.0
 * @date 2021/5/24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    private Integer aid;
    private String province;
    private String city;
}
