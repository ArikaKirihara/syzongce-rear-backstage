package com.sxy.zongce.mapper;

import com.sxy.zongce.entity.Project;
import com.sxy.zongce.vo.ProjectVo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class ProjectMapperTest {
    @Autowired
    ProjectMapper projectMapper;

    @Test
    void findAllProject() {
        List<ProjectVo> projectVoList =  projectMapper.findAllProject("80210122");
        System.out.println(projectVoList.size());
        System.out.println(projectVoList.get(1).getPro_Id());
        System.out.println(projectVoList.get(1).getPro_Name());
        System.out.println(projectVoList.get(1).getPro_Display());
    }

    @Test
    void updateProjectById() {
        System.out.println(projectMapper.updateProjectById("A1"));

    }

    @Test
    void addProject() {
        Project project =new Project();
        project.setPro_Id("A101");
        project.setPro_Name("项目类型");
        project.setAdmin_Id("80210122");
        project.setTem_Id("MU-01");
        System.out.println(projectMapper.addProject(project));
    }

    @Test
    void findTitleById(){
//        List<Project> projectList =  projectMapper.findTitleById("80210122","MU-01");
//        System.out.println(projectList.size());
//        System.out.println(projectList.get(1).getPro_Id());
//        System.out.println(projectList.get(1).getPro_Name());
//        System.out.println(projectList.get(1).getPro_Display());
    }

    @Test
    void findProject(){
        List<Project> list = projectMapper.findProject("190802103028","MU-1");
        System.out.println(list.size());
    }
}






