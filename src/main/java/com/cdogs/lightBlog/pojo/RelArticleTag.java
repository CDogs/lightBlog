package com.cdogs.lightBlog.pojo;

/**
 * 
 * 文章标签关系表
 * 
 * @author CDogs
 * @version [版本，2016-7-8]
 */
public class RelArticleTag {
    
    //ID
    private Integer id;
    
    //article ID 
    private Integer articleId;
    
    //tag ID
    private Integer tagId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }
    
}
