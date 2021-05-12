package com.mezzp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.mezzp.dao.BookShopDao;

public class BookShopServiceImpl implements BookShopService {
	@Autowired
	BookShopDao bookShopDao;

	@Override
	@Transactional
	public void buyBook(String isbn) {
		// 查价格
		int price = bookShopDao.getBookPrice(isbn);
		// 改库存
		bookShopDao.updateBookStock(isbn);
		// 改账户
		bookShopDao.updateAccount("Tom", price);
	}

}
