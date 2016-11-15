package com.cdogs.lightBlog.controller;


import com.cdogs.lightBlog.dto.ArticleDto;
import com.cdogs.lightBlog.dto.Page;
import com.cdogs.lightBlog.dto.PageResult;
import com.cdogs.lightBlog.pojo.*;
import com.cdogs.lightBlog.util.FileUtils;
import com.cdogs.lightBlog.util.JSONUtils;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 用户控制器
 * @author CDogs
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

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
    	//ModelAndView res = new ModelAndView();
        try {  
        	User user = new User();
        	user.setName(name);
        	user.setPassword(password);
        	user = userService.getUser(user);

			System.out.println("1==========" + user.getName());
        	if (user != null) {//登录成功
				request.getSession().setAttribute("user",user);
        		request.getSession().setAttribute("UserID", user.getId());
        		request.getSession().setAttribute("UserName", user.getName());
        		//return new ModelAndView("redirect:/admin/ucenter.htm");
				//return "/lightBlog/user/ucenter.htm";
				return SUCCESS;
        	} else {//登录失败
        		//res.addObject("message", INVALID_USER);
				return INVALID_USER;
        	}
        } catch (Exception e) {
                LOGGER.error("UserController.login();", e.getMessage());
                //res.addObject("message", ERROR);
				return ERROR;
        }
        //返回FAIL
		//System.out.println("login方法结束...");
    	//return res;
	}
	/**
	 * 注销
	 * @return ModelAndView
	 */
	@RequestMapping("/logout")
	public ModelAndView logout(HttpServletRequest request) {
		request.getSession().removeAttribute("user");
		request.getSession().removeAttribute("UserID");
		request.getSession().removeAttribute("UserName");
		return new ModelAndView("redirect:/article/index.htm");
	}

	/**
	 * 注册
	 * @param name
	 * @param password
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public String register(String name,String password) {
		System.out.println("你好！这里是注册");
		System.out.println(name);
		System.out.println(password);
		try {
			if(null != name && !name.trim().equals("") && null != password && !password.trim().equals("")) {
				User user = new User();
				user.setName(name);
				//user.setPassword(password);
				//用户已存在
				if(userService.getUser(user) != null) return INVALID_USER;
				user.setPassword(password);//重新设置密码
				if (userService.addUser(user)) {//注册成功
					System.out.println("注册成功！");
					return SUCCESS;
				} else {//登录失败
					System.out.println("注册失败");
					return ERROR;
				}
			}
		} catch (Exception e) {
			LOGGER.error("UserController.register();", e.getMessage());
			return ERROR;
		}
		return ERROR;
	}

	/**
	 * 初始化注册页面
	 * @return ModelAndView
	 */
	@RequestMapping("/init-register")
	public ModelAndView initRegisterPage() {
		//其他页面
		List<ExtendPage> pages = extendPageService.getAllPages();
		return new ModelAndView("/register").addObject("pages", pages);
	}

	@RequestMapping("findUser")
	public void findUser(){
		System.out.println("正在执行findUser");
		User user = new User();
		user.setId(1);

		user = userService.getUser(user);
		System.out.println(user.getId()+ "========" + user.getName() + "=========" + user.getPassword());

	}

	/**
	 * 初始化用户页面首页
	 * @return
	 */
	@RequestMapping("/ucenter")
	public ModelAndView initAdminPage(Page page,HttpServletRequest request) {

		int UserID = (Integer) request.getSession().getAttribute("UserID");
		//String UserName = (String) request.getSession().getAttribute("UserName");

		User user = new User();
		user.setId(UserID);

		System.out.println(" /ucenter" + UserID);
		System.out.println("/ucenter");
		ModelAndView response = new ModelAndView("/user/index");

		//文章Page
		PageResult<ArticleDto> data = null;
		//文章实例
		Article article = null;
		//类别列表
		List<ArticleCategory> artCates = null;
		//标签列表
		List<ArticleTag> tags = null;

		if( page.getCurrentPage() == 1) {
			page.setPageSize(5);
			page.repaginate();
		}

		//获取分页数据
		try {
			data = articleService.getArticlesByUser(user, article, page);
			tags = articleTagService.getAllTags();
			System.out.println(tags);
			artCates = articleCategoryService.getAllArtiCategorys();
		} catch (Exception e) {
			LOGGER.error("UserController." + "ucenter();", e.getMessage());
		}
		//设置page并重新分页
		response.addObject("articles", data.getList());
		response.addObject("page", data.getPage());

		System.out.println(tags);
		System.out.println(artCates);
		response.addObject("artCates", artCates);
		response.addObject("tags", tags);

		return response;
	}

	/**
	 * 初始化用户信息页面
	 * @return ModelAndView
	 */
	@RequestMapping("/init-info")
	public ModelAndView initInfoPage() {

		return new ModelAndView("/user/user_info");
	}

	/**
	 * 更新用户信息
	 * @return ModelAndView
	 */
	@ResponseBody
	@RequestMapping("/update")
	public String update(@RequestBody String bodyString,HttpServletRequest request) {

		try {
			System.out.println(bodyString);
			JSONObject json = JSONObject.fromObject(bodyString);
			User user = (User) JSONUtils.String2Object(json.get("user").toString(), User.class);
			System.out.println(json);
			System.out.println(user);
			System.out.println(user.getName());
			//参数正常

			if (!user.getName().isEmpty()) {
				if (userService.updateUser(user)) {
					user = userService.getUser(user);
					request.getSession().setAttribute("user",user);
					request.getSession().setAttribute("UserID",user.getId());
					request.getSession().setAttribute("UserName",user.getName());
					return SUCCESS;
				}
			}

		} catch (Exception e) {
			LOGGER.error("NoticeController.addNotice();", e);
		}
		return FAIL;
	}



	/**
	 * 异步需要用到@ResponseBody
	 * 头像上传
	 * @param file
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/portrait_upload", method = RequestMethod.POST)
	@ResponseBody
	public String accountPortraitUpload(MultipartFile file,HttpServletRequest request)throws Exception {


		System.out.println("开始异步上传。。。。");

		//记录上传过程起始时的时间，用来计算上传时间
		int pre = (int) System.currentTimeMillis();
		if( null == file || file.isEmpty()){
			System.out.println("文件为空，请重新检查");
			return FAIL;
		}


		User currentUser = (User) request.getSession().getAttribute("user");
		System.out.println(request.getSession().getServletContext().getContextPath());
		String basePath = request.getSession().getServletContext().getRealPath("/AccountFile") + "/" + currentUser.getId() + "/portrait/";
		String portraitPath = FileUtils.saveMultipartFileRelative(file, basePath);

		//记录上传该文件后的时间
		int finaltime = (int) System.currentTimeMillis();
		System.out.println(finaltime - pre);

		portraitPath = "AccountFile" + portraitPath;
		System.out.println(portraitPath);

		currentUser.setImage(portraitPath);
		System.out.println(currentUser.getName());
		//记录之前的密码，浅复制问题，string为不可变
		String finalPass=currentUser.getPassword();

		if (userService.updateUser(currentUser)) {
			currentUser.setPassword(finalPass);
			currentUser = userService.getUser(currentUser);

			System.out.println(currentUser.getName());
			request.getSession().setAttribute("user",currentUser);
			request.getSession().setAttribute("UserID", currentUser.getId());
			request.getSession().setAttribute("UserName",currentUser.getName());
			return SUCCESS;
		}

		return FAIL;
	}
}
