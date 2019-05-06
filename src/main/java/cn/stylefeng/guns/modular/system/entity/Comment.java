package cn.stylefeng.guns.modular.system.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
@Data
@TableName("comment")
public class Comment implements Serializable{
	private static final long serialVersionUID = 7810882938357003466L;
	
	@TableId(value = "ID", type = IdType.ID_WORKER)
	private Long id;
	
	@TableField("article_id")
	private Long articleId;	// 文章ID
	
	@TableField("avatar")
	private String avatar;	// 头像
	
	@TableField("name")
	private String name;	// 用户名
	
	@TableField("txt")
	private String txt;		// 文本消息
	
	@TableField("img")
	private String img;		// 图片消息
	
	@TableField("audio")
	private String audio;	// 语音消息
	
	@TableField(value = "create_time", fill = FieldFill.INSERT)
	private String createTime;	// 创建时间
}
