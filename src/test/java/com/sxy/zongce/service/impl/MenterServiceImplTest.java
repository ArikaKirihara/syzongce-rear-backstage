package com.sxy.zongce.service.impl;

import com.sxy.zongce.entity.Student;
import com.sxy.zongce.mapper.MenterMapper;
import com.sxy.zongce.util.PageRequest;
import com.sxy.zongce.util.PageResult;
import com.sxy.zongce.vo.ScoreVo;
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
class MenterServiceImplTest {

    @Autowired
    MenterServiceImpl menterService;
    @Autowired
    MenterMapper menterMapper;

    @Test
    void isExist() {
        System.out.println(menterService.isExist("80210201"));
    }

    @Test
    void findPwdById() {
        String pwd = menterService.findPwdById("80210201");
        System.out.println(pwd);
    }

    @Test
    void updatePwd() {
        menterService.updatePwd("80210201","123456");
        System.out.println(menterService.findPwdById("80210201"));
    }

    @Test
    void getMenterName(){
        System.out.println(menterService.getMenterName("80210201"));

    }

    @Test
    void checkScoreByMenter(){
        Student student = new Student();
        student.setMajor("国际经济与贸易");
        PageRequest pageRequest = new PageRequest();
        pageRequest.setPageNum(1);
        pageRequest.setPageSize(5);
        PageResult list = menterService.checkScoreByMenter(pageRequest,"80210201");
        System.out.println(list.getContent().size());

    }

    @Test
    void searchScoreByMenter(){
        Student student = new Student();
//        student.setMajor("国际经济与贸易");
//        PageRequest pageRequest = new PageRequest();
//        pageRequest.setPageNum(1);
//        pageRequest.setPageSize(5);
        Map<String,Object> params = new HashMap<>();
        params.put("menter_Id","80210201");
        params.put("pageIndex","1");
        params.put("pageSize","10");
        List<ScoreVo> list = menterMapper.checkScoreByMenter(params);
        System.out.println(list.size());
//        List<ScoreVo> list = menterMapper.searchScoreByMenter("80210201",
//                student.getGrade(), student.getMajor(), student.getStu_class(),
//                student.getStu_Id(), student.getStu_Name(),null);

    }

}