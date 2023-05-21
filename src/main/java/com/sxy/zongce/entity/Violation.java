package com.sxy.zongce.entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
public class Violation {
    private String vio_Id;

    @Value(value = "违纪扣分")
    private Float vio_Score;

    @Value(value = "学期")
    private String semester;

    @Value(value = "违纪时间")
    private String vio_Time;

    @Value(value = "违纪内容")
    private String vio_detail;

    private String cancel_Time;

    @Value(value = "学生学号")
    private String stu_Id;

    private Student student;

    public String getVio_Id() {
        return vio_Id;
    }

    public void setVio_Id(String vio_Id) {
        this.vio_Id = vio_Id;
    }

    public float getVio_Score() {
        return vio_Score;
    }

    public void setVio_Score(float vio_Score) {
        this.vio_Score = vio_Score;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
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

    public void setVio_Score(Float vio_Score) {
        this.vio_Score = vio_Score;
    }

    public String getCancel_Time() {
        return cancel_Time;
    }

    public void setCancel_Time(String cancel_Time) {
        this.cancel_Time = cancel_Time;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getStu_Id() {
        return stu_Id;
    }

    public void setStu_Id(String stu_Id) {
        this.stu_Id = stu_Id;
    }
}
