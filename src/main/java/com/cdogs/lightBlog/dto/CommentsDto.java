package com.cdogs.lightBlog.dto;


import com.cdogs.lightBlog.pojo.ArticleComment;

import java.util.List;

/**
 * 评论数据
 * @author CDogs
 *
 */
public class CommentsDto {
	
	//源评论
	private ArticleComment comment;
	//评论回复
	private List<ArticleComment> feedBack;
	
	public ArticleComment getComment() {
		return comment;
	}
	
	public void setComment(ArticleComment comment) {
		this.comment = comment;
	}
	
	public List<ArticleComment> getFeedBack() {
		return feedBack;
	}
	
	public void setFeedBack(List<ArticleComment> feedBack) {
		this.feedBack = feedBack;
	}

}
