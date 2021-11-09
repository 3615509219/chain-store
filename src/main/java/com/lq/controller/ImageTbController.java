package com.lq.controller;


import com.lq.pojo.ImageTb;
import com.lq.service.ImageTbService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author tooLate
 * @since 2021-01-21
 */
@Controller
@RequestMapping("/imageTb")
public class ImageTbController {

    @Resource
    private ImageTbService imageTbService;

    //获取全部图片
    @GetMapping("getImage")
    @ResponseBody
    public List<ImageTb> getImage(){
        return imageTbService.getAllImage();
    }

    //图片所在
    @GetMapping("imageAddrS")
    @ResponseBody
    public List<ImageTb> imageAddrS(Integer purchaseOrderProductPnId,String shopImage){
        return imageTbService.imageAdd(purchaseOrderProductPnId,shopImage);
    }

    //上传图片
    @PostMapping("uploadImageS")
    @ResponseBody
    public String uploadImageS(@RequestParam(value = "file", required = false) MultipartFile multipartFile,Integer purchaseOrderProductPnId,String shopImage){
        return imageTbService.uploadImage(multipartFile,purchaseOrderProductPnId,shopImage);
    }

    //删除图片
    @PostMapping("delImageS")
    @ResponseBody
    public void delImageS(Integer imageId){
        imageTbService.removeById(imageId);
    }

    //查询这个商品的图片
    @GetMapping("getImageS")
    @ResponseBody
    public List<ImageTb> getImageS(Integer purchaseOrderProductPnId){
        return imageTbService.getImage(purchaseOrderProductPnId);
    }


    //一件修改页面说明
    @PostMapping("updateImageS")
    @ResponseBody
    public void updateImageS(@RequestParam(value = "file", required = false) MultipartFile multipartFile){
        imageTbService.updateImage(multipartFile);
    }
}

