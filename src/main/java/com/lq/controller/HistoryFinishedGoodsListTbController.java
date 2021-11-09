package com.lq.controller;


import com.lq.pojo.HistoryFinishedGoodsListTb;
import com.lq.service.HistoryFinishedGoodsListTbService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author tooLate
 * @since 2021-05-11
 */
@Controller
@RequestMapping("/historyFinishedGoodsListTb")
public class HistoryFinishedGoodsListTbController {

    @Resource
    private HistoryFinishedGoodsListTbService historyFinishedGoodsListTbService;

    //下架
    @PostMapping("OffTheShelfS")
    @ResponseBody
    public void OffTheShelfS(Integer purchaseOrderProductPnId) {
        historyFinishedGoodsListTbService.OffTheShelf(purchaseOrderProductPnId);
    }

    //再次上架
    @PostMapping("noTheShelfS")
    @ResponseBody
    public int noTheShelfS(Integer purchaseOrderProductPnId, Integer id) {
        return historyFinishedGoodsListTbService.noTheShelf(purchaseOrderProductPnId, id);
    }

    //已下架的商品
    @GetMapping("allHistoryFinishedGoodsListS")
    @ResponseBody
    public List<HistoryFinishedGoodsListTb> allHistoryFinishedGoodsListS() {
        return historyFinishedGoodsListTbService.list();
    }
}

