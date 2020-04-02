package com.clfang.community.community.exception;

/**
 * ClassName:CustomizeException
 * Package:com.clfang.community.community.exception
 * Description:
 *自定义异常类
 * @Date:2020/4/2 21:40
 * @Author:CLFang
 */
public class CustomizeException extends RuntimeException{
    private String message;

    public CustomizeException(ICustomizeErrorCode errorCode) {
        this.message = errorCode.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
