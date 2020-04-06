package com.clfang.community.community.cache;

import com.clfang.community.community.dto.TagDto;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ClassName:TagCache
 * Package:com.clfang.community.community.cache
 * Description:
 *
 * @Date:2020/4/6 17:42
 * @Author:CLFang
 */
public class TagCache {
    public static List<TagDto> get(){
        List<TagDto> tagDtos = new ArrayList<>();//返回的结果集
        TagDto program = new TagDto();
        program.setCategoryName("开发语言");
        program.setTags(Arrays.asList("js","php","css","html","java","node","python","c++","c","golang","shell","swift",
                "c#","sass","ruby","bash","less","net","lua","scala","coffeescript","actionscript","rust","erlang","perl"));
        tagDtos.add(program);

        TagDto framework = new TagDto();
        framework.setCategoryName("平台框架");
        framework.setTags(Arrays.asList("laravel","spring","express","dgango","flask","yii","ruby-on-rails","torndo",
                "kao","struts"));
        tagDtos.add(framework);

        TagDto server = new TagDto();
        server.setCategoryName("服务器");
        server.setTags(Arrays.asList("linux","nginx","docker","apache","ubuntu","缓存","tomcat","负数均衡","unix",
                "hadoop","windows-server"));
        tagDtos.add(server);

        TagDto db = new TagDto();
        db.setCategoryName("数据库");
        db.setTags(Arrays.asList("mysql","redis","mongodb","sql","oracle","nosql-memcached","sqlserver","postgresql","sqlite"));
        tagDtos.add(db);

        TagDto tool = new TagDto();
        tool.setCategoryName("开发工具");
        tool.setTags(Arrays.asList("git","github","code","vim","sublime-text","xcode","intellij-idea","eclipse","maven",
                "ide","svn","visual-studio","atom","emacs","textmate","hg"));
        tagDtos.add(tool);

        return tagDtos;
    }

    public static String filterInvalid(String tags){
        String[] split = StringUtils.split(tags, ",");
        List<TagDto> tagDtos = get();
        List<String> tagList = tagDtos.stream().flatMap(tag -> tag.getTags().stream()).collect(Collectors.toList());
        String invalid = Arrays.stream(split).filter(t -> !tagList.contains(t)).collect(Collectors.joining(","));
        return invalid;
    }
}
