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
		// 0.ӳ��sql���
		// 1.��ȡmybatis��ȫ�������ļ�����������һ������SqlSessionFactory
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		// 2��ͨ��SqlSessionFactory�����ȡSqlSession����
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 3.ʹ��SqlSession������������ɾ�Ĳ�
		User user = sqlSession.selectOne("mezzp.getUserById", 2);
		System.out.println(user);
	}
}
