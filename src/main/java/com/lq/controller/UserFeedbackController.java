package com.lq.controller;


import com.lq.dao.UserFeedbackMapper;
import com.lq.pojo.UserFeedback;
import com.lq.service.UserFeedbackService;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

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
 * @since 2021-05-17
 */
@Controller
@RequestMapping("/userFeedback")
public class UserFeedbackController {

    @Resource
    private UserFeedbackService userFeedbackService;

    @Resource
    private UserFeedbackMapper userFeedbackMapper;

    //用户反馈Url
    @PostMapping("feedbackUrlS")
    @ResponseBody
    public void feedbackUrlS (Integer accountId, @RequestParam(value = "file", required = false) MultipartFile multipartFile, String feedback, String proposal){
        userFeedbackService.feedbackUrl(accountId, multipartFile, feedback, proposal);
    }
    //用户反馈
    @PostMapping("feedbackS")
    @ResponseBody
    public void feedbackS (Integer accountId, String feedback, String proposal){
        userFeedbackService.feedback(accountId, feedback, proposal);
    }
    //用户反馈待处理列表
    @GetMapping("dclS")
    @ResponseBody
    public List<UserFeedback> dclS (){
        return userFeedbackMapper.dcl();
    }
    //用户反馈已处理列表
    @GetMapping("yclS")
    @ResponseBody
    public List<UserFeedback> yclS (){
        return userFeedbackMapper.ycl();
    }
    //用户反馈处理结果
    @PostMapping("updateBzS")
    @ResponseBody
    public void updateBzS (Integer feedbackId){
        Date time = new Date();
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        userFeedbackMapper.updateBz(feedbackId,df2.format(time));
    }
}

