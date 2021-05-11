package com.mezzp.jdbc;


import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	public static void main(String[] args) {
		ApplicationContext context=new ClassPathXmlApplicationContext("spring-jdbc.xml");
		UserDao userDao = context.getBean("userDaoImpl",UserDao.class);
		
		User user = userDao.getUser(1);
		System.out.println("这是查询的1的结果："+user);
		
		List<User> users = userDao.getAllUsers();
		System.out.println("这是查询全部的结果："+users);
		
		User userupdate=new User("3","哈哈","123");
		userDao.updateUser(userupdate);
		
		List<Object[]> lists = new ArrayList<Object[]>();
		lists.add(new Object[]{"李白","111111"});
		lists.add(new Object[]{"杜甫","222222"});
		userDao.batchUpdateUser(lists);
		
		int usercount = userDao.getCount("张三");
		System.out.println("这是查询数量的结果："+usercount);
	}
}
