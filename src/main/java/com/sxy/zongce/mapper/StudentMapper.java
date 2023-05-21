package com.sxy.zongce.mapper;

import com.sxy.zongce.entity.Student;
import com.sxy.zongce.vo.ApplicationVo;
import com.sxy.zongce.vo.AwardLeverVo;
import com.sxy.zongce.vo.ScoreVo;
import com.sxy.zongce.vo.StudentVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {

    //测试，最后会删掉
    List<Student> findAll();

    //查询学生是否存在
    Student isExist(String stu_Id);

    //根据学号查询密码
    String findPwdById(String stu_Id);

    //根据学号修改密码
    void updatePwd(String stu_Id,String stu_Pwd);

    //根据学号获取学生姓名
    String getStuName(String stu_Id);

    //通过学号查询学生个人信息
    StudentVo findStuInfo(String stu_Id);

    //通过辅导员工号查询学生信息
    List<StudentVo> findStuByMenter(String menter_Id);

    //辅导员条件查询学生信息
    List<StudentVo> searchStuByMenter(String menter_Id,
                                      String stu_Id,String stu_Name,
                                      int grade,String major,String stu_class);

    //通过管理员工号查询学生信息
    List<StudentVo> findStuByAdmin(String admin_Id);

    //管理员条件查询学生信息
    List<StudentVo> searchStuByAdmin(String admin_Id,
                                     String stu_Id,String stu_Name,
                                     int grade,String major,String stu_class);

    //根据学生id分页查询认证申请
    List<ApplicationVo> findApp(String stu_Id);

    //学生查看综测成绩
    List<ScoreVo> checkScoreByStudent(Map<String,Object> params);

    //模块详细分值
    List<AwardLeverVo> checkScoreDetail(String semester,String stu_Id);
}
