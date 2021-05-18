package com.mezzp.dao;

import com.mezzp.bean.Address;

/**
 * AddressMapper
 *
 * @author zhaozhipeng
 * @version 1.0
 * @date 2021/5/18
 */
public interface AddressMapper {
    /**
     * 根据id查询
     * @param id id
     * @return user
     */
    Address getAddressById(Integer id);
}
