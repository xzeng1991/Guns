package cn.stylefeng.guns.modular.system.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class ArticleDto implements Serializable {
	private static final long serialVersionUID = -5213019458494216798L;
	
	private Long id;
	private String avatar;	// 头像
	private String author;	// 作者
	private String title;	// 名称
	private String img;	// 配图
	private String brife;	// 简介
	private String content;	// 内容
	private Integer collectNum;	// 收藏数
	private Integer readNum;	// 阅读数
	private Integer commentNum;	// 评论数
	private Integer upNum;	// 点赞数
	private Date createTime;	// 创建时间
}
