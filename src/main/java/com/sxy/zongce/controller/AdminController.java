package com.sxy.zongce.controller;

import com.sxy.zongce.entity.Admin;
import com.sxy.zongce.entity.Menter;
import com.sxy.zongce.entity.Student;
import com.sxy.zongce.exception.ResponseCode;
import com.sxy.zongce.exception.ResponseRes;
import com.sxy.zongce.exception.UserException;
import com.sxy.zongce.service.impl.AdminServiceImpl;
import com.sxy.zongce.util.PageRequest;
import com.sxy.zongce.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminServiceImpl adminService;

    //管理员登录
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseRes<Admin> login(String admin_Id,String admin_Pwd,HttpSession session){
        String admin_Name = "";
        String pwd = "";

        try {
            pwd = adminService.findPwdById(admin_Id);
            if(!admin_Pwd.equals(pwd)){
                return ResponseRes.error(ResponseCode.WRONG_PASSWORD);
            }
            admin_Name = adminService.getAdminName(admin_Id);

        }catch (UserException e){
            e.printStackTrace();
            return ResponseRes.error(ResponseCode.USER_NOT_EXIST);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseRes.error(ResponseCode.INNER_ERROR);
        }

        session.removeAttribute("stu_Id");
        session.removeAttribute("stu_Name");

        session.removeAttribute("menter_Id");
        session.removeAttribute("m_Name");

        session.setAttribute("admin_Id",admin_Id);
        session.setAttribute("admin_Name",admin_Name);
        //System.out.println(session.getAttribute("admin_Id").toString());
        Admin admin = new Admin();
        admin.setAdmin_Id(admin_Id);
        admin.setAdmin_Name(admin_Name);

        return ResponseRes.success(ResponseCode.SUCCESS,admin);
    }

    //管理员登出
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public ResponseRes<Object> Logout(HttpSession session){

        try {
            session.removeAttribute("admin_Id");
            session.removeAttribute("admin_Name");
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseRes.error(ResponseCode.INNER_ERROR);
        }

        return ResponseRes.success(ResponseCode.SUCCESS,null);
    }

    //管理员修改密码
    @RequestMapping(value = "/change-pwd", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseRes<Admin> updatePwd(Admin admin, HttpSession session){
        String admin_Id = session.getAttribute("admin_Id").toString();
        String admin_Pwd = admin.getAdmin_Pwd();

        try {
            adminService.updatePwd(admin_Id,admin_Pwd);
        }catch (UserException e){
            e.printStackTrace();
            return ResponseRes.error(ResponseCode.USER_NOT_EXIST);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseRes.error(ResponseCode.INNER_ERROR);
        }

        return ResponseRes.success(ResponseCode.SUCCESS,admin);
    }

    //管理员查看认证申请
    @RequestMapping(value = "/app", method = RequestMethod.GET)
    @ResponseBody
    public ResponseRes<Object> listApp(PageRequest pageRequest, HttpSession session){
        String admin_Id = session.getAttribute("admin_Id").toString();
        boolean flag = true;
        PageResult pageResult = null;
        try {
            flag = adminService.isExist(admin_Id);
            if(flag){
                pageResult = adminService.listApp(pageRequest, admin_Id);
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

    //管理员条件查询认证申请
    @RequestMapping(value = "/app/query", method = RequestMethod.GET)
    @ResponseBody
    public ResponseRes<Object> searchApp(PageRequest pageRequest, HttpSession session,
                                         String startTime, String endTime,
                                         String stu_Id, String stu_Name){
        String admin_Id = session.getAttribute("admin_Id").toString();
        boolean flag = true;
        PageResult pageResult = null;
        try {
            flag = adminService.isExist(admin_Id);
            if(flag){
                pageResult = adminService.searchApp(pageRequest, admin_Id,startTime, endTime,
                        stu_Id, stu_Name);
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

    //获取管理员姓名
    @RequestMapping(value = "/name", method = RequestMethod.GET)
    @ResponseBody
    public ResponseRes<String> getAdminName(HttpSession session){
        String admin_Id = session.getAttribute("admin_Id").toString();
        String admin_Name = "";
        try {
            admin_Name = adminService.getAdminName(admin_Id);
        }catch (UserException e){
            e.printStackTrace();
            return ResponseRes.error(ResponseCode.STUDENT_NOT_EXIST);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseRes.error(ResponseCode.INNER_ERROR);
        }

        return ResponseRes.success(ResponseCode.SUCCESS,admin_Name);
    }

    //管理员查看综测成绩
    @RequestMapping(value = "/total-score", method = RequestMethod.GET)
    @ResponseBody
    public ResponseRes<Object> checkScoreByAdmin(PageRequest pageRequest, HttpSession session){
        String admin_Id = session.getAttribute("admin_Id").toString();
        boolean flag = true;
        PageResult pageResult = null;
        try {
            flag = adminService.isExist(admin_Id);
            if(flag){
                pageResult = adminService.checkScoreByAdmin(pageRequest, admin_Id);
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

    //管理员条件查询综测成绩
    @RequestMapping(value = "/total-score/query", method = RequestMethod.GET)
    @ResponseBody
    public ResponseRes<Object> searchScoreByAdmin(PageRequest pageRequest, HttpSession session,
                                                  Student student, String semester){
        String admin_Id = session.getAttribute("admin_Id").toString();
        boolean flag = true;
        PageResult pageResult = null;
        try {
            flag = adminService.isExist(admin_Id);
            if(flag){
                pageResult = adminService.searchScoreByAdmin(pageRequest,admin_Id,student,semester);
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
