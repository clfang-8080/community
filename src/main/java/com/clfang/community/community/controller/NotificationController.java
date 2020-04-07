package com.clfang.community.community.controller;

import com.clfang.community.community.dto.NotificationDto;
import com.clfang.community.community.dto.PaginationDto;
import com.clfang.community.community.enums.NotificationTypeEnum;
import com.clfang.community.community.model.User;
import com.clfang.community.community.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * ClassName:NotificationController
 * Package:com.clfang.community.community.controller
 * Description:
 *
 * @Date:2020/4/7 12:14
 * @Author:CLFang
 */
@Controller
public class NotificationController {
    @Autowired
    private NotificationService notificationService;
    @GetMapping("/profile/notification/{id}")
    public String profile(
            @PathVariable(name = "id") Integer id,
            HttpServletRequest request
    ){
        User user = (User) request.getSession().getAttribute("user");
        if (user==null){
            return "redirect:/";
        }

        NotificationDto notificationDto = notificationService.read(id.longValue(),user);
        if (NotificationTypeEnum.REPLY_COMMENT.getType() == notificationDto.getType()
                ||NotificationTypeEnum.REPLY_QUESTION.getType() == notificationDto.getType()
        ){
            return "redirect:/question/" + notificationDto.getOuterId();
        }else {
            return "redirect:/";
        }
    }
}
