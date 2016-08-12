package com.cdogs.lightBlog.controller;


import com.cdogs.lightBlog.constants.ResponseResult;
import com.cdogs.lightBlog.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * BaseController 继承了ResponseResult常量类，
 * BaseController 集合了所有的Service对象，并实现自动注入
 * BaseController 包含了Logger记录器
 * @author CDogs
 */
public class BaseController extends ResponseResult {
    
    //Logger
    protected static Logger LOGGER =  LoggerFactory.getLogger(BaseController.class);
    
    /**
     * 下面是一些Service
     */
  
	//文章service
    @Autowired
    protected ArticleService articleService;

	//公告service
	@Autowired
	protected NoticeService noticeService;
    
    //友情链接service
    @Autowired
    protected FriendlyLinkService friendlyService;
	
    //文章评论service
	@Autowired
	protected ArticleCommentService articleCommentService;
	
	//文章标签service
	@Autowired
	protected ArticleTagService articleTagService;
	
	//导航分类service
    @Autowired
    protected ArticleCategoryService articleCategoryService;
    
	//友情链接Service
	@Autowired
	protected FriendlyLinkService friendlyLinkService;
    
	//用户Service
	@Autowired
	protected UserService userService;

	//管理员
	@Autowired
	protected AdminService adminService;
	
	//文章分类Service
	@Autowired
	protected ArticleCategoryService articleCateService;
	
	//文章分类Service
	@Autowired
	protected ExtendPageService extendPageService;
    
}
