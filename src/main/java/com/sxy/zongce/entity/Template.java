package com.sxy.zongce.entity;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Template {
    private String tem_Id;
    private String tem_Name;
    private String tem_Display;
    private String admin_Id;
    private Admin admin;
    private List<Project> projects;
    private List<AwardLever> awardLevers;

    public String getTem_Id() {
        return tem_Id;
    }

    public void setTem_Id(String tem_Id) {
        this.tem_Id = tem_Id;
    }

    public String getTem_Name() {
        return tem_Name;
    }

    public void setTem_Name(String tem_Name) {
        this.tem_Name = tem_Name;
    }

    public String getTem_Display() {
        return tem_Display;
    }

    public void setTem_Display(String tem_Display) {
        this.tem_Display = tem_Display;
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

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public List<AwardLever> getAwardLevers() {
        return awardLevers;
    }

    public void setAwardLevers(List<AwardLever> awardLevers) {
        this.awardLevers = awardLevers;
    }
}
