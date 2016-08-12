package com.cdogs.lightBlog.pojo;

/**
 * 
 * Admin POJO
 * 
 * @author  CDogs
 */
public class Admin {
    
    //ID
    private Integer id;
    
    //用户名
    private String name;
    
    //密码
    private String password;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}