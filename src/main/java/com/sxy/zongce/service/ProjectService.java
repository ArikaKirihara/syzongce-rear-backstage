package com.sxy.zongce.service;

import com.sxy.zongce.entity.Project;
import com.sxy.zongce.util.PageRequest;
import com.sxy.zongce.util.PageResult;
import com.sxy.zongce.vo.AwardLeverVo;

import java.util.List;

public interface ProjectService {

    //测试，最后会删掉
    List<Project> findAll();

    //获取项目类型最大的id
    String findMaxId();

    //根据管理员id查询所有项目类型
    PageResult findAllProject(PageRequest pageRequest, String admin_Id);

    //根据id更改项目类型状态
    void updateProjectById(String pro_Id);

    //添加项目类型
    void addProject(Project project);

    //根据管理员id和模板分类id查询所有项目类型名称和id
    List<Project> findProject(String stu_Id,String tem_Id);

    //根据id查询成果级别可见性
    boolean isVisible(String pro_Id);

    //根据管理员id和模板分类id筛选项目类型
    PageResult findProByTmp(PageRequest pageRequest, String admin_Id, String tem_Id);
}
