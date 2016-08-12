package com.cdogs.lightBlog.dto;


import java.util.Date;

/**
 * 
 * Notice DTO
 * 
 * @author  CDogs
 */
public class NoticeDto {

    //ID
	private Integer id;
	
	//文章内容
	private String content;
	
	//文章标题
	private String title;

	//创建时间
	private Date createTime;
	
	//是否已删除
	private Integer deleted;
	
	//作者ID
	private Integer authorId;
	
	//作者名字
	private String authorName;

	//统计字段
	private Integer count;

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

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }
}