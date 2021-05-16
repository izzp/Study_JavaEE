package com.mezzp.dao;

import com.mezzp.bean.User;

/**
 * @author zzp
 */
public interface UserMapper {
    /**
     * 查询用户
     *
     * @param id id
     * @return 用户信息
     */
    User selectUser(Integer id);

    /**
     * 添加用户
     *
     * @param user 用户信息
     */
    void insertUser(User user);

    /**
     * 更新用户密码
     *
     * @param id id
     * @param password 密码
     */
    void updateUser(Integer id, String password);

    /**
     * 删除用户
     *
     * @param name 用户名
     * @return
     */
    Integer deleteUser(String name);

}
