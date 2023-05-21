package com.sxy.zongce.vo;

public class ProjectVo {
    //成果级别管理页面显示的字段
    private String pro_Id;          //项目类型编号
    private String tem_Name;        //模板分类名称
    private String pro_Name;        //项目类型名称
    private String pro_Display;       //显示状态
    private String tem_Id;

    public String getPro_Id() {
        return pro_Id;
    }

    public void setPro_Id(String pro_Id) {
        this.pro_Id = pro_Id;
    }

    public String getTem_Name() {
        return tem_Name;
    }

    public void setTem_Name(String tem_Name) {
        this.tem_Name = tem_Name;
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

    public String getTem_Id() {
        return tem_Id;
    }

    public void setTem_Id(String tem_Id) {
        this.tem_Id = tem_Id;
    }
}
