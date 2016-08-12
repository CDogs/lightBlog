package com.cdogs.lightBlog.controller;

import com.cdogs.lightBlog.dto.NoticeDto;
import com.cdogs.lightBlog.pojo.Notice;
import com.cdogs.lightBlog.util.JSONUtils;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 后台公告管理
 * @author CDogs
 */
@Controller
@RequestMapping("/admin/notice")
public class BackNoticeController extends BaseController {
	
    /**
     * 
     * 添加公告
     * @return
     * @see [类、类#方法、类#成员]
     */
	@SuppressWarnings("unchecked")
	@ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public String addNotice(@RequestBody String bodyString) {
    	
        try {
        	JSONObject json = JSONObject.fromObject(bodyString);
        	Notice notice = (Notice) JSONUtils.String2Object(
        			json.get("notice").toString(), Notice.class);
        	
            //参数正常
            if (!notice.getTitle().isEmpty() && !notice.getContent().isEmpty()) {
                //如果公告添加ok
                if (noticeService.addNotice(notice)) {
                	return SUCCESS; 
                }      
            }
        } catch (Exception e) {
            LOGGER.error("NoticeController.addNotice();", e);
        }
        
        return FAIL;
    }
    
	/**
	 * 初始化更新公告
	 * @param notice
	 * @return
	 */
	@RequestMapping("/init-edit")
	public ModelAndView initEditPage(Notice notice) {
		
		ModelAndView response = new ModelAndView("/admin/notice_edit");
        //公告对象
        NoticeDto noticeDto = null;
        
		try {
			noticeDto = noticeService.getNotice(notice);
		} catch (Exception e) {
			LOGGER.error("NoticeController.initEditPage()",e.getMessage());
		}
		response.addObject("notice", noticeDto);
		return response;
		
	}
		
    /**
     * 
     * 更新公告
     * @return
     * @see [类、类#方法、类#成员]
     */
    @SuppressWarnings("unchecked")
	@ResponseBody
    @RequestMapping("/update")
    public String updateNotices(@RequestBody String bodyString) {
           	  	   	  	
        try {  
        	JSONObject json = JSONObject.fromObject(bodyString);
        	Notice notice = (Notice) JSONUtils.String2Object(
        			json.get("notice").toString(), Notice.class);
            //参数正常
            if (notice.getId() != null) {
                //如果更新成功,返回SUCCESS
                if (noticeService.updateNotice(notice)) {
                	return SUCCESS; 
                }   
            }
            
        } catch (Exception e) {
                LOGGER.error("NoticeController.addNotice();", e.getMessage());
        }
        //返回FAIL
        return FAIL;
    }

    /**
     * 根据关键字检索公告
     * @param key
     * @return
     */
    @RequestMapping("/search")
    public ModelAndView searchByKey(String key) {
        
        ModelAndView response = new ModelAndView("/admin/notices");
        List<NoticeDto> data = null;
        try {
        	//检索当月公告列表
        	data = noticeService.searchNotice(key);
        } catch (Exception e) {
           LOGGER.error("NoticeController." +
           		"searchByKey();", e.getMessage());
        }
        response.addObject("notices", data);
        return response;
    }
       
}
