package com.sxy.zongce.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sxy.zongce.entity.Application;
import com.sxy.zongce.exception.ApplicationException;
import com.sxy.zongce.exception.ResponseCode;
import com.sxy.zongce.mapper.ApplicationMapper;
import com.sxy.zongce.service.ApplicationService;
import com.sxy.zongce.util.PageRequest;
import com.sxy.zongce.util.PageResult;
import com.sxy.zongce.util.PageUtil;
import com.sxy.zongce.vo.ApplicationVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private ApplicationMapper applicationMapper;

    //测试，最后会删掉
    @Override
    public List<Application> findAll() {
        return applicationMapper.findAll();
    }

    @Override
    public String findMaxId() {
        return applicationMapper.findMaxId();
    }

    @Override
    public String checkState(String apply_Id) {
        String state = applicationMapper.checkState(apply_Id);
        if("".equals(state)){
            throw new ApplicationException(ResponseCode.APP_CHECKED);
        }
        return state;
    }

    @Override
    public boolean isExist(String apply_Id) {
        boolean res = true;
        Application application = applicationMapper.isExist(apply_Id);
        if(application == null){
            res = false;
            throw new ApplicationException(ResponseCode.APP_NOT_EXIST);
        }
        return res;
    }

    @Override
    public String checkReason(String apply_Id) {
        String reason = applicationMapper.checkReason(apply_Id);
        if(reason == null){
            throw new ApplicationException(ResponseCode.APP_NOT_EXIST);
        }
        return reason;
    }

    @Override
    public void addApp(Application application) {
        int res = applicationMapper.addApp(application);
        if (res == 0)
            throw new ApplicationException(ResponseCode.APP_EXITING);
    }

    @Override
    public void updateApp(Application application) {
        int res  = applicationMapper.updateApp(application);
        if (res == 0)
            throw new ApplicationException(ResponseCode.APP_CHECKED);
    }

    @Override
    public PageResult findUnchecked(PageRequest pageRequest,String menter_Id) {

        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        List<ApplicationVo> list = null;
        PageInfo<ApplicationVo> pageInfo = null;
        try {
            PageHelper.startPage(pageNum, pageSize);
            list = applicationMapper.findUnchecked(menter_Id);
            pageInfo = new PageInfo<>(list);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(ResponseCode.INNER_ERROR.getMsg());
        }

        return PageUtil.getPageResult(pageInfo);
    }

    @Override
    public PageResult searchUnchecked(PageRequest pageRequest,String menter_Id,
                                      String startTime, String endTime,
                                      String stu_Id, String stu_Name) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        List<ApplicationVo> list = null;
        PageInfo<ApplicationVo> pageInfo = null;
        try {
            PageHelper.startPage(pageNum, pageSize);
            list = applicationMapper.searchUnchecked(menter_Id,startTime,endTime,stu_Id,stu_Name);
            pageInfo = new PageInfo<>(list);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(ResponseCode.INNER_ERROR.getMsg());
        }

        return PageUtil.getPageResult(pageInfo);
    }

    @Override
    public void passApp(String apply_Id) {
        int res = applicationMapper.passApp(apply_Id);
        if(res == 0){
            throw new ApplicationException(ResponseCode.APP_CHECKED);
        }
    }

    @Override
    public void failApp(String apply_Id,String reason) {
        int res = applicationMapper.failApp(apply_Id,reason);
        if(res == 0){
            throw new ApplicationException(ResponseCode.APP_CHECKED);
        }
    }

    @Override
    public void cancelApp(String apply_Id) {
        int res = applicationMapper.cancelApp(apply_Id);
        if(res == 0){
            throw new ApplicationException(ResponseCode.APP_CHECKED);
        }
    }

    @Override
    public void deleteApp(String apply_Id) {
        int res = applicationMapper.deleteApp(apply_Id);
        if(res == 0){
            throw new ApplicationException(ResponseCode.APP_CHECKED);
        }
    }

    @Override
    public String checkAtt(String apply_Id) {
        String att_url = applicationMapper.checkAtt(apply_Id);
        if("".equals(att_url)){
            throw new ApplicationException(ResponseCode.APP_NOT_EXIST);
        }
        return att_url;
    }

    @Override
    public ApplicationVo getApplication(String apply_Id) {
        ApplicationVo applicationVo = applicationMapper.getApplication(apply_Id);
        if(applicationVo == null){
            throw new ApplicationException(ResponseCode.APP_NOT_EXIST);
        }
        return applicationVo;
    }

}
