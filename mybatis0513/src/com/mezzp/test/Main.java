package com.mezzp.test;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.mezzp.bean.User;
import com.mezzp.mapper.UserMapper;

public class Main {
	public static void main(String[] args) throws IOException {
		// 0.映射sql语句
		// 1.读取mybatis的全局配置文件参数，创建一个工厂SqlSessionFactory
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		// 2。通过SqlSessionFactory对象获取SqlSession对象
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 3.通过SqlSession对象来获取指定接口的代理对象类
		UserMapper userMapper =sqlSession.getMapper(UserMapper.class);
		// 4.代理类对象调用方法
		User user = userMapper.getUserById(2);
		System.out.println(user);
	}
}
