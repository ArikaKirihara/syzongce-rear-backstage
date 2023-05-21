package com.sxy.zongce.controller;

import com.sxy.zongce.entity.Notice;
import com.sxy.zongce.exception.AddException;
import com.sxy.zongce.exception.ExistingException;
import com.sxy.zongce.exception.ResponseCode;
import com.sxy.zongce.exception.ResponseRes;
import com.sxy.zongce.service.impl.AdminServiceImpl;
import com.sxy.zongce.service.impl.NoticeServiceImpl;
import com.sxy.zongce.util.IdUtil;
import com.sxy.zongce.util.PageRequest;
import com.sxy.zongce.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private NoticeServiceImpl noticeService;

    //查看公告列表
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public ResponseRes<Object> findAllNotice(PageRequest pageRequest) {
        PageResult pageResult = null;
        try {
            pageResult = noticeService.findAllNotice(pageRequest);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseRes.error(500,e.getMessage());
        }
        return ResponseRes.success(ResponseCode.SUCCESS,pageResult);
    }

    //删除公告
    @RequestMapping(value = "{notice_Id}/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseRes<Object> deleteNoticeById(@PathVariable String notice_Id){
        notice_Id = "n" + notice_Id;
        boolean flag = true;
        try {
            flag = noticeService.isExist(notice_Id);
            if(flag){
                noticeService.deleteNoticeById(notice_Id);
            }
        } catch (ExistingException e) {
            e.printStackTrace();
            return ResponseRes.error(e.getCode(),e.getMsg());
        }  catch (Exception e){
            e.printStackTrace();
            return ResponseRes.error(ResponseCode.INNER_ERROR);
        }

        return ResponseRes.success(ResponseCode.SUCCESS,null);
    }
    //添加公告
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseRes<Notice> addNotice(Notice notice,HttpSession session){
        String maxId = "";
        String admin_Id = session.getAttribute("admin_Id").toString();
        try {
            maxId = noticeService.findMaxId();
            notice.setNotice_Id(IdUtil.createNoticeId(maxId));
            notice.setAdmin_Id(admin_Id);
            noticeService.addNotice(notice);
        } catch (AddException e) {
            e.printStackTrace();
            return ResponseRes.error(e.getCode(),e.getMsg());
        } catch (Exception e){
            e.printStackTrace();
            return ResponseRes.error(ResponseCode.INNER_ERROR);
        }
        return ResponseRes.success(ResponseCode.SUCCESS,notice);
    }

    //修改公告页面
    @RequestMapping(value = "/update-page", method = RequestMethod.GET)
    @ResponseBody
    public ResponseRes<Notice> updateNoticePage(String notice_Id){
        notice_Id = "n" + notice_Id;
        boolean flag = true;
        Notice notice = null;
        try {
            flag = noticeService.isExist(notice_Id);
            if(flag){
                notice = noticeService.getNotice(notice_Id);
            }
        } catch (ExistingException e) {
            e.printStackTrace();
            return ResponseRes.error(e.getCode(),e.getMsg());
        } catch (Exception e){
            e.printStackTrace();
            return ResponseRes.error(ResponseCode.INNER_ERROR);
        }

        return ResponseRes.success(ResponseCode.SUCCESS,notice);
    }

    //修改公告
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseRes<Object> updateNoticeById(Notice notice){
        String notice_Id = notice.getNotice_Id();
        notice_Id = "n" + notice_Id;
        notice.setNotice_Id(notice_Id);
        boolean flag = true;
        try {
            flag = noticeService.isExist(notice_Id);
            if(flag){
                noticeService.updateNoticeById(notice);
            }
        } catch (ExistingException e) {
            e.printStackTrace();
            return ResponseRes.error(e.getCode(),e.getMsg());
        } catch (Exception e){
            e.printStackTrace();
            return ResponseRes.error(ResponseCode.INNER_ERROR);
        }

        return ResponseRes.success(ResponseCode.SUCCESS,notice);
    }

    //查询公告
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ResponseBody
    public ResponseRes<Object> searchNotice(PageRequest pageRequest, String title) {
        PageResult pageResult = null;
        try {
            pageResult = noticeService.searchNotice(pageRequest, title);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseRes.error(500,e.getMessage());
        }
        return ResponseRes.success(ResponseCode.SUCCESS,pageResult);
    }
}
