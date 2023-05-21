package com.sxy.zongce.mapper;

import com.sxy.zongce.entity.Project;
import com.sxy.zongce.vo.ProjectVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectMapper {

    //测试，最后会删掉
    List<Project> findAll();

    //获取项目类型最大的id
    String findMaxId();

    //根据id查询项目类型可见性
    String isVisible(String pro_Id);

    //根据管理员id查询所有项目类型
    List<ProjectVo> findAllProject(String admin_Id);

    //根据id更改项目类型状态
    int updateProjectById(String pro_Id);

    //添加项目类型
    int addProject(Project project);

    //根据学生id和模板分类id查询所有项目类型名称和id
    List<Project> findProject(String stu_Id,String tem_Id);

    //根据管理员id和模板分类id筛选项目类型
    List<ProjectVo> findProByTmp(String admin_Id, String tem_Id);
}
