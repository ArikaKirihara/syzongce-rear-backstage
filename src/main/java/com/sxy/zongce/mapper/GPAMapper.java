package com.sxy.zongce.mapper;

import com.sxy.zongce.entity.GPA;
import com.sxy.zongce.vo.GPAVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GPAMapper {

    //测试，最后会删掉
    List<GPA> findAll();

    //增量导入绩点
    int incrementImport(List<GPA> gpaList);

    //覆盖导入绩点
    int coverImport(List<GPA> gpaList);

    //根据辅导员id分页查询绩点
    List<GPAVo> listGPA(String menter_Id);

    //根据辅导员id分页条件查询绩点
    List<GPAVo> searchGPA(String menter_Id,
                          int grade, String major, String stu_class,
                          String stu_Id, String stu_Name, String semester);
}
