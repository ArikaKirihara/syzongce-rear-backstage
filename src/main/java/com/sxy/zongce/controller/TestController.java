package com.sxy.zongce.controller;

import com.sxy.zongce.entity.*;
import com.sxy.zongce.service.impl.*;
import com.sxy.zongce.util.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

//测试用，最后会删掉
@Controller
public class TestController {

    @Autowired
    private AdminServiceImpl adminService;
    @Autowired
    private ApplicationServiceImpl applicationService;
    @Autowired
    private AwardLeverServiceImpl awardLeverService;
    @Autowired
    private ElseServiceImpl elseService;
    @Autowired
    private GPAServiceImpl gpaService;
    @Autowired
    private MenterServiceImpl menterService;
    @Autowired
    private NoticeServiceImpl noticeService;
    @Autowired
    private ProjectServiceImpl projectService;
    @Autowired
    private PublicWelfareServiceImpl publicWelfareService;
    @Autowired
    private StudentServiceImpl studentService;
    @Autowired
    private TemplateServiceImpl templateService;
    @Autowired
    private ViolationServiceImpl violationService;


    @RequestMapping(value = "/1", method = RequestMethod.GET)
    @ResponseBody
    public List<Admin> findAllAdmin(){
        return adminService.findAll();
    }

    @RequestMapping(value = "/2", method = RequestMethod.GET)
    @ResponseBody
    public List<Application> findAllApplication(){
        return applicationService.findAll();
    }

    @RequestMapping(value = "/3", method = RequestMethod.GET)
    @ResponseBody
    public List<AwardLever> findAllAwardLever(){
        return awardLeverService.findAll();
    }

    @RequestMapping(value = "/4", method = RequestMethod.GET)
    @ResponseBody
    public List<Else> findAllElse(){
        return elseService.findAll();
    }

    @RequestMapping(value = "/5", method = RequestMethod.GET)
    @ResponseBody
    public List<GPA> findAllGPA(){
        return gpaService.findAll();
    }

    @RequestMapping(value = "/6", method = RequestMethod.GET)
    @ResponseBody
    public List<Menter> findAllMenter(){
        return menterService.findAll();
    }

    @RequestMapping(value = "/7", method = RequestMethod.GET)
    @ResponseBody
    public List<Notice> findAllNotice(){
        return noticeService.findAll();
    }

    @RequestMapping(value = "/8", method = RequestMethod.GET)
    @ResponseBody
    public List<Project> findAllProject(){
        return projectService.findAll();
    }

    @RequestMapping(value = "/9", method = RequestMethod.GET)
    @ResponseBody
    public List<PublicWelfare> findAllPublicWelfare(){
        return publicWelfareService.findAll();
    }

    @RequestMapping(value = "/10", method = RequestMethod.GET)
    @ResponseBody
    public List<Student> findAllStudent(){
        return studentService.findAll();
    }

    @RequestMapping(value = "/11", method = RequestMethod.GET)
    @ResponseBody
    public List<Template> findAllTemplate(){
        return templateService.findAll();
    }

    @RequestMapping(value = "/12", method = RequestMethod.GET)
    @ResponseBody
    public List<Violation> findAllViolation(){
        return violationService.findAll();
    }

    @RequestMapping(value = "/13", method = RequestMethod.GET)
    @ResponseBody
    public Object findStuByMenter(PageRequest pageQuery ,String menter_id){
        return studentService.findStuByMenter(pageQuery, menter_id);
    }
}
