package com.clfang.community.community.controller;

import com.clfang.community.community.dto.PaginationDto;
import com.clfang.community.community.model.User;
import com.clfang.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * ClassName:ProfileController
 * Package:com.clfang.community.community.controller
 * Description:
 *
 * @Date:2020/3/30 16:10
 * @Author:CLFang
 */
@Controller
public class ProfileController {
    @Autowired
    private QuestionService questionService;
    @GetMapping("/profile/{action}")
    public String profile(
            HttpServletRequest request,
            Model model,
            @RequestParam(name = "page",defaultValue = "1") Integer page,
            @RequestParam(name = "size",defaultValue = "5") Integer size,
            @PathVariable(name = "action") String action
    ){
        User user = (User) request.getSession().getAttribute("user");
        if (user==null){
            return "redirect:/";
        }
        if ("questions".equals(action)){
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的无聊话题");
        }else if ("replies".equals(action)){
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","最新无聊回复");
        }
        PaginationDto paginationDto = questionService.list(user.getId(), page, size);
        model.addAttribute("pagination",paginationDto);
        return "profile";
    }
}
