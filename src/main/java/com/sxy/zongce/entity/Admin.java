package com.sxy.zongce.entity;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
public class Admin {
    private String admin_Id;
    private String admin_Pwd;
    private String admin_Name;
    private String admin_Dept;
    private List<Menter> menters;
    private List<Student> students;
    private List<Notice> notices;
    private List<Template> templates;
    private List<Project> projects;
    private List<AwardLever> awardLevers;

    public String getAdmin_Id() {
        return admin_Id;
    }

    public void setAdmin_Id(String admin_Id) {
        this.admin_Id = admin_Id;
    }

    public String getAdmin_Pwd() {
        return admin_Pwd;
    }

    public void setAdmin_Pwd(String admin_Pwd) {
        this.admin_Pwd = admin_Pwd;
    }

    public String getAdmin_Name() {
        return admin_Name;
    }

    public void setAdmin_Name(String admin_Name) {
        this.admin_Name = admin_Name;
    }

    public String getAdmin_Dept() {
        return admin_Dept;
    }

    public void setAdmin_Dept(String admin_Dept) {
        this.admin_Dept = admin_Dept;
    }

    public List<Menter> getMenters() {
        return menters;
    }

    public void setMenters(List<Menter> menters) {
        this.menters = menters;
    }

    public List<Notice> getNotices() {
        return notices;
    }

    public void setNotices(List<Notice> notices) {
        this.notices = notices;
    }

    public void setAwardLevers(List<AwardLever> awardLevers) {
        this.awardLevers = awardLevers;
    }

    public List<Template> getTemplates() {
        return templates;
    }

    public void setTemplates(List<Template> templates) {
        this.templates = templates;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public List<AwardLever> getAwardLevers() {
        return awardLevers;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
