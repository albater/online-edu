package com.service.edu.service.impl;

import com.service.edu.entity.Comment;
import com.service.edu.mapper.CommentMapper;
import com.service.edu.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author alpha
 * @since 2022-07-17
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
