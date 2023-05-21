package com.sxy.zongce.util;

import com.sxy.zongce.exception.ApplicationException;
import com.sxy.zongce.exception.ResponseCode;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SemesterUtil {

    //获取当前学期
    public static String getCurrentSemester(){

        String semester = "";
        Calendar calendar = Calendar.getInstance();
        //获取当前年份
        int year = calendar.get(Calendar.YEAR);

        String spr_semester_start = year + "-03-01";
        String spr_semester_end = year + "-08-31";
        String aut_semester_start = year + "-09-01";
        String aut_semester_end = (year+1) + "-02-28";
        //判断平年闰年
        if((year%4 == 0 && year%100 != 0) || (year%400 == 0))
            aut_semester_end = (year+1) + "02-29";

        //获取当前时间
        Date date = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date spr_start = format.parse(spr_semester_start);
            Date spr_end = format.parse(spr_semester_end);
            Date aut_start = format.parse(aut_semester_start);
            Date aut_end = format.parse(aut_semester_end);

            //逐一比较日期大小，根据比较结果判断当前日期所在学期
            int spr_start_compare = date.compareTo(spr_start);
            int spr_end_compare = date.compareTo(spr_end);
            int aut_start_compare = date.compareTo(aut_start);
            int aut_end_compare = date.compareTo(aut_end);

            if((spr_start_compare == 0 || spr_start_compare == 1)
                    &&(spr_end_compare == 0 || spr_end_compare == -1)){
                semester = (year-1) + "-"+ year + "-2";
            }else if((aut_start_compare == 0 || aut_start_compare == 1)
                    &&(aut_end_compare == 0 || aut_end_compare == -1)){
                semester = year + "-" + (year+1) + "-1";
            }
        } catch (ParseException e) {
            e.printStackTrace();
            throw new ApplicationException(ResponseCode.DATE_ERROR);
        }

        return semester;
    }
}
