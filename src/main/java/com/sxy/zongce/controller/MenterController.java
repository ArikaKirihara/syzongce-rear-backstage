package com.sxy.zongce.controller;

import com.sxy.zongce.entity.Menter;
import com.sxy.zongce.entity.Student;
import com.sxy.zongce.exception.ResponseCode;
import com.sxy.zongce.exception.ResponseRes;
import com.sxy.zongce.exception.UserException;
import com.sxy.zongce.service.impl.MenterServiceImpl;
import com.sxy.zongce.util.PageRequest;
import com.sxy.zongce.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/menter")
public class MenterController {

    @Autowired
    private MenterServiceImpl menterService;

    //辅导员登录
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseRes<Menter> login(String menter_Id, String m_Pwd, HttpSession session){
        String m_Name = "";
        String pwd = "";

        try {
            pwd = menterService.findPwdById(menter_Id);
            if(!m_Pwd.equals(pwd)){
                return ResponseRes.error(ResponseCode.WRONG_PASSWORD);
            }
            m_Name = menterService.getMenterName(menter_Id);

        }catch (UserException e){
            e.printStackTrace();
            return ResponseRes.error(ResponseCode.USER_NOT_EXIST);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseRes.error(ResponseCode.INNER_ERROR);
        }

        session.removeAttribute("admin_Id");
        session.removeAttribute("admin_Name");

        session.removeAttribute("stu_Id");
        session.removeAttribute("stu_Name");

        session.setAttribute("menter_Id",menter_Id);
        session.setAttribute("m_Name",m_Name);
        Menter menter = new Menter();
        menter.setMenter_Id(menter_Id);
        menter.setM_Name(m_Name);
        return ResponseRes.success(ResponseCode.SUCCESS,menter);
    }

    //辅导员登出
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public ResponseRes<Object> Logout(HttpSession session){

        try {
            session.removeAttribute("menter_Id");
            session.removeAttribute("m_Name");
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseRes.error(ResponseCode.INNER_ERROR);
        }

        return ResponseRes.success(ResponseCode.SUCCESS,null);
    }

    //辅导员修改密码
    @RequestMapping(value = "/change-pwd", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseRes<Menter> updatePwd(Menter menter,HttpSession session){
        String menter_Id = session.getAttribute("menter_Id").toString();
        String m_Pwd = menter.getM_Pwd();

        try {
            menterService.updatePwd(menter_Id,m_Pwd);
        }catch (UserException e){
            e.printStackTrace();
            return ResponseRes.error(ResponseCode.STUDENT_NOT_EXIST);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseRes.error(ResponseCode.INNER_ERROR);
        }

        return ResponseRes.success(ResponseCode.SUCCESS,menter);
    }

    //辅导员查看认证申请
    @RequestMapping(value = "/app", method = RequestMethod.GET)
    @ResponseBody
    public ResponseRes<Object> listApp(PageRequest pageRequest,
                                       HttpSession session){
        String menter_Id =session.getAttribute("menter_Id").toString();
        boolean flag = true;
        PageResult pageResult = null;
        try {
            flag = menterService.isExist(menter_Id);
            if(flag){
                pageResult = menterService.listApp(pageRequest, menter_Id);
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

    //辅导员条件查询认证申请
    @RequestMapping(value = "/app/query", method = RequestMethod.GET)
    @ResponseBody
    public ResponseRes<Object> searchApp(PageRequest pageRequest, HttpSession session,
                                         String startTime, String endTime,
                                         String stu_Id, String stu_Name){
        String menter_Id =session.getAttribute("menter_Id").toString();
        boolean flag = true;
        PageResult pageResult = null;
        try {
            flag = menterService.isExist(menter_Id);
            if(flag){
                pageResult = menterService.searchApp(pageRequest,menter_Id,
                        startTime, endTime,
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

    //获取辅导员姓名
    @RequestMapping(value = "/name", method = RequestMethod.GET)
    @ResponseBody
    public ResponseRes<String> getMenterName(HttpSession session){
        String menter_Id = session.getAttribute("menter_Id").toString();
        String stu_Name = "";
        try {
            stu_Name = menterService.getMenterName(menter_Id);
        }catch (UserException e){
            e.printStackTrace();
            return ResponseRes.error(ResponseCode.STUDENT_NOT_EXIST);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseRes.error(ResponseCode.INNER_ERROR);
        }

        return ResponseRes.success(ResponseCode.SUCCESS,stu_Name);
    }

    //辅导员查看综测成绩
    @RequestMapping(value = "/total-score", method = RequestMethod.GET)
    @ResponseBody
    public ResponseRes<Object> checkScoreByMenter(PageRequest pageRequest,HttpSession session){
        String menter_Id = session.getAttribute("menter_Id").toString();
        boolean flag = true;
        PageResult pageResult = null;
        try {
            flag = menterService.isExist(menter_Id);
            if(flag){
                pageResult = menterService.checkScoreByMenter(pageRequest, menter_Id);
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

    //辅导员查看综测成绩
    @RequestMapping(value = "/total-score/query", method = RequestMethod.GET)
    @ResponseBody
    public ResponseRes<Object> searchScoreByMenter(PageRequest pageRequest, String menter_Id,
                                                   Student student, String semester){
//        String menter_Id = session.getAttribute("menter_Id").toString();
        boolean flag = true;
        PageResult pageResult = null;
        try {
            flag = menterService.isExist(menter_Id);
            if(flag){
                pageResult = menterService.searchScoreByMenter(pageRequest,menter_Id,student,semester);
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
