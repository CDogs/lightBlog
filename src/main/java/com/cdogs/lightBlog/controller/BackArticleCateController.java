package com.cdogs.lightBlog.controller;

import com.cdogs.lightBlog.dto.ArticleCategoryDto;
import com.cdogs.lightBlog.pojo.Article;
import com.cdogs.lightBlog.pojo.ArticleCategory;
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
import java.util.Locale;

/**
 * 后台文章分类管理
 * @author CDogs
 *
 */
@Controller
@RequestMapping("/admin/category")
public class BackArticleCateController extends BaseController {
	
	/**
	 * 更新标签
	 * @param tag
	 * @return JSONString
	 */
	@RequestMapping("/update")
	@ResponseBody
	public String updateCategory(ArticleCategory category) {
		
		try {
			if (articleCateService.updateCategory(category)) {
				return SUCCESS;
			}
		} catch (Exception e) {
			LOGGER.error("BackArticleCateController.updateTag()",e.getMessage());
		}
		return FAIL;
	}
	
/*	*//**
	 * 添加标签
	 * @param tag
	 * @return JSONString
	 *//*
	@RequestMapping("/add")
	@ResponseBody
	public String addCategory(ArticleCategory category) {
		
		try {
			if (articleCateService.addCategory(category)) {
				return SUCCESS;
			}
		} catch (Exception e) {
			LOGGER.error("BackArticleCateController.addCategory()",e.getMessage());
		}
		return FAIL;
	}*/

	/**
	 * 标签列表
	 * @param
	 * @return
	 */
	@RequestMapping("/categorys")
	public ModelAndView list() {
		ModelAndView response = new ModelAndView("/admin/category_list");
		List<ArticleCategoryDto> categorys = articleCateService.getArtCatesAndCount();
		response.addObject("categorys",categorys);
		return response;
	}

	/**
	 * (json数据：{"article":{"category":"4","title":"4","content":"4"},"tag":[1,3]}）
	 * 添加类别
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/add")
	public String addCategory(@RequestBody String bodyString,HttpServletRequest request) {

		try {
			System.out.println(bodyString);
			JSONObject json = JSONObject.fromObject(bodyString);
			ArticleCategory category = (ArticleCategory) JSONUtils.String2Object(json.get("category").toString(), ArticleCategory.class);
			System.out.println(json);
			System.out.println(category);
			System.out.println(category.getDescription());
			//参数正常
			if (!category.getName().isEmpty()){

				if (articleCategoryService.addCategory(category)) {
					return SUCCESS;
				}
			}

		} catch (Exception e) {
			LOGGER.error("BackArticleCateController.addCategory();", e);
		}

		return FAIL;
	}
	
}
