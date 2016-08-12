package com.cdogs.lightBlog.service.impl;

import com.cdogs.lightBlog.pojo.Admin;
import com.cdogs.lightBlog.service.BaseService;
import com.cdogs.lightBlog.service.AdminService;
import com.cdogs.lightBlog.util.CryptUtils;
import org.springframework.stereotype.Service;

/**
 * 管理员服务实现类
 * 
 * @author  CDogs
 */
@Service
public class AdminServiceImpl extends BaseService implements AdminService {

	public Admin getAdmin(Admin admin) {

		System.out.println("1--admin.password=" + admin.getPassword());
		if (admin.getPassword() != null) {
			//对密码加密，然后进行匹配查询
			admin.setPassword(CryptUtils.encryptString(admin.getPassword()));
		}
		// 检索用户信息后，对密码重新解密后set
		System.out.println(admin.getPassword());
		admin = adminDao.getAdmin(admin);
		System.out.println(admin);
		if (admin != null) {
			admin.setPassword(CryptUtils.decryptString(admin.getPassword()));
		}
		//System.out.println("2--admin.password=" + admin.getPassword());
		return admin;
	}


	public boolean updateAdmin(Admin admin) {
		
		if (admin.getPassword() != null) {
			//对密码加密
			admin.setPassword(CryptUtils.
					encryptString(admin.getPassword()));
		}
		int result = adminDao.updateAdmin(admin);
		return result > 0;
	}


	public boolean addAdmin(Admin admin) {
		//添加管理员

		System.out.println("addAdmin===============AdminService");
		System.out.println("1--admin.password=" + admin.getPassword());
		if (admin.getPassword() != null) {
			//对密码加密
			admin.setPassword(CryptUtils.encryptString(admin.getPassword()));
		}
		System.out.println("2--admin.password=" + admin.getPassword());
		int result = adminDao.addAdmin(admin);
		System.out.println(result);
		return (result > 0) ;
	}

}
