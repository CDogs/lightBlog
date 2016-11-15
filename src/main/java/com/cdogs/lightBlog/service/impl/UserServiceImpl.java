package com.cdogs.lightBlog.service.impl;

import com.cdogs.lightBlog.pojo.User;
import com.cdogs.lightBlog.service.BaseService;
import com.cdogs.lightBlog.service.UserService;
import com.cdogs.lightBlog.util.CryptUtils;
import org.springframework.stereotype.Service;


/**
 * 用户服务实现类
 * 
 * @author  CDogs
 */
@Service
public class UserServiceImpl extends BaseService implements UserService {
	

	public User getUser(User user) {
		System.out.println("1--user.password=" + user.getPassword());
		if (user.getPassword() != null) {				
			//对密码加密，然后进行匹配查询
			user.setPassword(CryptUtils.
					encryptString(user.getPassword()));
		}
		System.out.println("2--user.password=" + user.getPassword());
		// 检索用户信息后，对密码重新解密后set
		user = userDao.getUser(user);
		if (user != null) {
			user.setPassword(CryptUtils.decryptString(user.getPassword()));
			System.out.println("3--user.password=" + user.getPassword());
		}

		return user;
	}


	public boolean updateUser(User user) {
		
		if (user.getPassword() != null) {	
			//对密码加密
			user.setPassword(CryptUtils.encryptString(user.getPassword()));
		}
		System.out.println("修改中");
		System.out.println(user.getId());
		int result = userDao.updateUser(user);
		System.out.println("修改结束");
		return result > 0;
	}


	public boolean addUser(User user) {
		//添加用户
		System.out.println("addUser===============UserService");
		System.out.println("1--user.password=" + user.getPassword());
		if (user.getPassword() != null) {
			//对密码加密
			user.setPassword(CryptUtils.encryptString(user.getPassword()));
		}
		System.out.println("2--user.password=" + user.getPassword());
		int result = userDao.addUser(user);
		System.out.println(result);
		return (result > 0) ;
	}

}
