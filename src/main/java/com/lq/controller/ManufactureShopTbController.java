package com.lq.controller;


import com.lq.pojo.ManufactureCartTb;
import com.lq.pojo.ManufactureShopTb;
import com.lq.service.ManufactureShopTbService;
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
 * @since 2021-04-15
 */
@Controller
@RequestMapping("/manufactureShopTb")
public class ManufactureShopTbController {

    @Resource
    private ManufactureShopTbService manufactureShopTbService;

    //获取分类商城
    @GetMapping("categoryShopS")
    @ResponseBody
    public List<ManufactureShopTb> categoryShopS(String category) {
        return manufactureShopTbService.categoryShop(category);
    }

    //查看单个物品详情
    @GetMapping("oneShopS")
    @ResponseBody
    public ManufactureShopTb oneShopS(String shopPn) {
        return manufactureShopTbService.oneShop(shopPn);
    }

}

