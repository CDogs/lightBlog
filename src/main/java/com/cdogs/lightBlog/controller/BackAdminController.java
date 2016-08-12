package com.cdogs.lightBlog.controller;



import com.cdogs.lightBlog.dto.ArticleDto;
import com.cdogs.lightBlog.dto.Page;
import com.cdogs.lightBlog.dto.PageResult;
import com.cdogs.lightBlog.pojo.Article;
import com.cdogs.lightBlog.pojo.ArticleCategory;
import com.cdogs.lightBlog.pojo.ArticleTag;
import com.cdogs.lightBlog.pojo.FriendlyLink;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 后台管理
 * @author CDogs
 */
@Controller
@RequestMapping("/admin")
public class BackAdminController extends BaseController {
    
	/**
	 * 初始化管理页面首页
	 * @return
	 */
	@RequestMapping("/ucenter")
	public ModelAndView initAdminPage() {

		System.out.println("/ucenter");
		ModelAndView response = new ModelAndView("/admin/index");
		List<ArticleCategory> artCates = null;
        //标签列表
        List<ArticleTag> tags = null;
		try {
			tags = articleTagService.getAllTags();
			artCates = articleCategoryService.getAllArtiCategorys();	
		} catch (Exception e) {
			LOGGER.error("AdminController.initAdminPage()",e.getMessage());
		}
		response.addObject("artCates", artCates);
		response.addObject("tags", tags); 
		
		return response;		
	}
	
	/**
	 * 管理所有文章
	 * @return
	 */
	@RequestMapping("/articles")
	public ModelAndView initMageArticles(Page page, Integer cat) {
        
        ModelAndView response = new ModelAndView("/admin/articles");
        PageResult<ArticleDto> data = null;
        Article article = null;
        
        if (page == null) {
            page = new Page();
            page.setCurrentPage(1);
            page.setTotalRows(10);
        }
        
        if (cat != null) {
            article = new Article();
            article.setCategory(cat);
        }
        
        try {
        	//获取文章
        	data = articleService.getArticles(article, page);	
            if (data != null) {                
                response.addObject("articles", data.getList()); 
                response.addObject("page", data.getPage());
            }
        } catch (Exception e) {
           LOGGER.error("AdminController." +
           		"initMageArticles();", e.getMessage());
        }
        
        return response;		
	}
	
	/**
	 * 初始化管理页面首页
	 * @return ModelAndView
	 */
	@RequestMapping("/frlink")
	public ModelAndView initFriendlyLinkPage() {
		
		ModelAndView response = new ModelAndView("/admin/friendly_link");
        List<FriendlyLink> links = null;
		try {
            links = friendlyLinkService.getFriendlyLinks();
		} catch (Exception e) {
			LOGGER.error("AdminController.initFriendlyLinkPage()",e.getMessage());
		}
		response.addObject("links", links);	
		return response;	
	}
	
	/**
	 * 文章标签管理页面初始化
	 * @return
	 */
	@RequestMapping("/tags")
	public ModelAndView initArticleTagsPage() {
		
		ModelAndView response = new ModelAndView("/admin/article_tags");
		//文章标签
	    List<ArticleTag> tags = null;
		try {
			tags = articleTagService.getAllTags();
		} catch (Exception e) {
			LOGGER.error("AdminController.initArticleTagsPage()",e.getMessage());
		}
		response.addObject("tags", tags);	
		return response;	
	}
	
	/**
	 * 文章分类管理页面初始化
	 * @return
	 */
	@RequestMapping("/categorys")
	public ModelAndView initArticleCatesPage() {
		
		ModelAndView response = new ModelAndView("/admin/article_categorys");
		//文章标签
	    List<ArticleCategory> categorys = null;
		try {
			categorys = articleCateService.getAllArtiCategorys();
		} catch (Exception e) {
			LOGGER.error("AdminController.initArticleTagsPage()",e.getMessage());
		}
		response.addObject("categorys", categorys);	
		return response;	
	}

	/**
	 * 管理员主页初始化
	 * @return
	 */
	@RequestMapping("/admin_index")
	public ModelAndView initAdminIndex(Page page) {

		ModelAndView response = new ModelAndView("/admin/admin_index");
		//文章Page
		PageResult<ArticleDto> data = null;
		//文章实例
		Article article = null;
		page.setPageSize(5);

		//获取分页数据
		try {
			data = articleService.getArticles(article, page);
		} catch (Exception e) {
			LOGGER.error("BackAdminController." +
					"initAdminIndex();", e.getMessage());
		}
		//设置page并重新分页
		response.addObject("articles", data.getList());
		response.addObject("page", data.getPage());

		return response;
	}

}
