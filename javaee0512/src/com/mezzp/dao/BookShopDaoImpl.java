package com.mezzp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BookShopDaoImpl implements BookShopDao {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public int getBookPrice(String isbn) {
		String sqlString="select price from book where isbn=?";
		int price = jdbcTemplate.queryForObject(sqlString, Integer.class,isbn); 
		return price;
	}

	@Override
	public void updateBookStock(String isbn) {
		// TODO 自动生成的方法存根

	}

	@Override
	public void updateAccount(String name, int price) {
		// TODO 自动生成的方法存根
		
	}
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-tx.xml");
		BookShopDao bookShopDao=context.getBean("bookShopDaoImpl",BookShopDao.class);
		System.out.println(bookShopDao.getBookPrice("1001"));
	}

}
