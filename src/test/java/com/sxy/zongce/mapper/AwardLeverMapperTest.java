package com.sxy.zongce.mapper;

import com.sxy.zongce.entity.AwardLever;
import com.sxy.zongce.entity.Project;
import com.sxy.zongce.vo.AwardLeverVo;
import com.sxy.zongce.vo.ProjectVo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class AwardLeverMapperTest {
    @Autowired
    AwardLeverMapper awardLeverMapper;

    @Test
    void findAllAwardLever() {
        List<AwardLeverVo> AwardLeverVoList =  awardLeverMapper.findAllAwardLever("80210122");
        System.out.println(AwardLeverVoList.size());
        System.out.println(AwardLeverVoList.get(1).getAwl_Id());
        System.out.println(AwardLeverVoList.get(1).getAwl_Detail());
        System.out.println(AwardLeverVoList.get(1).getAwl_Score());
        System.out.println(AwardLeverVoList.get(1).getAwl_Display());
    }

    @Test
    void updateAwardLeverById() {
        System.out.println(awardLeverMapper.updateAwardLeverById("A100101"));
    }

    @Test
    void addAwardLever() {
        AwardLever awardLever =new AwardLever();
        awardLever.setAwl_Id("A10102");
        awardLever.setAwl_Detail("省级荣誉");
        awardLever.setAwl_Score(5.0f);
        awardLever.setPro_Id("A1");
        awardLever.setAdmin_Id("80210122");
        System.out.println(awardLeverMapper.addAwardLever(awardLever));
    }



    @Test
    void findScoreById() {
        Float a = awardLeverMapper.findScoreById("A100101");
        System.out.println(a);
    }

    @Test
    void isVisible() {
        String a = awardLeverMapper.isVisible("A100101");
        System.out.println(a);
    }
}

