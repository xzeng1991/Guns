package cn.stylefeng.guns.modular.api.service;

import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.modular.api.mapper.ArticleMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ArticleService {

    private static Logger logger = LoggerFactory.getLogger(MovieService.class);
    @Autowired
    private ArticleMapper articleMapper;


    public Page<Map<String, Object>> selectArticles(String condition) {
        logger.info("start search");
        Page page = LayuiPageFactory.defaultPage();
        return articleMapper.selectArticles(page, condition);
    }
}
