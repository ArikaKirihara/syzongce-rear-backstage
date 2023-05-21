package com.sxy.zongce.mapper;

import com.sxy.zongce.entity.PublicWelfare;
import com.sxy.zongce.vo.PublicWelfareVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PublicWelfareMapper {

    //测试，最后会删掉
    List<PublicWelfare> findAll();

    //增量导入公益积分
    int incrementImport(List<PublicWelfare> publicWelfareList);

    //覆盖导入公益积分
    int coverImport(List<PublicWelfare> publicWelfareList);

    //根据辅导员id分页查询公益积分
    List<PublicWelfareVo> listPW(String menter_Id);

    //根据辅导员id分页条件查询公益积分
    List<PublicWelfareVo> searchPW(String menter_Id,
                                   int grade, String major, String stu_class,
                                   String stu_Id, String stu_Name, String semester);
}
