package com.sxy.zongce.controller;

import com.sxy.zongce.entity.AwardLever;
import com.sxy.zongce.exception.*;
import com.sxy.zongce.service.impl.AdminServiceImpl;
import com.sxy.zongce.service.impl.AwardLeverServiceImpl;
import com.sxy.zongce.service.impl.ProjectServiceImpl;
import com.sxy.zongce.service.impl.StudentServiceImpl;
import com.sxy.zongce.util.IdUtil;
import com.sxy.zongce.util.PageRequest;
import com.sxy.zongce.util.PageResult;
import com.sxy.zongce.vo.AwardLeverVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/award-lever")
public class AwardLeverController {

    @Autowired
    private AwardLeverServiceImpl awardLeverService;
    @Autowired
    private AdminServiceImpl adminService;
    @Autowired
    private ProjectServiceImpl projectService;
    @Autowired
    private StudentServiceImpl studentService;

    //成果级别列表
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public ResponseRes<Object> findAllAwardLever(PageRequest pageRequest, HttpSession session){
        String admin_Id = session.getAttribute("admin_Id").toString();
        boolean flag = true;
        PageResult pageResult = null;
        try {
            flag = adminService.isExist(admin_Id);
            if(flag){
                pageResult = awardLeverService.findAllAwardLever(pageRequest, admin_Id);
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

    //筛选成果级别
    @RequestMapping(value = "{pro_Id}/filt", method = RequestMethod.GET)
    @ResponseBody
    public ResponseRes<Object> findAwlByPro(PageRequest pageRequest, HttpSession session,@PathVariable String pro_Id){
        String admin_Id = session.getAttribute("admin_Id").toString();
        pro_Id = "P" + pro_Id;
        boolean flag = true;
        PageResult pageResult = null;
        try {
            flag = adminService.isExist(admin_Id);
            if(flag){
                pageResult = awardLeverService.findAwlByPro(pageRequest, admin_Id, pro_Id);
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

    //更改成果级别状态
    @RequestMapping(value = "/{awl_Id}/switch", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseRes<Object> updateAwardLeverById(@PathVariable("awl_Id") String awl_Id){
        awl_Id = "A" + awl_Id;
        try{
            awardLeverService.updateAwardLeverById(awl_Id);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseRes.error(500,e.getMessage());
        }
        return ResponseRes.success(ResponseCode.SUCCESS,null);
    }

    //添加成果级别
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseRes<AwardLever> addAwardLever(AwardLever awardLever , HttpSession session){
        awardLever.setAdmin_Id(session.getAttribute("admin_Id").toString());
        String max_Id = "";
        try {
            //获取数字顺序最大的id
            max_Id = awardLeverService.findMaxId();
            //自动生成模板分类id，实现id自增
            awardLever.setAwl_Id(IdUtil.createAwlId(max_Id));
            awardLeverService.addAwardLever(awardLever);
        } catch (AddException e) {
            e.printStackTrace();
            return ResponseRes.error(e.getCode(),e.getMsg());
        }  catch (Exception e) {
            e.printStackTrace();
            return ResponseRes.error(ResponseCode.INNER_ERROR);
        }
        return ResponseRes.success(ResponseCode.SUCCESS,awardLever);
    }

    //获取成果级别
    @RequestMapping(value = "{pro_Id}/now", method = RequestMethod.GET)
    @ResponseBody
    public ResponseRes<Object> findAwardLever(HttpSession session,@PathVariable("pro_Id") String pro_Id){
        String stu_Id = session.getAttribute("stu_Id").toString();
        Object object = null;
        boolean stuFlag = true;
        boolean proFlag = true;
        try {
            stuFlag = studentService.isExist(stu_Id);
            proFlag = projectService.isVisible(pro_Id);
            if(stuFlag && proFlag){
                object = awardLeverService.findAwardLever(stu_Id,pro_Id);
            }
        } catch (UserException e) {
            e.printStackTrace();
            return ResponseRes.error(e.getCode(),e.getMsg());
        } catch (VisibilityException e){
            e.printStackTrace();
            return ResponseRes.error(e.getCode(),e.getMsg());
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseRes.error(500,e.getMessage());
        }
        return ResponseRes.success(ResponseCode.SUCCESS,object);
    }

    //获取分值
    @RequestMapping(value = "/score/now", method = RequestMethod.GET)
    @ResponseBody
    public ResponseRes<Float> findScoreById(String awl_Id){
        Float awl_Score = 0.0F;
//        float awl_Score = 0;
        boolean flag = true;
        try {
            flag = awardLeverService.isVisible(awl_Id);
            if(flag) {
                awl_Score = awardLeverService.findScoreById(awl_Id);
            }
        } catch (VisibilityException e){
            e.printStackTrace();
            return ResponseRes.error(e.getCode(),e.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseRes.error(ResponseCode.INNER_ERROR);
        }
        return ResponseRes.success(ResponseCode.SUCCESS,awl_Score);
    }

}
