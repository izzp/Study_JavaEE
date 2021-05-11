package com.mezzp.jdbc;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class Main {
	public static void main(String[] args) {
		ApplicationContext context=new ClassPathXmlApplicationContext("spring-jdbc.xml");
		JdbcTemplate jdbcTemplate=context.getBean("jdbcTempLate",JdbcTemplate.class);
		String sql = "select * from users where id =?";
		RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
		User  user =jdbcTemplate.queryForObject(sql, rowMapper,1);
		System.out.println(user);
	}
}
