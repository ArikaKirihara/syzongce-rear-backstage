package com.sxy.zongce.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sxy.zongce.entity.PublicWelfare;
import com.sxy.zongce.entity.Student;
import com.sxy.zongce.exception.FileException;
import com.sxy.zongce.exception.ResponseCode;
import com.sxy.zongce.mapper.PublicWelfareMapper;
import com.sxy.zongce.service.PublicWelfareService;
import com.sxy.zongce.util.PageRequest;
import com.sxy.zongce.util.PageResult;
import com.sxy.zongce.util.PageUtil;
import com.sxy.zongce.vo.PublicWelfareVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublicWelfareServiceImpl implements PublicWelfareService {

    @Autowired
    private PublicWelfareMapper publicWelfareMapper;

    //测试，最后会删掉
    @Override
    public List<PublicWelfare> findAll() {
        return publicWelfareMapper.findAll();
    }

    @Override
    public void incrementImport(List<PublicWelfare> publicWelfareList) {
        int res = publicWelfareMapper.incrementImport(publicWelfareList);
        if(res == 0){
            throw new FileException(ResponseCode.REPEAT_IMPORT);
        }
    }

    @Override
    public void coverImport(List<PublicWelfare> publicWelfareList) {
        int res = publicWelfareMapper.coverImport(publicWelfareList);
        if(res == 0){
            throw new FileException(ResponseCode.REPEAT_IMPORT);
        }
    }

    @Override
    public PageResult listPW(PageRequest pageRequest, String menter_Id) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        List<PublicWelfareVo> list = null;
        PageInfo<PublicWelfareVo> pageInfo = null;
        try {
            PageHelper.startPage(pageNum, pageSize);
            list = publicWelfareMapper.listPW(menter_Id);
            pageInfo = new PageInfo<>(list);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(ResponseCode.INNER_ERROR.getMsg());
        }

        return PageUtil.getPageResult(pageInfo);
    }

    @Override
    public PageResult searchPW(PageRequest pageRequest, String menter_Id,
                               Student student, String semester) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        List<PublicWelfareVo> list = null;
        PageInfo<PublicWelfareVo> pageInfo = null;
        try {
            PageHelper.startPage(pageNum, pageSize);
            list = publicWelfareMapper.searchPW(menter_Id,
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
