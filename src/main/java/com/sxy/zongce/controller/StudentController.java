package com.sxy.zongce.controller;

import com.sxy.zongce.entity.Student;
import com.sxy.zongce.exception.ResponseCode;
import com.sxy.zongce.exception.ResponseRes;
import com.sxy.zongce.exception.UserException;
import com.sxy.zongce.service.impl.StudentServiceImpl;
import com.sxy.zongce.util.PageRequest;
import com.sxy.zongce.util.PageResult;
import com.sxy.zongce.vo.ScoreVo;
import com.sxy.zongce.vo.StudentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentServiceImpl studentService;

    //学生登录
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseRes<StudentVo> Login(String stu_Id,String stu_Pwd,HttpSession session){
        String stu_Name = "";
        String pwd = "";

        try {
            pwd = studentService.findPwdById(stu_Id);
            if(!stu_Pwd.equals(pwd)){
                return ResponseRes.error(ResponseCode.WRONG_PASSWORD);
            }
            stu_Name = studentService.getStuName(stu_Id);

        }catch (UserException e1){
            e1.printStackTrace();
            return ResponseRes.error(e1.getCode(),e1.getMsg());
        } catch (RuntimeException e2) {
            e2.printStackTrace();
            return ResponseRes.error(500,e2.getMessage());
        }

        session.removeAttribute("admin_Id");
        session.removeAttribute("admin_Name");

        session.removeAttribute("menter_Id");
        session.removeAttribute("m_Name");

        session.setAttribute("stu_Id",stu_Id);
        session.setAttribute("stu_Name",stu_Name);
        StudentVo studentVo = new StudentVo();
        studentVo.setStu_Id(stu_Id);
        studentVo.setStu_Name(stu_Name);

        return ResponseRes.success(ResponseCode.SUCCESS,studentVo);
    }

    //学生登出
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public ResponseRes<Object> Logout(HttpSession sessiom){

        try {
            sessiom.removeAttribute("stu_Id");
            sessiom.removeAttribute("stu_Name");
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseRes.error(ResponseCode.INNER_ERROR);
        }

        return ResponseRes.success(ResponseCode.SUCCESS,null);
    }

    //学生修改密码
    @RequestMapping(value = "/change-pwd", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseRes<Student> updatePwd(Student student,HttpSession session){
        String stu_Id = session.getAttribute("stu_Id").toString();
        String stu_Pwd = student.getStu_Pwd();

        try {
            studentService.updatePwd(stu_Id,stu_Pwd);
        }catch (UserException e){
            e.printStackTrace();
            return ResponseRes.error(ResponseCode.USER_NOT_EXIST);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseRes.error(ResponseCode.INNER_ERROR);
        }

        return ResponseRes.success(ResponseCode.SUCCESS,student);
    }

    //查看个人信息
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public ResponseRes<StudentVo> findStuInfo(HttpSession session){
        String stu_Id = session.getAttribute("stu_Id").toString();
        StudentVo studentVo = null;
        try {
            studentVo = studentService.findStuInfo(stu_Id);
        }catch (UserException e){
            e.printStackTrace();
            return ResponseRes.error(ResponseCode.STUDENT_NOT_EXIST);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseRes.error(ResponseCode.INNER_ERROR);
        }

        return ResponseRes.success(ResponseCode.SUCCESS,studentVo);

    }

    //获取学生姓名
    @RequestMapping(value = "/input/name", method = RequestMethod.GET)
    @ResponseBody
    public ResponseRes<String> getStuName(String stu_Id){

        String stu_Name = "";
        try {
            stu_Name = studentService.getStuName(stu_Id);
        }catch (UserException e){
            e.printStackTrace();
            return ResponseRes.error(ResponseCode.STUDENT_NOT_EXIST);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseRes.error(ResponseCode.INNER_ERROR);
        }

        return ResponseRes.success(ResponseCode.SUCCESS,stu_Name);
    }

    //通过session获取对应学号的学生姓名
    @RequestMapping(value = "/name", method = RequestMethod.GET)
    @ResponseBody
    public ResponseRes<String> getStuName(HttpSession session){
        String stu_Id = session.getAttribute("stu_Id").toString();

        String stu_Name = "";
        try {
            stu_Name = studentService.getStuName(stu_Id);
        }catch (UserException e){
            e.printStackTrace();
            return ResponseRes.error(ResponseCode.STUDENT_NOT_EXIST);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseRes.error(ResponseCode.INNER_ERROR);
        }

        return ResponseRes.success(ResponseCode.SUCCESS,stu_Name);
    }

    //辅导员查看用户列表
    @RequestMapping(value = "/menter/list", method = RequestMethod.GET)
    @ResponseBody
    public ResponseRes<Object> findStuByMenter(PageRequest pageQuery , HttpSession session){
        String menter_Id = session.getAttribute("menter_Id").toString();
        Object object = null;
        try {
            object = studentService.findStuByMenter(pageQuery, menter_Id);
        }catch (UserException e){
            e.printStackTrace();
            return ResponseRes.error(ResponseCode.USER_NOT_EXIST);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseRes.error(ResponseCode.INNER_ERROR);
        }
        return ResponseRes.success(ResponseCode.SUCCESS,object);
    }

    //辅导员条件查询用户
    @RequestMapping(value = "menter/search", method = RequestMethod.GET)
    @ResponseBody
    public ResponseRes<Object> searchStuByMenter(PageRequest pageQuery,HttpSession session,Student student){
        String menter_Id = session.getAttribute("menter_Id").toString();
        Object object = null;
        try {
            object = studentService.searchStuByMenter(pageQuery, menter_Id,student);
        }catch (UserException e){
            e.printStackTrace();
            return ResponseRes.error(ResponseCode.USER_NOT_EXIST);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseRes.error(ResponseCode.INNER_ERROR);
        }
        return ResponseRes.success(ResponseCode.SUCCESS,object);
    }

    //管理员查看用户列表
    @RequestMapping(value = "/admin/list", method = RequestMethod.GET)
    @ResponseBody
    public ResponseRes<Object> findStuByAdmin(PageRequest pageQuery , HttpSession session){
        String admin_Id = session.getAttribute("admin_Id").toString();
        Object object = null;
        try {
            object = studentService.findStuByAdmin(pageQuery, admin_Id);
        }catch (UserException e){
            e.printStackTrace();
            return ResponseRes.error(ResponseCode.USER_NOT_EXIST);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseRes.error(ResponseCode.INNER_ERROR);
        }
        return ResponseRes.success(ResponseCode.SUCCESS,object);
    }

    //管理员条件查询用户
    @RequestMapping(value = "/admin/search", method = RequestMethod.GET)
    @ResponseBody
    public ResponseRes<Object> searchStuByAdmin(PageRequest pageQuery,HttpSession session,Student student){
        String admin_Id = session.getAttribute("admin_Id").toString();
        Object object = null;
        try {
            object = studentService.searchStuByAdmin(pageQuery, admin_Id,student);
        }catch (UserException e){
            e.printStackTrace();
            return ResponseRes.error(ResponseCode.USER_NOT_EXIST);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseRes.error(ResponseCode.INNER_ERROR);
        }
        return ResponseRes.success(ResponseCode.SUCCESS,object);
    }

    ///学生查看认证申请
    @RequestMapping(value = "/app", method = RequestMethod.GET)
    @ResponseBody
    public ResponseRes<Object> listApp(PageRequest pageRequest, HttpSession session){
        String stu_Id = session.getAttribute("stu_Id").toString();
        boolean flag = true;
        PageResult pageResult = null;
        try {
            flag = studentService.isExist(stu_Id);
            if(flag){
                pageResult = studentService.findApp(pageRequest, stu_Id);
            }
        } catch (UserException e) {
            e.printStackTrace();
            return ResponseRes.error(e.getCode(),e.getMsg());
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseRes.error(ResponseCode.INNER_ERROR);
        }
        return ResponseRes.success(ResponseCode.SUCCESS,pageResult);
    }

    //学生查看综测成绩
    @RequestMapping(value = "/total-score", method = RequestMethod.GET)
    @ResponseBody
    public ResponseRes<Object> checkScoreByStudent(HttpSession session){
        String stu_Id = session.getAttribute("stu_Id").toString();
        boolean flag = true;
        List<ScoreVo> list = null;
        try {
            flag = studentService.isExist(stu_Id);
            if(flag){
                list = studentService.checkScoreByStudent(stu_Id);
            }
        } catch (UserException e) {
            e.printStackTrace();
            return ResponseRes.error(e.getCode(),e.getMsg());
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseRes.error(ResponseCode.INNER_ERROR);
        }
        return ResponseRes.success(ResponseCode.SUCCESS,list);
    }

    //模块详细分值
    @RequestMapping(value = "/total-score/detail", method = RequestMethod.GET)
    @ResponseBody
    public ResponseRes<Object> checkScoreDetail(PageRequest pageRequest, String semester, HttpSession session){
        String stu_Id = session.getAttribute("stu_Id").toString();
        boolean flag = true;
        PageResult pageResult = null;
        try {
            flag = studentService.isExist(stu_Id);
            if(flag){
                pageResult = studentService.checkScoreDetail(pageRequest, semester, stu_Id);
            }
        } catch (UserException e) {
            e.printStackTrace();
            return ResponseRes.error(e.getCode(),e.getMsg());
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseRes.error(ResponseCode.INNER_ERROR);
        }
        return ResponseRes.success(ResponseCode.SUCCESS,pageResult);
    }

}
