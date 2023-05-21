package com.sxy.zongce.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sxy.zongce.entity.Student;
import com.sxy.zongce.entity.Violation;
import com.sxy.zongce.exception.*;
import com.sxy.zongce.mapper.ViolationMapper;
import com.sxy.zongce.service.ViolationService;
import com.sxy.zongce.util.PageRequest;
import com.sxy.zongce.util.PageResult;
import com.sxy.zongce.util.PageUtil;
import com.sxy.zongce.vo.ViolationVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViolationServiceImpl implements ViolationService {

    @Autowired
    private ViolationMapper violationMapper;

    //测试，最后会删掉
    @Override
    public List<Violation> findAll() {
        return violationMapper.findAll();
    }

    @Override
    public String findMaxId() {
        return violationMapper.findMaxId();
    }

    @Override
    public boolean isExist(String vio_Id) {
        boolean res = true;
        Violation violation = violationMapper.isExist(vio_Id);
        if(violation == null){
            res = false;
            throw new ExistingException(ResponseCode.VIO_NOT_EXIST);
        }
        return res;
    }

    @Override
    public boolean isCanceled(String vio_Id) {
        boolean res = false;
        String cancel_Time = violationMapper.isCanceled(vio_Id);
        if(!"0001-01-01".equals(cancel_Time)){
            res = true;
        } else if ("".equals(cancel_Time)){
            throw new VisibilityException(ResponseCode.VIO_NOT_EXIST);
        }
        return res;
    }

    @Override
    public void incrementImport(List<Violation> violationList) {
        int res = violationMapper.incrementImport(violationList);
        if(res == 0){
            throw new FileException(ResponseCode.REPEAT_IMPORT);
        }
    }

    @Override
    public void coverImport(List<Violation> violationList) {
        int res = violationMapper.coverImport(violationList);
        if(res == 0){
            throw new FileException(ResponseCode.REPEAT_IMPORT);
        }
    }

    @Override
    public PageResult findVioByStudent(PageRequest pageRequest, String stu_Id) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        List<ViolationVo> list = null;
        PageInfo<ViolationVo> pageInfo = null;
        try {
            PageHelper.startPage(pageNum, pageSize);
            list = violationMapper.findVioByStudent(stu_Id);
            //修改违纪时间显示方式
            for(ViolationVo v: list){
                String cancel_Time = v.getCancel_Time();
                if("0001-01-01".equals(cancel_Time)){
                    v.setCancel_Time("————");
                }
            }
            pageInfo = new PageInfo<>(list);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(ResponseCode.INNER_ERROR.getMsg());
        }

        return PageUtil.getPageResult(pageInfo);
    }

    @Override
    public PageResult findVioByMenter(PageRequest pageRequest, String menter_Id) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        List<ViolationVo> list = null;
        PageInfo<ViolationVo> pageInfo = null;
        try {
            PageHelper.startPage(pageNum, pageSize);
            list = violationMapper.findVioByMenter(menter_Id);
            //修改违纪时间显示方式
            for(ViolationVo v: list){
                String cancel_Time = v.getCancel_Time();
                if("0001-01-01".equals(cancel_Time)){
                    v.setCancel_Time("————");
                }
            }
            pageInfo = new PageInfo<>(list);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(ResponseCode.INNER_ERROR.getMsg());
        }

        return PageUtil.getPageResult(pageInfo);
    }

    @Override
    public PageResult searchVioByMenter(PageRequest pageRequest, String menter_Id, String vio_Time,
                                        Student student, String vio_detail) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        List<ViolationVo> list = null;
        PageInfo<ViolationVo> pageInfo = null;
        try {
            PageHelper.startPage(pageNum, pageSize);
            list = violationMapper.searchVioByMenter(menter_Id,vio_Time,
                    student.getStu_Id(),student.getStu_Name(),
                    student.getMajor(),student.getStu_class(),vio_detail);
            //修改违纪时间显示方式
            for(ViolationVo v: list){
                String cancel_Time = v.getCancel_Time();
                if("0001-01-01".equals(cancel_Time)){
                    v.setCancel_Time("————");
                }
            }
            pageInfo = new PageInfo<>(list);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(ResponseCode.INNER_ERROR.getMsg());
        }

        return PageUtil.getPageResult(pageInfo);
    }

    @Override
    public void cancelVioById(String vio_Id) {
        int res = violationMapper.cancelVioById(vio_Id);
        if(res == 0)
            throw new RuntimeException(ResponseCode.INNER_ERROR.getMsg());
    }

    @Override
    public void deleteVioById(String vio_Id) {
        int res = violationMapper.deleteVioById(vio_Id);
        if(res == 0)
            throw new RuntimeException(ResponseCode.INNER_ERROR.getMsg());
    }

    @Override
    public void entryViolation(Violation violation) {
        int res = violationMapper.entryViolation(violation);
        if(res == 0)
            throw new AddException(ResponseCode.DATA_EXISTING);
    }

    @Override
    public PageResult listViolation(PageRequest pageRequest, String menter_Id) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        List<ViolationVo> list = null;
        PageInfo<ViolationVo> pageInfo = null;
        try {
            PageHelper.startPage(pageNum, pageSize);
            list = violationMapper.listViolation(menter_Id);
            //修改违纪时间显示方式
            for(ViolationVo v: list){
                String cancel_Time = v.getCancel_Time();
                if("0001-01-01".equals(cancel_Time)){
                    v.setCancel_Time("————");
                }
            }
            pageInfo = new PageInfo<>(list);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(ResponseCode.INNER_ERROR.getMsg());
        }

        return PageUtil.getPageResult(pageInfo);
    }

    @Override
    public PageResult searchViolation(PageRequest pageRequest, String menter_Id, Student student, String semester) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        List<ViolationVo> list = null;
        PageInfo<ViolationVo> pageInfo = null;
        try {
            PageHelper.startPage(pageNum, pageSize);
            list = violationMapper.searchViolation(menter_Id,
                    student.getGrade(), student.getMajor(), student.getStu_class(),
                    student.getStu_Id(), student.getStu_Name(), semester);
            //修改违纪时间显示方式
            for(ViolationVo v: list){
                String cancel_Time = v.getCancel_Time();
                if("0001-01-01".equals(cancel_Time)){
                    v.setCancel_Time("————");
                }
            }
            pageInfo = new PageInfo<>(list);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(ResponseCode.INNER_ERROR.getMsg());
        }

        return PageUtil.getPageResult(pageInfo);
    }
}
