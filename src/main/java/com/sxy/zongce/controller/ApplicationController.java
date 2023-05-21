package com.sxy.zongce.controller;

import com.sxy.zongce.entity.Application;
import com.sxy.zongce.exception.*;
import com.sxy.zongce.service.impl.AdminServiceImpl;
import com.sxy.zongce.service.impl.ApplicationServiceImpl;
import com.sxy.zongce.service.impl.MenterServiceImpl;
import com.sxy.zongce.service.impl.StudentServiceImpl;
import com.sxy.zongce.util.*;
import com.sxy.zongce.vo.ApplicationVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/app")
public class ApplicationController {

    @Autowired
    private ApplicationServiceImpl applicationService;
    @Autowired
    private StudentServiceImpl studentService;
    @Autowired
    private MenterServiceImpl menterService;

    //待审核认证申请列表
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public ResponseRes<Object> findUnchecked(PageRequest pageRequest, HttpSession session){
        String menter_Id = session.getAttribute("menter_Id").toString();
        boolean flag = true;
        PageResult pageResult = null;
        try {
            flag = menterService.isExist(menter_Id);
            if(flag){
                pageResult = applicationService.findUnchecked(pageRequest, menter_Id);
            }

        } catch (UserException e) {
            e.printStackTrace();
            return ResponseRes.error(e.getCode(),e.getMsg());
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseRes.error(500,e.getMessage());
        }
        return ResponseRes.success(ResponseCode.SUCCESS,pageResult);
    }

    //条件查询待审核认证申请
    @RequestMapping(value = "/list/query", method = RequestMethod.GET)
    @ResponseBody
    public ResponseRes<Object> searchUnchecked(PageRequest pageRequest,HttpSession session,
                                               String startTime, String endTime,
                                               String stu_Id, String stu_Name){
        String menter_Id = session.getAttribute("menter_Id").toString();
        boolean flag = true;
        PageResult pageResult = null;
        try {
            flag = menterService.isExist(menter_Id);
            if(flag){
                pageResult = applicationService.searchUnchecked(pageRequest,menter_Id,
                        startTime, endTime,
                        stu_Id, stu_Name);
            }
        } catch (UserException e) {
            e.printStackTrace();
            return ResponseRes.error(e.getCode(),e.getMsg());
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseRes.error(500,e.getMessage());
        }
        return ResponseRes.success(ResponseCode.SUCCESS,pageResult);
    }

    //通过认证申请
    @RequestMapping(value = "/{apply_Id}/pass", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseRes<Object> passApp(@PathVariable("apply_Id") String apply_Id){
        apply_Id = "ap" + apply_Id;
        boolean flag = true;
        String state = "";

        try {
            flag = applicationService.isExist(apply_Id);
            state = applicationService.checkState(apply_Id);
            if(flag && "待审核".equals(state)){
                applicationService.passApp(apply_Id);
            } else if(!"待审核".equals(state)){
                throw new ApplicationException(ResponseCode.APP_CHECKED);
            }

        } catch (ApplicationException e) {
            e.printStackTrace();
            return ResponseRes.error(e.getCode(),e.getMsg());
        } catch (Exception e){
            e.printStackTrace();
            return ResponseRes.error(ResponseCode.INNER_ERROR);
        }

        return ResponseRes.success(ResponseCode.SUCCESS,null);
    }

    //不通过认证申请
    @RequestMapping(value = "/{apply_Id}/fail", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseRes<Object> failApp(@PathVariable("apply_Id") String apply_Id,String reason){
        apply_Id = "ap" + apply_Id;
        boolean flag = true;
        String state = "";

        try {
            flag = applicationService.isExist(apply_Id);
            state = applicationService.checkState(apply_Id);
            if(flag && "待审核".equals(state)){
                applicationService.failApp(apply_Id,reason);
            } else if(!"待审核".equals(state)){
                throw new ApplicationException(ResponseCode.APP_CHECKED);
            }

        } catch (ApplicationException e) {
            e.printStackTrace();
            return ResponseRes.error(e.getCode(),e.getMsg());
        } catch (Exception e){
            e.printStackTrace();
            return ResponseRes.error(ResponseCode.INNER_ERROR);
        }

        return ResponseRes.success(ResponseCode.SUCCESS,null);
    }

    ///撤审
    @RequestMapping(value = "{apply_Id}/cancel", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseRes<Object> cancelApp(@PathVariable("apply_Id") String apply_Id){
        apply_Id = "ap" + apply_Id;
        boolean flag = true;
        String state = "";

        try {
            flag = applicationService.isExist(apply_Id);
            state = applicationService.checkState(apply_Id);
            if(flag && !"待审核".equals(state)){
                applicationService.cancelApp(apply_Id);
            } else if("待审核".equals(state)){
                throw new ApplicationException(ResponseCode.APP_CHECKED);
            }

        } catch (ApplicationException e) {
            e.printStackTrace();
            return ResponseRes.error(e.getCode(),e.getMsg());
        } catch (Exception e){
            e.printStackTrace();
            return ResponseRes.error(ResponseCode.INNER_ERROR);
        }

        return ResponseRes.success(ResponseCode.SUCCESS,null);
    }


    //提交认证申请
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    @ResponseBody
    public ResponseRes<Application> submitApp(Application application, MultipartFile file,
                                              HttpSession session){

        String stu_Id = session.getAttribute("stu_Id").toString();
        String max_Id = "";
        boolean res = true;
        try {
            res = studentService.isExist(stu_Id);
            if(res){
                max_Id = applicationService.findMaxId();
                application.setApply_Id(IdUtil.createAppId(max_Id));
                application.setAtt_url(UploadUtil.upload(file));
                application.setSemester(SemesterUtil.getCurrentSemester());
                application.setStu_Id(stu_Id);
                applicationService.addApp(application);
            }
        } catch (ApplicationException e1) {
            e1.printStackTrace();
            return ResponseRes.error(e1.getCode(), e1.getMsg());
        } catch (FileException e2) {
            e2.printStackTrace();
            return ResponseRes.error(e2.getCode(), e2.getMsg());
        } catch (UserException e3){
            e3.printStackTrace();
            return ResponseRes.error(e3.getCode(), e3.getMsg());
        } catch (Exception e4){
            e4.printStackTrace();
            return ResponseRes.error(ResponseCode.INNER_ERROR);
        }

        return ResponseRes.success(ResponseCode.SUCCESS,application);
    }

    //修改认证申请页面
    @RequestMapping(value = "/update-page", method = RequestMethod.GET)
    @ResponseBody
    public ResponseRes<ApplicationVo> updateAppPage(String apply_Id){
        apply_Id = "ap" + apply_Id;
        boolean flag = true;
        String state = "";
        ApplicationVo applicationVo = null;
        try {
            flag = applicationService.isExist(apply_Id);
            state = applicationService.checkState(apply_Id);
            if(flag && "待审核".equals(state)) {
                applicationVo = applicationService.getApplication(apply_Id);
            } else if(!"待审核".equals(state)){
                throw new ApplicationException(ResponseCode.APP_CHECKED);
            }
        } catch (ApplicationException e) {
            e.printStackTrace();
            return ResponseRes.error(e.getCode(),e.getMsg());
        }   catch (Exception e){
            e.printStackTrace();
            return ResponseRes.error(ResponseCode.INNER_ERROR);
        }
        return ResponseRes.success(ResponseCode.SUCCESS,applicationVo);
    }

    //修改认证申请
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ResponseRes<Application> updateApp(MultipartFile file, Application application){
        boolean flag = true;
        String state = "";
        String apply_Id = application.getApply_Id();
        apply_Id = "ap" + apply_Id;
        application.setApply_Id(apply_Id);
        try {
            flag = applicationService.isExist(apply_Id);
            state = applicationService.checkState(apply_Id);
            if(flag && "待审核".equals(state)) {
                application.setAtt_url(UploadUtil.upload(file));
                application.setSemester(SemesterUtil.getCurrentSemester());
                applicationService.updateApp(application);
            } else if(!"待审核".equals(state)){
                throw new ApplicationException(ResponseCode.APP_CHECKED);
            }
        } catch (ApplicationException e1) {
            e1.printStackTrace();
            return ResponseRes.error(e1.getCode(), e1.getMsg());
        } catch (FileException e2) {
            e2.printStackTrace();
            return ResponseRes.error(e2.getCode(), e2.getMsg());
        }  catch (Exception e3){
            e3.printStackTrace();
            return ResponseRes.error(ResponseCode.INNER_ERROR);
        }

        return ResponseRes.success(ResponseCode.SUCCESS,application);
    }

    //删除认证申请
    @RequestMapping(value = "{apply_Id}/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseRes<Object> deleteApp(@PathVariable("apply_Id") String apply_Id){
        apply_Id = "ap" + apply_Id;
        boolean flag = true;
        String state = "";
        try {
            flag = applicationService.isExist(apply_Id);
            state = applicationService.checkState(apply_Id);
            if(flag && "待审核".equals(state)) {
                applicationService.deleteApp(apply_Id);
            } else if(!"待审核".equals(state)){
                throw new ApplicationException(ResponseCode.APP_CHECKED);
            }
        } catch (ApplicationException e) {
            e.printStackTrace();
            return ResponseRes.error(e.getCode(),e.getMsg());
        } catch (Exception e){
            return ResponseRes.error(ResponseCode.INNER_ERROR);
        }
        return ResponseRes.success(ResponseCode.SUCCESS,null);
    }

    //获取认证申请状态
    @RequestMapping(value = "/status", method = RequestMethod.GET)
    @ResponseBody
    public ResponseRes<String> checkState(String apply_Id){
        String state = "";
        boolean flag = true;
        try {
            flag = applicationService.isExist(apply_Id);
            if(flag){
                state = applicationService.checkState(apply_Id);
            }
        } catch (ApplicationException e) {
            e.printStackTrace();
            return ResponseRes.error(e.getCode(),e.getMsg());
        } catch (Exception e){
            e.printStackTrace();
            return ResponseRes.error(ResponseCode.INNER_ERROR);
        }

        return ResponseRes.success(ResponseCode.SUCCESS,state);
    }

    //查看不通过原因
    @RequestMapping(value = "{apply_Id}/reason", method = RequestMethod.GET)
    @ResponseBody
    public ResponseRes<String> checkReason(@PathVariable("apply_Id") String apply_Id){
        apply_Id = "ap"+apply_Id;
        String reason = "";
        try {
            reason = applicationService.checkReason(apply_Id);
        } catch (ApplicationException e) {
            e.printStackTrace();
            return ResponseRes.error(e.getCode(),e.getMsg());
        } catch (Exception e){
            e.printStackTrace();
            return ResponseRes.error(ResponseCode.INNER_ERROR);
        }

        return ResponseRes.success(ResponseCode.SUCCESS,reason);
    }

    //查看附件
    @RequestMapping(value = "{apply_Id}/att", method = RequestMethod.GET)
    @ResponseBody
    public ResponseRes<Object> checkAtt(@PathVariable("apply_Id") String apply_Id,HttpServletResponse response){
        apply_Id = "ap" + apply_Id;
        String att_url = "";
        try {
            att_url = applicationService.checkAtt(apply_Id);
            if(!"".equals(att_url)){
                att_url = ClassUtils.getDefaultClassLoader()
                        .getResource("static/upload/attachment").getPath() + att_url;
                DownloadUtil.browseAtt(att_url,response);
            }
        } catch (ApplicationException e) {
            e.printStackTrace();
            return ResponseRes.error(e.getCode(), e.getMsg());
        } catch (FileException e){
            e.printStackTrace();
            return ResponseRes.error(e.getCode(), e.getMsg());
        }
        return ResponseRes.success(ResponseCode.SUCCESS,null);
    }

}
