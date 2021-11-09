package com.lq.controller;


import com.lq.dao.PointsTbMapper;
import com.lq.pojo.PersonRegisterTb;
import com.lq.pojo.PointsTb;
import com.lq.service.PointsTbService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author tooLate
 * @since 2021-05-08
 */
@Controller
@RequestMapping("/pointsTb")
public class PointsTbController {

    @Resource
    private PointsTbService pointsTbService;
    @Resource
    private PointsTbMapper pointsTbMapper;
    //积分兑换
    @PostMapping("exchangeS")
    @ResponseBody
    public void exchangeS(Integer bankId , Integer points) {
        pointsTbService.exchange(bankId, points);
    }
    //查询用户兑换的积分列表
    @GetMapping("allPointsS")
    @ResponseBody
    public List<PointsTb> allPointsS (Integer accountId , String handle){
        return pointsTbService.allPoints(accountId, handle);
    }

    //查询所有用户兑换积分的列表
    @GetMapping("webPointsS")
    @ResponseBody
    public List<PointsTb> webPointsS (String handle){
        return pointsTbMapper.webPoints(handle);
    }

    //已转账
    @PostMapping("updateHandleS")
    @ResponseBody
    public void updateHandleS (Integer pointsId){
        Date time = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        pointsTbMapper.updateHandle(pointsId,df.format(time));
    }

    //查询已转账
    @GetMapping("selectHandleS")
    @ResponseBody
    public List<PointsTb> selectHandleS(){
        return pointsTbMapper.selectHandle();
    }

    //总积分
    @GetMapping("pointS")
    @ResponseBody
    public int pointS (){
        return pointsTbService.point();
    }

    //个人总积分
    @GetMapping("personalPointsS")
    @ResponseBody
    public int personalPointsS (Integer accountId){
        return pointsTbService.personalPoints(accountId);
    }

    //兑换过的用户
    @GetMapping("userS")
    @ResponseBody
    public List<PointsTb> userS(){
        return pointsTbMapper.userAccountId();
    }
}

