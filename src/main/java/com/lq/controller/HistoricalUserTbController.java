package com.lq.controller;


import com.lq.service.HistoricalUserTbService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author tooLate
 * @since 2021-05-10
 */
@Controller
@RequestMapping("/historicalUserTb")
public class HistoricalUserTbController {

    @Resource
    private HistoricalUserTbService historicalUserTbService;

    //注销
    @PostMapping("cancellationS")
    @ResponseBody
    public void cancellationS(Integer accountId){
        historicalUserTbService.cancellation(accountId);
    }

}

