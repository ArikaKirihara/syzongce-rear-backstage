package com.sxy.zongce.vo;

public class AwardLeverVo {
    //成果级别管理页面显示的字段
    private String awl_Id;          //成果级别编号
    private String tem_Name;        //模板分类名称
    private String pro_Name;        //项目类型名称
    private String awl_Detail;      //成鬼级别名称
    private float awl_Score;        //分值
    private String awl_Display;       //状态
    private String pro_Id;          //插入外键要用到
    private String tem_Id;          //同上

    public String getAwl_Id() {
        return awl_Id;
    }

    public void setAwl_Id(String awl_Id) {
        this.awl_Id = awl_Id;
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

    public String getPro_Id() {
        return pro_Id;
    }

    public void setPro_Id(String pro_Id) {
        this.pro_Id = pro_Id;
    }

    public String getTem_Id() {
        return tem_Id;
    }

    public void setTem_Id(String tem_Id) {
        this.tem_Id = tem_Id;
    }
}
