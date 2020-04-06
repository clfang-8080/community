package com.clfang.community.community.dto;

import lombok.Data;

/**
 * ClassName:CommentDto
 * Package:com.clfang.community.community.dto
 * Description:
 *
 * @Date:2020/4/3 14:45
 * @Author:CLFang
 */
@Data
public class CommentCreateDto {
    private long parentId;
    private String content;
    private Integer type;//是哪种回复
}
