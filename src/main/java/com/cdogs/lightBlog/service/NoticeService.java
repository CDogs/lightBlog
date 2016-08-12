package com.cdogs.lightBlog.service;


import com.cdogs.lightBlog.dto.NoticeDto;
import com.cdogs.lightBlog.dto.Page;
import com.cdogs.lightBlog.dto.PageResult;
import com.cdogs.lightBlog.pojo.Notice;

import java.util.List;

public interface NoticeService {
    
    /**
     * 查询公告
     * @param notice
     * @param page
     * @return
     * @see [类、类#方法、类#成员]
     */
    public PageResult<NoticeDto> getNotices(Notice notice, Page page)
    		throws Exception;
   
    /**
     * 获取所有公告的数量
     * @return
     * @throws Exception
     */
    public int getCountOfAllNotices() throws Exception;
    
    /**
     * 公告搜索
     * @param key
     * @return List<Notice>
     * @throws Exception
     */
    public List<NoticeDto> searchNotice(String key)
    		throws Exception;

    
    /**
     * 获取归档公告列表，该归档公告按年月份归档
     * @return
     * @throws Exception
     */
    public List<NoticeDto> getArchiveByTime() throws Exception;

    
    /**
     * 检索检索当月公告列表
     * @param dateTime
     * @param timeType 根据时间类型，例如year则按年，mouth,day等
     * @return
     * @throws Exception
     */
    public List<NoticeDto> getNoticesByTime(String dateTime, String timeType)
    		throws Exception;
    
    /**
     * 
     * 获取某一篇公告
     * @param notice
     * @return
     * @throws Exception
     * @see [类、类#方法、类#成员]
     */
    public NoticeDto getNotice(Notice notice) throws Exception;
    
    /**
     * 
     * 添加公告
     * @param NoticeDto
     * @return
     * @see [类、类#方法、类#成员]
     */
    public boolean addNotice(Notice notice) throws Exception;
    
    /**
     * 
     * 更新公告
     * @param notice
     * @return
     * @see [类、类#方法、类#成员]
     */
    public boolean updateNotice(Notice notice) throws Exception;
}
