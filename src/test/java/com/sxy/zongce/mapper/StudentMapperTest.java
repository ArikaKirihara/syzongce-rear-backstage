package com.sxy.zongce.mapper;

import com.sxy.zongce.entity.Student;
import com.sxy.zongce.vo.StudentVo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class StudentMapperTest {

    @Autowired
    StudentMapper studentMapper;

    @Test
    public void findStuInfo(){
        StudentVo stuInfo = studentMapper.findStuInfo("190802103028");
        System.out.println(stuInfo.getStu_Id());
        System.out.println(stuInfo.getStu_Name());
    }

    @Test
    void searchStuByMenter(){
        StudentVo studentVo = new StudentVo();
        studentVo.setStu_Id("190802103028");
        List<StudentVo> stuInfo = studentMapper.searchStuByMenter("80210203",studentVo.getStu_Id(),
                studentVo.getStu_Name(),studentVo.getGrade(),studentVo.getMajor(),studentVo.getStu_class());
//        System.out.println(stuInfo.size());
        System.out.println(stuInfo.get(0).getStu_Name());
//        System.out.println(studentVo.getStu_Id());
//        System.out.println(studentVo.getStu_Name());
    }
}