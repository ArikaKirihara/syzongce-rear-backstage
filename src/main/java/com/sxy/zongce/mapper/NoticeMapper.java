package com.sxy.zongce.mapper;

import com.sxy.zongce.entity.Notice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMapper {

    //测试，最后会删掉
    List<Notice> findAll();

    //获取公告最大的id（有用别动）
    String findMaxId();

    //查询公告是否存在
    Notice isExist(String notice_Id);

    //查看公告列表
    List<Notice> findAllNotice();

    //根据id删除公告
    int deleteNoticeById(String notice_Id);

    //添加公告
    int addNotice(Notice notice);

    //根据id修改单个公告
    int updateNoticeById(Notice notice);

    //查询公告
    List<Notice> searchNotice(String title);

    //根据id获取待修改公告
    Notice getNotice(String notice_Id);
}
