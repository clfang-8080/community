package com.clfang.community.community.dto;

import lombok.Data;

/**
 * ClassName:QuestionQueryDto
 * Package:com.clfang.community.community.dto
 * Description:
 *
 * @Date:2020/4/11 10:05
 * @Author:CLFang
 */
@Data
public class QuestionQueryDto {
    private String search;
    private Integer page;
    private Integer size;
}
