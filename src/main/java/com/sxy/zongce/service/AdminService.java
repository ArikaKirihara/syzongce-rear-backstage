package com.sxy.zongce.service;

import com.sxy.zongce.entity.Admin;
import com.sxy.zongce.entity.Student;
import com.sxy.zongce.util.PageRequest;
import com.sxy.zongce.util.PageResult;
import com.sxy.zongce.vo.ApplicationVo;
import com.sxy.zongce.vo.ScoreVo;

import java.util.List;

public interface AdminService {

    //测试，最后会删掉
    List<Admin> findAll();

    //查询管理员是否存在
    boolean isExist(String admin_Id);

    //根据工号查询密码
    String findPwdById(String admin_Id);

    //根据工号修改密码
    void updatePwd(String admin_Id,String admin_Pwd);

    //根据工号获取管理员姓名
    String getAdminName(String admin_Id);

    //根据管理员员id分页查询认证申请
    PageResult listApp(PageRequest pageRequest,String admin_Id);

    //根据管理员id分页条件查询认证申请
    PageResult searchApp(PageRequest pageRequest,String admin_Id,
                                  String startTime, String endTime,
                                  String stu_Id, String stu_Name);

    //管理员查看综测成绩
    PageResult checkScoreByAdmin(PageRequest pageRequest, String admin_Id);

    //管理员条件查询综测成绩
    PageResult searchScoreByAdmin(PageRequest pageRequest, String admin_Id,
                                  Student student, String semester);
}
