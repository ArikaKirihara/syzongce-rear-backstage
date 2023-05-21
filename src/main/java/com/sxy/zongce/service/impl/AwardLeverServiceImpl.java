package com.sxy.zongce.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sxy.zongce.entity.AwardLever;
import com.sxy.zongce.exception.AddException;
import com.sxy.zongce.exception.ResponseCode;
import com.sxy.zongce.exception.VisibilityException;
import com.sxy.zongce.mapper.AwardLeverMapper;
import com.sxy.zongce.service.AwardLeverService;
import com.sxy.zongce.util.PageRequest;
import com.sxy.zongce.util.PageResult;
import com.sxy.zongce.util.PageUtil;
import com.sxy.zongce.vo.AwardLeverVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AwardLeverServiceImpl implements AwardLeverService {

    @Autowired
    private AwardLeverMapper awardLeverMapper;

    //测试，最后会删掉
    @Override
    public List<AwardLever> findAll() {
        return awardLeverMapper.findAll();
    }

    @Override
    public String findMaxId() {
        return awardLeverMapper.findMaxId();
    }

    @Override
    public PageResult findAllAwardLever(PageRequest pageRequest, String admin_Id) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        List<AwardLeverVo> list = null;
        PageInfo<AwardLeverVo> pageInfo = null;
        try {
            PageHelper.startPage(pageNum, pageSize);
            list = awardLeverMapper.findAllAwardLever(admin_Id);
            pageInfo = new PageInfo<>(list);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(ResponseCode.INNER_ERROR.getMsg());
        }

        return PageUtil.getPageResult(pageInfo);
    }

    @Override
    public PageResult findAwlByPro(PageRequest pageRequest, String admin_Id, String pro_Id) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        List<AwardLeverVo> list = null;
        PageInfo<AwardLeverVo> pageInfo = null;
        try {
            PageHelper.startPage(pageNum, pageSize);
            list = awardLeverMapper.findAwlByPro(admin_Id, pro_Id);
            pageInfo = new PageInfo<>(list);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(ResponseCode.INNER_ERROR.getMsg());
        }

        return PageUtil.getPageResult(pageInfo);
    }

    @Override
    public void updateAwardLeverById(String awl_Id) {
        int res = awardLeverMapper.updateAwardLeverById(awl_Id);
        if(res == 0){
            throw new RuntimeException(ResponseCode.INNER_ERROR.getMsg());
        }
    }

    @Override
    public void addAwardLever(AwardLever awardLever) {
        int res = awardLeverMapper.addAwardLever(awardLever);
        if(res == 0){
            throw new AddException(ResponseCode.AWARD_LEVER_EXITING);
        }
    }

    @Override
    public float findScoreById(String awl_Id) {
        float awl_Score = 0.0F;
        try {
            awl_Score = awardLeverMapper.findScoreById(awl_Id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(ResponseCode.INNER_ERROR.getMsg());
        }
        return awl_Score;
    }

    @Override
    public List<AwardLever> findAwardLever(String stu_Id, String pro_Id) {
        List<AwardLever> list = null;
        try {
            list = awardLeverMapper.findAwardLever(stu_Id, pro_Id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(ResponseCode.INNER_ERROR.getMsg());
        }
        return list;
    }

    @Override
    public boolean isVisible(String awl_Id) {
        boolean res = true;
        String awl_Display = awardLeverMapper.isVisible(awl_Id);
        if("否".equals(awl_Display)){
            res = false;
            throw new VisibilityException(ResponseCode.AWARD_LEVER_NOT_FOUND);
        }
        return res;
    }


}
