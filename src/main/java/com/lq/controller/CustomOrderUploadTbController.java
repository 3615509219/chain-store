package com.lq.controller;


import com.lq.service.CustomOrderUploadTbService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author tooLate
 * @since 2021-09-15
 */
@Controller
@RequestMapping("/customOrderUploadTb")
public class CustomOrderUploadTbController {

    @Resource
    private CustomOrderUploadTbService customOrderUploadTbService;

    //定制上传文件
    @PostMapping("uploadS")
    @ResponseBody
    public void uploadS (@RequestParam(value = "file",required = false) MultipartFile multipartFile, Integer accountId, Integer customOrderId, String customOrderUploadName, String customOrderUploadDescribe){
        customOrderUploadTbService.Upload(multipartFile, accountId, customOrderId, customOrderUploadName, customOrderUploadDescribe);
    }

}

