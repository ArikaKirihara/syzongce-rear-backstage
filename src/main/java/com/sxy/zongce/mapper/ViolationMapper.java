package com.sxy.zongce.mapper;

import com.sxy.zongce.entity.Violation;
import com.sxy.zongce.vo.ViolationVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViolationMapper {

    //测试，最后会删掉
    List<Violation> findAll();

    //获取违规违纪最大的id（有用别动）
    String findMaxId();

    //增量导入违纪通报
    int incrementImport(List<Violation> violationList);

    //覆盖导入违纪通报
    int coverImport(List<Violation> violationList);

    //查询违纪通报是否存在
    Violation isExist(String vio_Id);

    //查询违纪通报是否已消除
    String isCanceled(String vio_Id);

    //根据学生id查询违纪通报和学生姓名
    List<ViolationVo> findVioByStudent(String stu_Id);

    //根据辅导员id查询相关学生的违纪通报和学生姓名
    List<ViolationVo> findVioByMenter(String menter_Id);

    //根据辅导员id查询相关学生的违纪通报和学生姓名
    List<ViolationVo> searchVioByMenter(String menter_Id, String vio_Time,
                                        String stu_Id, String stu_Name,
                                        String major, String stu_class, String vio_detail);

    //根据id更改消除时间
    int cancelVioById(String vio_Id);

    //根据id删除违纪通报
    int deleteVioById(String vio_Id);

    //录入违纪通报
    int entryViolation(Violation violation);

    //查看违纪通报列表（导入）
    List<ViolationVo> listViolation(String menter_Id);

    //查看违纪通报列表（导入）
    List<ViolationVo> searchViolation(String menter_Id,
                                      int grade, String major, String stu_class,
                                      String stu_Id, String stu_Name, String semester);
}
