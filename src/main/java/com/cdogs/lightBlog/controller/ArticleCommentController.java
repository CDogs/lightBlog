package com.cdogs.lightBlog.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.cdogs.lightBlog.auth.AuthPassport;
import com.cdogs.lightBlog.dto.CommentsDto;
import com.cdogs.lightBlog.dto.Page;
import com.cdogs.lightBlog.dto.PageResult;
import com.cdogs.lightBlog.pojo.ArticleComment;
import com.cdogs.lightBlog.util.Configer;
import com.cdogs.lightBlog.util.JSONUtils;
import com.cdogs.lightBlog.util.RegexpCheckUtils;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Iterator;


/**
 * 文章评论控制器
 * @author CDogs
 *
 */
@Controller
@RequestMapping("/comment")
public class ArticleCommentController extends BaseController {
	
	/**
	 * 添加新的文章评论
	 * 暂时未添加邮件回复提醒
	 * @param comment
	 * @return String
	 */
	@AuthPassport
	@RequestMapping("/add")
	@ResponseBody
	public String addArticleComment(ArticleComment comment,
			HttpServletRequest request) {


		try {
			System.out.println("执行addArticleComment()");
			//如果博客地址不为空
			if(!comment.getBlogUrl().isEmpty()) {				
				if (!RegexpCheckUtils.checkWebSite(comment.getBlogUrl())) {
					return "INVALIDE_URL";
				}
			}
			//如果用户名为空，则默认为匿名
			if (comment.getUserName().isEmpty()) comment.setUserName("匿名");
			//去除内容里面的html标签元素
			comment.setContent(RegexpCheckUtils.htmlRemoveTag(comment.getContent()));
			//如果成功添加文章，则返回true
			if (articleCommentService.addComment(comment)) { 
				
/*				String domain = Configer.getInstance().getProperty("blog_domain");
				// 发送评论邮件
				String readUrl = "";
				if (!domain.equals("")) {
					readUrl = "http://"+domain+"/article/read.htm?id="+
							comment.getArticleId();
				} else {
					readUrl = request.getScheme()+"://"+
							request.getServerName()+":"+request.getServerPort()+
							request.getContextPath()+"/article/read.htm?id="+
							comment.getArticleId();					
				}
				new Email(comment, readUrl).start();
				*/

				return SUCCESS; 
			}						
		} catch (Exception e) { 
            LOGGER.error("ArticleController.addArticleComment();", e.getMessage());
		}	
		return FAIL;
	}
	
    /**
     * 
     * 获取所有的评论，按照时间降序排列
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/load")
	@ResponseBody
    public String getArticleComments(@RequestBody String bodyString) {

		System.out.println(bodyString);
		JSONObject json = JSONObject.fromObject(bodyString);
		Integer articleId = json.getInt("articleId");
		Page page = (Page) JSONUtils.String2Object(json.getString("page").toString(), Page.class);
		System.out.println("执行getArticleComments() == articleId=" + articleId + " currentPage=" + page.getCurrentPage() + " pageSize=" + page.getPageSize());
        //ModelAndView response = new ModelAndView("/comment");
        PageResult<CommentsDto> comments = null;
        try {  
            comments = articleCommentService.getArticleComment(articleId, page);
        } catch (Exception e) {
            LOGGER.error("ArticleController.getArticleComments();", e.getMessage());
        }
		if(comments == null) return JSONUtils.toJSONObjectString("ERROR");

        //response.addObject("comments", comments.getList());
        //response.addObject("page", comments.getPage());
		//System.out.println(JSONUtils.toJSONObjectString(comments));
        return JSONUtils.toJSONObjectString(comments);
		//return comments;
    }



	/**
	 * 
	 * @param comment
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public String deleteTag(ArticleComment comment) {
		
		try {
			if (articleCommentService.deleteComment(comment)) {
				return SUCCESS;
			}
		} catch (Exception e) {
			LOGGER.error("ArticleTagController.deleteTag()",e.getMessage());
		}
		return FAIL;
	}
	
	/**
	 * 发送邮件服务
	 * @author Ziv
	 */
	private class Email extends Thread {	
		
		private ArticleComment comment;
		private String readUrl;
	
		public Email(ArticleComment comment, String readUrl) {
			super();
			this.comment = comment;
			this.readUrl = readUrl;
		}

		@Override
		public void run() {
			try {
				articleCommentService.notifyByEmail(comment, readUrl);
			} catch (Exception e) {
				LOGGER.error("ArticleController.Email.send();", e.getMessage());
			}
		}		
	}
}
