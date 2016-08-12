package com.cdogs.lightBlog.service;



import com.cdogs.lightBlog.dto.ArticleCategoryDto;
import com.cdogs.lightBlog.pojo.ArticleCategory;

import java.util.List;

public interface ArticleCategoryService {
	
	/**
	 * 获取所有的文章分类
	 * 
	 * @return List<NavigatorCategory>
	 */
	List<ArticleCategory> getAllArtiCategorys();
	
    /**
     * 
     * 检索分类列表信息并统计文章数量
     * @param user
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<ArticleCategoryDto> getArtCatesAndCount();
    
    /**
     * 插入新分类
     * @param category
     * @return boolean
     */
    public boolean addCategory(ArticleCategory category);
    
    /**
     * 删除分类
     * @param category
     * @return boolean
     */
    public boolean deleteCategory(ArticleCategory category);
    
    /**
     * 更新分类
     * @param category
     * @return boolean
     */
    public boolean updateCategory(ArticleCategory category);
    
}
