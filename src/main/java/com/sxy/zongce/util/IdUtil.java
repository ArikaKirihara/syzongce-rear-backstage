package com.sxy.zongce.util;

import com.sxy.zongce.exception.AddException;
import com.sxy.zongce.exception.ResponseCode;

//id生成器
public class IdUtil {
    //id模板，把相同的代码提取出来，减少耦合
    //传入最大的id、数字部分的最大值、字符串切割起点索引和前缀
    public static String idTmp(String last_id, int max, int index, String prefix){
        //暂时存放数字部分
        int num = Integer.parseInt(last_id.substring(index));
        //最大值以下数字部分自增，这里或许可以设置当id达到最大值后抛出异常
        if(num<max){
            num++;
        }
        //将前缀和自增后的数字部分拼接成一个新的id，直接返回
        String latest_id = prefix + num;
        return latest_id;
    }

    //生成模板分类id
    public static String createTemplateId(String maxId){
        return IdUtil.idTmp(maxId,10,3,"MU-");
    }

    //生成项目类型id
    public static String createProjectId(String maxId){
        return IdUtil.idTmp(maxId, 9999, 1,"P");
    }

    //生成成果级别id
    public static String createAwlId(String maxId){
        return IdUtil.idTmp(maxId,999999,1,"A");
    }

    //生成公告id
    public static String createNoticeId(String maxId){
        return IdUtil.idTmp(maxId,9999,1,"n");
    }

    //生成申请单id
    public static String createAppId(String maxId){
        return IdUtil.idTmp(maxId,9999,2,"ap");
    }

    //生成违纪id
    public static String createViolationId(String maxId){
        return IdUtil.idTmp(maxId,9999,1,"v");
    }


}
