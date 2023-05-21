package com.sxy.zongce.vo;

import java.util.Date;

public class ViolationVo {
    private String vio_Id;
    private int grade;
    private String major;
    private String stu_class;
    private String stu_Id;
    private String stu_Name;
    private String vio_Time;
    private String vio_detail;
    private float vio_Score;
    private String cancel_Time;
    private String semester;

    public String getVio_Id() {
        return vio_Id;
    }

    public void setVio_Id(String vio_Id) {
        this.vio_Id = vio_Id;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getStu_class() {
        return stu_class;
    }

    public void setStu_class(String stu_class) {
        this.stu_class = stu_class;
    }

    public String getStu_Id() {
        return stu_Id;
    }

    public void setStu_Id(String stu_Id) {
        this.stu_Id = stu_Id;
    }

    public String getStu_Name() {
        return stu_Name;
    }

    public void setStu_Name(String stu_Name) {
        this.stu_Name = stu_Name;
    }

    public String getVio_Time() {
        return vio_Time;
    }

    public void setVio_Time(String vio_Time) {
        this.vio_Time = vio_Time;
    }

    public String getVio_detail() {
        return vio_detail;
    }

    public void setVio_detail(String vio_detail) {
        this.vio_detail = vio_detail;
    }

    public float getVio_Score() {
        return vio_Score;
    }

    public void setVio_Score(float vio_Score) {
        this.vio_Score = vio_Score;
    }

    public String getCancel_Time() {
        return cancel_Time;
    }

    public void setCancel_Time(String cancel_Time) {
        this.cancel_Time = cancel_Time;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }
}
