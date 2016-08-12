package com.cdogs.lightBlog.controller;


import com.cdogs.lightBlog.pojo.ExtendPage;
import com.cdogs.lightBlog.pojo.Admin;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 管理员管理
 * @author CDogs
 */
@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {

	/**
	 * 初始化登录页面
	 * @return ModelAndView
	 */
	@RequestMapping("/init-login")
	public ModelAndView initLoginPage() {
        //其他页面
        List<ExtendPage> pages = extendPageService.getAllPages();
		return new ModelAndView("/login").addObject("pages", pages);
	}
	
	/**
	 * 管理登录
	 * @param
	 * @return ModelAndView
	 */
	@ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/login")
	public String login(String name, String password,
			HttpServletRequest request) {
		System.out.println("正在执行login方法...");
		System.out.println("name = " + name + " password = " + password);
    	//ModelAndView res = new ModelAndView("/login");
        try {  
        	Admin admin = new Admin();
        	admin.setName(name);
        	admin.setPassword(password);
        	admin = adminService.getAdmin(admin);
        	
        	if (admin != null) {//登录成功
				request.getSession().setAttribute("admin",admin);
        		request.getSession().setAttribute("AdminID", admin.getId());
        		request.getSession().setAttribute("AdminName", admin.getName());
        		//return new ModelAndView("redirect:/admin/ucenter.htm");
				return "/lightBlog/admin/admin_index.htm";
        	} else {//登录失败
        		//res.addObject("message", INVALID_USER);
				return INVALID_USER;
        	}
        } catch (Exception e) {
                LOGGER.error("AdminController.login();", e.getMessage());
                //res.addObject("message", ERROR);
			return ERROR;
        }
        //返回FAIL
    	//return res;
	}
	/**
	 * 注销
	 * @return ModelAndView
	 */
	@RequestMapping("/logout")
	public ModelAndView logout(HttpServletRequest request) {
		request.getSession().removeAttribute("admin");
		request.getSession().removeAttribute("AdminID");
		request.getSession().removeAttribute("AdminName");
		return new ModelAndView("redirect:/article/index.htm");
	}

	/**
	 * 注册
	 * @param name
	 * @param password
	 */
	@ResponseBody
	@RequestMapping("/register")
	public String register(String name,String password) {
		System.out.println("你好！这里是注册");
		System.out.println(name);
		System.out.println(password);
		try {
			if(null != name && !name.trim().equals("") && null != password && !password.trim().equals("")) {
				Admin admin = new Admin();
				admin.setName(name);
				admin.setPassword(password);
				//用户已存在
				if(adminService.getAdmin(admin) != null)return INVALID_USER;
				admin.setPassword(password);
				if (adminService.addAdmin(admin)) {//注册成功
					System.out.println("注册成功！");
					return SUCCESS;
				} else {//登录失败
					System.out.println("注册失败");
					return ERROR;
				}
			}
		} catch (Exception e) {
			LOGGER.error("AdminController.register();", e.getMessage());
			return ERROR;
		}
		return ERROR;
	}

	/**
	 *
	 * 更新管理员资料
	 * @return
	 *
	 */
	@RequestMapping("/update")
	public ModelAndView updateAdminInfo(Admin admin, HttpServletRequest request) {

		ModelAndView response = new ModelAndView("/admin/adminInfo");
		try {
			String redirect = request.getParameter("redirect");
			// 如果重定向标记不为空,且等于account，则返回页面地址到账户密码修改页面
			if (redirect != null && redirect.equals("account")) {
				response = new ModelAndView("/admin/account_edit");
			}
			//返回FAIL
			response.addObject("message", FAIL);

			if (admin != null) {
				//如果更新成功,返回SUCCESS
				if (adminService.updateAdmin(admin)) {
					response.addObject("message", SUCCESS);
				}
			}
		} catch (Exception e) {
			LOGGER.error("BackAdminController.updateAdminInfo()",e.getMessage());
		}
		response.addObject("admin", admin);
		return response;
	}
}
