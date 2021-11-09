package com.lq.controller;


import com.lq.dao.EvaluateTbMapper;
import com.lq.pojo.EvaluateTb;
import com.lq.service.EvaluateTbService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author tooLate
 * @since 2021-05-21
 */
@Controller
@RequestMapping("/evaluateTb")
public class EvaluateTbController {

    @Resource
    private EvaluateTbService evaluateTbService;
    @Resource
    private EvaluateTbMapper evaluateTbMapper;

    //增加评论
    @PostMapping("addEvaluateS")
    @ResponseBody
    public int addEvaluateS (Integer accountId, String accountName, Integer purchaseOrderProductPnId, String evaluate,String txImg){
       return evaluateTbService.addEvaluate(accountId,accountName, purchaseOrderProductPnId, evaluate,txImg);
    }
    //删除评论
    @PostMapping("delEvaluateS")
    @ResponseBody
    public void delEvaluateS (Integer evaluateId){
        evaluateTbService.delEvaluate(evaluateId);
    }

    //显示评论
    @GetMapping("selectEvaluateS")
    @ResponseBody
    public List<EvaluateTb> selectEvaluateS (Integer purchaseOrderProductPnId){
        return evaluateTbService.selectEvaluate(purchaseOrderProductPnId);
    }
    //显示两条评论
    @GetMapping("selectEvaluate1S")
    @ResponseBody
    public List<EvaluateTb> selectEvaluate1S (Integer purchaseOrderProductPnId){
        return evaluateTbService.selectEvaluate1(purchaseOrderProductPnId);
    }

    //回复评论
    @PostMapping("replyS")
    @ResponseBody
    public void replyS (Integer evaluateId,String lianQiReply ){
        evaluateTbService.reply(evaluateId, lianQiReply);
    }
    //当天未回复的评论
    @GetMapping("noReplyS")
    @ResponseBody
    public List<EvaluateTb> noReplyS (String time){
        return evaluateTbService.noReply(time);
    }
    //当天已回复的评论
    @GetMapping("yesReplyS")
    @ResponseBody
    public List<EvaluateTb> yesReplyS (String time){
        return evaluateTbService.yesReply(time);
    }

    //获取全部评论
    @GetMapping("allEvaluateS")
    @ResponseBody
    public List<EvaluateTb> allEvaluateS (){
       return evaluateTbMapper.allEvaluate();
    }
    //当天某个商品未回复的评论
    @GetMapping("noReplyIdS")
    @ResponseBody
    public List<EvaluateTb> noReplyIdS (String time,Integer purchaseOrderProductPnId){
        return evaluateTbMapper.noReplyId(time,purchaseOrderProductPnId);
    }
    //当天某个商品已回复的评论
    @GetMapping("yesReplyIdS")
    @ResponseBody
    public List<EvaluateTb> yesReplyIdS (String time,Integer purchaseOrderProductPnId){
        return evaluateTbMapper.yesReplyId(time,purchaseOrderProductPnId);
    }

    //某个商品所有未回复的评论
    @GetMapping("noIdS")
    @ResponseBody
    public List<EvaluateTb> noIdS (Integer purchaseOrderProductPnId){
        return evaluateTbMapper.noId(purchaseOrderProductPnId);
    }
    //某个商品所有已回复的评论
    @GetMapping("yesIdS")
    @ResponseBody
    public List<EvaluateTb> yesIdS (Integer purchaseOrderProductPnId){
        return evaluateTbMapper.yesId(purchaseOrderProductPnId);
    }

    //所有评论商品的id
    @GetMapping("IdS")
    @ResponseBody
    public List<EvaluateTb> IdS (){
        return evaluateTbMapper.id();
    }
}

