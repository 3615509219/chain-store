package com.lq.controller;


import com.lq.pojo.CustomerCartTb;
import com.lq.service.CustomerCartTbService;
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
 * @since 2021-01-13
 */
@Controller
@RequestMapping("/customerCartTb")
public class CustomerCartTbController {
    @Resource
    private CustomerCartTbService customerCartTbService;

    //获取购物车
    @GetMapping("getAllCustomerCart")
    @ResponseBody
    public List<CustomerCartTb> getAllCustomerCart(Integer accountId) {
        return customerCartTbService.getCart(accountId);
    }

    //删除购物车
    @PostMapping("delCustomerCart")
    @ResponseBody
    public void delCustomerCart(Integer customerCartId, Integer accountId) {
        customerCartTbService.deiCart(customerCartId, accountId);
    }

    //物品减少数量
    @PostMapping("reduceCustomerCart")
    @ResponseBody
    public void reduceCustomerCart(Integer customerCartId, Integer accountId) {
        customerCartTbService.reduceCart(customerCartId, accountId);
    }

    //物品增加数量
    @PostMapping("addCustomerCart")
    @ResponseBody
    public void addCustomerCart(Integer customerCartId, Integer accountId) {
        customerCartTbService.addCart(customerCartId, accountId);
    }

    //加入到购物车状态1上传和备注
    @PostMapping("cart1")
    @ResponseBody
    public int cart1(Integer accountId, Integer finishedGoodsQty , Integer purchaseOrderProductPnId, Integer finishedGoodsListSubId,Integer selectStatus,Integer integral,Integer jf) {
       return customerCartTbService.cart1(accountId,finishedGoodsQty,purchaseOrderProductPnId,finishedGoodsListSubId,selectStatus,integral,jf);
    }
    //加入到购物车状态0没有diy
    @PostMapping("cart0")
    @ResponseBody
    public int cart0(Integer accountId, Integer finishedGoodsQty, Integer purchaseOrderProductPnId,String purchaseOrderProductPn, Integer finishedGoodsListSubId,Integer selectStatus,Integer integral,Integer jf) {
        return customerCartTbService.cart0(accountId,finishedGoodsQty,purchaseOrderProductPnId,purchaseOrderProductPn,finishedGoodsListSubId,selectStatus,integral,jf);
    }
    //加入到购物车状态2只有备注
    @PostMapping("cart2")
    @ResponseBody
    public int cart2(Integer accountId, Integer finishedGoodsQty , Integer purchaseOrderProductPnId, Integer finishedGoodsListSubId,String comment,Integer selectStatus,Integer integral,Integer jf) {
        return customerCartTbService.cart2(accountId,finishedGoodsQty,purchaseOrderProductPnId,finishedGoodsListSubId,comment,selectStatus,integral,jf);
    }
    //选择物品
    @PostMapping("choose")
    @ResponseBody
    public void choose(Integer customerCartId){
        customerCartTbService.choose(customerCartId);
    }
    //取消选择物品
    @PostMapping("cancelChoose")
    @ResponseBody
    public void cancelChoose(Integer customerCartId){
        customerCartTbService.cancelChoose(customerCartId);
    }
    //全选物品
    @PostMapping("allChoose")
    @ResponseBody
    public void allChoose(Integer accountId){
        customerCartTbService.allChoose(accountId);
    }
    //取消全选物品
    @PostMapping("cancelAllChoose")
    @ResponseBody
    public void cancelAllChoose(Integer accountId){
        customerCartTbService.cancelAllChoose(accountId);
    }
    //获取打勾的物品
    @GetMapping("getStatus")
    @ResponseBody
    public List<CustomerCartTb> getStatus(Integer accountId) {
        return customerCartTbService.getSelectStatus(accountId);
    }
    //修改购物车积分
    @PostMapping("updateIntegralS")
    @ResponseBody
    public void updateIntegralS(Integer integral,Integer customerCartId){
        customerCartTbService.updateIntegral(integral,customerCartId);
    }

}

