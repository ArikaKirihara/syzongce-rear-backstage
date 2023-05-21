package com.sxy.zongce.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sxy.zongce.entity.Template;
import com.sxy.zongce.exception.AddException;
import com.sxy.zongce.exception.ResponseCode;
import com.sxy.zongce.exception.VisibilityException;
import com.sxy.zongce.mapper.TemplateMapper;
import com.sxy.zongce.service.TemplateService;
import com.sxy.zongce.util.PageRequest;
import com.sxy.zongce.util.PageResult;
import com.sxy.zongce.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemplateServiceImpl implements TemplateService {

    @Autowired
    private TemplateMapper templateMapper;

    //测试，最后会删掉
    @Override
    public List<Template> findAll() {
        return templateMapper.findAll();
    }

    @Override
    public String findMaxId() {
        return templateMapper.findMaxId();
    }

    @Override
    public PageResult findAllTemplate(PageRequest pageRequest, String admin_Id) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        List<Template> list = null;
        PageInfo<Template> pageInfo = null;
        try {
            PageHelper.startPage(pageNum, pageSize);
            list = templateMapper.findAllTemplate(admin_Id);
            pageInfo = new PageInfo<>(list);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(ResponseCode.INNER_ERROR.getMsg());
        }

        return PageUtil.getPageResult(pageInfo);
    }

    @Override
    public void updateTemplateById(String tem_Id) {
        int res = templateMapper.updateTemplateById(tem_Id);
        if(res == 0){
            throw new RuntimeException(ResponseCode.INNER_ERROR.getMsg());
        }
    }

    @Override
    public void addTemplate(Template template) {
        int res = templateMapper.addTemplate(template);
        if(res == 0){
            throw new AddException(ResponseCode.TEMPLATE_EXITING);
        }
    }

    @Override
    public List<Template> findTemplate(String stu_Id) {
        List<Template> list = null;
        try {
            list = templateMapper.findTemplate(stu_Id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(ResponseCode.INNER_ERROR.getMsg());
        }
        return list;
    }

    @Override
    public boolean isVisible(String tem_Id) {
        boolean res = true;
        String pro_Display = templateMapper.isVisible(tem_Id);
        if("否".equals(pro_Display)){
            res = false;
            throw new VisibilityException(ResponseCode.TEMPLATE_NOT_FOUND);
        }
        return res;
    }
}
