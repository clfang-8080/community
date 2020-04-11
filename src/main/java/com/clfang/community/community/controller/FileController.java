package com.clfang.community.community.controller;

import com.clfang.community.community.dto.FileDto;
import com.clfang.community.community.provider.UCloudProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

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

    @Autowired
    private UCloudProvider uCloudProvider;

    @RequestMapping("/file/upload")
    @ResponseBody
    public FileDto upload(HttpServletRequest request){
        //用于获取页面填写内容
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("editormd-image-file");
        String uploadUrl=null;
        try {
            byte[] bytes = file.getBytes();
            //System.out.println(bytes.toString());
            //System.out.println(file.getOriginalFilename());
            uploadUrl = uCloudProvider.upload(bytes, file.getOriginalFilename());
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileDto fileDto = new FileDto();
        fileDto.setSuccess(1);
        fileDto.setUrl(uploadUrl);
        return fileDto;
    }
}
