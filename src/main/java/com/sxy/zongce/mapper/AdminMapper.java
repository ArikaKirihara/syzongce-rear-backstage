package com.sxy.zongce.mapper;

import com.sxy.zongce.entity.Admin;
import com.sxy.zongce.vo.ApplicationVo;
import com.sxy.zongce.vo.ScoreVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface AdminMapper {

    //测试，最后会删掉
    List<Admin> findAll();

    //查询管理员是否存在
    Admin isExist(String admin_Id);

    //根据工号查询密码
    String findPwdById(String admin_Id);

    //根据工号修改密码
    void updatePwd(String admin_Id,String admin_Pwd);

    //根据工号获取管理员姓名
    String getAdminName(String admin_Id);

    //根据管理员员id分页查询认证申请
    List<ApplicationVo> listApp(String admin_Id);

    //根据管理员id分页条件查询认证申请
    List<ApplicationVo> searchApp(String admin_Id,
                                  String startTime, String endTime,
                                  String stu_Id, String stu_Name);

    //管理员查看综测成绩
    List<ScoreVo> checkScoreByAdmin(Map<String,Object> params);

    //管理员条件查询综测成绩
    List<ScoreVo> searchScoreByAdmin(Map<String,Object> params);
}
