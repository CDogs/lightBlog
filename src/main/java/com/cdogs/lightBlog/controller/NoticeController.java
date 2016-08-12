package com.cdogs.lightBlog.controller;

import com.cdogs.lightBlog.constants.Common;
import com.cdogs.lightBlog.dto.NoticeDto;
import com.cdogs.lightBlog.dto.Page;
import com.cdogs.lightBlog.dto.PageResult;
import com.cdogs.lightBlog.pojo.Article;
import com.cdogs.lightBlog.pojo.Notice;
import com.cdogs.lightBlog.pojo.ExtendPage;
import com.cdogs.lightBlog.pojo.FriendlyLink;
import com.cdogs.lightBlog.util.JSONUtils;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 公告管理控制
 * @author CDogs
 */
@Controller
@RequestMapping("/notice")
public class NoticeController extends BaseController {

    /**
     * 
     * 获取所有的公告列表，按照时间降序排列
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/index")
    public ModelAndView getNotices(Page page) {

        System.out.println("正在执行index方法...");
        ModelAndView response = new ModelAndView("/admin/notice_list");
        //公告Page
        PageResult<NoticeDto> data = null;
        //公告实例
        Notice notice = null;
        
        if( page.getCurrentPage() == 1) {
            page.setPageSize(5);
            page.repaginate();           
        }

        //获取分页数据
        try {
        	data = noticeService.getNotices(notice, page);
        } catch (Exception e) {
           LOGGER.error("NoticeController." +
           		"getNotices();", e.getMessage());
        }
        //设置page并重新分页   
        response.addObject("notices", data.getList());
        response.addObject("page", data.getPage());        

        return response;
    }

    /**
     *
     * 获取所有的公告列表，按照时间降序排列
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/notices")
    public ModelAndView Notices(Page page) {

        System.out.println("正在执行notices方法...");
        ModelAndView response = new ModelAndView("/notice_list");
        //公告Page
        PageResult<NoticeDto> data = null;
        //公告实例
        Notice notice = null;

        if( page.getCurrentPage() == 1) {
            page.setPageSize(5);
            page.repaginate();
        }

        //获取分页数据
        try {
            data = noticeService.getNotices(notice, page);
        } catch (Exception e) {
            LOGGER.error("NoticeController." +
                    "getNotices();", e.getMessage());
        }
        //设置page并重新分页
        response.addObject("notices", data.getList());
        response.addObject("page", data.getPage());

        return response;
    }
    
    /**
     * 
     * 获取所有的公告列表，按照时间降序排列
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/list")
    public ModelAndView noticeList(Page page) {
        
        ModelAndView response = new ModelAndView("/notice_list");
        PageResult<NoticeDto> data = null;

        if( page.getCurrentPage() == 1) {
            page.setPageSize(15);
            page.repaginate();           
        }
        
        response.addObject("notices", data.getList());
        response.addObject("page", data.getPage());
        String action = "/notice/list.htm";
        response.addObject("action", action);  

        return response;
    }
    
    /**
     * 检索当前月份的公告列表
     * @param time
     * @return
     */
    @RequestMapping("/search-archives")
    public ModelAndView searchByMouth(String time) {
        
        ModelAndView response = new ModelAndView("/notice_list");
        List<NoticeDto> data = null;

        try {
        	//检索当月公告列表
        	data = noticeService.getNoticesByTime(time, Common.BY_MOUTH);
        } catch (Exception e) {
           LOGGER.error("NoticeController." +
           		"searchByTime();", e.getMessage());
        }

        response.addObject("notices", data);

        return response;
    }

    /**
     * 
     * 获取具体的公告
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/read")
    public ModelAndView getNotice(int id, String flag) {
        
        ModelAndView response = new ModelAndView("/notice_detail");
        NoticeDto notice = null;
        Notice param = new Notice();
        //其他页面
        List<ExtendPage> pages = null;
        try {  
        	pages = extendPageService.getAllPages();
        	param.setId(id);
        	notice = noticeService.getNotice(param);

        } catch (Exception e) {
            LOGGER.error("NoticeController.getNotice();", e.getMessage());
        } 
        if (notice != null && pages != null) {
        	response.addObject("notice", notice);
        	response.addObject("pages", pages); 
        }
        response.addObject("flag", flag); 


        return response;
    }
    
    /**
     * 根据关键字检索公告
     * @param key
     * @return
     */
    @RequestMapping("/search")
    public ModelAndView searchByKey(String key) {
        
        ModelAndView response = new ModelAndView("/notice_list");
        List<NoticeDto> data = null;

        try {
        	//检索当月公告列表
        	data = noticeService.searchNotice(key);
        } catch (Exception e) {
           LOGGER.error("NoticeController." +
           		"searchByKey();", e.getMessage());
        }
        if (data != null) {
        	response.addObject("notices", data);
        }
        response.addObject("label", "搜索结果");

        return response;
    }

    /**
     * (json数据：{"notice":{"title":"4","content":"4"}}）
     * 添加公告
     * @return
     * @see [类、类#方法、类#成员]
     */
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public String addArticle(@RequestBody String bodyString,HttpServletRequest request) {

        try {
            System.out.println(bodyString);
            JSONObject json = JSONObject.fromObject(bodyString);
            Notice notice = (Notice) JSONUtils.String2Object(json.get("notice").toString(), Notice.class);
            System.out.println(json);
            System.out.println(notice);
            System.out.println(notice.getContent());
            //参数正常
            Integer AdminID = (Integer) request.getSession().getAttribute("AdminID");
            System.out.println(AdminID);
            notice.setAuthorId(AdminID);
            if (!notice.getTitle().isEmpty() && !notice.getContent().isEmpty()) {
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
     * 添加公告初始化页面
     * @param
     * @return
     */
    @RequestMapping("/init_add")
    public ModelAndView initAdd() {

        ModelAndView response = new ModelAndView("/admin/notice_edit");


        return response;
    }


         
}
