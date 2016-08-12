package com.cdogs.lightBlog.service.impl;



import com.cdogs.lightBlog.pojo.FriendlyLink;
import com.cdogs.lightBlog.service.BaseService;
import com.cdogs.lightBlog.service.FriendlyLinkService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 友情链接实现类
 * @author CDogs
 */
@Service
public class FriendlyLinkServiceImpl extends BaseService
	implements FriendlyLinkService {

	/**
	 * 获取友情链接
	 */

	public List<FriendlyLink> getFriendlyLinks() {
		return friendlyLinkDao.getFriendlyLinks();
	}

	/**
	 * 添加友情链接
	 */

	public boolean addFriendlyLink(FriendlyLink friendlyLink) {     
        int result = friendlyLinkDao.addFriendlyLink(friendlyLink);
		return (result > 0);
	}

	/**
	 * 更新友情链接
	 */

	public boolean updateFriendLink(FriendlyLink friendlyLink) {
        int result = friendlyLinkDao.updateFriendLink(friendlyLink);
        return (result > 0);
	}

	/**
	 * 删除友情链接
	 */

	public boolean deleteFriendLink(FriendlyLink friendlyLink) {
        int result = friendlyLinkDao.deleteFriendLink(friendlyLink);
        return (result > 0);
	}
    
}
