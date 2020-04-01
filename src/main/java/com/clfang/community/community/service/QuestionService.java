package com.clfang.community.community.service;

import com.clfang.community.community.dto.PaginationDto;
import com.clfang.community.community.dto.QuestionDto;
import com.clfang.community.community.mapper.QuestionMapper;
import com.clfang.community.community.mapper.UserMapper;
import com.clfang.community.community.model.Question;
import com.clfang.community.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:QuestionService
 * Package:com.clfang.community.community.service
 * Description:
 *
 * @Date:2020/3/29 16:20
 * @Author:CLFang
 */
@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    public PaginationDto list(Integer page, Integer size) {
        PaginationDto paginationDto = new PaginationDto();
        Integer totalPage;//定义总页数
        Integer totalCount = questionMapper.count();//totalCount:数据库总记录条数
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
        List<Question> questions = questionMapper.list(offset,size);
        List<QuestionDto> questionDtoList = new ArrayList<>();

        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question,questionDto);
            questionDto.setUser(user);
            questionDtoList.add(questionDto);
        }
        paginationDto.setQuestions(questionDtoList);


        return paginationDto;
    }

    public PaginationDto list(Integer userId, Integer page, Integer size) {
        PaginationDto paginationDto = new PaginationDto();
        Integer totalPage;//定义总页数
        Integer totalCount = questionMapper.countByUserId(userId);//totalCount:数据库总记录条数
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
        List<Question> questions = questionMapper.listByUserId(userId,offset,size);
        List<QuestionDto> questionDtoList = new ArrayList<>();

        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question,questionDto);
            questionDto.setUser(user);
            questionDtoList.add(questionDto);
        }
        paginationDto.setQuestions(questionDtoList);


        return paginationDto;
    }

    public QuestionDto getById(Integer id) {
        QuestionDto questionDto = new QuestionDto();
        Question question = questionMapper.getById(id);
        BeanUtils.copyProperties(question,questionDto);
        User user = userMapper.findById(question.getCreator());
        questionDto.setUser(user);
        return questionDto;
    }
}
