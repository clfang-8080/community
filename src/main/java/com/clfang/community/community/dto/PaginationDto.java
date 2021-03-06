package com.clfang.community.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:PaginationDto
 * Package:com.clfang.community.community.dto
 * Description:
 *封装页面页码数
 * @Date:2020/3/29 21:22
 * @Author:CLFang
 */
@Data
public class PaginationDto<T> {
    private List<T> data;
    private boolean showPrevious;//有无向前按钮
    private boolean showFirstPage;//有无第一页按钮
    private boolean showNext;//有无下一页按钮
    private boolean showEndPage;//有无尾页按钮
    private Integer page;//当前页面
    private List<Integer> pages = new ArrayList<>();//展示页码数组
    private Integer totalPage;//尾页数

    public void setPagination(Integer totalPage, Integer page) {
        this.totalPage=totalPage;
        this.page=page;
        //确定页码数组
        pages.add(page);
        for(int i = 1;i<=3;i++){
            if(page - i > 0){//判断是否还有上一页
                pages.add(0,page - i);
            }
            if(page + i <= totalPage){//判断是否还有下一页
                pages.add(page+i);
            }
        }

        if(page==1){//是否显示上一页按钮
            showPrevious=false;
        }else {
            showPrevious=true;
        }
        if(page ==totalPage){//是否显示下一页按钮
            showNext=false;
        }else {
            showNext=true;
        }
        if(pages.contains(1)){//是否显示第一页按钮（contains（1）:数组中包含1）
            showFirstPage=false;
        }else{
            showFirstPage=true;
        }
        if(pages.contains(totalPage)){//是否显示最后一页按钮
            showEndPage=false;
        }else{
            showEndPage=true;
        }
    }
}
