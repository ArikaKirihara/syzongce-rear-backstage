package com.sxy.zongce.service;

import com.sxy.zongce.entity.GPA;
import com.sxy.zongce.entity.Student;
import com.sxy.zongce.util.PageRequest;
import com.sxy.zongce.util.PageResult;

import java.util.List;

public interface GPAService {

    //测试，最后会删掉
    List<GPA> findAll();

    //增量导入绩点
    void incrementImport(List<GPA> gpaList);

    //覆盖导入绩点
    void coverImport(List<GPA> gpaList);

    //根据辅导员id分页查询绩点
    PageResult listGPA(PageRequest pageRequest, String menter_Id);

    //根据辅导员id分页条件查询绩点
    PageResult searchGPA(PageRequest pageRequest, String menter_Id,
                         Student student, String semester);
}
