package com.cdogs.lightBlog.dao;

import com.cdogs.lightBlog.pojo.ArticleComment;

import java.util.List;
import java.util.Map;

/**
 * 
 * 文章评论Dao
 * 
 * @author  CDogs
 */
public interface ArticleCommentDao {
    
    /**
     * 查询某文章的评论信息
     * @param param
     * @return List<ArticleComment>
     * @see [类、类#方法、类#成员]
     */
    public List<ArticleComment> getArticleComments(Map<String, Object> param);
    
    /**
     * 根据ID获取评论
     * @param id
     * @return ArticleComment
     */
    public ArticleComment getCommentById(Integer id);
    
    /**
     * 添加文章评论
     * @param comment
     * @return int
     */
    public int insertArticleCommment(ArticleComment comment);
    
    /**
     * 删除文章评论
     * @param comment
     * @return int
     */
    public int deletArticleComment(ArticleComment comment);
    
    /**
     * 统计某文章的评论数量
     * @param
     * @return int
     */
    public int countOfArticleComments(Map<String, Object> param);
    
}
