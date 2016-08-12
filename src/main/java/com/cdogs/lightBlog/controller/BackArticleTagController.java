/**
 * 
 */
package com.cdogs.lightBlog.controller;

import com.cdogs.lightBlog.dto.ArticleTagDto;
import com.cdogs.lightBlog.pojo.ArticleCategory;
import com.cdogs.lightBlog.pojo.ArticleTag;
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
 * 后台文章标签管理
 * @author CDogs
 *
 */
@Controller
@RequestMapping("/admin/tag")
public class BackArticleTagController extends BaseController {
		
	/**
	 * 更新标签
	 * @param tag
	 * @return JSONString
	 */
	@RequestMapping("/update")
	@ResponseBody
	public String updateTag(ArticleTag tag) {
		
		try {
			if (articleTagService.updateArticleTag(tag)) {
				return SUCCESS;
			}
		} catch (Exception e) {
			LOGGER.error("ArticleTagController.updateTag()",e.getMessage());
		}
		return FAIL;
	}
	
	/**
	 * 删除标签
	 * @param tag
	 * @return JSONString
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public String deleteTag(ArticleTag tag) {
		
		try {
			if (articleTagService.deleteTag(tag)) {
				return SUCCESS;
			}
		} catch (Exception e) {
			LOGGER.error("ArticleTagController.deleteTag()",e.getMessage());
		}
		return FAIL;
	}


	/**
	 * 标签列表
	 * @param
	 * @return
	 */
	@RequestMapping("/tags")
	public ModelAndView list() {
		ModelAndView response = new ModelAndView("/admin/tag_list");

		try {
			List<ArticleTag> tags = articleTagService.getAllTags();
			response.addObject("tags",tags);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}

	/**
	 * (json数据：{"article":{"category":"4","title":"4","content":"4"},"tag":[1,3]}）
	 * 添加标签
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/add")
	public String addTag(@RequestBody String bodyString,HttpServletRequest request) {

		try {
			System.out.println(bodyString);
			JSONObject json = JSONObject.fromObject(bodyString);
			ArticleTag tag = (ArticleTag) JSONUtils.String2Object(json.get("tag").toString(), ArticleTag.class);
			System.out.println(json);
			System.out.println(tag);
			System.out.println(tag.getDescription());
			//参数正常
			if (!tag.getName().isEmpty()){

				if (articleTagService.saveArticleTag(tag)) {
					return SUCCESS;
				}
			}

		} catch (Exception e) {
			LOGGER.error("BackArticleTagController.addTag();", e);
		}

		return FAIL;
	}
}
