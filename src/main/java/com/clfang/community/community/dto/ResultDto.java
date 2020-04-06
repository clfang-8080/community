package com.clfang.community.community.dto;

import com.clfang.community.community.exception.CustomizeErrorCode;
import com.clfang.community.community.exception.CustomizeException;
import lombok.Data;

/**
 * ClassName:ResultDto
 * Package:com.clfang.community.community.dto
 * Description:
 *
 * @Date:2020/4/3 20:25
 * @Author:CLFang
 */
@Data
public class ResultDto<T> {
    private Integer code;//状态码
    private String message;//状态信息
    private T data;

    public static ResultDto errorOf(Integer code,String message){
        ResultDto resultDto = new ResultDto();
        resultDto.setCode(code);
        resultDto.setMessage(message);
        return resultDto;
    }
    //请求失败
    public static ResultDto errorOf(CustomizeErrorCode errorCode) {
        return errorOf(errorCode.getCode(),errorCode.getMessage());
    }
    //通用异常
    public static ResultDto errorOf(CustomizeException ex) {
        return errorOf(ex.getCode(),ex.getMessage());
    }
    //请求成功
    public static ResultDto okOF(){
        ResultDto resultDto = new ResultDto();
        resultDto.setCode(200);
        resultDto.setMessage("请求成功");
        return resultDto;
    }

    public static <T> ResultDto okOF(T t){
        ResultDto resultDto = new ResultDto();
        resultDto.setCode(200);
        resultDto.setMessage("请求成功");
        resultDto.setData(t);
        return resultDto;
    }

}
