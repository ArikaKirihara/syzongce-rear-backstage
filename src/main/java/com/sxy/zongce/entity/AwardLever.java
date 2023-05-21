package com.sxy.zongce.entity;

import org.springframework.stereotype.Component;

@Component
public class AwardLever {
    private String awl_Id;
    private String awl_Detail;
    private float awl_Score;
    private String awl_Display;
    private String pro_Id;
    private String admin_Id;
    private Template template;
    private Project project;
    private Admin admin;

    public String getAwl_Id() {
        return awl_Id;
    }

    public void setAwl_Id(String awl_Id) {
        this.awl_Id = awl_Id;
    }

    public String getAwl_Detail() {
        return awl_Detail;
    }

    public void setAwl_Detail(String awl_Detail) {
        this.awl_Detail = awl_Detail;
    }

    public float getAwl_Score() {
        return awl_Score;
    }

    public void setAwl_Score(float awl_Score) {
        this.awl_Score = awl_Score;
    }

    public String getAwl_Display() {
        return awl_Display;
    }

    public void setAwl_Display(String awl_Display) {
        this.awl_Display = awl_Display;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public String getPro_Id() {
        return pro_Id;
    }

    public void setPro_Id(String pro_Id) {
        this.pro_Id = pro_Id;
    }

    public String getAdmin_Id() {
        return admin_Id;
    }

    public void setAdmin_Id(String admin_Id) {
        this.admin_Id = admin_Id;
    }

    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }
}
