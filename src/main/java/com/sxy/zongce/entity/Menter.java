package com.sxy.zongce.entity;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Menter {
    private String menter_Id;
    private String m_Pwd;
    private String m_Name;
    private String m_Dept;
    private int grade;
    private String admin_Id;
    private Admin admin;
    private List<Student> students;
    private List<Application> applications;

    public String getMenter_Id() {
        return menter_Id;
    }

    public void setMenter_Id(String menter_Id) {
        this.menter_Id = menter_Id;
    }

    public String getM_Pwd() {
        return m_Pwd;
    }

    public void setM_Pwd(String m_Pwd) {
        this.m_Pwd = m_Pwd;
    }

    public String getM_Name() {
        return m_Name;
    }

    public void setM_Name(String m_Name) {
        this.m_Name = m_Name;
    }

    public String getM_Dept() {
        return m_Dept;
    }

    public void setM_Dept(String m_Dept) {
        this.m_Dept = m_Dept;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
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

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Application> getApplications() {
        return applications;
    }

    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }
}
