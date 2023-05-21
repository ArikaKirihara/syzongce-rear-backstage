package com.sxy.zongce.vo;

public class GPAVo {
    private int grade;
    private String major;
    private String stu_class;
    private String stu_Id;
    private String stu_Name;
    private float GPA_score;
    private float ungetcredit;
    private String semester;

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

    public float getGPA_score() {
        return GPA_score;
    }

    public void setGPA_score(float GPA_score) {
        this.GPA_score = GPA_score;
    }

    public float getUngetcredit() {
        return ungetcredit;
    }

    public void setUngetcredit(float ungetcredit) {
        this.ungetcredit = ungetcredit;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }
}
