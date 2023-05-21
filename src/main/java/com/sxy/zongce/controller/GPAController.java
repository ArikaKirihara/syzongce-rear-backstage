package com.sxy.zongce.controller;

import com.sxy.zongce.entity.GPA;
import com.sxy.zongce.entity.Student;
import com.sxy.zongce.exception.FileException;
import com.sxy.zongce.exception.ResponseCode;
import com.sxy.zongce.exception.ResponseRes;
import com.sxy.zongce.exception.UserException;
import com.sxy.zongce.service.impl.GPAServiceImpl;
import com.sxy.zongce.service.impl.MenterServiceImpl;
import com.sxy.zongce.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/gpa")
public class GPAController {

    @Autowired
    private GPAServiceImpl gpaService;
    @Autowired
    private MenterServiceImpl menterService;

    //增量导入绩点
    @RequestMapping(value = "/import/increment", method = RequestMethod.POST)
    @ResponseBody
    public ResponseRes<Object> incrementImport(MultipartFile file) {
        try {
            String newFilename = UploadUtil.getNewFileName(file,"excel");
            UploadUtil.upload(newFilename,file);
            //输入流，获取刚刚上传文件转成输入流
            FileInputStream fileInputStream = new FileInputStream(new File(ClassUtils.getDefaultClassLoader().getResource("static/upload/excel").getPath().replaceFirst("/","") + "/" + newFilename));
            //定义一个list变量，模拟excel结构
            List<List<Object>> list = ExcelUtil.getListByExcel(fileInputStream, newFilename);
            //定义firstRows变量，用来获取第一行，就是标题，每列名字
            List<Object> firstRows = null;
            //定义violationList变量，用来存储文件内容(违纪通报)
            List<GPA> gpaList = new ArrayList<>();
            //如果 list 不为空，大小大于0则获取第一行存到firstRows 里面
            if (list != null && list.size() > 0) {
                firstRows = list.get(0);
            }else {
                throw new FileException(ResponseCode.EXCEL_WORKBOOK_EMPTY);
            }
            //对list进行遍历，因为第一行是标题，第二行是格式，不用存到数据库，所以从第三行开始遍历
            for (int i = 2; i < list.size(); i++) {
                //获取第i行数据
                List<Object> rows = list.get(i);
                //定义gpa遍历，存储第i行数据
                GPA gpa = new GPA();
                //对第i行数据进行遍历，
                for (int j = 0; j < rows.size(); j++) {
                    //获取第i行第j列数据，存到cellVal 变量里面
                    String cellVal = (String) rows.get(j);
                    //调用setFileValueByFieldName函数，把数据存到gpa对应的属性里面
                    ExcelUtil.setFileValueByFieldName(gpa, firstRows.get(j).toString().trim(), cellVal);
                }
                //gpa变量加到gpaList
                gpaList.add(gpa);
            }
            gpaService.incrementImport(gpaList);
        } catch (FileException e1) {
            e1.printStackTrace();
            ResponseRes.error(e1.getCode(),e1.getMsg());
        } catch (Exception e2){
            e2.printStackTrace();
            ResponseRes.error(ResponseCode.INNER_ERROR);
        }

        return ResponseRes.success(ResponseCode.SUCCESS,null);
    }

    //覆盖导入绩点
    @RequestMapping(value = "/import/cover", method = RequestMethod.POST)
    @ResponseBody
    public ResponseRes<Object> coverImport(MultipartFile file) {
        try {
            String newFilename = UploadUtil.getNewFileName(file,"excel");
            UploadUtil.upload(newFilename,file);
            //输入流，获取刚刚上传文件转成输入流
            FileInputStream fileInputStream = new FileInputStream(new File(ClassUtils.getDefaultClassLoader().getResource("static/upload/excel").getPath().replaceFirst("/","") + "/" + newFilename));
            //定义一个list变量，模拟excel结构
            List<List<Object>> list = ExcelUtil.getListByExcel(fileInputStream, newFilename);
            //定义firstRows变量，用来获取第一行，就是标题，每列名字
            List<Object> firstRows = null;
            //定义violationList变量，用来存储文件内容(违纪通报)
            List<GPA> gpaList = new ArrayList<>();
            //如果 list 不为空，大小大于0则获取第一行存到firstRows 里面
            if (list != null && list.size() > 0) {
                firstRows = list.get(0);
            }else {
                throw new FileException(ResponseCode.EXCEL_WORKBOOK_EMPTY);
            }
            //对list进行遍历，因为第一行是标题，第二行是格式，不用存到数据库，所以从第三行开始遍历
            for (int i = 2; i < list.size(); i++) {
                //获取第i行数据
                List<Object> rows = list.get(i);
                //定义gpa遍历，存储第i行数据
                GPA gpa = new GPA();
                //对第i行数据进行遍历，
                for (int j = 0; j < rows.size(); j++) {
                    //获取第i行第j列数据，存到cellVal 变量里面
                    String cellVal = (String) rows.get(j);
                    //调用setFileValueByFieldName函数，把数据存到gpa对应的属性里面
                    ExcelUtil.setFileValueByFieldName(gpa, firstRows.get(j).toString().trim(), cellVal);
                }
                //gpa变量加到gpaList
                gpaList.add(gpa);
            }
            gpaService.coverImport(gpaList);
        } catch (FileException e1) {
            e1.printStackTrace();
            ResponseRes.error(e1.getCode(),e1.getMsg());
        } catch (Exception e2){
            e2.printStackTrace();
            ResponseRes.error(ResponseCode.INNER_ERROR);
        }

        return ResponseRes.success(ResponseCode.SUCCESS,null);
    }

    //查看绩点列表
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public ResponseRes<Object> listGPA(PageRequest pageRequest, HttpSession session){

        String menter_Id = session.getAttribute("menter_Id").toString();
        boolean flag = true;
        PageResult pageResult = null;
        try {
            flag = menterService.isExist(menter_Id);
            if(flag){
                pageResult = gpaService.listGPA(pageRequest, menter_Id);
            }

        } catch (UserException e) {
            e.printStackTrace();
            return ResponseRes.error(e.getCode(),e.getMsg());
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseRes.error(500,e.getMessage());
        }
        return ResponseRes.success(ResponseCode.SUCCESS,pageResult);
    }

    //条件查询绩点
    @RequestMapping(value = "/list/query", method = RequestMethod.GET)
    @ResponseBody
    public ResponseRes<Object> searchGPA(PageRequest pageRequest, HttpSession session,
                                         Student student, String semester){
        String menter_Id = session.getAttribute("menter_Id").toString();
        boolean flag = true;
        PageResult pageResult = null;
        try {
            flag = menterService.isExist(menter_Id);
            if(flag){
                pageResult = gpaService.searchGPA(pageRequest, menter_Id, student, semester);
            }

        } catch (UserException e) {
            e.printStackTrace();
            return ResponseRes.error(e.getCode(),e.getMsg());
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseRes.error(500,e.getMessage());
        }
        return ResponseRes.success(ResponseCode.SUCCESS,pageResult);
    }

    //下载绩点模板
    @RequestMapping(value = "/tmp", method = RequestMethod.GET)
    @ResponseBody
    public ResponseRes<Object> downloadTmp(HttpServletResponse response){
        try {
            DownloadUtil.downloadTmp("gpa",response);
        } catch (FileException e){
            e.printStackTrace();
            return ResponseRes.error(e.getCode(), e.getMsg());
        } catch (UnsupportedEncodingException e){
            e.printStackTrace();
            return ResponseRes.error(500,e.getMessage());
        }

        return ResponseRes.success(ResponseCode.SUCCESS,null);
    }

}
