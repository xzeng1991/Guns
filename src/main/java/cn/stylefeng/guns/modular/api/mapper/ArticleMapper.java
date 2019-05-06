package cn.stylefeng.guns.modular.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import cn.stylefeng.guns.modular.system.entity.Article;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface ArticleMapper extends BaseMapper<Article> {


    /**
     * 根据条件查询文章列表
     *
     * @return
     * @date 2017年2月12日 下午9:14:34
     */
    Page<Map<String, Object>> selectArticles(@Param("page") Page page, @Param("condition") String condition);

}
