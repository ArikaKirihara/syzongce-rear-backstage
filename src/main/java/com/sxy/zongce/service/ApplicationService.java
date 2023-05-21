package com.sxy.zongce.service;


import com.sxy.zongce.entity.Application;
import com.sxy.zongce.util.PageRequest;
import com.sxy.zongce.util.PageResult;
import com.sxy.zongce.vo.ApplicationVo;

import java.util.List;

public interface ApplicationService {

    //测试，最后会删掉
    List<Application> findAll();

    //获取申请单最大的id（有用别动）
    String findMaxId();

    //根据id查询认证申请状态
    String checkState(String apply_Id);

    //查询申请单是否存在
    boolean isExist(String apply_Id);

    //根据id查看不通过原因
    String checkReason(String apply_Id);

    //新增认证申请
    void addApp(Application application);

    //修改认证申请
    void updateApp(Application application);

    //根据辅导员id分页查询所有状态为待审核的认证申请
    PageResult findUnchecked(PageRequest pageRequest, String menter_Id);

    //根据辅导员id分页条件查询所有状态为待审核的认证申请
    PageResult searchUnchecked(PageRequest pageRequest,String menter_Id,
                                        String startTime, String endTime,
                                        String stu_Id, String stu_Name);

    //根据申请单id更改状态为通过
    void passApp(String apply_Id);

    //根据申请单id更改状态为不通过
    void failApp(String apply_Id,String reason);

    //根据申请单id更改状态为待审核
    void cancelApp(String apply_Id);

    //根据id删除认证申请
    void deleteApp(String apply_Id);

    //根据id获取附件地址
    String checkAtt(String apply_Id);

    //根据id获取待修改申请单
    ApplicationVo getApplication(String apply_Id);

}
