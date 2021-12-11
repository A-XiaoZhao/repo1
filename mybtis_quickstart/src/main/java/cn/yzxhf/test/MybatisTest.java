package cn.yzxhf.test;

import cn.yzxhf.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class MybatisTest {
    /**
     * 快速入门
     */
    @Test
    public  void  mybatisQuickStart() throws IOException {

        //加载核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");

        //获取sqlSessionFactory工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        //获取sqlSession会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //执行sql
        List<User> list = sqlSession.selectList("UserMapper.findAll");

        //打印结果
        for (User user : list) {

            System.out.println(user);
        }
        sqlSession.close();


    }

    @Test
    public  void  testSave() throws IOException {

        //加载核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");

        //获取sqlSessionFactory工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        //获取sqlSession会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        User user=new User();
        user.setUsername("jack");
        user.setBirthday(new Date());
        user.setSex("男");
        user.setAddress("浙江杭州");
        int insert = sqlSession.insert("UserMapper.saveUser", user);

        System.out.println(insert);
      //  sqlSession.commit();
        sqlSession.close();


    }
    @Test
    public  void  testUpdate() throws IOException {
        //加载核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");

        //获取sqlSessionFactory工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        //获取sqlSession会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        User user=new User();
        user.setId(6);
        user.setUsername("lucy");
        user.setBirthday(new Date());
        user.setSex("女");
        user.setAddress("浙江杭州");
        int insert = sqlSession.update("UserMapper.updateUser", user);

        System.out.println(insert);
        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public  void  testDelete() throws IOException {
        //加载核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");

        //获取sqlSessionFactory工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        //获取sqlSession会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        int delete = sqlSession.delete("UserMapper.deleteUser", 7);

        System.out.println(delete);
        sqlSession.commit();
        sqlSession.close();
    }
}
