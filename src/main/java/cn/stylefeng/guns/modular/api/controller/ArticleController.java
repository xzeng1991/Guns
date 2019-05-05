package cn.stylefeng.guns.modular.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.stylefeng.guns.modular.system.entity.Article;
import cn.stylefeng.guns.modular.system.service.ArticleService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.SuccessResponseData;

@RestController
@RequestMapping("/gunsApi/article")
public class ArticleController extends BaseController{
	@Autowired
	private ArticleService articleService;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
    public Object index() {
		Map<String, Object> map = new HashMap<>();
		List<Article> articles = articleService.list();
		map.put("articleList", articles);
		
		return new SuccessResponseData(map);
	}
}
