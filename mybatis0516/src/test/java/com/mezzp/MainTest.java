package com.mezzp;

import com.mezzp.bean.User;
import com.mezzp.dao.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * 测试
 *
 * @author zhaozhipeng
 * @version 1.0
 * @date 2021/5/16
 */
public class MainTest {
    public static void main(String[] args) throws IOException {
        // 0.映射sql语句
        // 1.读取mybatis的全局配置文件参数，创建一个工厂SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 2。通过SqlSessionFactory对象获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 3.通过SqlSession对象来获取指定接口的代理对象类
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        // 4.代理类对象调用方法

        //查询
        User user1 = userMapper.selectUser(3);
        System.out.println(user1);

        //新增
//        User user  =new User();
//        user.setUserName("haha");
//        user.setPassword("我是密码");
//        userMapper.insertUser(user);
        //更新
        //userMapper.updateUser(20,"aaa");
        //删除
        //userMapper.deleteUser("haha");
        sqlSession.commit();
        sqlSession.close();
    }
}
