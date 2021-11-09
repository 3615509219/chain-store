package com.lq.controller;


import com.lq.dao.FinishedGoodsListSubTbMapper;
import com.lq.pojo.FinishedGoodsListSubTb;
import com.lq.pojo.FinishedGoodsListTb;
import com.lq.service.FinishedGoodsListSubTbService;
import com.lq.service.FinishedGoodsListTbService;
import com.lq.utils.DealFile;
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
 * @since 2021-08-26
 */
@Controller
@RequestMapping("/finishedGoodsListSubTb")
public class FinishedGoodsListSubTbController {

    @Resource
    private FinishedGoodsListSubTbService finishedGoodsListSubTbService;
    @Resource
    private DealFile dealFile;
    @Resource
    private FinishedGoodsListSubTbMapper finishedGoodsListSubTbMapper;
    @Resource
    private FinishedGoodsListTbService finishedGoodsListTbService;

    //查询本商品的子商品
    @GetMapping("selectSubS")
    @ResponseBody
    public List<FinishedGoodsListSubTb> selectSubS (Integer purchaseOrderProductPnId){
        return finishedGoodsListSubTbService.selectSub(purchaseOrderProductPnId);
    }

    //增加本商品的子商品
    @PostMapping("addSubS")
    @ResponseBody
    public int addSubS (FinishedGoodsListSubTb finishedGoodsListSubTb){
        return finishedGoodsListSubTbService.addSub(finishedGoodsListSubTb);
    }

    //删除本商品的子商品
    @PostMapping("delSubS")
    @ResponseBody
    public void delSubS (Integer finishedGoodsListSubId){
        finishedGoodsListSubTbService.delSub(finishedGoodsListSubId);
    }
    //上传子集的3d文档
    @PostMapping("subUploadS")
    @ResponseBody
    public void subUploadS (@RequestParam(value = "file",required = false) MultipartFile multipartFile,Integer finishedGoodsListSubId){
        try {
            String photoPath = dealFile.getPhotoPath(multipartFile);
            finishedGoodsListSubTbMapper.updateDrawingFilesAddr(photoPath,finishedGoodsListSubId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //上传子集的homePage图片
    @PostMapping("subUploadHomePageS")
    @ResponseBody
    public void subUploadHomePageS (@RequestParam(value = "file",required = false) MultipartFile multipartFile,Integer finishedGoodsListSubId){
        try {
            String photoPath = dealFile.getPhotoPath(multipartFile);
            finishedGoodsListSubTbMapper.updateHomePageAddr(photoPath,finishedGoodsListSubId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //查询子集商品详情
    @GetMapping("selectS")
    @ResponseBody
    public FinishedGoodsListSubTb selectS (Integer finishedGoodsListSubId){
        return finishedGoodsListSubTbService.getById(finishedGoodsListSubId);
    }
    //查询子集商品描述
    @GetMapping("describe")
    @ResponseBody
    public String describe (Integer finishedGoodsListSubId){
        FinishedGoodsListSubTb byId = finishedGoodsListSubTbService.getById(finishedGoodsListSubId);
        FinishedGoodsListTb byId1 = finishedGoodsListTbService.getById(byId.getPurchaseOrderProductPnId());
        return byId1.getFinishedGoodsDescription();
    }

    //修改子集商城数据
    @PostMapping("updateFinishedSub")
    @ResponseBody
    public void updateFinishedSub(FinishedGoodsListSubTb finishedGoodsListSubTb) {
        finishedGoodsListSubTbService.updateById(finishedGoodsListSubTb);
    }

    //修改子集商城文档地址
    @PostMapping("updateUploadFinishedSub")
    @ResponseBody
    public void updateUploadFinishedSub(@RequestParam(value = "file", required = false)MultipartFile multipartFile, Integer finishedGoodsListSubId) {
        try {
            String photoPath = dealFile.getPhotoPath(multipartFile);
            finishedGoodsListSubTbMapper.updateUploadFinishedSub(photoPath, finishedGoodsListSubId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //修改子集首页图片
    @PostMapping("updateHomeImgSub")
    @ResponseBody
    public void updateHomeImgSub(@RequestParam(value = "file", required = false)MultipartFile multipartFile, Integer finishedGoodsListSubId) {
        try {
            String photoPath = dealFile.getPhotoPath(multipartFile);
            finishedGoodsListSubTbMapper.updateHomeImgSub(photoPath, finishedGoodsListSubId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

