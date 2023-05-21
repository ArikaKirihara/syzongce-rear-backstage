package com.sxy.zongce.service.impl;

import com.sxy.zongce.entity.Application;
import com.sxy.zongce.exception.ApplicationException;
import com.sxy.zongce.exception.ResponseCode;
import com.sxy.zongce.util.PageRequest;
import com.sxy.zongce.util.PageResult;
import com.sxy.zongce.util.SemesterUtil;
import com.sxy.zongce.vo.ApplicationVo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.ClassUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class ApplicationServiceImplTest {
    @Autowired
    private ApplicationServiceImpl applicationService;

    @Test
    void addApp() {
        System.out.println(SemesterUtil.getCurrentSemester());
    }

    @Test
    void findUnchecked(){
        PageRequest pageRequest = new PageRequest();
        pageRequest.setPageNum(10);
        pageRequest.setPageSize(20);
        PageResult pageResult = applicationService.findUnchecked(pageRequest,"80210201");
        List<ApplicationVo> applicationVos = (List<ApplicationVo>)pageResult.getContent();
//        System.out.println(applicationVos.get(1).getAtt_url());
    }

    @Test
    void checkAtt(){

        System.out.println(ClassUtils.getDefaultClassLoader()
                .getResource("static/upload/attachment").getPath());
    }
}