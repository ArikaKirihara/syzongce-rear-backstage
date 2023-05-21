package com.sxy.zongce.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sxy.zongce.entity.Application;
import com.sxy.zongce.entity.Notice;
import com.sxy.zongce.exception.*;
import com.sxy.zongce.mapper.NoticeMapper;
import com.sxy.zongce.service.NoticeService;
import com.sxy.zongce.util.PageRequest;
import com.sxy.zongce.util.PageResult;
import com.sxy.zongce.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    //测试，最后会删掉
    @Override
    public List<Notice> findAll() {
        return noticeMapper.findAll();
    }

    @Override
    public String findMaxId() {
        return noticeMapper.findMaxId();
    }

    @Override
    public boolean isExist(String notice_Id) {
        boolean res = true;
        Notice notice = noticeMapper.isExist(notice_Id);
        if(notice == null){
            res = false;
            throw new VisibilityException(ResponseCode.NOTICE_NOT_EXIST);
        }
        return res;
    }

    @Override
    public PageResult findAllNotice(PageRequest pageRequest) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        List<Notice> list = null;
        PageInfo<Notice> pageInfo = null;
        try {
            PageHelper.startPage(pageNum, pageSize);
            list = noticeMapper.findAllNotice();
            pageInfo = new PageInfo<>(list);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(ResponseCode.INNER_ERROR.getMsg());
        }

        return PageUtil.getPageResult(pageInfo);
    }

    @Override
    public void deleteNoticeById(String notice_Id) {
        int res = noticeMapper.deleteNoticeById(notice_Id);
        if(res == 0)
            throw new ExistingException(ResponseCode.NOTICE_NOT_EXIST);
    }

    @Override
    public void addNotice(Notice notice) {
        int res  = noticeMapper.addNotice(notice);
        if (res == 0)
            throw new AddException(ResponseCode.NOTICE_EXISTING);
    }

    @Override
    public void updateNoticeById(Notice notice) {
        int res = noticeMapper.updateNoticeById(notice);
        if(res == 0)
            throw new ExistingException(ResponseCode.NOTICE_NOT_EXIST);
    }

    @Override
    public PageResult searchNotice(PageRequest pageRequest, String title) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        List<Notice> list = null;
        PageInfo<Notice> pageInfo = null;
        try {
            PageHelper.startPage(pageNum, pageSize);
            list = noticeMapper.searchNotice(title);
            pageInfo = new PageInfo<>(list);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(ResponseCode.INNER_ERROR.getMsg());
        }
        return PageUtil.getPageResult(pageInfo);
    }

    @Override
    public Notice getNotice(String notice_Id) {
        Notice notice = noticeMapper.getNotice(notice_Id);
        if(notice == null){
            throw new VisibilityException(ResponseCode.NOTICE_NOT_EXIST);
        }
        return notice;
    }
}
