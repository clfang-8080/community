package com.clfang.community.community.advice;

import com.clfang.community.community.exception.CustomizeException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

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
    ModelAndView handle(Throwable ex, Model model) {
        if (ex instanceof CustomizeException) {//预设异常
            model.addAttribute("message",ex.getMessage());
        }else {//其他异常
            model.addAttribute("message","服务器过载，还是稍后再试吧o(∩_∩)o ~ ~");
        }
        return new ModelAndView("error");
    }
}
