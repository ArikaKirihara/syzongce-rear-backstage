package com.sxy.zongce.controller;

import com.sxy.zongce.entity.Project;
import com.sxy.zongce.entity.Template;
import com.sxy.zongce.exception.AddException;
import com.sxy.zongce.exception.ResponseCode;
import com.sxy.zongce.exception.ResponseRes;
import com.sxy.zongce.exception.UserException;
import com.sxy.zongce.service.impl.AdminServiceImpl;
import com.sxy.zongce.service.impl.StudentServiceImpl;
import com.sxy.zongce.service.impl.TemplateServiceImpl;
import com.sxy.zongce.util.IdUtil;
import com.sxy.zongce.util.PageRequest;
import com.sxy.zongce.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/template")
public class TemplateController {

    @Autowired
    private TemplateServiceImpl templateService;
    @Autowired
    private AdminServiceImpl adminService;
    @Autowired
    private StudentServiceImpl studentService;

    //模板分类列表
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public ResponseRes<Object> findAllTemplate(PageRequest pageRequest, String admin_Id){
        boolean flag = true;
        PageResult pageResult = null;
        try {
            flag = adminService.isExist(admin_Id);
            if(flag){
                pageResult = templateService.findAllTemplate(pageRequest, admin_Id);
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

    //更改模板分类状态
    @RequestMapping(value = "/{tem_Id}/switch", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseRes<Object> updateTemplateById(@PathVariable("tem_Id") String tem_Id){
        tem_Id = "MU-" + tem_Id;
        try{
            templateService.updateTemplateById(tem_Id);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseRes.error(500,e.getMessage());
        }
        return ResponseRes.success(ResponseCode.SUCCESS,null);
    }

    //添加模板分类
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseRes<Template> addProject(Template template){
        String max_Id = "";
        try {
            //获取数字顺序最大的id
            max_Id = templateService.findMaxId();
            //自动生成模板分类id，实现id自增
            template.setTem_Id(IdUtil.createTemplateId(max_Id));
            templateService.addTemplate(template);
        } catch (AddException e) {
            e.printStackTrace();
            return ResponseRes.error(e.getCode(),e.getMsg());
        }  catch (Exception e) {
            e.printStackTrace();
            return ResponseRes.error(ResponseCode.INNER_ERROR);
        }
        return ResponseRes.success(ResponseCode.SUCCESS,template);
    }

    //获取模板分类
    @RequestMapping(value = "/now", method = RequestMethod.GET)
    @ResponseBody
    public ResponseRes<Object> findTemplate(HttpSession session){
        String stu_Id = session.getAttribute("stu_Id").toString();
        Object object = null;
        boolean flag = true;
        try {
            flag = studentService.isExist(stu_Id);
            if(flag){
                object = templateService.findTemplate(stu_Id);
            }
        } catch (UserException e) {
            e.printStackTrace();
            return ResponseRes.error(e.getCode(),e.getMsg());
        }  catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseRes.error(500,e.getMessage());
        }
        return ResponseRes.success(ResponseCode.SUCCESS,object);
    }
}
