package com.lq.controller;


import com.lq.pojo.UploadTb;
import com.lq.pojo.WarehouseNumber;
import com.lq.pojo.WarehouseTb;
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
 *  前端控制器
 * </p>
 *
 * @author tooLate
 * @since 2021-04-15
 */
@Controller
@RequestMapping("/warehouseTb")
public class WarehouseTbController {

    @Resource
    private WarehouseTbService warehouseTbService;
    //查看库存
    @GetMapping("selectWarehouse")
    @ResponseBody
    public List<WarehouseTb> selectWarehouse(Integer manufactureId, String category) {
        return warehouseTbService.selectWarehouse(manufactureId, category);
    }

    //查看所有库存
    @GetMapping("allWarehouse")
    @ResponseBody
    public List<WarehouseTb> allWarehouse(Integer manufactureId) {
        return warehouseTbService.allWarehouse(manufactureId);
    }

    //分类
    @GetMapping("allCategoryS")
    @ResponseBody
    public List<WarehouseTb> allCategoryS() {
        return warehouseTbService.allCategory();
    }

    //总数
    @GetMapping("sumNumberS")
    @ResponseBody
    public List<WarehouseNumber> sumNumberS(String category) {
        return warehouseTbService.sumNumber(category);
    }
}

