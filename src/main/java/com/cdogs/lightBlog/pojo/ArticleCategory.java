package com.cdogs.lightBlog.pojo;
// default package


/**
 * 
 * 文章分类
 * 
 * @author  CDogs
 */
public class ArticleCategory {
    
    //ID
    private Integer id;
    
    //名称
    private String name;
    
    //描述
    private String description;
    
    //排序字段
    private Integer sort;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
    
}