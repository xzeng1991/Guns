package cn.stylefeng.guns.modular.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import cn.stylefeng.guns.modular.system.entity.Article;
import cn.stylefeng.guns.modular.system.entity.Comment;
import cn.stylefeng.guns.modular.system.service.ArticleService;
import cn.stylefeng.guns.modular.system.service.CommentService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.SuccessResponseData;

@RestController
@RequestMapping("/gunsApi/article")
public class ArticleController extends BaseController{
	@Autowired
	private ArticleService articleService;
	@Autowired
	private CommentService commentService;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
    public Object index() {
		Map<String, Object> map = new HashMap<>();
		List<Article> articles = articleService.list();
		map.put("articleList", articles);
		
		return new SuccessResponseData(map);
	}
	
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
    public Object detail(Long id) {
		Map<String, Object> map = new HashMap<>();
		
		Article article = articleService.getById(id);
		map.put("article", article);
		
		return new SuccessResponseData(map);
	}
	
	@RequestMapping(value = "/articleComment", method = RequestMethod.GET)
    public Object articleComment(Long articleId) {
		Map<String, Object> map = new HashMap<>();
		
		Comment query = new Comment();
		query.setArticleId(articleId);
		
		List<Comment> commentList = commentService.list(new QueryWrapper<Comment>(query));
		
		map.put("commentList", commentList);
		
		return new SuccessResponseData(map);
	}
}
