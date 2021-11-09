package com.lq.controller;


import com.lq.dao.EvaluateImgTbMapper;
import com.lq.pojo.EvaluateImgTb;
import com.lq.pojo.EvaluateTb;
import com.lq.service.EvaluateImgTbService;
import com.lq.service.EvaluateTbService;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author tooLate
 * @since 2021-05-24
 */
@Controller
@RequestMapping("/evaluateImgTb")
public class EvaluateImgTbController {

    @Resource
    private EvaluateImgTbService evaluateImgTbService;
    @Resource
    private EvaluateImgTbMapper evaluateImgTbMapper;

    @Resource
    private EvaluateTbService evaluateTbService;
    //增加评论图片
    @PostMapping("addEvaluateS")
    @ResponseBody
    public void addEvaluateS (@RequestParam(value = "file", required = false) MultipartFile multipartFile,Integer evaluateId){
        evaluateImgTbService.addImg(multipartFile,evaluateId);
    }

//    //显示评论图片
//    @GetMapping("selectEvaluateImgS")
//    @ResponseBody
//    public List<EvaluateImgTb> selectEvaluateImgS (Integer purchaseOrderProductPnId){
//        List<EvaluateTb> evaluateTbs = evaluateTbService.selectEvaluate(purchaseOrderProductPnId);
//        for (EvaluateTb e: evaluateTbs) {
//            List<EvaluateImgTb> evaluateImgTbs = evaluateImgTbMapper.selectImg(e.getEvaluateId());
//            return evaluateImgTbs;
//        }
//    return null;
//    }


    @GetMapping("sss")
    @ResponseBody
    public List<EvaluateImgTb> sss  (){
        return evaluateImgTbService.list();
    }
}

