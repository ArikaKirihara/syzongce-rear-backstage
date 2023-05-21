package com.sxy.zongce.entity;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Student {
    private String stu_Id;
    private String stu_Pwd;
    private String stu_Name;
    private char stu_Sex;
    private String stu_Dept;
    private String major;
    private int grade;
    private String stu_class;
    private String identity_Num;
    private String menter_Id;
    private Menter menter;
    private Admin admin;
    private List<Application> applications;
    private List<PublicWelfare> publicWelfares;
    private List<Violation> violations;
    private List<GPA> gpas;
    private List<Else> elses;

    public String getStu_Id() {
        return stu_Id;
    }

    public void setStu_Id(String stu_Id) {
        this.stu_Id = stu_Id;
    }

    public String getStu_Pwd() {
        return stu_Pwd;
    }

    public void setStu_Pwd(String stu_Pwd) {
        this.stu_Pwd = stu_Pwd;
    }

    public String getStu_Name() {
        return stu_Name;
    }

    public void setStu_Name(String stu_Name) {
        this.stu_Name = stu_Name;
    }

    public char getStu_Sex() {
        return stu_Sex;
    }

    public void setStu_Sex(char stu_Sex) {
        this.stu_Sex = stu_Sex;
    }

    public String getStu_Dept() {
        return stu_Dept;
    }

    public void setStu_Dept(String stu_Dept) {
        this.stu_Dept = stu_Dept;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getIdentity_Num() {
        return identity_Num;
    }

    public void setIdentity_Num(String identity_Num) {
        this.identity_Num = identity_Num;
    }

    public Menter getMenter() {
        return menter;
    }

    public void setMenter(Menter menter) {
        this.menter = menter;
    }

    public String getStu_class() {
        return stu_class;
    }

    public void setStu_class(String stu_class) {
        this.stu_class = stu_class;
    }

    public String getMenter_Id() {
        return menter_Id;
    }

    public void setMenter_Id(String menter_Id) {
        this.menter_Id = menter_Id;
    }

    public List<Application> getApplications() {
        return applications;
    }

    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }

    public List<PublicWelfare> getPublicWelfares() {
        return publicWelfares;
    }

    public void setPublicWelfares(List<PublicWelfare> publicWelfares) {
        this.publicWelfares = publicWelfares;
    }

    public List<Violation> getViolations() {
        return violations;
    }

    public void setViolations(List<Violation> violations) {
        this.violations = violations;
    }

    public List<GPA> getGpas() {
        return gpas;
    }

    public void setGpas(List<GPA> gpas) {
        this.gpas = gpas;
    }

    public List<Else> getElses() {
        return elses;
    }

    public void setElses(List<Else> elses) {
        this.elses = elses;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }


}
