package cn.stylefeng.guns.modular.system.service;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.stylefeng.guns.modular.system.entity.Comment;
import cn.stylefeng.guns.modular.system.mapper.CommentMapper;

@Service
public class CommentService extends ServiceImpl<CommentMapper, Comment> {

}
