package com.sxy.zongce.service;

import com.sxy.zongce.entity.PublicWelfare;
import com.sxy.zongce.entity.Student;
import com.sxy.zongce.util.PageRequest;
import com.sxy.zongce.util.PageResult;

import java.util.List;

public interface PublicWelfareService {

    //测试，最后会删掉
    List<PublicWelfare> findAll();

    //增量导入公益积分
    void incrementImport(List<PublicWelfare> publicWelfareList);

    //覆盖导入公益积分
    void coverImport(List<PublicWelfare> publicWelfareList);

    //根据辅导员id分页查询公益积分
    PageResult listPW(PageRequest pageRequest, String menter_Id);

    //根据辅导员id分页条件查询公益积分
    PageResult searchPW(PageRequest pageRequest, String menter_Id,
                        Student student, String semester);
}
