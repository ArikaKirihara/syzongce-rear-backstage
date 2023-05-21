package com.sxy.zongce.util;

import com.sxy.zongce.exception.FileException;
import com.sxy.zongce.exception.ResponseCode;
import org.apache.commons.io.IOUtils;
import org.springframework.util.ClassUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class  DownloadUtil {

    private static final String ELSE_TMP = "导入其他加分模板.xls";
    private static final String GPA_TMP = "导入绩点模板.xls";
    private static final String PUBLIC_WELFARE_TMP = "导入公益积分模板.xls";
    private static final String VIOLATION_TMP = "导入违纪违规模板.xls";
    private static final String TMP_PATH = ClassUtils.getDefaultClassLoader()
            .getResource("templates").getPath();

    public static void browseAtt(String att_url, HttpServletResponse response){
        try {
            // 获取文件输入流（把磁盘上的文件通过IO加载到程序（内存）中
            FileInputStream fis = new FileInputStream(new File(att_url));
            //截取文件名
            String fileName = att_url.substring(att_url.lastIndexOf("/")+1);
            //打开附件
            response.setHeader("content-disposition",
                "inline;fileName=" + fileName);
            // 获取响应流（找到后需要通过Response发送回给用户
            ServletOutputStream os = response.getOutputStream();
            // 文件拷贝
            IOUtils.copy(fis, os);
            // 关闭流
            IOUtils.closeQuietly(fis);
            IOUtils.closeQuietly(os);
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileException(ResponseCode.FILE_IO_ERROR);
        }
    }

    public static void downloadTmp(String tableName,HttpServletResponse response) throws UnsupportedEncodingException {
        String fileName = "";

        switch (tableName){
            case "else":
                fileName = ELSE_TMP;
                break;
            case "gpa":
                fileName = GPA_TMP;
                break;
            case "publicwelfare":
                fileName = PUBLIC_WELFARE_TMP;
                break;
            case "violation":
                fileName = VIOLATION_TMP;
                break;
            default:
                fileName = "";
                break;
        }


        try {
            // 获取文件输入流（把磁盘上的文件通过IO加载到程序（内存）中
            FileInputStream fis = new FileInputStream(new File(TMP_PATH + "/" +fileName));
            //请求下载文件
            response.setHeader("content-disposition",
                    "attachment;fileName=" + URLEncoder.encode(fileName, "UTF-8"));

            // 获取响应流（找到后需要通过Response发送回给用户
            ServletOutputStream os = response.getOutputStream();
            // 文件拷贝
            IOUtils.copy(fis, os);
            // 关闭流
            IOUtils.closeQuietly(fis);
            IOUtils.closeQuietly(os);
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileException(ResponseCode.FILE_IO_ERROR);
        }

    }
}
