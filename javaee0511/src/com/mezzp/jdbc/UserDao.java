package com.mezzp.jdbc;

import java.util.List;

public interface UserDao {
	User getUser(Integer id);
	List<User> getAllUsers();
	void updateUser(User user);
	int[] batchUpdateUser(List<Object[]> lists);
	int getCount(String name);
}
