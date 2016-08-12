package com.cdogs.lightBlog.service.impl;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import com.cdogs.lightBlog.dto.ArticleDto;
import com.cdogs.lightBlog.dto.Page;
import com.cdogs.lightBlog.dto.PageResult;
import com.cdogs.lightBlog.pojo.Article;
import com.cdogs.lightBlog.pojo.ArticleTag;
import com.cdogs.lightBlog.pojo.User;
import com.cdogs.lightBlog.service.ArticleService;
import com.cdogs.lightBlog.service.BaseService;
import com.cdogs.lightBlog.util.HTMLUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 博文服务实现类
 * @author CDogs
 */
@Service
public class ArticleServiceImpl  extends BaseService
	implements ArticleService {
    
    /**
     * 获取文章（带检索，id,title,content,category,createTime)
     * 重载方法
     * @param article
     * @param page
     * @return
     * @throws Exception
     */

    public PageResult<ArticleDto> getArticles(Article article, Page page)
    		throws Exception {
        
    	PageResult<ArticleDto> result = new PageResult<ArticleDto>();
        Map<String, Object> param = new HashMap<String, Object>();

        //设置文章参数
        if (article != null) {
            param.put("id", article.getId());
            param.put("title", article.getTitle());
            param.put("content", article.getContent());
            param.put("category", article.getCategory());
            param.put("createTime", article.getCreateTime());            
        }
        //检索（合适）文章总数
        int totalRows = articleDao.countOfArticles(param);
        
        if (totalRows > 0) {
        
        	page.setTotalRows(totalRows);
        	page.repaginate();       	
        	//设置分页参数
        	param.put("pageNo", page.getStartNum()-1);
        	param.put("pageSize", page.getPageSize());

			//检索（合适）文章
        	List<ArticleDto> articles = articleDao.selectArticles(param);     
        	//获取文章标签
        	if (articles != null) {
        		for (ArticleDto art : articles) {
        			param.put("articleId", art.getId());
        			// 此处对页面输出作字符限制
        			//String content = HTMLUtils.html2Text(art.getContent());
					String content = art.getContent();
        			content = content.length() > 500 ? content.substring(0, 500) : content;   					
        			art.setContent(content + " ..." );
					//检索文章标签
        			List<ArticleTag> tags = articleTagDao.queryArticleTags(param);
        			if (tags != null) {
        				art.setTags(tags);
        			}
        		}
        	}
        	page.repaginate();
        	result.setPage(page);
        	result.setList(articles);
        }
        
        return result;
    }
    
    /**
     * 添加文章
     * 重载方法
     * @param article
     * @return
     * @throws Exception
     */

	@Transactional
    public boolean addArticle(Article article)
        throws Exception {
    	//添加文章
        int result = articleDao.addArticle(article);       
        return (result > 0) ;
    }
    
    /**
     * 更新文章
     * 重载方法
     * @param article
     * @return
     * @throws Exception
     */

    public boolean updateArticle(Article article)
        throws Exception {
        
        int result = articleDao.updateArticle(article);
        return (result > 0);
    }

	/**
	 * 按用户查找文章
	 * @param article
	 * @param page
	 * @return
	 */
	public PageResult<ArticleDto> getArticlesByUser(User user,Article article, Page page)
			throws Exception{
		PageResult<ArticleDto> result = new PageResult<ArticleDto>();
		Map<String, Object> param = new HashMap<String, Object>();

		//设置文章参数
		//if (article != null) {
			//param.put("id", article.getId());
			//param.put("title", article.getTitle());
			//param.put("content", article.getContent());
			//param.put("category", article.getCategory());
			//param.put("createTime", article.getCreateTime());
			param.put("authorId", user.getId());
		//}
		//检索（合适）文章总数
		int totalRows = articleDao.countOfArticles(param);
		System.out.println("该用户有" + totalRows + "篇文章");

		if (totalRows > 0) {

			page.setTotalRows(totalRows);
			page.repaginate();
			//设置分页参数
			param.put("pageNo", page.getStartNum()-1);
			param.put("pageSize", page.getPageSize());

			System.out.println("/getArticlesByUser " + user.getId() );
			//检索（合适）文章
			List<ArticleDto> articles = articleDao.selectArticlesByUser(param);
			//获取文章标签
			if (articles != null) {
				for (ArticleDto art : articles) {
					param.put("articleId", art.getId());
					// 此处对页面输出作字符限制
					String content = HTMLUtils.html2Text(art.getContent());
					content = content.length() > 500 ? content.substring(0, 500) : content;
					art.setContent(content + " ..." );
					//检索文章标签
					List<ArticleTag> tags = articleTagDao.queryArticleTags(param);
					if (tags != null) {
						art.setTags(tags);
					}
				}
			}
			page.repaginate();
			result.setPage(page);
			result.setList(articles);
		}

		return result;
	}

	/**
     * 
     * 重载方法
     * @param article
     * @return
     * @throws Exception
     */

    public ArticleDto getArticle(Article article)
        throws Exception {   
    	System.out.println("正在执行getArticle方法...");
		System.out.println(article.getId());
    	ArticleDto art = articleDao.selectArticle(article);
        Map<String, Object> param = new HashMap<String, Object>();
		param.put("articleId", art.getId());
		List<ArticleTag> tags = articleTagDao.queryArticleTags(param);
		if (tags != null) {
			art.setTags(tags);
		}
        return art;
    }
    
    /**
     * 获取热门文章列表
     */

	public List<ArticleDto> getHotArticles() throws Exception {
		return articleDao.selectHotArticles();
	}
	
	/**
	 * 获取归档列表
	 */

	public List<ArticleDto> getArchiveByTime() throws Exception {
		return articleDao.selectArchiveByTime();
	}
	
	/**
	 * 根据时间段获取文章
	 */

	public List<ArticleDto> getArticlesByTime(String dateTime, String timeType)
			throws Exception {
        Map<String, Object> param = new HashMap<String, Object>();
		param.put("time", dateTime);
		param.put("type", timeType);
		//return articleDao.selectArticlesByTime(param);


		//修改后，加载标签，修剪文章内容
		List<ArticleDto> articles = articleDao.selectArticlesByTime(param);
		//获取文章标签
		if (articles != null) {
			for (ArticleDto art : articles) {
				param.put("articleId", art.getId());
				// 此处对页面输出作字符限制
				//String content = HTMLUtils.html2Text(art.getContent());
				String content = art.getContent();
				content = content.length() > 200 ? content.substring(0, 200) : content;
				art.setContent(content + " ..." );
				//检索文章标签
				List<ArticleTag> tags = articleTagDao.queryArticleTags(param);
				if (tags != null) {
					art.setTags(tags);
				}
			}
		}

		return articles;
	}
	
	/**
	 * 通过标签检索文章
	 */

	public PageResult<ArticleDto> getArticlesByTag(Page page, Integer tagId)
			throws Exception {
		
    	PageResult<ArticleDto> result = new PageResult<ArticleDto>();
        Map<String, Object> param = new HashMap<String, Object>();
        List<ArticleDto> data = null;
        param.put("tagId", tagId);
        int totalRows = articleDao.countOfArticleByTag(param);
        
        if (totalRows > 0) {
        
        	page.setTotalRows(totalRows);
        	page.repaginate();
        	
        	//设置分页参数
        	param.put("pageNo", page.getStartNum()-1);
        	param.put("pageSize", page.getPageSize());
        	data = articleDao.selectArticlesByTag(param);

			//获取文章标签
			if (data != null) {
				for (ArticleDto art : data) {
					param.put("articleId", art.getId());
					// 此处对页面输出作字符限制
					//String content = HTMLUtils.html2Text(art.getContent());
					String content = art.getContent();
					content = content.length() > 200 ? content.substring(0, 200) : content;
					art.setContent(content + " ..." );
					//检索文章标签
					List<ArticleTag> tags = articleTagDao.queryArticleTags(param);
					if (tags != null) {
						art.setTags(tags);
					}
				}
			}

        	result.setPage(page);
        	result.setList(data);
        }
		return result;
	}

	/**
	 * 统计文章数量
	 * @return
	 * @throws Exception
	 */

	public int getCountOfAllArticles() throws Exception {
		return articleDao.countOfArticles(null);
	}
	
	/**
	 * 用key去匹配名称和ID
	 */

	public List<ArticleDto> searchArticle(String key) throws Exception {
		
        Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", key);
		param.put("name", "%"+key+"%");
		return articleDao.searchArticle(param);
	}
    
}
