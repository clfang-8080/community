package com.clfang.community.community.dto;

import com.clfang.community.community.model.User;
import lombok.Data;

/**
 * ClassName:QuestionDto
 * Package:com.clfang.community.community.dto
 * Description:
 *
 * @Date:2020/3/29 16:16
 * @Author:CLFang
 */
@Data
public class QuestionDto {
    private Integer id;
    private String title;//标题
    private String description;//内容
    private String tag;//标签
    private long gmtCreate;//创建时间
    private long gmtModified;//修改时间
    private Integer creator;//作者(作者的id)
    private  Integer commentCount;//回复数
    private Integer viewCount;//浏览数
    private Integer likeCount;//点赞数
    private User user;//用于获取头像url
}
