package com.sxy.zongce.exception;

public enum ResponseCode {

    SUCCESS(200,"成功"),
    INNER_ERROR(500,"服务器异常"),
    USER_NOT_EXIST(11000,"用户不存在"),
    WRONG_PASSWORD(11001,"密码错误"),
    STUDENT_NOT_EXIST(11003,"学生不存在"),
    NOTICE_EXISTING(12000,"添加失败，公告已存在"),
    NOTICE_NOT_EXIST(12001,"公告不存在"),
    TEMPLATE_EXITING(13000,"添加失败，模版分类已存在"),
    PROJECT_EXITING(13002,"添加失败，项目类型已存在"),
    AWARD_LEVER_EXITING(13003,"添加失败，成果级别已存在"),
    TEMPLATE_NOT_FOUND(13005,"找不到模板分类"),
    PROJECT_NOT_FOUND(13006,"找不到项目类型"),
    AWARD_LEVER_NOT_FOUND(13007,"找不到成果级别"),
    REPEAT_IMPORT(14000,"导入失败，请勿重复导入"),
    FILE_TOO_LARGE(14001,"文件过大，请重新上传"),
    FILE_IO_ERROR(14002,"文件读写异常"),
    FILE_TYPE_ERROR(14003,"文件格式有误"),
    FILE_EMPTY(14004,"请选择文件"),
    DATA_EXISTING(14005,"数据已存在，请勿重复录入"),
    EXCEL_WORKBOOK_EMPTY(14006,"创建Excel工作薄为空"),
    VIO_NOT_EXIST(14007,"违纪记录不存在"),
    VIO_CANCELED(14008,"违纪记录已消除"),
    APP_EXITING(15000,"提交失败，请勿重复提交"),
    APP_CHECKED(15001,"操作失败"),
    DATE_ERROR(15002,"日期错误"),
    APP_NOT_EXIST(15003,"申请单不存在");

    ;

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    ResponseCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
