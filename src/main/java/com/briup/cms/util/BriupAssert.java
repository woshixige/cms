package com.briup.cms.util;

/**
 * @author zzx
 * @version 1.0
 * @date 2024-08-26 14:41
 */

import com.briup.cms.exception.CustomException;
import org.springframework.lang.Nullable;

/**
 * 工具类用于参数的判断
 */
public abstract class BriupAssert {

    public static void hasText(@Nullable String text, ResultCode rc) {
        if (text==null || "".equals(text.trim())){
            throw new CustomException(rc);
        }
    }
    public static void notNull(@Nullable Object object, ResultCode rc) {
        if (object == null) {
            throw new CustomException(rc);
        }
    }

    /**
     * 表达式是否正确
     * @param expression
     * @param rc
     */
    public static void isTure(boolean expression,ResultCode rc){
        if (!expression){
            throw new CustomException(rc);
        }
    }
}
