package com.mezzp.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mezzp.exception.AccountException;
import com.mezzp.exception.StockException;

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
		// 1.查库存
		String sqlString="select stock from book_stock where isbn=?";
		int stock=jdbcTemplate.queryForObject(sqlString, Integer.class,isbn);
		// 2。判断库存
		if(stock<=0){
			throw new StockException("库存不足");
		}
		sqlString = "update book_stock set stock=stock-1 where isbn=?";
		jdbcTemplate.update(sqlString,isbn);
	}

	@Override
	public void updateAccount(String name, int price) {
		// TODO 自动生成的方法存根
		String sqlString="select balance from account where username=?";
		int balance=jdbcTemplate.queryForObject(sqlString, Integer.class,name);
		if(balance<price){
			throw new AccountException("余额不足！");
		}
		sqlString = "update account set balance =balance-? where username=?";
		jdbcTemplate.update(sqlString,price,name);
	}
//	public static void main(String[] args) {
//		ApplicationContext context = new ClassPathXmlApplicationContext("spring-tx.xml");
//		BookShopDao bookShopDao=context.getBean("bookShopDaoImpl",BookShopDao.class);
//		System.out.println(bookShopDao.getBookPrice("1001"));
//	}

}
