package com.cdogs.lightBlog.service;


import com.cdogs.lightBlog.pojo.Admin;

/**
 * 管理员Service
 *
 * @author  CDogs
 */
public interface AdminService {
	
    /**
     * 
     * 根据管理员对象检索管理员信息
     * @param admin
     * @return Admin
     * @see [类、类#方法、类#成员]
     */
    public Admin getAdmin(Admin admin);
    
    /**
     * 更新管理员信息
     * @param admin
     * @return boolean
     */
    public boolean updateAdmin(Admin admin);

    /**
     * 添加管理员
     * @param admin
     * @return
     */
    public boolean addAdmin(Admin admin);
}
