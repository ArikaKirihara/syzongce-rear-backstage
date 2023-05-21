package com.sxy.zongce.entity;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Application {
    private String apply_Id;
    private String att_url;
    private String apply_detail;
    private String apply_time;
    private String semester;
    private String state;
    private String reason;
    private String stu_Id;
    private String awl_Id;
    private Student student;
    private AwardLever awardLever;

    public String getApply_Id() {
        return apply_Id;
    }

    public void setApply_Id(String apply_Id) {
        this.apply_Id = apply_Id;
    }

    public String getAtt_url() {
        return att_url;
    }

    public void setAtt_url(String att_url) {
        this.att_url = att_url;
    }

    public String getApply_detail() {
        return apply_detail;
    }

    public void setApply_detail(String apply_detail) {
        this.apply_detail = apply_detail;
    }

    public String getApply_time() {
        return apply_time;
    }

    public void setApply_time(String apply_time) {
        this.apply_time = apply_time;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public AwardLever getAwardLever() {
        return awardLever;
    }

    public void setAwardLever(AwardLever awardLever) {
        this.awardLever = awardLever;
    }

    public String getStu_Id() {
        return stu_Id;
    }

    public void setStu_Id(String stu_Id) {
        this.stu_Id = stu_Id;
    }

    public String getAwl_Id() {
        return awl_Id;
    }

    public void setAwl_Id(String awl_Id) {
        this.awl_Id = awl_Id;
    }
}
