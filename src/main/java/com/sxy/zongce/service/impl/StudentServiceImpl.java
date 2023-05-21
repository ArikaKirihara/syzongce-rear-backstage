package com.sxy.zongce.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sxy.zongce.entity.AwardLever;
import com.sxy.zongce.entity.Student;
import com.sxy.zongce.exception.ResponseCode;
import com.sxy.zongce.exception.UserException;
import com.sxy.zongce.mapper.StudentMapper;
import com.sxy.zongce.service.StudentService;
import com.sxy.zongce.util.PageRequest;
import com.sxy.zongce.util.PageResult;
import com.sxy.zongce.util.PageUtil;
import com.sxy.zongce.vo.ApplicationVo;
import com.sxy.zongce.vo.AwardLeverVo;
import com.sxy.zongce.vo.ScoreVo;
import com.sxy.zongce.vo.StudentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private MenterServiceImpl menterService;
    @Autowired
    private AdminServiceImpl adminService;

    //测试，最后会删掉
    @Override
    public List<Student> findAll() {
        return studentMapper.findAll();
    }

    @Override
    public boolean isExist(String stu_Id) {
        boolean res = true;
        Student student = null;
        try {
            student = studentMapper.isExist(stu_Id);
            if(student == null){
                res = false;
                throw new UserException(ResponseCode.USER_NOT_EXIST);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(ResponseCode.INNER_ERROR.getMsg());
        }
        return res;
    }

    @Override
    public String findPwdById(String stu_Id) {
        String pwd = "";
        try {
            pwd = studentMapper.findPwdById(stu_Id);
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
    public void updatePwd(String stu_Id, String stu_Pwd) {
        boolean flag = false;
        try {
            flag = isExist(stu_Id);
            if(flag == true)
                studentMapper.updatePwd(stu_Id,stu_Pwd);
        } catch (UserException e){
            e.printStackTrace();
            throw new UserException(ResponseCode.USER_NOT_EXIST);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(ResponseCode.INNER_ERROR.getMsg());
        }
    }

    @Override
    public String getStuName(String stu_Id) {
        String name = "";
        try {
            name = studentMapper.getStuName(stu_Id);
            if(name == null)
                throw new UserException(ResponseCode.STUDENT_NOT_EXIST);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(ResponseCode.INNER_ERROR.getMsg());
        }

        return name;
    }

    @Override
    public StudentVo findStuInfo(String stu_Id) {
        StudentVo studentVo = null;
        try {
            studentVo = studentMapper.findStuInfo(stu_Id);
            if(studentVo == null)
                throw new UserException(ResponseCode.STUDENT_NOT_EXIST);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(ResponseCode.INNER_ERROR.getMsg());
        }

        return studentVo;

    }

    @Override
    public PageResult findStuByMenter(PageRequest pageRequest, String menter_Id) {
        boolean flag = false;
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        List<StudentVo> list = null;
        PageInfo<StudentVo> pageInfo = null;
        try {
            flag = menterService.isExist(menter_Id);
            if(flag == true){
                PageHelper.startPage(pageNum, pageSize);
                list = studentMapper.findStuByMenter(menter_Id);
                pageInfo = new PageInfo<>(list);
            }
        } catch (UserException e){
            e.printStackTrace();
            throw new UserException(ResponseCode.USER_NOT_EXIST);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(ResponseCode.INNER_ERROR.getMsg());
        }
        return PageUtil.getPageResult(pageInfo);
    }

    @Override
    public PageResult searchStuByMenter(PageRequest pageRequest, String menter_Id,Student student) {
        boolean flag = false;
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        List<StudentVo> list = null;
        PageInfo<StudentVo> pageInfo = null;
        try {
            flag = menterService.isExist(menter_Id);
            if(flag == true){
                PageHelper.startPage(pageNum, pageSize);
                list = studentMapper.searchStuByMenter(menter_Id,student.getStu_Id(), student.getStu_Name(),
                        student.getGrade(),student.getMajor(),student.getStu_class());
                pageInfo = new PageInfo<>(list);
            }
        } catch (UserException e){
            e.printStackTrace();
            throw new UserException(ResponseCode.USER_NOT_EXIST);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(ResponseCode.INNER_ERROR.getMsg());
        }
        return PageUtil.getPageResult(pageInfo);
    }

    @Override
    public PageResult findStuByAdmin(PageRequest pageRequest, String admin_Id) {
        boolean flag = false;
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        List<StudentVo> list = null;
        PageInfo<StudentVo> pageInfo = null;
        try {
            flag = adminService.isExist(admin_Id);
            if(flag == true){
                PageHelper.startPage(pageNum, pageSize);
                list = studentMapper.findStuByAdmin(admin_Id);
                pageInfo = new PageInfo<>(list);
            }
        } catch (UserException e){
            e.printStackTrace();
            throw new UserException(ResponseCode.USER_NOT_EXIST);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(ResponseCode.INNER_ERROR.getMsg());
        }
        return PageUtil.getPageResult(pageInfo);
    }

    @Override
    public PageResult searchStuByAdmin(PageRequest pageRequest, String admin_Id,Student student) {
        boolean flag = false;
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        List<StudentVo> list = null;
        PageInfo<StudentVo> pageInfo = null;
        try {
            flag = adminService.isExist(admin_Id);
            if(flag == true){
                PageHelper.startPage(pageNum, pageSize);
                list = studentMapper.searchStuByAdmin(admin_Id,student.getStu_Id(), student.getStu_Name(),
                        student.getGrade(),student.getMajor(),student.getStu_class());
                pageInfo = new PageInfo<>(list);
            }
        } catch (UserException e){
            e.printStackTrace();
            throw new UserException(ResponseCode.USER_NOT_EXIST);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(ResponseCode.INNER_ERROR.getMsg());
        }
        return PageUtil.getPageResult(pageInfo);
    }

    @Override
    public PageResult findApp(PageRequest pageRequest,String stu_Id) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        List<ApplicationVo> list = null;
        PageInfo<ApplicationVo> pageInfo = null;
        try {
            PageHelper.startPage(pageNum, pageSize);
            list = studentMapper.findApp(stu_Id);
            pageInfo = new PageInfo<>(list);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(ResponseCode.INNER_ERROR.getMsg());
        }

        return PageUtil.getPageResult(pageInfo);
    }

    @Override
    public List<ScoreVo> checkScoreByStudent(String stu_Id) {
        List<ScoreVo> list = null;
        try {
            Map<String,Object> params = new HashMap<>();
            params.put("stu_Id",stu_Id);
            list = studentMapper.checkScoreByStudent(params);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(ResponseCode.INNER_ERROR.getMsg());
        }

        return list;
    }

    @Override
    public PageResult checkScoreDetail(PageRequest pageRequest, String semester, String stu_Id) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        List<AwardLeverVo> list = null;
        PageInfo<AwardLeverVo> pageInfo = null;
        try {
            PageHelper.startPage(pageNum, pageSize);
            list = studentMapper.checkScoreDetail(semester,stu_Id);
            pageInfo = new PageInfo<>(list);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(ResponseCode.INNER_ERROR.getMsg());
        }

        return PageUtil.getPageResult(pageInfo);
    }

}
