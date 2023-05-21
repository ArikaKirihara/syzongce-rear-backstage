package com.sxy.zongce.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sxy.zongce.entity.Else;
import com.sxy.zongce.entity.Student;
import com.sxy.zongce.exception.AddException;
import com.sxy.zongce.exception.FileException;
import com.sxy.zongce.exception.ResponseCode;
import com.sxy.zongce.mapper.ElseMapper;
import com.sxy.zongce.service.ElseService;
import com.sxy.zongce.util.PageRequest;
import com.sxy.zongce.util.PageResult;
import com.sxy.zongce.util.PageUtil;
import com.sxy.zongce.vo.ElseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElseServiceImpl implements ElseService {

    @Autowired
    private ElseMapper elseMapper;

    //测试，最后会删掉
    @Override
    public List<Else> findAll() {
        return elseMapper.findAll();
    }

    @Override
    public void incrementImport(List<Else> elseList) {
        int res = elseMapper.incrementImport(elseList);
        if(res == 0){
            throw new FileException(ResponseCode.REPEAT_IMPORT);
        }
    }

    @Override
    public void coverImport(List<Else> elseList) {
        int res = elseMapper.coverImport(elseList);
        if(res == 0){
            throw new FileException(ResponseCode.REPEAT_IMPORT);
        }
    }

    @Override
    public void addElse(Else anElse) {
        int res = elseMapper.addElse(anElse);
        if(res == 0){
            throw new AddException(ResponseCode.DATA_EXISTING);
        }
    }

    @Override
    public PageResult listElse(PageRequest pageRequest, String menter_Id) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        List<ElseVo> list = null;
        PageInfo<ElseVo> pageInfo = null;
        try {
            PageHelper.startPage(pageNum, pageSize);
            list = elseMapper.listElse(menter_Id);
            pageInfo = new PageInfo<>(list);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(ResponseCode.INNER_ERROR.getMsg());
        }

        return PageUtil.getPageResult(pageInfo);
    }

    @Override
    public PageResult searchElse(PageRequest pageRequest, String menter_Id,
                                 Student student, String semester) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        List<ElseVo> list = null;
        PageInfo<ElseVo> pageInfo = null;
        try {
            PageHelper.startPage(pageNum, pageSize);
            list = elseMapper.searchElse(menter_Id,
                    student.getGrade(), student.getMajor(), student.getStu_class(),
                    student.getStu_Id(), student.getStu_Name(), semester);
            pageInfo = new PageInfo<>(list);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(ResponseCode.INNER_ERROR.getMsg());
        }

        return PageUtil.getPageResult(pageInfo);
    }
}
