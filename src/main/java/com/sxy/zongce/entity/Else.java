package com.sxy.zongce.entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
public class Else {
    @Value(value = "学期")
    private String semester;
    @Value(value = "加分分值")
    private Float E_score;
    @Value(value = "加分内容")
    private String detail;
    @Value(value = "学生学号")
    private String stu_Id;
    private Student student;

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public float getE_score() {
        return E_score;
    }

    public void setE_score(Float e_score) {
        E_score = e_score;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
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
