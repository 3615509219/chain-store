package com.lq.controller;


import com.lq.pojo.RefundImgTb;
import com.lq.service.RefundImgTbService;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author tooLate
 * @since 2021-06-25
 */
@Controller
@RequestMapping("/refundImgTb")
public class RefundImgTbController {

    @Resource
    private RefundImgTbService refundImgTbService;

    //售后上传图片
    @PostMapping("UploadRefundImgS")
    @ResponseBody
    public void UploadRefundImgS(@RequestParam(value = "file", required = false) MultipartFile multipartFile, Integer refundId) throws Exception {
        refundImgTbService.UploadRefundImg(multipartFile, refundId);
    }
    //查看售后上传的图片
    @GetMapping("selectRefundImgS")
    @ResponseBody
    public List<RefundImgTb> selectRefundImgS (Integer refundId){
        return  refundImgTbService.selectRefundImg(refundId);
    }

}

