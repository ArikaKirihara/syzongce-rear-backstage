package com.sxy.zongce.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sxy.zongce.entity.Project;
import com.sxy.zongce.exception.AddException;
import com.sxy.zongce.exception.ResponseCode;
import com.sxy.zongce.exception.VisibilityException;
import com.sxy.zongce.mapper.ProjectMapper;
import com.sxy.zongce.service.ProjectService;
import com.sxy.zongce.util.PageRequest;
import com.sxy.zongce.util.PageResult;
import com.sxy.zongce.util.PageUtil;
import com.sxy.zongce.vo.ProjectVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    //测试，最后会删掉
    @Override
    public List<Project> findAll() {
        return projectMapper.findAll();
    }

    @Override
    public String findMaxId() {
        return projectMapper.findMaxId();
    }

    @Override
    public PageResult findAllProject(PageRequest pageRequest, String admin_Id) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        List<ProjectVo> list = null;
        PageInfo<ProjectVo> pageInfo = null;
        try {
            PageHelper.startPage(pageNum, pageSize);
            list = projectMapper.findAllProject(admin_Id);
            pageInfo = new PageInfo<>(list);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(ResponseCode.INNER_ERROR.getMsg());
        }

        return PageUtil.getPageResult(pageInfo);
    }

    @Override
    public void updateProjectById(String pro_Id) {
        int res = projectMapper.updateProjectById(pro_Id);
        if(res == 0){
            throw new RuntimeException(ResponseCode.INNER_ERROR.getMsg());
        }
    }

    @Override
    public void addProject(Project project) {
        int res = projectMapper.addProject(project);
        if(res == 0){
            throw new AddException(ResponseCode.PROJECT_EXITING);
        }
    }

    @Override
    public List<Project> findProject(String stu_Id, String tem_Id) {
        List<Project> list = null;
        try {
            list = projectMapper.findProject(stu_Id, tem_Id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(ResponseCode.INNER_ERROR.getMsg());
        }
        return list;
    }

    @Override
    public boolean isVisible(String pro_Id) {
        boolean res = true;
        String pro_Display = projectMapper.isVisible(pro_Id);
        if("否".equals(pro_Display)){
            res = false;
            throw new VisibilityException(ResponseCode.PROJECT_NOT_FOUND);
        }
        return res;
    }

    @Override
    public PageResult findProByTmp(PageRequest pageRequest, String admin_Id, String tem_Id) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        List<ProjectVo> list = null;
        PageInfo<ProjectVo> pageInfo = null;
        try {
            PageHelper.startPage(pageNum, pageSize);
            list = projectMapper.findProByTmp(admin_Id, tem_Id);
            pageInfo = new PageInfo<>(list);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(ResponseCode.INNER_ERROR.getMsg());
        }

        return PageUtil.getPageResult(pageInfo);
    }
}
