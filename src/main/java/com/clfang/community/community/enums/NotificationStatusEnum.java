package com.clfang.community.community.enums;

/**
 * ClassName:NotificationStatusEnum
 * Package:com.clfang.community.community.enums
 * Description:
 *
 * @Date:2020/4/7 9:58
 * @Author:CLFang
 */
public enum NotificationStatusEnum {
    UNREAD(0),//(默认)未读
    READ(1)//已读
    ;
    private int status;

    public int getStatus() {
        return status;
    }

    NotificationStatusEnum(int status) {
        this.status = status;
    }
}
