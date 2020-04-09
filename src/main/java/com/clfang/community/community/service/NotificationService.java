package com.clfang.community.community.service;

import com.clfang.community.community.dto.NotificationDto;
import com.clfang.community.community.dto.PaginationDto;
import com.clfang.community.community.dto.QuestionDto;
import com.clfang.community.community.enums.NotificationStatusEnum;
import com.clfang.community.community.enums.NotificationTypeEnum;
import com.clfang.community.community.exception.CustomizeErrorCode;
import com.clfang.community.community.exception.CustomizeException;
import com.clfang.community.community.mapper.NotificationMapper;
import com.clfang.community.community.mapper.UserMapper;
import com.clfang.community.community.model.*;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * ClassName:NotificationService
 * Package:com.clfang.community.community.service
 * Description:
 *
 * @Date:2020/4/7 10:36
 * @Author:CLFang
 */
@Service
public class NotificationService {

    @Autowired
    private NotificationMapper notificationMapper;

    public PaginationDto list(Long userId, Integer page, Integer size) {
        PaginationDto<NotificationDto> paginationDto = new PaginationDto<>();
        Integer totalPage;//定义总页数
        //totalCount:数据库总记录条数
        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria()
                .andReceiverEqualTo(userId);
        Integer totalCount = (int)notificationMapper.countByExample(notificationExample);
        if(totalCount%size==0){
            totalPage = totalCount/size;
        }else {
            totalPage = totalCount/size+1;
        }
        //避免传入不存在页码
        if(page<1){
            page=1;
        }
        if(page>totalPage){
            page=totalPage;
        }
        paginationDto.setPagination(totalPage,page);
        //size*(page-1)
        Integer offset = size*(page-1);
        NotificationExample example = new NotificationExample();
        example.createCriteria()
                .andReceiverEqualTo(userId);
        example.setOrderByClause("gmt_create desc");//倒叙
        List<Notification> notifications = notificationMapper.selectByExampleWithRowbounds(example, new RowBounds(offset, size));
        if (notifications.size()==0){
            return paginationDto;
        }

        List<NotificationDto> notificationDtos = new ArrayList<>();
        for (Notification notification : notifications) {
            NotificationDto notificationDto = new NotificationDto();
            BeanUtils.copyProperties(notification,notificationDto);//前面的传到后面去
            notificationDto.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));
            notificationDtos.add(notificationDto);
        }
        

        paginationDto.setData(notificationDtos);
        return paginationDto;
    }

    public Long unreadCount(Long userId) {
        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria()
                .andReceiverEqualTo(userId)
                .andStatusEqualTo(NotificationStatusEnum.UNREAD.getStatus());
        return notificationMapper.countByExample(notificationExample);
    }

    public NotificationDto read(Long id, User user) {
        Notification notification = notificationMapper.selectByPrimaryKey(id);
        if (notification == null){//问题已不存在
            throw new CustomizeException(CustomizeErrorCode.NOTIFICATION_NOT_FOUND);
        }
        if (!notification.getReceiver().equals(user.getId())){//操作者不是作者
            throw new CustomizeException(CustomizeErrorCode.READ_NOTIFICATION_FAIL);
        }
        notification.setStatus(NotificationStatusEnum.READ.getStatus());//查看状态变为已读
        notificationMapper.updateByPrimaryKey(notification);
        NotificationDto notificationDto = new NotificationDto();
        BeanUtils.copyProperties(notification,notificationDto);//前面的传到后面去
        notificationDto.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));
        return notificationDto;
    }
}
