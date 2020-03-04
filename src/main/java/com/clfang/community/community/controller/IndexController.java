package com.clfang.community.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * ClassName:IndexController
 * Package:com.clfang.community.community.controller
 * Description:
 *
 * @Date:2020/3/3 21:17
 * @Author:CLFang
 */
@Controller
public class IndexController {
    @GetMapping("/")
    public String index(){
        return "index";
    }
}
