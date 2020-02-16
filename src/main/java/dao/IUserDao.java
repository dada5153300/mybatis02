package dao;

import bean.QueryVo;
import bean.User;

import java.util.List;
import java.util.Queue;

public interface IUserDao  {

     List<User> findAll();

     void  saveUser(User user);

     void updateUser(User user);

     void deleteUser(Integer userId);
     User findById(Integer userId);

     List<User> findByName (String userName);

     int count();

     /**
      * 根据queryVo中的查询条件查询用户
      * 包装起来查询
      * @param vo
      * @return
      */
     List<User> findUserByVo(QueryVo vo);
}
