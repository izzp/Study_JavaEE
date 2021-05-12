package com.mezzp.dao;

public interface BookShopDao {
	int getBookPrice(String isbn);
	void updateBookStock(String isbn);
	void updateAccount(String name,int price);
}
