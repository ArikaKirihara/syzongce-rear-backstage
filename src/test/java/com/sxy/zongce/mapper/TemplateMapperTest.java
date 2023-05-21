package com.sxy.zongce.mapper;

import com.sxy.zongce.entity.Project;
import com.sxy.zongce.entity.Template;
import com.sxy.zongce.service.impl.ApplicationServiceImpl;
import com.sxy.zongce.service.impl.NoticeServiceImpl;
import com.sxy.zongce.service.impl.TemplateServiceImpl;
import com.sxy.zongce.service.impl.ViolationServiceImpl;
import com.sxy.zongce.util.IdUtil;
import com.sxy.zongce.vo.ProjectVo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.ClassUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class TemplateMapperTest {
    @Autowired
    TemplateMapper templateMapper;
    @Autowired
    TemplateServiceImpl templateService;
    @Autowired
    static ApplicationServiceImpl applicationService;
    @Autowired
    NoticeServiceImpl noticeService;
    @Autowired
    ViolationServiceImpl violationService;


    @Test
    void findAllTemplate() {
//        List<Template> templateList =  templateMapper.findAllTemplate("80210122");
//        System.out.println(templateList.size());
//        System.out.println(templateList.get(1).getTem_Id());
//        System.out.println(templateList.get(1).getTem_Name());
//        System.out.println(templateList.get(1).getTem_Display());
    }

    @Test
    void updateTemplateById() {
        System.out.println(templateMapper.updateTemplateById("MU-01"));
    }

    @Test
    void addTemplate() {
        Template template =new Template();
        template.setTem_Id("MU-04");
        template.setTem_Name("某某加分");
        template.setAdmin_Id("80210122");
        System.out.println(templateMapper.addTemplate(template));
    }

    @Test
    void getTd(){
        System.out.println(IdUtil.createViolationId("v019"));
        System.out.println(IdUtil.createTemplateId("MU-03"));
        System.out.println(IdUtil.createProjectId("A45"));
        System.out.println(IdUtil.createAwlId("A450319"));
        System.out.println(IdUtil.createNoticeId("n8002"));
        System.out.println(IdUtil.createAppId("ap2461"));
    }

    @Test
    void getDownloadPath(){
        System.out.println(ClassUtils.getDefaultClassLoader()
                .getResource("templates").getPath());
    }
}
