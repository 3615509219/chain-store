package com.lq.controller;


import com.lq.dao.BuyNowImgTbMapper;
import com.lq.dao.ProductImgTbMapper;
import com.lq.dao.ProductTbMapper;
import com.lq.pojo.BuyNowImgTb;
import com.lq.pojo.ProductImgTb;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author tooLate
 * @since 2021-05-25
 */
@Controller
@RequestMapping("/productImgTb")
public class ProductImgTbController {

    @Resource
    private ProductImgTbMapper productImgTbMapper;

    //查看加入购物车的图片
    @GetMapping("productImgS")
    @ResponseBody
    public List<ProductImgTb> productImgS (Integer customerCartId){
        return productImgTbMapper.allImg(customerCartId);
    }

}

