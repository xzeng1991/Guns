package cn.stylefeng.guns.modular.api.entity;

import lombok.Data;

import java.util.Date;

/**
 * 文章实体类
 */
@Data
public class ArticleInfo {
    private String id;
    private String avatar;//作者头像
    private String author;//作者
    private String title;//标题
    private String img;//图片
    private String brife;//简介
    private String content;//内容
    private String collectNum;//收藏数
    private String readNum;//阅读数
    private String commentNum;//评论数
    private String upNum;//点赞数
    private Date createTime;//创建时间
}
