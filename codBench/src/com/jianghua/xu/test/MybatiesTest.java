package com.jianghua.xu.test;

import com.opensymphony.xwork2.interceptor.annotations.Before;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

/**
 * @author : xujianghua
 * @description : TODO
 * @date : 2019/3/7 16:27
 */
public class MybatiesTest {
    private SqlSessionFactory sqlSessionFactory = null;

    public static void main(String[] args) throws Exception{

        MybatiesTest mb = new MybatiesTest();
        mb.testQueryCus();
    }
    @Before
    public void init() throws Exception{
        // 创建sqlSessionFactoryBuilder对象
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        // 加载sqlMapConfig.xml对象
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 创建sqlSessionFactory对象
        this.sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);

    }

    public void testQueryCus() throws Exception{
        // 创建sqlSessionFactoryBuilder对象
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        // 加载sqlMapConfig.xml对象
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 创建sqlSessionFactory对象
        this.sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
        // 创建sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 执行sqlSession查询结果
        Object cust = sqlSession.selectOne("queryCustomer", 4);
        // 打印结果
        System.out.println(cust);
        // 释放资源
        sqlSession.close();
    }

}
