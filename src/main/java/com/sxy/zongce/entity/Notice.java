package com.sxy.zongce.entity;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Notice {
    private String notice_Id;
    private String title;
    private String create_Time;
    private String content;
    private String admin_Id;
    private Admin admin;

    public String getNotice_Id() {
        return notice_Id;
    }

    public void setNotice_Id(String notice_Id) {
        this.notice_Id = notice_Id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreate_Time() {
        return create_Time;
    }

    public void setCreate_Time(String create_Time) {
        this.create_Time = create_Time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public String getAdmin_Id() {
        return admin_Id;
    }

    public void setAdmin_Id(String admin_Id) {
        this.admin_Id = admin_Id;
    }
}
