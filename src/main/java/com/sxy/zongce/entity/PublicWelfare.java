package com.sxy.zongce.entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
//@Component
public class PublicWelfare {

    @Value(value = "公益积分")
    private Float PW_score;
    @Value(value = "学期")
    private String semester;
    @Value(value = "学生学号")
    private String stu_Id;
    private Student student;

    public Float getPW_score() {
        return PW_score;
    }

    public void setPW_score(Float PW_score) {
        this.PW_score = PW_score;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
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
