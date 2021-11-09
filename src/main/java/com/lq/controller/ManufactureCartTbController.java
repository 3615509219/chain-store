package com.lq.controller;


import com.lq.pojo.CustomerCartTb;
import com.lq.pojo.ManufactureCartTb;
import com.lq.pojo.WarehouseTb;
import com.lq.service.ManufactureCartTbService;
import com.lq.service.WarehouseTbService;
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
 * @since 2021-04-15
 */
@Controller
@RequestMapping("/manufactureCartTb")
public class ManufactureCartTbController {

    @Resource
    private ManufactureCartTbService manufactureCartTbService;

    //获取工厂购物车
    @GetMapping("allCartS")
    @ResponseBody
    public List<ManufactureCartTb> allCartS(Integer manufactureId) {
        return manufactureCartTbService.allCart(manufactureId);
    }

    //删除购物车
    @PostMapping("delCartS")
    @ResponseBody
    public void delCartS(Integer manufactureCartId, Integer manufactureId) {
        manufactureCartTbService.delCart(manufactureCartId, manufactureId);
    }

    //物品减少数量
    @PostMapping("reduceManufactureCartS")
    @ResponseBody
    public void reduceManufactureCartS(Integer manufactureCartId, Integer manufactureId) {
        manufactureCartTbService.reduceManufactureCart(manufactureCartId, manufactureId);
    }

    //物品增加数量
    @PostMapping("addManufactureCartS")
    @ResponseBody
    public void addManufactureCartS(Integer manufactureCartId, Integer manufactureId) {
        manufactureCartTbService.addManufactureCart(manufactureCartId, manufactureId);
    }

    //选择物品
    @PostMapping("choose")
    @ResponseBody
    public void choose(Integer manufactureCartId){
        manufactureCartTbService.choose(manufactureCartId);
    }
    //取消选择物品
    @PostMapping("cancelChoose")
    @ResponseBody
    public void cancelChoose(Integer manufactureCartId){
        manufactureCartTbService.cancelChoose(manufactureCartId);
    }
    //全选物品
    @PostMapping("allChoose")
    @ResponseBody
    public void allChoose(Integer manufactureId){
        manufactureCartTbService.allChoose(manufactureId);
    }
    //取消全选物品
    @PostMapping("cancelAllChoose")
    @ResponseBody
    public void cancelAllChoose(Integer manufactureId){
        manufactureCartTbService.cancelAllChoose(manufactureId);
    }
    //获取打勾的物品
    @GetMapping("getStatusS")
    @ResponseBody
    public List<ManufactureCartTb> getStatusS(Integer manufactureId) {
        return manufactureCartTbService.getStatus(manufactureId);
    }

    //加入购物车
    @PostMapping("manufactureCartS")
    @ResponseBody
    public void manufactureCartS(Integer manufactureId, Integer shopId, Integer number, Integer status) {
        manufactureCartTbService.manufactureCart(manufactureId, shopId, number, status);
    }
}

