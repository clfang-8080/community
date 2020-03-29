package com.clfang.community.community.model;


import lombok.Data;

/**
 * ClassName:Question
 * Package:com.clfang.community.community.model
 * Description:
 *
 * @Date:2020/3/15 9:36
 * @Author:CLFang
 */
@Data
public class Question {
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


}
