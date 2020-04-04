package com.clfang.community.community.advice;

import com.alibaba.fastjson.JSON;
import com.clfang.community.community.dto.ResultDto;
import com.clfang.community.community.exception.CustomizeErrorCode;
import com.clfang.community.community.exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * ClassName:CustomizeExceptionHandler
 * Package:com.clfang.community.community.advice
 * Description:
 *异常
 * @Date:2020/4/2 21:13
 * @Author:CLFang
 */
@ControllerAdvice
public class CustomizeExceptionHandler {
    @ExceptionHandler(Exception.class)
    ModelAndView handle(Throwable ex, Model model, HttpServletRequest request, HttpServletResponse response) {
        //根据contentType判断是哪种错误
        String contentType = request.getContentType();
        if ("application/json".equals(contentType)){//返回json
            ResultDto resultDto;
            if (ex instanceof CustomizeException) {//预设异常
                resultDto = ResultDto.errorOf((CustomizeException)ex);
            }else {//其他异常
                resultDto = ResultDto.errorOf(CustomizeErrorCode.SYS_ERROR);
            }
            try {
                response.setContentType("application/json");
                response.setStatus(200);
                response.setCharacterEncoding("utf-8");
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(resultDto));
                writer.close();
            } catch (IOException ioe) {

            }
            return null;
        }else {//错误页面跳转
            if (ex instanceof CustomizeException) {//预设异常
                model.addAttribute("message",ex.getMessage());
            }else {//其他异常
                model.addAttribute("message",CustomizeErrorCode.SYS_ERROR.getMessage());
            }
            return new ModelAndView("error");
        }

    }
}
