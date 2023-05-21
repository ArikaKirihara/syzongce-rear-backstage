package com.sxy.zongce.service.impl;

import com.sxy.zongce.entity.Violation;
import com.sxy.zongce.mapper.ViolationMapper;
import com.sxy.zongce.util.IdUtil;
import com.sxy.zongce.util.PageRequest;
import com.sxy.zongce.util.SemesterUtil;
import com.sxy.zongce.vo.ViolationVo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class ViolationServiceImplTest {

    @Autowired
    ViolationMapper violationMapper;

    @Test
    void incrementImport() {
    }

    @Test
    void coverImport() {
    }

    @Test
    void findVioByStudent() {
    }

    @Test
    void findVioByMenter() {
//        PageRequest pageRequest = new PageRequest();
//        pageRequest.setPageNum(1);
//        pageRequest.setPageSize(10);
        List<ViolationVo> violationVoList = violationMapper.findVioByMenter("80210201");
        for(ViolationVo v: violationVoList){
            String cancel_Time = v.getCancel_Time();
            if("0001-01-01".equals(cancel_Time)){
                v.setCancel_Time("————");
            }
        }
        System.out.println(violationVoList.get(1).getCancel_Time());
    }

    @Test
    void searchVioByMenter() {
    }

    @Test
    void cancelVioById() {
    }

    @Test
    void deleteVioById() {
    }

    @Test
    void entryViolation() {
        Violation violation = new Violation();
        violation.setVio_Id(IdUtil.createViolationId("v1020"));
        violation.setSemester(SemesterUtil.getCurrentSemester());
        violation.setVio_detail("未填问卷");
        violation.setVio_Time("2022-09-01");
        violation.setVio_Score(-1);
        violation.setStu_Id("190802103028");
        System.out.println(violationMapper.entryViolation(violation));
    }
}