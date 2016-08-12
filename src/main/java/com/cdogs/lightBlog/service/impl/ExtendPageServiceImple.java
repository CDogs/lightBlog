package com.cdogs.lightBlog.service.impl;



import com.cdogs.lightBlog.pojo.ExtendPage;
import com.cdogs.lightBlog.service.BaseService;
import com.cdogs.lightBlog.service.ExtendPageService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 扩展页面服务实现类
 * @author CDogs
 */
@Service
public class ExtendPageServiceImple extends BaseService implements ExtendPageService {

	

	public ExtendPage getPage(Integer pageId) {
		ExtendPage page = new ExtendPage();
		page.setId(pageId);
		return extendPageDao.selectPage(page);
	}


	public List<ExtendPage> getAllPages() {
		return extendPageDao.selectAllPages();
	}


	public boolean updatePageInfo(ExtendPage page) {
		if (page.getDisplay() == null) {
			page.setDisplay(1);
		}
		int result = extendPageDao.updatePageInfo(page);
		return result>0;
	}


	public boolean addPage(ExtendPage page) {
		int result = extendPageDao.insertPage(page);
		return result>0;
	}


	public boolean deletePage(Integer pageId) {
		ExtendPage page = new ExtendPage();
		page.setId(pageId);
		int result = extendPageDao.deletePage(page);
		return result>0;
	}

}
