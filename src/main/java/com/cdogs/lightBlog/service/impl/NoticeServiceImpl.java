package com.cdogs.lightBlog.service.impl;

import com.cdogs.lightBlog.dto.NoticeDto;
import com.cdogs.lightBlog.dto.Page;
import com.cdogs.lightBlog.dto.PageResult;
import com.cdogs.lightBlog.pojo.Notice;
import com.cdogs.lightBlog.service.NoticeService;
import com.cdogs.lightBlog.service.BaseService;
import com.cdogs.lightBlog.util.HTMLUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 公告服务实现类
 * @author CDogs
 */
@Service
public class NoticeServiceImpl extends BaseService
	implements NoticeService {
    
    /**
     * 获取公告（带检索，id,title,content,createTime)
     * 重载方法
     * @param notice
     * @param page
     * @return
     * @throws Exception
     */

    public PageResult<NoticeDto> getNotices(Notice notice, Page page)
    		throws Exception {
        
    	PageResult<NoticeDto> result = new PageResult<NoticeDto>();
        Map<String, Object> param = new HashMap<String, Object>();

        //设置公告参数
        if (notice != null) {
            param.put("id", notice.getId());
            param.put("title", notice.getTitle());
            param.put("content", notice.getContent());
            param.put("createTime", notice.getCreateTime());
        }
        //检索（合适）公告总数
        int totalRows = noticeDao.countOfNotices(param);
        
        if (totalRows > 0) {
        
        	page.setTotalRows(totalRows);
        	page.repaginate();       	
        	//设置分页参数
        	param.put("pageNo", page.getStartNum()-1);
        	param.put("pageSize", page.getPageSize());

			//检索（合适）公告
        	List<NoticeDto> notices = noticeDao.selectNotices(param);
        	//获取公告标签
        	if (notices != null) {
        		for (NoticeDto ntc : notices) {
        			param.put("noticeId", ntc.getId());
        			// 此处对页面输出作字符限制
        			String content = HTMLUtils.html2Text(ntc.getContent());
        			//content = content.length() > 500 ? content.substring(0, 500) : content;
					//ntc.setContent(content + " ..." );
        		}
        	}
        	page.repaginate();
        	result.setPage(page);
        	result.setList(notices);
        }
        return result;
    }
    
    /**
     * 添加公告
     * 重载方法
     * @param notice
     * @return
     * @throws Exception
     */

	@Transactional
    public boolean addNotice(Notice notice)
        throws Exception {
    	//添加公告
		System.out.println("service正在执行addNotice..");
		System.out.println(notice.getTitle());
        int result = noticeDao.addNotice(notice);
		System.out.println("service执行addNotice..结束");
        return (result > 0) ;
    }
    
    /**
     * 更新公告
     * 重载方法
     * @param notice
     * @return
     * @throws Exception
     */

    public boolean updateNotice(Notice notice)
        throws Exception {
        int result = noticeDao.updateNotice(notice);
        return (result > 0);
    }
    
    /**
     * 
     * 得到公告
     * @param notice
     * @return
     * @throws Exception
     */

    public NoticeDto getNotice(Notice notice)
        throws Exception {   
    	
    	NoticeDto ntc = noticeDao.selectNotice(notice);
        Map<String, Object> param = new HashMap<String, Object>();
		param.put("noticeId", ntc.getId());
        return ntc;
    }

	
	/**
	 * 获取归档列表
	 */

	public List<NoticeDto> getArchiveByTime() throws Exception {
		return noticeDao.selectArchiveByTime();
	}
	
	/**
	 * 根据时间段获取公告
	 */

	public List<NoticeDto> getNoticesByTime(String dateTime, String timeType)
			throws Exception {
        Map<String, Object> param = new HashMap<String, Object>();
		param.put("time", dateTime);
		param.put("type", timeType);
		return noticeDao.selectNoticesByTime(param);
	}

	/**
	 * 统计公告数量
	 * @return
	 * @throws Exception
	 */

	public int getCountOfAllNotices() throws Exception {
		return noticeDao.countOfNotices(null);
	}
	
	/**
	 * 用key去匹配名称和ID
	 */

	public List<NoticeDto> searchNotice(String key) throws Exception {
		
        Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", key);
		param.put("name", "%"+key+"%");
		return noticeDao.searchNotice(param);
	}
    
}
