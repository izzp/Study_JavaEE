package com.mezzp.dao;

import com.mezzp.bean.User;

/**
 * UserMapper
 *
 * @author zhaozhipeng
 * @version 1.0
 * @date 2021/5/18
 */
public interface UserMapper {
    /**
     * 根据id查询
     * @param id id
     * @return user
     */
    User getUserById(Integer id);
}
