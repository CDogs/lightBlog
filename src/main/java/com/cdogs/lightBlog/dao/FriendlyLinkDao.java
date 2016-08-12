package com.cdogs.lightBlog.dao;



import com.cdogs.lightBlog.pojo.FriendlyLink;

import java.util.List;

/**
 * 用户Dao
 * 
 * @author  CDogs
 */
public interface FriendlyLinkDao {
    
    /**
     * 
     * 检索所以友情链接
     * @return List<FriendlyLink>
     */
    List<FriendlyLink> getFriendlyLinks();
    
    /**
     * 添加友情链接
     * @param friendlyLink
     * @return int 操作成功量
     */
    int addFriendlyLink(FriendlyLink friendlyLink);
    
    /**
     * 更新友情链接
     * @param friendlyLink
     * @return int 操作成功量
     */
    int updateFriendLink(FriendlyLink friendlyLink);
    
    /**
     * 删除友情链接
     * @param friendlyLink
     * @return int 操作成功量
     */
    int deleteFriendLink(FriendlyLink friendlyLink);
}
