package com.clfang.community.community.controller;

import com.clfang.community.community.dto.QuestionDto;
import com.clfang.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * ClassName:QuestionController
 * Package:com.clfang.community.community.controller
 * Description:
 *点击问题标题进入详情页，展示内容并回复
 * @Date:2020/4/1 17:24
 * @Author:CLFang
 */
@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @GetMapping("/question/{id}")
    public String question(
            @PathVariable(name = "id") long id,
            Model model
            ){
        QuestionDto questionDto = questionService.getById(id);
        //累加阅读数
        questionService.incView(id);
        model.addAttribute("question" , questionDto);
        return "question";
    }
}
