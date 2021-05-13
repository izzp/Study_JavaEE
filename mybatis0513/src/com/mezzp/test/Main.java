package com.mezzp.test;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.mezzp.bean.User;

public class Main {
	public static void main(String[] args) throws IOException {
		// 0.映射sql语句
		// 1.读取mybatis的全局配置文件参数，创建一个工厂SqlSessionFactory
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		// 2。通过SqlSessionFactory对象获取SqlSession对象
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 3.使用SqlSession对象来进行增删改查
		User user = sqlSession.selectOne("mezzp.getUserById", 2);
		System.out.println(user);
	}
}
