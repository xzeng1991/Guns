package cn.stylefeng.guns.modular.system.entity;

import java.beans.Transient;
import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("article")
public class Article implements Serializable {
	private static final long serialVersionUID = -6093196433240212499L;
	
	@TableId(value = "ID", type = IdType.ID_WORKER)
	private Long id;
	
	@TableField("avatar")
	private String avatar;	// 头像
	
	@TableField("author")
	private String author;	// 作者
	
	@TableField("title")
	private String title;	// 名称
	
	@TableField("img")
	private String img;	// 配图
	
	@TableField("brife")
	private String brife;	// 简介
	
	@TableField("content")
	private String content;	// 内容
	
	@TableField("collect_num")
	private Integer collectNum;	// 收藏数
	
	@TableField("read_num")
	private Integer readNum;	// 阅读数
	
	@TableField("comment_num")
	private Integer commentNum;	// 评论数
	
	@TableField("up_num")
	private Integer upNum;	// 点赞数
	
	@TableField(value = "create_time", fill = FieldFill.INSERT)
	private Date createTime;	// 创建时间
	
}
