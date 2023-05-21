package com.sxy.zongce.service;

import com.sxy.zongce.entity.Notice;
import com.sxy.zongce.util.PageRequest;
import com.sxy.zongce.util.PageResult;

import java.util.List;

public interface NoticeService {

    //测试，最后会删掉
    List<Notice> findAll();

    //获取公告最大的id（有用别动）
    String findMaxId();

    //查询公告是否存在
    boolean isExist(String notice_Id);

    //查看公告列表
    PageResult findAllNotice(PageRequest pageRequest);

    //根据id删除公告
    void deleteNoticeById(String notice_Id);

    //添加公告
    void addNotice(Notice notice);

    //根据id修改单个公告
    void updateNoticeById(Notice notice);

    //查询公告
    PageResult searchNotice(PageRequest pageRequest, String title);

    //根据id获取待修改公告
    Notice getNotice(String notice_Id);
}
