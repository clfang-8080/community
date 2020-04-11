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

    QUESTION_NOT_FOUND(2001,"此问题不存在或已删除，请换个试试o(∩_∩)o"),
    TARGET_PARAM_NOT_FOUND(2002,"未选中任何问题或评论进行回复"),
    NO_LOGIN(2003,"当前操作需要登录，请登陆后重试"),
    SYS_ERROR(2004,"服务器过载，还是稍后再试吧o(∩_∩)o"),
    TYPE_TARAM_WRONG(2005,"评论类型错误或不存在"),
    COMMENT_NOT_FOUNT(2006,"你回复的评论不存在或已删除，请换个试试o(∩_∩)o"),
    CONTENT_IS_EMPTY(2007,"输入内容不能为空"),
    READ_NOTIFICATION_FAIL(2008,"此信息作者不是你"),
    NOTIFICATION_NOT_FOUND(2009,"此问题已经不存在，好奇怪"),
    FILE_UPLOAD_FAIL(2010,"图片上传失败")
    ;

    @Override
    public String getMessage() {
        return message;
    }
    @Override
    public Integer getCode() {
        return code;
    }

    private Integer code;
    private String message;

    CustomizeErrorCode(Integer code ,String message) {
        this.code = code;
        this.message = message;
    }

}
