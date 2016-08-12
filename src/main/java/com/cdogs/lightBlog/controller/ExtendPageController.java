package com.cdogs.lightBlog.controller;

import com.cdogs.lightBlog.pojo.ExtendPage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * À©Õ¹Ò³Ãæ¿ØÖÆÆ÷
 * @author CDogs
 */
@Controller
@RequestMapping("/view")
public class ExtendPageController extends BaseController {
	
	/**
	 * 
	 * @param id
	 * @return
	 */
    @RequestMapping("/page")
    public ModelAndView getPage(int id) {
        
        ModelAndView response = new ModelAndView("/extend_page");
        ExtendPage page = null;
        List<ExtendPage> pages = null;
        try {  
        	page = extendPageService.getPage(id);   
        	pages = extendPageService.getAllPages();
        } catch (Exception e) {
            LOGGER.error("ExtendPageController.getPage();", e.getMessage());
        } 
        response.addObject("pages", pages); 
        response.addObject("page", page);    
        return response;
    }
	
}
