package com.lq.controller;


import com.lq.pojo.AccessoryTb;
import com.lq.service.AccessoryTbService;
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
 * @since 2021-01-13
 */
@Controller
@RequestMapping("/accessoryTb")
public class AccessoryTbController {

    @Resource
    private AccessoryTbService accessoryTbService;

    //获取商城打印机配件
    @GetMapping("allAccessoryS")
    @ResponseBody
    public List<AccessoryTb> allAccessoryS(){
        return accessoryTbService.allAccessory();
    }

}

