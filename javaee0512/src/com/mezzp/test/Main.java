package com.mezzp.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mezzp.service.BookShopService;

public class Main {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-tx.xml");
		BookShopService bookShopService =context.getBean("bookShopServiceImpl",BookShopService.class);
		bookShopService.buyBook("1002");
	}
}
