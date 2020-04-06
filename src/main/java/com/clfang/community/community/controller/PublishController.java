package com.clfang.community.community.controller;
import com.clfang.community.community.cache.TagCache;
import com.clfang.community.community.dto.QuestionDto;
import com.clfang.community.community.mapper.QuestionMapper;
import com.clfang.community.community.model.Question;
import com.clfang.community.community.model.User;
import com.clfang.community.community.service.QuestionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * ClassName:PublishController
 * Package:com.clfang.community.community.controller
 * Description:
 *
 * @Date:2020/3/13 11:08
 * @Author:CLFang
 */


@Controller
public class PublishController {
    @Autowired
    private QuestionService questionService;
    //修改、重新编辑问题
    @GetMapping("/publish/{id}")
    public String edit(
            @PathVariable(name = "id") long id,
            Model model
    ){
        QuestionDto question = questionService.getById(id);
        model.addAttribute("title",question.getTitle());
        model.addAttribute("description",question.getDescription());
        model.addAttribute("tag",question.getTag());
        model.addAttribute("id",question.getId());
        model.addAttribute("tags", TagCache.get());//传入预设标签
        return "publish";
    }

    @GetMapping("/publish")
    public String publish(Model model){
        model.addAttribute("tags", TagCache.get());//传入预设标签
        return "publish";
    }
    //新增问题
    @PostMapping("/publish")
    public String doPublish(
            @RequestParam(value = "title",required = false) String title,
            @RequestParam(value = "description",required = false) String description,
            @RequestParam(value = "tag",required = false) String tag,
            @RequestParam(value = "id",required = false) Integer id,
            HttpServletRequest request,
            Model model
    ){
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);
        model.addAttribute("tags", TagCache.get());//传入预设标签
        if (title==null||title==""){
            model.addAttribute("error","标题不能为空");
            return "publish";
        }
        if (description==null||description==""){
            model.addAttribute("error","内容不能为空");
            return "publish";
        }
        if (tag==null||tag==""){
            model.addAttribute("error","标签不能为空");
            return "publish";
        }
        String invalid = TagCache.filterInvalid(tag);
        if (StringUtils.isNoneBlank(invalid)){//校验的标签为空
            model.addAttribute("error","输入非正经标签"+invalid);
            return "publish";
        }

        User user = (User) request.getSession().getAttribute("user");
        if (user==null){
            model.addAttribute("error","用户未登录");
            return "publish";
        }
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCommentCount(0);//回复数
        question.setViewCount(0);//阅读数
        question.setCreator(user.getId());
        //question.setGmtCreate(System.currentTimeMillis());
        //question.setGmtModified(question.getGmtCreate());
        if (id != null){
            question.setId(id.longValue());
        }
        questionService.createOrUpdate(question);
        //questionMapper.create(question);
        return "redirect:/";
    }
}
