package com.briup.cms.util;

/**
 * 返回结果工具类
 */
public class ResultUtil {
    private ResultUtil() {
        //工具类不能实例化
    }

    /**
     * 默认返回的响应提示信息：用在新增，修改 删除
     * @return
     */
    public static Result success(){
        return success(ResultCode.SUCCESS);
    }

    /**
     * 返回指定的提示信息，
     * @param rc 提示信息必须来自枚举对象
     * @return
     */
    public static Result success(ResultCode rc){
        return success(rc,null);
    }

    /**
     * 返回查询的结果及自定义响应信息
     * @param rc  响应信息
     * @param data 查询结果
     * @return
     */
    public static <T> Result success(ResultCode rc,T data){
        return new Result(rc,data);
    }

    /**
     * 返回查询的结果和默认的响应提示信息
     * @param data
     * @return
     */
    public static <T> Result success(T data){
        return success(ResultCode.SUCCESS,data);
    }

    /**
     * 响应默认的错误提示：
     * @return
     */
    public static Result error(){
        return error(ResultCode.ERROR);
    }

    /**
     * 响应指定的错误提示信息
     * @param rc
     * @return
     */
    public static Result error(ResultCode rc){
        return new Result(rc,null);
    }
}
