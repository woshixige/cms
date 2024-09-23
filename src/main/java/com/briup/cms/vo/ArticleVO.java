package com.briup.cms.vo;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author zzx
 * @version 1.0
 * @date 2024-09-19 9:16
 */
@Data
public class ArticleVO {
    private Integer pageNum;
    private Integer pageSize;
    private String title;
    private String status;
}
