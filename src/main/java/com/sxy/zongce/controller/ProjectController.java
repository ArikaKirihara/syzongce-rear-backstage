package com.sxy.zongce.controller;

import com.sxy.zongce.entity.Project;
import com.sxy.zongce.exception.*;
import com.sxy.zongce.service.impl.AdminServiceImpl;
import com.sxy.zongce.service.impl.ProjectServiceImpl;
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
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectServiceImpl projectService;
    @Autowired
    private AdminServiceImpl adminService;
    @Autowired
    private TemplateServiceImpl templateService;
    @Autowired
    private StudentServiceImpl studentService;

    //项目类型列表
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public ResponseRes<Object> findAllAwardLever(PageRequest pageRequest, HttpSession session){
        String admin_Id = session.getAttribute("admin_Id").toString();
        boolean flag = true;
        PageResult pageResult = null;
        try {
            flag = adminService.isExist(admin_Id);
            if(flag){
                pageResult = projectService.findAllProject(pageRequest, admin_Id);
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

    //筛选项目类型
    @RequestMapping(value = "{tem_Id}/filt", method = RequestMethod.GET)
    @ResponseBody
    public ResponseRes<Object> findProByTmp(PageRequest pageRequest, HttpSession session,@PathVariable String tem_Id){
        String admin_Id = session.getAttribute("admin_Id").toString();
        tem_Id = "MU-" + tem_Id;
        boolean flag = true;
        PageResult pageResult = null;
        try {
            flag = adminService.isExist(admin_Id);
            if(flag){
                pageResult = projectService.findProByTmp(pageRequest, admin_Id, tem_Id);
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

    //更改项目类型状态
    @RequestMapping(value = "/{pro_Id}/switch", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseRes<Object> updateProjectById(@PathVariable("pro_Id") String pro_Id){
        pro_Id = "A" + pro_Id;
        try{
            projectService.updateProjectById(pro_Id);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseRes.error(500,e.getMessage());
        }
        return ResponseRes.success(ResponseCode.SUCCESS,null);
    }

    //添加项目类型
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseRes<Project> addProject(Project project , HttpSession session){
        project.setAdmin_Id(session.getAttribute("admin_Id").toString());
        String max_Id = "";
        try {
            //获取数字顺序最大的id
            max_Id = projectService.findMaxId();
            //自动生成模板分类id，实现id自增
            project.setPro_Id(IdUtil.createProjectId(max_Id));
            projectService.addProject(project);
        } catch (AddException e) {
            e.printStackTrace();
            return ResponseRes.error(e.getCode(),e.getMsg());
        }  catch (Exception e) {
            e.printStackTrace();
            return ResponseRes.error(ResponseCode.INNER_ERROR);
        }
        return ResponseRes.success(ResponseCode.SUCCESS,project);
    }

    //获取项目类型
    @RequestMapping(value = "/{tem_Id}/now", method = RequestMethod.GET)
    @ResponseBody
    public ResponseRes<Object> findProject(HttpSession session,@PathVariable("tem_Id")  String tem_Id){
        String stu_Id = session.getAttribute("stu_Id").toString();
        Object object = null;
        boolean stuFlag = true;
        boolean temFlag = true;
        try {
            stuFlag = studentService.isExist(stu_Id);
            temFlag = templateService.isVisible(tem_Id);
            if(stuFlag && temFlag){
                object = projectService.findProject(stu_Id,tem_Id);
            }
        } catch (UserException e) {
            e.printStackTrace();
            return ResponseRes.error(e.getCode(),e.getMsg());
        } catch (VisibilityException e){
            e.printStackTrace();
            return ResponseRes.error(e.getCode(),e.getMsg());
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseRes.error(500,e.getMessage());
        }
        return ResponseRes.success(ResponseCode.SUCCESS,object);
    }


}
