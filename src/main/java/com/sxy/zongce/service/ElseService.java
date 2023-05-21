package com.sxy.zongce.service;

import com.sxy.zongce.entity.Else;
import com.sxy.zongce.entity.Student;
import com.sxy.zongce.util.PageRequest;
import com.sxy.zongce.util.PageResult;

import java.util.List;

public interface ElseService {

    //测试，最后会删掉
    List<Else> findAll();

    //增量导入其他加分
    void incrementImport(List<Else> elseList);

    //覆盖导入其他加分
    void coverImport(List<Else> elseList);

    //录入其他加分
    void addElse(Else anElse);

    //根据辅导员id分页查询其他加分
    PageResult listElse(PageRequest pageRequest, String menter_Id);

    //根据辅导员id分页条件查询其他加分
    PageResult searchElse(PageRequest pageRequest, String menter_Id,
                          Student student, String semester);
}
