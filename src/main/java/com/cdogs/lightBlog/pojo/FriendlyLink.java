package com.cdogs.lightBlog.pojo;

/**
 * 
 * 友情链接
 * 
 * @author  CDogs
 */
public class FriendlyLink {
    
    //ID
    private Integer id;
    
    //链接名称
    private String name;
    
    //链接地址
    private String link;

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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

}
