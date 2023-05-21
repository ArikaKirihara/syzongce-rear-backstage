package com.sxy.zongce.service;

import com.sxy.zongce.entity.AwardLever;
import com.sxy.zongce.util.PageRequest;
import com.sxy.zongce.util.PageResult;
import com.sxy.zongce.vo.AwardLeverVo;

import java.util.List;

public interface AwardLeverService {

    //测试，最后会删掉
    List<AwardLever> findAll();

    //获取成果级别最大的id
    String findMaxId();

    //根据管理员id查询所有成果级别
    PageResult findAllAwardLever(PageRequest pageRequest, String admin_Id);

    //根据id更改成果级别状态
    void updateAwardLeverById(String awl_Id);

    //添加成果级别
    void addAwardLever(AwardLever awardLever);

    //根据id获取成果级别的分值
    float findScoreById (String awl_Id);

    //根据管理员id和项目类型id查询成果级别名称和成果级别id
    List<AwardLever> findAwardLever(String stu_Id,String pro_Id);

    //根据id查询成果级别可见性
    boolean isVisible(String awl_Id);

    //根据管理员id和模板分类id筛选项目类型
    PageResult findAwlByPro(PageRequest pageRequest, String admin_Id, String pro_Id);
}
