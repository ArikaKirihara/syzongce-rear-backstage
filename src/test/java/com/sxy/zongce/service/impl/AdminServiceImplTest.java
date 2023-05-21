package com.sxy.zongce.service.impl;

import com.sxy.zongce.entity.Student;
import com.sxy.zongce.mapper.AdminMapper;
import com.sxy.zongce.vo.ScoreVo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class AdminServiceImplTest {

    @Autowired
    AdminServiceImpl adminService;
    @Autowired
    AdminMapper adminMapper;

    @Test
    void isExist() {
        System.out.println(adminService.isExist("80210122"));
    }

    @Test
    void findPwdById() {
        String pwd = adminService.findPwdById("80210122");
        System.out.println(pwd);
    }

    @Test
    void updatePwd() {
        adminService.updatePwd("80210122","123456");
        System.out.println(adminService.findPwdById("80210122"));
    }

    @Test
    void getAdminName(){
        System.out.println(adminService.getAdminName("80210122"));
    }

    @Test
    void searchScoreByAdmin(){
        Student student = new Student();
        student.setStu_Id("190803103519");
//        List<ScoreVo> list = adminMapper.searchScoreByAdmin("80210122",
//                student.getGrade(), student.getMajor(), student.getStu_class(),
//                student.getStu_Id(), student.getStu_Name(),null);
//        System.out.println(list.size());
    }

    @Test
    void checkScoreByAdmin(){
//        List<ScoreVo> list = adminMapper.checkScoreByAdmin("80210122");
//        System.out.println(list.size());
    }
}