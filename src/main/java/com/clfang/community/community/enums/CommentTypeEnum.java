package com.clfang.community.community.enums;

/**
 * ClassName:CommentTypeEnums
 * Package:com.clfang.community.community.enums
 * Description:
 *判断是不是回复内容枚举类
 * @Date:2020/4/3 20:36
 * @Author:CLFang
 */
public enum CommentTypeEnum {
    QUESTION(1),
    COMMENT(2);
    private Integer type;

    public static boolean isExist(Integer type) {
        for (CommentTypeEnum commentTypeEnum : CommentTypeEnum.values()) {
            if (commentTypeEnum.getType()==type){
                return true;
            }
        }
        return false;
    }

    public Integer getType() {
        return type;
    }

    CommentTypeEnum(Integer type) {
        this.type = type;
    }
}
