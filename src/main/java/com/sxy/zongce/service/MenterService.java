package com.sxy.zongce.service;

import com.sxy.zongce.entity.Menter;
import com.sxy.zongce.entity.Student;
import com.sxy.zongce.util.PageRequest;
import com.sxy.zongce.util.PageResult;
import com.sxy.zongce.vo.ApplicationVo;
import com.sxy.zongce.vo.ScoreVo;

import java.util.List;

public interface MenterService {

    //测试，最后会删掉
    List<Menter> findAll();

    //查询辅导员是否存在
    boolean isExist(String menter_Id);

    //根据工号查询密码
    String findPwdById(String menter_Id);

    //根据工号修改密码
    void updatePwd(String menter_Id,String m_Pwd);

    //根据工号获取辅导员姓名
    String getMenterName(String menter_Id);

    //根据辅导员id分页查询认证申请
    PageResult listApp(PageRequest pageRequest,String menter_Id);

    //根据辅导员id分页条件查询认证申请
    PageResult searchApp(PageRequest pageRequest, String menter_Id,
                         String startTime, String endTime,
                         String stu_Id, String stu_Name);

    //辅导员查看综测成绩
    PageResult checkScoreByMenter(PageRequest pageRequest,String menter_Id);

    //辅导员条件查询综测成绩
    PageResult searchScoreByMenter(PageRequest pageRequest, String menter_Id,
                                      Student student, String semester);
}
