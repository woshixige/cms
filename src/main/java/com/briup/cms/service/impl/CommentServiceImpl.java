package com.briup.cms.service.impl;

import com.briup.cms.bean.basic.Comment;
import com.briup.cms.mapper.basic.CommentMapper;
import com.briup.cms.service.ICommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangzx
 * @since 2024-09-18
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

}
