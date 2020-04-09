package com.clfang.community.community.controller;

import com.clfang.community.community.dto.FileDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ClassName:FileController
 * Package:com.clfang.community.community.controller
 * Description:
 *
 * @Date:2020/4/9 13:53
 * @Author:CLFang
 */
@Controller
public class FileController {
    @RequestMapping("/file/upload")
    @ResponseBody
    public FileDto upload(){
        FileDto fileDto = new FileDto();
        fileDto.setSuccess(1);
        fileDto.setUrl("/images/tupian1.gif");
        return fileDto;
    }
}
