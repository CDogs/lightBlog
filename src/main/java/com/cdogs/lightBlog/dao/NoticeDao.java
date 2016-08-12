package com.cdogs.lightBlog.dao;


import com.cdogs.lightBlog.dto.NoticeDto;
import com.cdogs.lightBlog.pojo.Notice;

import java.util.List;
import java.util.Map;

/**
 * 公告Dao
 * @author CDogs
 */
public interface NoticeDao {
    
    /**
     * 根据公告字段检索公告，带分页
     * @param param 封装了PageSize,PageNum，Notice对象
     * @return List<NoticeDto>
     * @see [类、类#方法、类#成员]
     */
    public List<NoticeDto> selectNotices(Map<String, Object> param)
    		throws Exception;
    
    
    /**
     * 公告搜索
     * @param key
     * @return List<Notice>
     * @throws Exception
     */
    public List<NoticeDto> searchNotice(Map<String, Object> param)
    		throws Exception;

    /**
     * 按时间检索公告
     * @param param 封装了PageSize,PageNum， 时间段time
     * @throws Exception
     */
    public List<NoticeDto> selectNoticesByTime(Map<String, Object> param)
    		throws Exception;
    
    /**
     * 按时间归档公告
     * @return List<NoticeDto>
     * @throws Exception
     */
    public List<NoticeDto> selectArchiveByTime() throws Exception;
    
    
    /**
     * 根据公告字段检索公告
     * @param param
     * @return NoticeDto
     * @see [类、类#方法、类#成员]
     */
    public NoticeDto selectNotice(Notice notice) throws Exception;
    
    /**
     * 
     * 添加公告
     * @param notice
     * @return int
     * @see [类、类#方法、类#成员]
     */
    public int addNotice(Notice notice) throws Exception;
    
    /**
     * 
     * 更新公告
     * @param notice
     * @return int
     * @see [类、类#方法、类#成员]
     */
    public int updateNotice(Notice notice) throws Exception;
    
    /**
     * 统计公告数量
     * @param param
     * @return int
     * @throws Exception
     */
    public int countOfNotices(Map<String, Object> param)
    		throws Exception;

}
