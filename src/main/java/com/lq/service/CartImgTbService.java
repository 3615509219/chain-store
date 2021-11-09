package com.lq.service;

import com.lq.pojo.CartImgTb;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tooLate
 * @since 2021-05-25
 */
public interface CartImgTbService extends IService<CartImgTb> {

    //增加购物车图片
    void addCartImg (@RequestParam(value = "file", required = false) MultipartFile multipartFile,Integer customerCartID,String comment);
}
