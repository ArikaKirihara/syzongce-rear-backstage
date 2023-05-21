package com.sxy.zongce.service;

import com.sxy.zongce.entity.Student;
import com.sxy.zongce.util.PageRequest;
import com.sxy.zongce.util.PageResult;
import com.sxy.zongce.vo.ApplicationVo;
import com.sxy.zongce.vo.ScoreVo;
import com.sxy.zongce.vo.StudentVo;

import java.util.List;

public interface StudentService {

    //测试，最后会删掉
    List<Student> findAll();

    //查询学生是否存在
    boolean isExist(String stu_Id);

    //根据学号查询密码
    String findPwdById(String stu_Id);

    //根据学号修改密码
    void updatePwd(String stu_Id,String stu_Pwd);

    //根据学号获取学生姓名
    String getStuName(String stu_Id);

    //通过学号查询学生个人信息
    StudentVo findStuInfo(String stu_Id);

    //通过辅导员工号查询学生信息
    PageResult findStuByMenter(PageRequest pageRequest,String menter_Id);

    //辅导员条件查询学生信息
    PageResult searchStuByMenter(PageRequest pageRequest,String menter_Id,Student student);

    //通过管理员工号查询学生信息
    PageResult findStuByAdmin(PageRequest pageRequest,String admin_Id);

    //管理员条件查询学生信息
    PageResult searchStuByAdmin(PageRequest pageRequest,String admin_Id,Student student);

    //根据学生id分页查询认证申请
    PageResult findApp(PageRequest pageRequest,String stu_Id);

    //学生查看综测成绩
    List<ScoreVo> checkScoreByStudent(String stu_Id);

    //模块详细分值
    PageResult checkScoreDetail(PageRequest pageRequest,String semester,String stu_Id);

}
