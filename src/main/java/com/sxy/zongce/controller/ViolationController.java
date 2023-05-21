package com.sxy.zongce.controller;

import com.sxy.zongce.entity.Student;
import com.sxy.zongce.entity.Violation;
import com.sxy.zongce.exception.*;
import com.sxy.zongce.service.impl.MenterServiceImpl;
import com.sxy.zongce.service.impl.StudentServiceImpl;
import com.sxy.zongce.service.impl.ViolationServiceImpl;
import com.sxy.zongce.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/violation")
public class ViolationController {

    @Autowired
    private ViolationServiceImpl violationService;
    @Autowired
    private StudentServiceImpl studentService;
    @Autowired
    private MenterServiceImpl menterService;

    //学生查看违纪通报
    @RequestMapping(value = "list/student", method = RequestMethod.GET)
    @ResponseBody
    public ResponseRes<Object> indVioByStudent(PageRequest pageRequest, HttpSession session){
        String stu_Id = session.getAttribute("stu_Id").toString();
        boolean flag = true;
        PageResult pageResult = null;
        try {
            flag = studentService.isExist(stu_Id);
            if(flag){
                pageResult = violationService.findVioByStudent(pageRequest, stu_Id);
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

    //辅导员查看违纪通报
    @RequestMapping(value = "/list/menter", method = RequestMethod.GET)
    @ResponseBody
    public ResponseRes<Object> findVioByMenter(PageRequest pageRequest, HttpSession session){
        String menter_Id = session.getAttribute("menter_Id").toString();
        boolean flag = true;
        PageResult pageResult = null;
        try {
            flag = menterService.isExist(menter_Id);
            if(flag){
                pageResult = violationService.findVioByMenter(pageRequest, menter_Id);
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

    //条件查询违纪通报
    @RequestMapping(value = "/list/query", method = RequestMethod.GET)
    @ResponseBody
    public ResponseRes<Object> searchVioByMenter(PageRequest pageRequest, HttpSession session,
                                                 String vio_Time, Student student, String vio_detail){
        String menter_Id = session.getAttribute("menter_Id").toString();
        boolean flag = true;
        PageResult pageResult = null;
        try {
            flag = menterService.isExist(menter_Id);
            if(flag){
                pageResult = violationService.searchVioByMenter(pageRequest, menter_Id, vio_Time, student, vio_detail);
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

    //消除违纪通报
    @RequestMapping(value = "/{vio_Id}/cancel", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseRes<Object> cancelVioById(@PathVariable("vio_Id") String vio_Id) {
        vio_Id = "v" + vio_Id;
        boolean existFlag = true;
        boolean cancelFlag = false;
        try {
            existFlag = violationService.isExist(vio_Id);
            cancelFlag = violationService.isCanceled(vio_Id);
            if (existFlag && !cancelFlag) {
                violationService.cancelVioById(vio_Id);
            } else if (cancelFlag) {
                throw new VisibilityException(ResponseCode.VIO_CANCELED);
            }
        } catch (ExistingException e) {
            e.printStackTrace();
            return ResponseRes.error(e.getCode(), e.getMsg());
        } catch (VisibilityException e) {
            e.printStackTrace();
            return ResponseRes.error(e.getCode(), e.getMsg());
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseRes.error(500, e.getMessage());
        }
        return ResponseRes.success(ResponseCode.SUCCESS,null);
    }

    //删除违纪通报
    @RequestMapping(value = "/{vio_Id}/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseRes<Object> deleteVioById(@PathVariable("vio_Id") String vio_Id){
        vio_Id = "v" + vio_Id;
        boolean flag = true;
        try {
            flag = violationService.isExist(vio_Id);
            if(flag){
                violationService.deleteVioById(vio_Id);
            }
        }  catch (ExistingException e){
            e.printStackTrace();
            return ResponseRes.error(e.getCode(),e.getMsg());
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseRes.error(500,e.getMessage());
        }

        return ResponseRes.success(ResponseCode.SUCCESS,null);
    }


    //录入违纪通报
    @RequestMapping(value = "/entry", method = RequestMethod.POST)
    @ResponseBody
    public ResponseRes<Violation> entryViolation(Violation violation){
        String stu_Id = violation.getStu_Id();
        String max_Id = "";
        boolean res = true;
        try {
            res = studentService.isExist(stu_Id);
            if(res){
                max_Id = violationService.findMaxId();
                violation.setVio_Id(IdUtil.createViolationId(max_Id));
                violation.setSemester(SemesterUtil.getCurrentSemester());
                violationService.entryViolation(violation);
            }
        } catch (AddException e) {
            e.printStackTrace();
            return ResponseRes.error(e.getCode(),e.getMsg());
        }  catch (UserException e){
            e.printStackTrace();
            return ResponseRes.error(e.getCode(), e.getMsg());
        } catch (Exception e){
            e.printStackTrace();
            return ResponseRes.error(ResponseCode.INNER_ERROR);
        }

        return ResponseRes.success(ResponseCode.SUCCESS,violation);
    }

    //增量导入违纪通报
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
            List<Violation> violationList = new ArrayList<>();
            //如果 list 不为空，大小大于0则获取第一行存到firstRows 里面
            if (list != null && list.size() > 0) {
                firstRows = list.get(0);
            }else {
                throw new FileException(ResponseCode.EXCEL_WORKBOOK_EMPTY);
            }
            //找到当前数字部分最大的违纪id
            String maxId = violationService.findMaxId();
            //截取违纪id的数字部分作为初始值
            int idNum = Integer.parseInt(maxId.substring(1));
            //对list进行遍历，因为第一行是标题，第二行是格式，不用存到数据库，所以从第三行开始遍历
            for (int i = 2; i < list.size(); i++) {
                //获取第i行数据
                List<Object> rows = list.get(i);
                //定义violation遍历，存储第i行数据
                Violation violation = new Violation();
                //对第i行数据进行遍历，
                for (int j = 0; j < rows.size(); j++) {
                    //获取第i行第j列数据，存到cellVal 变量里面
                    String cellVal = (String) rows.get(j);
                    //调用setFileValueByFieldName函数，把数据存到violation对应的属性里面
                    ExcelUtil.setFileValueByFieldName(violation, firstRows.get(j).toString().trim(), cellVal);
                }
                //id数字部分自增
                idNum++;
                //字符串拼接成一个新id
                String newId = "v" + idNum;
                //将自动生成的id添加到violation对象中
                violation.setVio_Id(newId);
                //把violation变量加到violationList
                violationList.add(violation);
            }
            violationService.incrementImport(violationList);
        } catch (FileException e1) {
            e1.printStackTrace();
            ResponseRes.error(e1.getCode(),e1.getMsg());
        } catch (Exception e2){
            e2.printStackTrace();
            ResponseRes.error(ResponseCode.INNER_ERROR);
        }

        return ResponseRes.success(ResponseCode.SUCCESS,null);
    }

    //覆盖导入违纪通报
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
            List<Violation> violationList = new ArrayList<>();
            //如果 list 不为空，大小大于0则获取第一行存到firstRows 里面
            if (list != null && list.size() > 0) {
                firstRows = list.get(0);
            }else {
                throw new FileException(ResponseCode.EXCEL_WORKBOOK_EMPTY);
            }
            //找到当前数字部分最大的违纪id
            String maxId = violationService.findMaxId();
            //截取违纪id的数字部分作为初始值
            int idNum = Integer.parseInt(maxId.substring(1));
            //对list进行遍历，因为第一行是标题，第二行是格式，不用存到数据库，所以从第三行开始遍历
            for (int i = 2; i < list.size(); i++) {
                //获取第i行数据
                List<Object> rows = list.get(i);
                //定义violation遍历，存储第i行数据
                Violation violation = new Violation();
                //对第i行数据进行遍历，
                for (int j = 0; j < rows.size(); j++) {
                    //获取第i行第j列数据，存到cellVal 变量里面
                    String cellVal = (String) rows.get(j);
                    //调用setFileValueByFieldName函数，把数据存到violation对应的属性里面
                    ExcelUtil.setFileValueByFieldName(violation, firstRows.get(j).toString().trim(), cellVal);
                }
                //id数字部分自增
                idNum++;
                //字符串拼接成一个新id
                String newId = "v" + idNum;
                //将自动生成的id添加到violation对象中
                violation.setVio_Id(newId);
                //把violation变量加到violationList
                violationList.add(violation);
            }
            violationService.coverImport(violationList);
        } catch (FileException e1) {
            e1.printStackTrace();
            ResponseRes.error(e1.getCode(),e1.getMsg());
        } catch (Exception e2){
            e2.printStackTrace();
            ResponseRes.error(ResponseCode.INNER_ERROR);
        }

        return ResponseRes.success(ResponseCode.SUCCESS,null);
    }

    //下载违纪通报模板
    @RequestMapping(value = "/tmp", method = RequestMethod.GET)
    @ResponseBody
    public ResponseRes<Object> downloadTmp(HttpServletResponse response){
        try {
            DownloadUtil.downloadTmp("violation",response);
        } catch (FileException e){
            e.printStackTrace();
            return ResponseRes.error(e.getCode(), e.getMsg());
        } catch (UnsupportedEncodingException e){
            e.printStackTrace();
            return ResponseRes.error(500,e.getMessage());
        }

        return ResponseRes.success(ResponseCode.SUCCESS,null);
    }

    //查看违纪通报列表（导入）
    @RequestMapping(value = "/list/import", method = RequestMethod.GET)
    @ResponseBody
    public ResponseRes<Object> listViolation(PageRequest pageRequest, HttpSession session){
        String menter_Id = session.getAttribute("menter_Id").toString();
        boolean flag = true;
        PageResult pageResult = null;
        try {
            flag = menterService.isExist(menter_Id);
            if(flag){
                pageResult = violationService.listViolation(pageRequest, menter_Id);
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

    //查看违纪通报列表（导入）
    @RequestMapping(value = "/query/import", method = RequestMethod.GET)
    @ResponseBody
    public ResponseRes<Object> searchViolation(PageRequest pageRequest, HttpSession session,
                                               Student student, String semester){
        String menter_Id = session.getAttribute("menter_Id").toString();
        boolean flag = true;
        PageResult pageResult = null;
        try {
            flag = menterService.isExist(menter_Id);
            if(flag){
                pageResult = violationService.searchViolation(pageRequest, menter_Id,
                        student, semester);
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
}
