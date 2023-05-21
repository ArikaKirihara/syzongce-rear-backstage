package com.sxy.zongce.mapper;

import com.sxy.zongce.entity.Menter;
import com.sxy.zongce.vo.ApplicationVo;
import com.sxy.zongce.vo.ScoreVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface MenterMapper {

    //测试，最后会删掉
    List<Menter> findAll();

    //查询辅导员是否存在
    Menter isExist(String menter_Id);

    //根据工号查询密码
    String findPwdById(String menter_Id);

    //根据工号修改密码
    void updatePwd(String menter_Id,String m_Pwd);

    //根据工号获取辅导员姓名
    String getMenterName(String menter_Id);

    //根据辅导员id分页查询认证申请
    List<ApplicationVo> listApp(String menter_Id);

    //根据辅导员id分页条件查询认证申请
    List<ApplicationVo> searchApp(String menter_Id,
                                  String startTime, String endTime,
                                  String stu_Id, String stu_Name);

    //辅导员查看综测成绩
    List<ScoreVo> checkScoreByMenter(Map<String,Object> params);

    //辅导员条件查询综测成绩
    List<ScoreVo> searchScoreByMenter(Map<String,Object> params);
}
