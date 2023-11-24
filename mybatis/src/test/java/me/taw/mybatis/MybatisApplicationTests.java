package me.taw.mybatis;

import com.github.pagehelper.PageHelper;
import me.taw.mybatis.pojo.Emp;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootTest
class MybatisApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void findAll() throws IOException {
        //1.读取mybatis的核心配置文件(mybatis-config.xml)
        InputStream in = Resources
                .getResourceAsStream("mybatis-config.xml");
        //2.通过配置信息获取一个SqlSessionFactory工厂对象
        SqlSessionFactory fac =
        new SqlSessionFactoryBuilder().build( in );
        //3.通过工厂获取一个SqlSession对象
        SqlSession session = fac.openSession();
        //4.通过namespace+id找到要执行的sql语句并执行sql语句
        List<Emp> list = session
                .selectList("me.taw.mybatis.EmpMapper.findAll");
        //5.输出结果
        for(Emp e : list) {
            System.out.println( e );
        }
    }


    @Test
    public void findAllEx() throws IOException {
        //1.读取mybatis的核心配置文件(mybatis-config.xml)
        InputStream in = Resources
                .getResourceAsStream("mybatis-config.xml");
        //2.通过配置信息获取一个SqlSessionFactory工厂对象
        SqlSessionFactory fac =
                new SqlSessionFactoryBuilder().build( in );
        //3.通过工厂获取一个SqlSession对象
        SqlSession session = fac.openSession();
        //4.通过namespace+id找到要执行的sql语句并执行sql语句
        List<Emp> list = session
                .selectList("me.taw.mybatis.EmpMapper.findAllEx");
        //5.输出结果
        for(Emp e : list) {
            System.out.println( e );
        }
    }

    @Test
    public void findPage() throws IOException {
        //1.读取mybatis的核心配置文件(mybatis-config.xml)
        InputStream in = Resources
                .getResourceAsStream("mybatis-config.xml");
        //2.通过配置信息获取一个SqlSessionFactory工厂对象
        SqlSessionFactory fac =
                new SqlSessionFactoryBuilder().build( in );
        //3.通过工厂获取一个SqlSession对象
        SqlSession session = fac.openSession();
        //4.通过namespace+id找到要执行的sql语句并执行sql语句
        PageHelper.startPage(1, 2);
        List<Emp> list = session
                .selectList("me.taw.mybatis.EmpMapper.findAllEx");
        //5.输出结果
        for(Emp e : list) {
            System.out.println( e );
        }
    }
}
