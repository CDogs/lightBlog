package com.cdogs.lightBlog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 静态页面控制器
 * @author CDogs
 */

@Controller
@RequestMapping("/static")
public class StaticPage extends BaseController {
	
	/**
	 * 初始化登陆界面
	 * @return ModelAndView
	 */
	@RequestMapping("/login")
	public ModelAndView initLogin() {
		return new ModelAndView("/admin/login");
	}
}
