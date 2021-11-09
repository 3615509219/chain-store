package com.lq.controller;


import com.lq.dao.BuyNowImgTbMapper;
import com.lq.pojo.BuyNowImgTb;
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
 * @since 2021-05-31
 */
@Controller
@RequestMapping("/buyNowImgTb")
public class BuyNowImgTbController {

    @Resource
    private BuyNowImgTbMapper buyNowImgTbMapper;

    //查看立即购买的图片
    @GetMapping("buyNowImgS")
    @ResponseBody
    public List<BuyNowImgTb> buyNowImg (Integer productId){
        return buyNowImgTbMapper.allImg(productId);
    }

}

