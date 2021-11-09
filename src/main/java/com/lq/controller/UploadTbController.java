package com.lq.controller;


import com.lq.pojo.ImageTb;
import com.lq.pojo.UploadTb;
import com.lq.service.UploadTbService;
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
 * @since 2021-02-04
 */
@Controller
@RequestMapping("/uploadTb")
public class UploadTbController {

    @Resource
    private UploadTbService uploadTbService;

    //增加上传文件
    @PostMapping("UploadFile")
    @ResponseBody
    public String UploadFile(@RequestParam(value = "file", required = false) MultipartFile multipartFile, Integer accountId, String description,String patent,String title) throws Exception {
       return uploadTbService.Upload(multipartFile, accountId, description,patent,title);
    }

    //立即购买增加上传文件
    @PostMapping("BuyNowUploadFile")
    @ResponseBody
    public void BuyNowUploadFile(@RequestParam(value = "file", required = false) MultipartFile multipartFile, Integer productId, String productBz) throws Exception {
        uploadTbService.BuyNow(multipartFile, productId,productBz);
    }

    //审核中
    @GetMapping("UnderReviewS")
    @ResponseBody
    public List<UploadTb> UnderReviewS(Integer accountId){
        return uploadTbService.UnderReview(accountId);
    }

    //审核失败
    @GetMapping("AuditFailedS")
    @ResponseBody
    public List<UploadTb> AuditFailedS(Integer accountId){
        return uploadTbService.AuditFailed(accountId);
    }

    //审核通过
    @GetMapping("ApprovedS")
    @ResponseBody
    public List<UploadTb> ApprovedS(Integer accountId){
        return uploadTbService.Approved(accountId);
    }

    //客户上传3D文档列表
    @GetMapping("uploadTbListS")
    @ResponseBody
    public List<UploadTb> uploadTbListS(){
        return uploadTbService.uploadList();
    }

    //上传修改后的客户3D文档到服务器
    @PostMapping("newUploadS")
    @ResponseBody
    public void newUploadS(@RequestParam(value = "file", required = false) MultipartFile multipartFile,Integer accountId,Integer uploadID,String title,String description,String approveStatus,String fail ,String patent) throws Exception {
        uploadTbService.newUpload(multipartFile,accountId, uploadID, title, description, approveStatus, fail,patent);
    }

    //全部审核通过
    @GetMapping("ApprovedListS")
    @ResponseBody
    public List<UploadTb> ApprovedListS(){
        return uploadTbService.ApprovedList();
    }

    //全部审核失败列表
    @GetMapping("noUploadS")
    @ResponseBody
    public List<UploadTb> noUploadS(){
        return uploadTbService.noUpload();
    }

    //删除审核失败
    @PostMapping("delUploadS")
    @ResponseBody
    public void delUploadS (Integer uploadId){
        uploadTbService.removeById(uploadId);
    }

    //全部审核通过列表
    @GetMapping("yesUploadS")
    @ResponseBody
    public List<UploadTb> yesUploadS(){
        return uploadTbService.yesUpload();
    }

    //修改审核通过为已处理
    @PostMapping("updateStateS")
    @ResponseBody
    public void updateStateS (Integer uploadId){
        uploadTbService.stateUpload(uploadId);
    }
}

