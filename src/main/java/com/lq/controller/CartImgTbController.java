package com.lq.controller;


import com.lq.dao.CustomerCartTbMapper;
import com.lq.pojo.CartImgTb;
import com.lq.service.CartImgTbService;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @since 2021-05-25
 */
@Controller
@RequestMapping("/cartImgTb")
public class CartImgTbController {
    @Resource
    private CustomerCartTbMapper customerCartTbMapper;
    @Resource
    private CartImgTbService cartImgTbService;

    //增加购物车图片
    @PostMapping("addCartImgS")
    @ResponseBody
    public void addCartImgS (@RequestParam(value = "file", required = false) MultipartFile multipartFile,Integer customerCartID,String comment){
        cartImgTbService.addCartImg(multipartFile,customerCartID,comment);
    }
    //增加购物车图片
    @PostMapping("addCartImgNull")
    @ResponseBody
    public void addCartImgNull (Integer customerCartID,String CommentBz){
        CartImgTb cartImgTb = new CartImgTb();
        cartImgTb.setCustomerCartId(customerCartID);
        cartImgTb.setCartImg("null");
        cartImgTb.setCommentBz(CommentBz);
        cartImgTbService.save(cartImgTb);
    }
}

