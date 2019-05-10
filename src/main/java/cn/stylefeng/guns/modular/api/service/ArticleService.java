package cn.stylefeng.guns.modular.api.service;


import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.modular.api.mapper.ArticleMapper;
import cn.stylefeng.guns.modular.system.entity.Article;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class ArticleService  extends ServiceImpl<ArticleMapper, Article>{

    private static Logger logger = LoggerFactory.getLogger(MovieService.class);
    @Autowired
    private ArticleMapper articleMapper;


    public Page<Map<String, Object>> selectArticles(String condition) {
        logger.info("start search");
        Page<?> page = LayuiPageFactory.defaultPage();
        return articleMapper.selectArticles(page, condition);
    }
    
    /**
     * 添加
     *
     */
    @Transactional(rollbackFor = Exception.class)
    public void addArticle(Article article) {
    	articleMapper.insert(article);
    }

    /**
     * 编辑
     *
     */
    @Transactional(rollbackFor = Exception.class)
    public void editArticle(Article article) {
    	articleMapper.updateById(article);
    }

    

    /**
     * 删除
     *
     */
    public void delArticleById(Long id) {
    	articleMapper.deleteById(id);
    }
}
