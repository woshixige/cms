package com.briup.cms.util;

/**
 * 响应信息的枚举类：
 */
public enum ResultCode {
    SUCCESS(200,"操作成功"),
    DELETE_BATCH_SUCCESS(200,"批量删除成功"),
    ERROR(0,"操作失败"),

    //操作成功：2000-2999
    ADD_SUCCESS(2000,"新增成功"),
    UPDATE_SUCCESS(2000,"修改成功"),
    DELETE_SUCCESS(2000,"删除成功"),


    //参数错误： 4000-4999
    USER_ID_ERROR(4000,"用户ID不合法"),
    USER_NAME_REPET(4001,"用户名重复"),
    USER_PASSWORD_NULL(4002,"密码不能为空"),
    USER_PASSWORD_LENGTH(4003,"密码长度不能小于6位"),
    PAGE_PARGNUM_EORROR(4004,"分页参数不合法"),
    NAME_NOT_EMPTY(4005,"名字不能为空"),
    DESC_NOT_EMPTY(4006,"描述不能不空"),
    DATA_NOT_EXSISTS(4007,"数据不存在"),
    FENCE_INNER_HURDLE_NOT_NULL(4008,"栏舍下有多个栏圈信息，不允许删除"),
    HURDLE_IS_FULL(4009,"插入失败,该栏圈已存满"),
    PASSWORD_ERROR(4010,"密码有误"),
    FIND_USER_NULL(4011,"用户不存在"),
    USER_NOT_LOGIN(4012,"用户未登录"),
    USER_TOKEN_FAILURE(4013,"用户登录凭证失效，请重新登录"),
    USER_DISABLED(4014,"账号已被禁用"),
    HURDLE_NOT_EXIST(4015,"该栏舍不存在，请选择其他栏舍"),
    ANIMAL_ID_NULL(4016,"动物的id不能为空"),
    DISEASE_ID_NOT_FIND(4017,"删除的病症记录id不存在"),
    ID_IS_NULL(4018,"id为空"),
    ENTITY_IS_NULL(4019,"填写的实体信息不能为空"),
    CATEGORY_NOT_FIND(4019,"没有找到该父类的栏目"),




    //服务器内部错误  5000-5999
    SERVER_INTER_ERROR(5000,"程序内部错误,请联系管理员");



    private Integer code;
    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    //为了获取具体的响应信息，不提供set方法，防止被其他地方修改

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
