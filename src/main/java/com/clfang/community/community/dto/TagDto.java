package com.clfang.community.community.dto;

import lombok.Data;

import java.util.List;

/**
 * ClassName:TagDto
 * Package:com.clfang.community.community.dto
 * Description:
 *
 * @Date:2020/4/6 17:43
 * @Author:CLFang
 */
@Data
public class TagDto {
    private String categoryName;//标签种类
    private List<String> tags;//标签集合
}
