package com.briup.cms.service.impl;

import com.briup.cms.bean.basic.Log;
import com.briup.cms.mapper.basic.LogMapper;
import com.briup.cms.service.ILogService;
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
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements ILogService {

}
