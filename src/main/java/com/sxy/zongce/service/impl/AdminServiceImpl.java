package com.sxy.zongce.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sxy.zongce.entity.Admin;
import com.sxy.zongce.entity.Student;
import com.sxy.zongce.exception.ResponseCode;
import com.sxy.zongce.exception.UserException;
import com.sxy.zongce.mapper.AdminMapper;
import com.sxy.zongce.service.AdminService;
import com.sxy.zongce.util.PageRequest;
import com.sxy.zongce.util.PageResult;
import com.sxy.zongce.util.PageUtil;
import com.sxy.zongce.vo.ApplicationVo;
import com.sxy.zongce.vo.GPAVo;
import com.sxy.zongce.vo.ScoreVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    //测试，最后会删掉
    @Override
    public List<Admin> findAll() {
        return adminMapper.findAll();
    }

    @Override
    public boolean isExist(String admin_Id) {
        boolean res = true;
        Admin admin = null;
        try {
            admin = adminMapper.isExist(admin_Id);
            if(admin == null){
                res = false;
                throw new UserException(ResponseCode.USER_NOT_EXIST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(ResponseCode.INNER_ERROR.getMsg());
        }
        return res;
    }

    @Override
    public String findPwdById(String admin_Id) {
        String pwd = "";
        try {
            pwd = adminMapper.findPwdById(admin_Id);
            if(pwd == null){
                throw new UserException(ResponseCode.USER_NOT_EXIST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(ResponseCode.INNER_ERROR.getMsg());
        }

        return pwd;
    }

    @Override
    public void updatePwd(String admin_Id, String admin_Pwd) {
        boolean flag = false;
        try {
            flag = isExist(admin_Id);
            if(flag == true)
                adminMapper.updatePwd(admin_Id,admin_Pwd);
        } catch (UserException e){
            e.printStackTrace();
            throw new UserException(ResponseCode.USER_NOT_EXIST);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(ResponseCode.INNER_ERROR.getMsg());
        }
    }

    @Override
    public String getAdminName(String admin_Id) {
        String name = "";
        try {
            name = adminMapper.getAdminName(admin_Id);
            if(name == null)
                throw new UserException(ResponseCode.USER_NOT_EXIST);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(ResponseCode.INNER_ERROR.getMsg());
        }

        return name;
    }

    @Override
    public PageResult listApp(PageRequest pageRequest,String admin_Id) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        List<ApplicationVo> list = null;
        PageInfo<ApplicationVo> pageInfo = null;
        try {
            PageHelper.startPage(pageNum, pageSize);
            list = adminMapper.listApp(admin_Id);
            pageInfo = new PageInfo<>(list);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(ResponseCode.INNER_ERROR.getMsg());
        }

        return PageUtil.getPageResult(pageInfo);
    }

    @Override
    public PageResult searchApp(PageRequest pageRequest,String admin_Id,
                                String startTime, String endTime,
                                String stu_Id, String stu_Name) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        List<ApplicationVo> list = null;
        PageInfo<ApplicationVo> pageInfo = null;
        try {
            PageHelper.startPage(pageNum, pageSize);
            list = adminMapper.searchApp(admin_Id,startTime,endTime,stu_Id,stu_Name);
            pageInfo = new PageInfo<>(list);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(ResponseCode.INNER_ERROR.getMsg());
        }

        return PageUtil.getPageResult(pageInfo);
    }

    @Override
    public PageResult checkScoreByAdmin(PageRequest pageRequest, String admin_Id) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        long totalSize = 0;
        int totalPages = 0;
        List<ScoreVo> list = null;
        try {
            Map<String,Object> params = new HashMap<>();
            params.put("admin_Id",admin_Id);
            params.put("pageIndex",pageNum);
            params.put("pageSize",pageSize);
            list = adminMapper.checkScoreByAdmin(params);
            String str1 = String.valueOf(params.get("totalRecordCount"));
            String str2 = String.valueOf(params.get("pageCount"));
            totalSize = Long.parseLong(str1);
            totalPages = Integer.parseInt(str2);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(ResponseCode.INNER_ERROR.getMsg());
        }

        return PageUtil.getPageResult(pageNum,pageSize,totalSize,totalPages,list);
    }

    @Override
    public PageResult searchScoreByAdmin(PageRequest pageRequest, String admin_Id,
                                         Student student, String semester) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        long totalSize = 0;
        int totalPages = 0;
        List<ScoreVo> list = null;
        try {
            Map<String,Object> params = new HashMap<>();
            params.put("stu_Id",student.getStu_Id());
            params.put("admin_Id",admin_Id);
            params.put("semeter",semester);
            params.put("grade",student.getGrade());
            params.put("major",student.getMajor());
            params.put("stu_Name",student.getStu_Name());
            params.put("pageIndex",pageNum);
            params.put("pageSize",pageSize);
            list = adminMapper.searchScoreByAdmin(params);
            String str1 = String.valueOf(params.get("totalRecordCount"));
            String str2 = String.valueOf(params.get("pageCount"));
            totalSize = Long.parseLong(str1);
            totalPages = Integer.parseInt(str2);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(ResponseCode.INNER_ERROR.getMsg());
        }

        return PageUtil.getPageResult(pageNum,pageSize,totalSize,totalPages,list);
    }


}
