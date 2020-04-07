package com.clfang.community.community.dto;

import com.clfang.community.community.model.User;
import lombok.Data;

/**
 * ClassName:NotificationDto
 * Package:com.clfang.community.community.dto
 * Description:
 *
 * @Date:2020/4/7 10:29
 * @Author:CLFang
 */
@Data
public class NotificationDto {
    private Long id;
    private Long gmtCreate;
    private Integer status;
    private Long notifier;
    private String notifierName;
    private String outerTitle;
    private Long outerId;
    private String typeName;
    private Integer type;
}
