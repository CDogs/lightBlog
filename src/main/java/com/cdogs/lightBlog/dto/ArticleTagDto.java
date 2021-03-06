package com.cdogs.lightBlog.dto;

/**
 * 文章标签
 * 
 * @author  CDogs
 */
public class ArticleTagDto {
    
    //ID
    private Integer id;
    
    //标签名称
    private String name;
    
    //描述
    private String description;
    
    //文章数量
    private Integer count;

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

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

}
