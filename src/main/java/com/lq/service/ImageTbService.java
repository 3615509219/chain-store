package com.lq.service;

import com.lq.pojo.ImageTb;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tooLate
 * @since 2021-01-21
 */
public interface ImageTbService extends IService<ImageTb> {
    //获取全部图片
    List<ImageTb> getAllImage();

    List<ImageTb> imageAdd (Integer purchaseOrderProductPnId,String shopImage);

    //添加图片
    String uploadImage (@RequestParam(value = "file",required = false) MultipartFile multipartFile,Integer purchaseOrderProductPnId,String shopImage);

    //查询这个商品的图片
    List<ImageTb> getImage(Integer purchaseOrderProductPnId);

    //一件修改页面说明
    void updateImage (@RequestParam(value = "file",required = false) MultipartFile multipartFile);
}
