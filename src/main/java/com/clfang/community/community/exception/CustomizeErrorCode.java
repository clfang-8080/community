package com.clfang.community.community.exception;

/**
 * ClassName:CustomizeErrorCode
 * Package:com.clfang.community.community.exception
 * Description:
 *实现异常信息接口
 * @Date:2020/4/2 22:01
 * @Author:CLFang
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode{

    QUESTION_NOT_FOUND("此问题不存在或已删除，请换个试试o(∩_∩)o~");

    @Override
    public String getMessage() {
        return message;
    }

    private String message;

    CustomizeErrorCode(String message) {
        this.message = message;
    }
}
