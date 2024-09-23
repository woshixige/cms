package com.briup.cms.service.impl;

import com.briup.cms.bean.basic.Slideshow;
import com.briup.cms.mapper.basic.SlideshowMapper;
import com.briup.cms.service.ISlideshowService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.briup.cms.util.BriupAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.briup.cms.util.ResultCode.ENTITY_IS_NULL;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangzx
 * @since 2024-09-10
 */
@Service
public class SlideshowServiceImpl extends ServiceImpl<SlideshowMapper, Slideshow> implements ISlideshowService {
    @Autowired
    private SlideshowMapper slideshowMapper;

    @Override
    public boolean saveOrUpdate(Slideshow entity) {
        BriupAssert.notNull(entity, ENTITY_IS_NULL);
        if (entity.getId()==null){//新增操作
            String uuid = UUID.randomUUID().toString().replace("-", "");
            entity.setDeleted(0);
            entity.setUrl(uuid+".jpg");//模拟一个图片路径
            entity.setUploadTime(LocalDateTime.now());
            slideshowMapper.insert(entity);
        }else {//修改操作
            slideshowMapper.updateById(entity);
        }
        return true;
    }
}
