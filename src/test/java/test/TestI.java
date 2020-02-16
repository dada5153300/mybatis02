package test;

import bean.User;
import dao.IUserDao;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import  org.junit.Test;


import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLOutput;
import java.util.List;

import  org.apache.ibatis.io.Resources;

public class TestI {


    @Test
    public void testFindAll() throws IOException {

        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory =new SqlSessionFactoryBuilder().build(in);
        SqlSession session =factory.openSession();
        IUserDao userDao = session.getMapper(IUserDao.class);
        List<User> users=userDao.findAll();
        for(User user :users)
        {
            System.out.println(user.toString());
        }
    }

    /**
     * 保存细节
     * @throws IOException
     */
    @Test
    public void testSave() throws IOException {

        User user =new User();

        user.setUserName("xiaohua");
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory =new SqlSessionFactoryBuilder().build(in);
        SqlSession session =factory.openSession();
        IUserDao userDao = session.getMapper(IUserDao.class);
        System.out.println(user.toString());
        userDao.saveUser(user);
        System.out.println(user.toString());
        session.commit();
//        System.out.println(user.toString());
        session.close();
        in.close();
    }

    /**
     * 来回测试简单的
     * @throws IOException
     */
    @Test
    public void testUser() throws IOException
    {

       /*
        User user =new User();
        user.setId(1);
        user.setUserName("xiaoda");
*/
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory =new SqlSessionFactoryBuilder().build(in);
        SqlSession session =factory.openSession();
        IUserDao userDao = session.getMapper(IUserDao.class);
        System.out.println(userDao.count());
    /*  User user=userDao.findById(1);
        userDao.deleteUser();
        userDao.updateUser();*/
        //List<User> users=userDao.findByName("%xiao%");
        //进行add和update必须进行session.commit();
        //如果没有session.commit()虽然会添加但是添加不上可是自增的id增长了
        //为什么呢？ 执行了但是没有保存成功事务回滚。
        session.commit();
//        System.out.println(user.toString());
        session.close();
        in.close();


     /*   for(User user1 :users)
        {
            System.out.println(user1.toString());
        }*/
    }
}
