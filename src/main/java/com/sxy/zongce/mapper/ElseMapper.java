package com.sxy.zongce.mapper;


import com.sxy.zongce.entity.Else;
import com.sxy.zongce.vo.ElseVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ElseMapper {

    //测试，最后会删掉
    List<Else> findAll();

    //增量导入其他加分
    int incrementImport(List<Else> elseList);

    //覆盖导入其他加分
    int coverImport(List<Else> elseList);

    //录入其他加分
    int addElse(Else anElse);

    //根据辅导员id分页查询其他加分
    List<ElseVo> listElse(String menter_Id);

    //根据辅导员id分页条件查询其他加分
    List<ElseVo> searchElse(String menter_Id,
                            int grade, String major, String stu_class,
                            String stu_Id, String stu_Name, String semester);
}
