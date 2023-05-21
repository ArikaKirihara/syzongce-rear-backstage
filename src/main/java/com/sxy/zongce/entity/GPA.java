package com.sxy.zongce.entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Data
//@Component
public class GPA {
    @Value(value = "绩点")
    private Float GPA_score;
    @Value(value = "学期")
    private String semester;
    @Value(value = "未得学分")
    private Float ungetcredit;
    @Value(value = "学生学号")
    private String stu_Id;
    private Student student;

    public Float getGPA_score() {
        return GPA_score;
    }

    public void setGPA_score(Float GPA_score) {
        this.GPA_score = GPA_score;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public Float getUngetcredit() {
        return ungetcredit;
    }

    public void setUngetcredit(Float ungetcredit) {
        this.ungetcredit = ungetcredit;
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
