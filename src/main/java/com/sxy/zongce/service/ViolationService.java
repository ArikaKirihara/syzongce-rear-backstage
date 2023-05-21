package com.sxy.zongce.service;

import com.sxy.zongce.entity.Student;
import com.sxy.zongce.entity.Violation;
import com.sxy.zongce.util.PageRequest;
import com.sxy.zongce.util.PageResult;
import com.sxy.zongce.vo.ViolationVo;

import java.util.List;

public interface ViolationService {

    //测试，最后会删掉
    List<Violation> findAll();

    //获取违规违纪最大的id（有用别动）
    String findMaxId();

    //查询违纪通报是否存在
    boolean isExist(String vio_Id);

    //查询违纪通报是否已消除
    boolean isCanceled(String vio_Id);

    //增量导入违纪通报
    void incrementImport(List<Violation> violationList);

    //覆盖导入违纪通报
    void coverImport(List<Violation> violationList);

    //根据学生id查询违纪通报和学生姓名
    PageResult findVioByStudent(PageRequest pageRequest, String stu_Id);

    //根据辅导员id查询相关学生的违纪通报和学生姓名
    PageResult findVioByMenter(PageRequest pageRequest, String menter_Id);

    //根据辅导员id查询相关学生的违纪通报和学生姓名
    PageResult searchVioByMenter(PageRequest pageRequest, String menter_Id, String vio_Time,
                                 Student student,String vio_detail);

    //根据id更改消除时间
    void cancelVioById(String vio_Id);

    //根据id删除违纪通报
    void deleteVioById(String vio_Id);

    //录入违纪通报
    void entryViolation(Violation violation);

    //查看违纪通报列表（导入）
    PageResult listViolation(PageRequest pageRequest, String menter_Id);

    //查看违纪通报列表（导入）
    PageResult searchViolation(PageRequest pageRequest, String menter_Id,
                                      Student student, String semester);


}
