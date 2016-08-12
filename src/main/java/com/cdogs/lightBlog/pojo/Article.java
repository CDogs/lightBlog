package com.cdogs.lightBlog.pojo;
// default package

import java.util.Date;

/**
 * 
 * Article POJO
 * 
 * @author  CDogs
 */
public class Article {

    //ID
	private Integer id;
	
	//文章内容
	private String content;
	
	//文章标题
	private String title;
	
	//文章分类
	private Integer category;
	
	//创建时间
	private Date createTime;
	
	//是否已删除,默认0表未删除,1表示删除
	private Integer deleted;
	
	//阅读数量
	private Integer readCount;

    //作者id
    private Integer authorId;
	
	
    public Article() {
		super();
	}

	public Article(Integer id) {
		super();
		this.id = id;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public Integer getReadCount() {
        return readCount;
    }

    public void setReadCount(Integer readCount) {
        this.readCount = readCount;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }
}