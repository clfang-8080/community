package com.clfang.community.community.service;

import com.clfang.community.community.dto.PaginationDto;
import com.clfang.community.community.dto.QuestionDto;
import com.clfang.community.community.exception.CustomizeErrorCode;
import com.clfang.community.community.exception.CustomizeException;
import com.clfang.community.community.mapper.QuestionExtMapper;
import com.clfang.community.community.mapper.QuestionMapper;
import com.clfang.community.community.mapper.UserMapper;
import com.clfang.community.community.model.Question;
import com.clfang.community.community.model.QuestionExample;
import com.clfang.community.community.model.User;
import org.apache.ibatis.session.RowBounds;
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
    @Autowired
    private QuestionExtMapper questionExtMapper;

    public PaginationDto list(Integer page, Integer size) {
        PaginationDto paginationDto = new PaginationDto();
        Integer totalPage;//定义总页数
        //totalCount:数据库总记录条数
        Integer totalCount = (int)questionMapper.countByExample(new QuestionExample());
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
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(new QuestionExample(), new RowBounds(offset, size));
        List<QuestionDto> questionDtoList = new ArrayList<>();

        for (Question question : questions) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
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
        //totalCount:数据库总记录条数
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria()
                .andCreatorEqualTo(userId);
        Integer totalCount = (int)questionMapper.countByExample(questionExample);
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
        //List<Question> questions = questionMapper.listByUserId(userId,offset,size);
        QuestionExample example = new QuestionExample();
        example.createCriteria()
                .andCreatorEqualTo(userId);
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(example, new RowBounds(offset, size));
        List<QuestionDto> questionDtoList = new ArrayList<>();

        for (Question question : questions) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question,questionDto);
            questionDto.setUser(user);
            questionDtoList.add(questionDto);
        }
        paginationDto.setQuestions(questionDtoList);


        return paginationDto;
    }

    public QuestionDto getById(Integer id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        if (question == null) {
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        QuestionDto questionDto = new QuestionDto();
        BeanUtils.copyProperties(question,questionDto);
        User user = userMapper.selectByPrimaryKey(question.getCreator());
        questionDto.setUser(user);
        return questionDto;
    }

    public void createOrUpdate(Question question) {
        if(question.getId()==null){//如果没有ID，说明这是第一次创建数据
            question.setGmtCreate(System.currentTimeMillis());//创建时间
            question.setGmtModified(question.getGmtCreate());//更新时间
            questionMapper.insert(question);
        }else {//有id，说明这是修改、更新数据
            Question updateQuestion = new Question();
            updateQuestion.setGmtModified(System.currentTimeMillis());
            updateQuestion.setTitle(question.getTitle());
            updateQuestion.setDescription(question.getDescription());
            updateQuestion.setTag(question.getTag());
            QuestionExample questionExample = new QuestionExample();
            questionExample.createCriteria()
                    .andIdEqualTo(question.getId());
            int updated = questionMapper.updateByExampleSelective(updateQuestion, questionExample);
            if (updated != 1){//更新失败
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
        }
    }

    //增加阅读数的方法
    public void incView(Integer id) {
        Question question = new Question();
        question.setId(id);
        question.setViewCount(1);
        questionExtMapper.incView(question);
    }
}
