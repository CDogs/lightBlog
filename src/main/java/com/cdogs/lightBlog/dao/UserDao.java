package com.cdogs.lightBlog.dao;


import com.cdogs.lightBlog.pojo.User;

/**
 * 用户Dao
 * 
 * @author  CDogs
 */
public interface UserDao {
    
    /**
     * 
     * 根据用户对象检索用户信息
     * @param user
     * @return User
     * @see [类、类#方法、类#成员]
     */
    public User getUser(User user);
    
    /**
     * 更新用户信息
     * @param user
     * @return int
     */
    public int updateUser(User user);

    /**
     * 添加用户
     * @param user
     */
    public int addUser(User user);
}
