package com.cdogs.lightBlog.controller;

import java.util.Date;
import java.util.List;

import com.cdogs.lightBlog.constants.Common;
import com.cdogs.lightBlog.dto.ArticleDto;
import com.cdogs.lightBlog.pojo.Article;
import com.cdogs.lightBlog.pojo.ArticleCategory;
import com.cdogs.lightBlog.pojo.ArticleTag;
import com.cdogs.lightBlog.pojo.RelArticleTag;
import com.cdogs.lightBlog.util.JSONUtils;
import net.sf.json.JSONObject;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 后台文章管理
 * @author CDogs
 */
@Controller
@RequestMapping("/user/article")
public class BackArticleController extends BaseController {

    /**
     * (json数据：{"article":{"category":"4","title":"4","content":"4"},"tag":[1,3]}）
     * 添加文章
     * @return
     * @see [类、类#方法、类#成员]
     */
	@SuppressWarnings("unchecked")
	@ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public String addArticle(@RequestBody String bodyString,HttpServletRequest request) {
    	
        try {
			System.out.println(bodyString);
        	JSONObject json = JSONObject.fromObject(bodyString);
        	Article article = (Article) JSONUtils.String2Object(json.get("article").toString(), Article.class);
        	System.out.println(json);
			System.out.println(article);
			System.out.println(article.getContent());
            //参数正常
			Integer UserID = (Integer) request.getSession().getAttribute("UserID");
			System.out.println(UserID);
			article.setAuthorId(UserID);
            if (!article.getTitle().isEmpty() && !article.getContent().isEmpty()
                && article.getCategory() != null) {
                //如果文章添加ok,继续添加文章标签

                if (articleService.addArticle(article)) {
                	List<Integer> tags  = (List<Integer>) JSONUtils.String2Object(json.get("tag").toString(), List.class);
	        		if (tags != null && tags.size() > 0) {    		
	        			//保存书签
                        System.out.println("===tags:===="+tags+"=======");
	        			articleTagService.saveRelArticleTag(article, tags);
	        		}
					ArticleDto articleDto = articleService.getArticle(article);
                	return JSONUtils.toJSONObjectString(articleDto);
                }      
            } 

        } catch (Exception e) {
            LOGGER.error("ArticleController.addArticle();", e);
        }
        
        return FAIL;
    }
    
	/**
     * 返回数据：artCates,tags,tagJson,article
	 * 初始化更新文章
	 * @param article
	 * @return
	 */
	@RequestMapping("/init-edit")
	public ModelAndView initEditPage(Article article) {
		
		ModelAndView response = new ModelAndView("/admin/article_edit");
		//导航
		List<ArticleCategory> artCates = null;
        //标签列表
        List<ArticleTag> tags = null;
        //文章对象
        ArticleDto articleDto = null;
        
		try {
			articleDto = articleService.getArticle(article);
			tags = articleTagService.getAllTags();
			artCates = articleCategoryService.getAllArtiCategorys();	
		} catch (Exception e) {
			LOGGER.error("ArticleController.initEditPage()",e.getMessage());
		}
		response.addObject("artCates", artCates);
		response.addObject("tags", tags); 
		response.addObject("tagJson", JSONUtils.toJSONString(articleDto.getTags()));
		response.addObject("article", articleDto); 
		
		return response;
		
	}
		
    /**
     * json数据：{"article":{"id":"1","category":"3","title":"777","content":"777"},"tag":[1,3]}
     * 更新文章
     * @return
     * @see [类、类#方法、类#成员]
     */
    @SuppressWarnings("unchecked")
	@ResponseBody
    @RequestMapping("/update")
    public String updateArticles(@RequestBody String bodyString) {
           	  	   	  	
        try {
            System.out.println(bodyString);
        	JSONObject json = JSONObject.fromObject(bodyString);
        	Article article = (Article) JSONUtils.String2Object(
        			json.get("article").toString(), Article.class);
            System.out.println(article);
            //参数正常
            if (article.getId() != null) {
                //如果更新成功,返回SUCCESS
                if (articleService.updateArticle(article)) { 
                	
                	//如果执行的不是删除操作
                	RelArticleTag rel = new RelArticleTag();
                	rel.setArticleId(article.getId());
                	//先清除之前的文章标签关系
                	articleTagService.deleteArticleTag(rel);
                	
                	//如果当前操作非删除操作，则重新更新当前标签关系
                	if (article.getDeleted() != Common.IS_DELETE) {
                		//JSON数据转换
                		List<Integer> tags = (List<Integer>) JSONUtils.String2Object(
                				json.get("tag").toString(), List.class);
                		//如果标签数据不为空
                		if (tags != null && tags.size() > 0 ) {             		
                			//然后插入最新的文章标签
                			articleTagService.saveRelArticleTag(article, tags);               		
                		}                		
                	}
                	return SUCCESS; 
                }   
            }
            
        } catch (Exception e) {
                LOGGER.error("ArticleController.addArticle();", e.getMessage());
        }
        //返回FAIL
        return FAIL;
    }
    
    /**
     * 根据关键字检索文章
     * @param key
     * @return
     */
    @RequestMapping("/search")
    public ModelAndView searchByKey(String key) {
        
        ModelAndView response = new ModelAndView("/admin/articles");
        List<ArticleDto> data = null;

        try {
        	//检索当月文章列表
        	data = articleService.searchArticle(key);
        } catch (Exception e) {
           LOGGER.error("ArticleController." +
           		"searchByKey();", e.getMessage());
        }
       
        response.addObject("articles", data);
        return response;
    }
       
}
