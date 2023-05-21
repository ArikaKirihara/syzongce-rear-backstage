package com.sxy.zongce.mapper;

import com.sxy.zongce.entity.Template;
import com.sxy.zongce.util.PageRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TemplateMapper {

    //测试，最后会删掉
    List<Template> findAll();

    //获取模板分类最大的id（有用别动）
    String findMaxId();

    //根据管理员id查询所有模板分类
    List<Template> findAllTemplate(String admin_Id);

    //根据id更改模板分类状态
    int updateTemplateById(String tem_Id);

    //添加模板分类
    int addTemplate(Template template);

    //根据管理员id查询所有所有模板分类名称和id
    List<Template> findTemplate(String stu_Id);

    //根据id查询项目类型可见性
    String isVisible(String tem_Id);

}
