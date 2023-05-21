package com.sxy.zongce.service.impl;

import com.sxy.zongce.entity.Menter;
import com.sxy.zongce.entity.Student;
import com.sxy.zongce.mapper.StudentMapper;
import com.sxy.zongce.util.PageRequest;
import com.sxy.zongce.util.PageResult;
import com.sxy.zongce.vo.ScoreVo;
import com.sxy.zongce.vo.StudentVo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class StudentServiceImplTest {

    @Autowired
    StudentMapper studentMapper;
    @Autowired
    StudentServiceImpl studentService;

    @Test
    void isExist() {
        System.out.println(studentMapper.isExist("190802103028"));
//        System.out.println(studentService.isExist("190802103028"));
    }

    @Test
    void findPwdById() {
        String pwd = studentService.findPwdById("190802103028");
        System.out.println(pwd);
    }

    @Test
    void updatePwd() {
        studentService.updatePwd("190802103028","12345");
        System.out.println(studentService.findPwdById("190802103028"));
    }

    @Test
    void getStuName() {
        String name = studentService.getStuName("190802103028");
        System.out.println(name);
    }

    @Test
    public void findStuInfo() {
//        Student student1 = studentMapper.findStuInfo("190802103028");
        StudentVo student2 = studentService.findStuInfo("190802103028");
        System.out.println(student2.getStu_Name());
    }

    @Test
    void findStuByMenter() {
        PageRequest pageRequest = new PageRequest();
        pageRequest.setPageNum(10);
        pageRequest.setPageSize(20);
        Object object = studentService.findStuByMenter(pageRequest,"80210203");
        System.out.println(object);
    }

    @Test
    void searchStuByMenter() {
        PageRequest pageRequest = new PageRequest();
        pageRequest.setPageNum(10);
        pageRequest.setPageSize(20);

        Student student = new Student();
        student.setStu_Id("190802103028");

        Object object = studentService.searchStuByMenter(pageRequest,"80210203",student);
        System.out.println(object);
    }

    @Test
    void findStuByAdmin() {
        PageRequest pageRequest = new PageRequest();
        pageRequest.setPageNum(10);
        pageRequest.setPageSize(20);
        Object object = studentService.findStuByAdmin(pageRequest,"80210122");
        System.out.println(object);
    }

    @Test
    void searchStuByAdmin() {
        PageRequest pageRequest = new PageRequest();
        pageRequest.setPageNum(10);
        pageRequest.setPageSize(20);

        Student student = new Student();
//        student.setStu_Id("190802103028");

        PageResult pageResult = studentService.searchStuByAdmin(pageRequest,"80210122",student);
        System.out.println(pageResult.getContent().size());
    }

    @Test
    void checkScoreByStudent(){
//        List<Object> list = studentMapper.checkScoreByStudent("190802103028");
//        System.out.println(list.size());
        Map<String,Object> params = new HashMap<>();
        params.put("stu_Id","190802103028");
        List<ScoreVo> list = studentMapper.checkScoreByStudent(params);
        System.out.println(list.size());
//        Long totalSize = (Long)params.get("totalRecordCount");
//        String s = (String) params.get("totalRecordCount");
//        int a  =Integer.parseInt(s);
//        System.out.println(a);

    }
}