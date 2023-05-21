package com.sxy.zongce.util;

import com.sxy.zongce.exception.FileException;
import com.sxy.zongce.exception.ResponseCode;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ClassUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class UploadUtil {
    //附件上传路径，是项目中附件文件存储位置的相对类路径
    private static final String ATT_FILE_PATH = ClassUtils.getDefaultClassLoader()
            .getResource("static/upload/attachment").getPath();
    //excel文件上传路径
    private static final String EXCEL_FILE_PATH = ClassUtils.getDefaultClassLoader()
            .getResource("static/upload/excel").getPath();
    //文件大小上限：3MB
    private static final float FILE_MAX_SIZE = 3 * 1024 * 1024;
    //附件合法的文件类型列表
    private static final List<String> ATT_TYPE = new ArrayList<>();
    static{
        ATT_TYPE.add(".jpg");
        ATT_TYPE.add(".png");
        ATT_TYPE.add(".PNG");
        ATT_TYPE.add(".pdf");
    }
    //配置日志
    private static final Logger log = LoggerFactory.getLogger("UploadUtil");

    public static boolean fileCheck(MultipartFile file){
        boolean flag = true;
        //判断文件是否为空
        if(file.isEmpty()){
            flag = false;
            throw new FileException(ResponseCode.FILE_EMPTY);
        }
        //判断文件大小是否超过上限
        if(file.getSize() > FILE_MAX_SIZE){
            flag = false;
            throw new FileException(ResponseCode.FILE_TOO_LARGE);
        }
        return flag;
    }

    public static String getNewFileName(MultipartFile file,String type){
        //取文件名后缀
        String extension = "." + FilenameUtils.getExtension(file.getOriginalFilename());
        //如果是附件，判断文件类型是否合法；如果是excel文件，判断是否为xls文件
        if("att".equals(type)){
            if(!ATT_TYPE.contains(extension)){
                throw new FileException(ResponseCode.FILE_TYPE_ERROR);
            }

        } else if("excel".equals(type)){
            if(!".xls".equals(extension)){
                throw new FileException(ResponseCode.FILE_TYPE_ERROR);
            }
        }
        //为附件生成新的文件名，编码为时间戳+随机id
        String newFileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())
                + UUID.randomUUID().toString().replace("-","") + extension;

        return newFileName;
    }

    public static String upload(MultipartFile file){
        if(!UploadUtil.fileCheck(file)){
            return "";
        }
        String newFileName = UploadUtil.getNewFileName(file,"att");
        // 得到文件保存的位置以及新文件名
        File dest = new File(ATT_FILE_PATH + "/" + newFileName);
        //检测是否存在目录
        if(!dest.getParentFile().exists()){
            //新建文件夹
            dest.getParentFile().mkdirs();
        }
        //上传文件，放到指定路径
        try {
            file.transferTo(dest);
            log.info("上传成功，当前上传的文件保存在 {}",ATT_FILE_PATH + "/" + newFileName);
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e.toString());
            throw new FileException(ResponseCode.FILE_IO_ERROR);
        }

        return "/" + newFileName;
    }

    public static void upload(String fileName,MultipartFile file) throws Exception {
        if(!UploadUtil.fileCheck(file)){
            return;
        }
        // 得到文件保存的位置以及新文件名
        File dest = new File(EXCEL_FILE_PATH + "/" + fileName);
        //检测是否存在目录
        if(!dest.getParentFile().exists()){
            //新建文件夹
            dest.getParentFile().mkdirs();
        }
        //上传文件，放到指定路径
        try {
            file.transferTo(dest);
            log.info("上传成功，当前上传的文件保存在 {}",EXCEL_FILE_PATH + "/" + fileName);
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e.toString());
            throw new FileException(ResponseCode.FILE_IO_ERROR);
        }
    }
}
