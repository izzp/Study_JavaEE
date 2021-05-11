package com.mezzp.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
@Repository
public class UserDaoImpl implements UserDao{
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Override
	public User getUser(Integer id) {
		String sql = "select * from users where id =?";
		RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
		User  user =jdbcTemplate.queryForObject(sql, rowMapper,id);
		return user;
	}
	
	@Override
	public int getCount(String name) {
		String sql = "select count(*) from users where name =?";
		int count = jdbcTemplate.queryForObject(sql, Integer.class, name);
		return count;
	}
	
	@Override
	public List<User> getAllUsers() {
		String sql = "select * from users";
		RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
		List<User> users =jdbcTemplate.query(sql, rowMapper);
		return users;
	}

	@Override
	public void updateUser(User user) {
		String sql = "update users set name=?,password=? where id=?";
		jdbcTemplate.update(sql,user.getName(),user.getPassword(),user.getId());
		
	}

	@Override
	public int[] batchUpdateUser(List<Object[]> lists) {
		String sql = "insert into users(name,password) values(?,?)";
		int[] count =jdbcTemplate.batchUpdate(sql,lists);
		return count;
	}



}
