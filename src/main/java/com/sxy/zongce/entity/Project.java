package com.sxy.zongce.entity;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Project {
    private String pro_Id;
    private String pro_Name;
    private String pro_Display;
    private String tem_Id;
    private String admin_Id;
    private Template template;
    private Admin admin;
    private List<AwardLever> awardLevers;

    public String getPro_Id() {
        return pro_Id;
    }

    public void setPro_Id(String pro_Id) {
        this.pro_Id = pro_Id;
    }

    public String getPro_Name() {
        return pro_Name;
    }

    public void setPro_Name(String pro_Name) {
        this.pro_Name = pro_Name;
    }

    public String getPro_Display() {
        return pro_Display;
    }

    public void setPro_Display(String pro_Display) {
        this.pro_Display = pro_Display;
    }

    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
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

    public String getTem_Id() {
        return tem_Id;
    }

    public void setTem_Id(String tem_Id) {
        this.tem_Id = tem_Id;
    }

    public List<AwardLever> getAwardLevers() {
        return awardLevers;
    }

    public void setAwardLevers(List<AwardLever> awardLevers) {
        this.awardLevers = awardLevers;
    }
}
