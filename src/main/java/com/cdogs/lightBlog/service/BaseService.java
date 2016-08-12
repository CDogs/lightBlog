package com.cdogs.lightBlog.service;


import com.cdogs.lightBlog.dao.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * 基础的Service
 * @author CDogs
 */
public class BaseService {
	
	@Autowired
	protected ArticleCategoryDao categoryDao;
	
	@Autowired
	protected ArticleCommentDao articleCommentDao;
	
    @Autowired
    protected ArticleDao articleDao;
    
	@Autowired
	protected ArticleTagDao articleTagDao;
	
	@Autowired
	protected ExtendPageDao extendPageDao;
	
	@Autowired
	protected FriendlyLinkDao friendlyLinkDao;
	
	@Autowired
	protected UserDao userDao;

	@Autowired
	protected AdminDao adminDao;

	@Autowired
	protected NoticeDao noticeDao;
}
