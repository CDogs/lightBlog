package com.cdogs.lightBlog.controller;

import com.cdogs.lightBlog.auth.AuthPassport;
import com.cdogs.lightBlog.constants.Common;
import com.cdogs.lightBlog.dto.ArticleCategoryDto;
import com.cdogs.lightBlog.dto.ArticleDto;
import com.cdogs.lightBlog.dto.Page;
import com.cdogs.lightBlog.dto.PageResult;
import com.cdogs.lightBlog.pojo.Article;
import com.cdogs.lightBlog.pojo.ArticleTag;
import com.cdogs.lightBlog.pojo.ExtendPage;
import com.cdogs.lightBlog.pojo.FriendlyLink;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 博文管理控制
 * @author CDogs
 */
@Controller
@RequestMapping("/article")
public class ArticleController extends BaseController {

    /**
     * 
     * 获取所有的文章列表，按照时间降序排列
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/index")
    public ModelAndView getArticles(Page page, Integer cat) {
        
        ModelAndView response = new ModelAndView("/index");
        //文章Page
        PageResult<ArticleDto> data = null;
        //文章实例
        Article article = null;    
        
        if( page.getCurrentPage() == 1) {
            page.setPageSize(5);
            page.repaginate();           
        }
        
        //如果分类ID不为空的话，那么按文章分类检索文章，跳转至article_list页面
        if (cat != null) {
        	//改变分页数量
        	page.setPageSize(15);
            article = new Article();
            article.setCategory(cat);
            response = new ModelAndView("/article_list");
            //参数带至跳转页面
            response.addObject("catId", cat);
            String action = "/article/index.htm";
            response.addObject("action", action);  
        }

        //获取分页数据
        try {
        	data = articleService.getArticles(article, page);
        } catch (Exception e) {
           LOGGER.error("ArticleController." +
           		"getArticles();", e.getMessage());
        }
        //设置page并重新分页   
        response.addObject("articles", data.getList());    
        response.addObject("page", data.getPage());        
        //添加内容导航列表
        addContentNavList(response);
        
        return response;
    }
    
    
    /**
     * 
     * 获取所有的文章列表，按照时间降序排列
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/list")
    public ModelAndView articleList(Page page, Integer tagId) {
        
        ModelAndView response = new ModelAndView("/article_list");
        PageResult<ArticleDto> data = null;

        if( page.getCurrentPage() == 1) {
            page.setPageSize(15);
            page.repaginate();           
        }
        
        try {
        	if (tagId != null) {
        		data = articleService.getArticlesByTag(page, tagId);
        	} else {        		
        		data = articleService.getArticles(null, page);
        	}
        } catch (Exception e) {
           LOGGER.error("ArticleController." +
           		"articleList();", e.getMessage());
        }
        
        response.addObject("articles", data.getList());
        response.addObject("page", data.getPage());
        response.addObject("tagId", tagId);
        String action = "/article/list.htm";
        response.addObject("action", action);  
        //添加内容导航列表
        addContentNavList(response);
        return response;
    }
    
    /**
     * /search-archives?time=2016-07-12
     * 检索当前月份的文章列表
     * @param time
     * @return
     */
    @RequestMapping("/search-archives")
    public ModelAndView searchByMouth(String time) {
        
        ModelAndView response = new ModelAndView("/article_list");
        List<ArticleDto> data = null;

        try {
        	//检索当月文章列表
        	data = articleService.getArticlesByTime(time, Common.BY_MOUTH);
        } catch (Exception e) {
           LOGGER.error("ArticleController." +
           		"searchByTime();", e.getMessage());
        }
       
        response.addObject("articles", data);
        //添加内容导航列表
        addContentNavList(response);
        return response;
    }

    /**
     * /read?id=3
     * 获取具体的文章
     * @return
     * @see [类、类#方法、类#成员]
     */

    @RequestMapping("/read")
    public ModelAndView getArticle(int id, String flag) {

        System.out.println(id);
        ModelAndView response = new ModelAndView("/article_detail");
        ArticleDto article = null;
        Article param = new Article();
        //其他页面
        List<ExtendPage> pages = null;
        try {
            System.out.println("=============");
        	pages = extendPageService.getAllPages();
        	param.setId(id);
            System.out.println("=============");
        	article = articleService.getArticle(param);           
            //更新浏览次数
        	if (article != null) {
        		param.setReadCount(article.getReadCount()+1);
        		articleService.updateArticle(param);      		
        	}

        } catch (Exception e) {
            LOGGER.error("ArticleController.getArticle();", e.getMessage());
        }
        System.out.println(article);
        if (article != null && pages != null) {        	
        	response.addObject("article", article);    
        	response.addObject("pages", pages); 
        }
        response.addObject("flag", flag); 
        //添加内容导航列表
        addContentNavList(response);

        System.out.println(article.getAuthorName());
        return response;
    }
    
    /**
     * /search?key=4
     * 根据关键字检索文章
     * @param key
     * @return
     */
    @RequestMapping("/search")
    public ModelAndView searchByKey(String key) {
        
        ModelAndView response = new ModelAndView("/article_list");
        List<ArticleDto> data = null;

        try {
        	//检索当月文章列表
        	data = articleService.searchArticle(key);
        } catch (Exception e) {
           LOGGER.error("ArticleController." +
           		"searchByKey();", e.getMessage());
        }
        if (data != null) {
        	response.addObject("articles", data);        	
        }
        response.addObject("label", "搜索结果");
        //添加内容导航列表
        addContentNavList(response);
        return response;
    }

    /**
     * 添加内容导航列表
     * @param response
     */
    public void addContentNavList(ModelAndView response) {
        //热门文章列表,归档列表
        List<ArticleDto> hotArticles = null,archives = null;
        //友情链接
        List<FriendlyLink> links = null;
        //分类列表
        List<ArticleTag> tags = null;
        //文章分类列表
        List<ArticleCategoryDto> categorys = null;
        //其他页面
        List<ExtendPage> pages = null;
        int articleCount = 0;
        try {
        	links = friendlyService.getFriendlyLinks();
        	tags = articleTagService.getAllTags();
        	hotArticles = articleService.getHotArticles();
        	archives = articleService.getArchiveByTime();
        	articleCount = articleService.getCountOfAllArticles();
        	categorys = articleCategoryService.getArtCatesAndCount();
        	pages = extendPageService.getAllPages();

        } catch (Exception e) {
           LOGGER.error("ArticleController." +
           		"getArticles();", e.getMessage());
        }
        response.addObject("links", links);  
        response.addObject("countOfAllArticles", articleCount);  
        response.addObject("hotArticles", hotArticles);  
        response.addObject("archives", archives);  
        response.addObject("categorys", categorys); 
        response.addObject("pages", pages); 
        response.addObject("tags", tags); 
    }
         
}
