package com.sxy.zongce.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sxy.zongce.entity.GPA;
import com.sxy.zongce.entity.Student;
import com.sxy.zongce.exception.FileException;
import com.sxy.zongce.exception.ResponseCode;
import com.sxy.zongce.mapper.GPAMapper;
import com.sxy.zongce.service.GPAService;
import com.sxy.zongce.util.PageRequest;
import com.sxy.zongce.util.PageResult;
import com.sxy.zongce.util.PageUtil;
import com.sxy.zongce.vo.GPAVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GPAServiceImpl implements GPAService {

    @Autowired
    private GPAMapper gpaMapper;

    //测试，最后会删掉
    @Override
    public List<GPA> findAll() {
        return gpaMapper.findAll();
    }

    @Override
    public void incrementImport(List<GPA> gpaList) {
        int res = gpaMapper.incrementImport(gpaList);
        if(res == 0){
            throw new FileException(ResponseCode.REPEAT_IMPORT);
        }
    }

    @Override
    public void coverImport(List<GPA> gpaList) {
        int res = gpaMapper.coverImport(gpaList);
        if(res == 0){
            throw new FileException(ResponseCode.REPEAT_IMPORT);
        }
    }

    @Override
    public PageResult listGPA(PageRequest pageRequest, String menter_Id) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        List<GPAVo> list = null;
        PageInfo<GPAVo> pageInfo = null;
        try {
            PageHelper.startPage(pageNum, pageSize);
            list = gpaMapper.listGPA(menter_Id);
            pageInfo = new PageInfo<>(list);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(ResponseCode.INNER_ERROR.getMsg());
        }

        return PageUtil.getPageResult(pageInfo);
    }

    @Override
    public PageResult searchGPA(PageRequest pageRequest, String menter_Id,
                                Student student, String semester) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        List<GPAVo> list = null;
        PageInfo<GPAVo> pageInfo = null;
        try {
            PageHelper.startPage(pageNum, pageSize);
            list = gpaMapper.searchGPA(menter_Id,
                    student.getGrade(),student.getMajor(),student.getStu_class(),
                    student.getStu_Id(),student.getStu_Name(),semester);
            pageInfo = new PageInfo<>(list);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(ResponseCode.INNER_ERROR.getMsg());
        }

        return PageUtil.getPageResult(pageInfo);
    }


}
