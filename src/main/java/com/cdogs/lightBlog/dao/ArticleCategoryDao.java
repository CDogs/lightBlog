package com.cdogs.lightBlog.dao;

import com.cdogs.lightBlog.dto.ArticleCategoryDto;
import com.cdogs.lightBlog.pojo.ArticleCategory;

import java.util.List;

/**
 * 文章分类Dao
 * 
 * @author  CDogs
 */
public interface ArticleCategoryDao {
    
    /**
     * 
     * 检索分类列表信息
     * @param
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<ArticleCategory> selectArticleCategorys();
    
    /**
     * 
     * 检索分类列表信息并统计文章数量
     * @param
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<ArticleCategoryDto> selectArtCatesAndCount();
    
    /**
     * 插入新分类
     * @param category
     * @return int
     */
    public int insertCategory(ArticleCategory category);
    
    /**
     * 删除分类
     * @param category
     * @return int
     */
    public int deleteCategory(ArticleCategory category);
    
    /**
     * 更新分类
     * @param category
     * @return int
     */
    public int updateCategory(ArticleCategory category);
}
