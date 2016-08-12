package com.cdogs.lightBlog.dao;


import com.cdogs.lightBlog.pojo.Admin;

/**
 * 管理员Dao
 * 
 * @author  CDogs
 */
public interface AdminDao {
    
    /**
     * 
     * 根据管理员对象检索管理员信息
     * @param admin
     * @return Admin
     * @see [类、类#方法、类#成员]
     */
    public Admin getAdmin(Admin admin);
    
    /**
     * 更新更新信息
     * @param admin
     * @return int
     */
    public int updateAdmin(Admin admin);

    /**
     * 添加管理员
     * @param admin
     */
    public int addAdmin(Admin admin);
}
