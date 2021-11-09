package com.lq.controller;


import com.lq.dao.CategoryTbMapper;
import com.lq.pojo.CategoryTb;
import com.lq.pojo.ImageTb;
import com.lq.service.CategoryTbService;
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
 * @since 2021-05-21
 */
@Controller
@RequestMapping("/categoryTb")
public class CategoryTbController {

    @Resource
    private CategoryTbService categoryTbService;
    @Resource
    private CategoryTbMapper categoryTbMapper;

    //添加物品分类
    @PostMapping("addCategoryS")
    @ResponseBody
    public String addCategoryS (Integer purchaseOrderProductPnId, String finishedGoodsCategory){
        return categoryTbService.addCategory(purchaseOrderProductPnId, finishedGoodsCategory);
    }

    //删除分类
    @PostMapping("delCategoryS")
    @ResponseBody
    public void delCategoryS(Integer categoryId){
        categoryTbService.removeById(categoryId);
    }

    //查询这个商品的分类
    @GetMapping("getCategoryS")
    @ResponseBody
    public List<CategoryTb> getCategoryS(Integer purchaseOrderProductPnId){
        return categoryTbService.getCategory(purchaseOrderProductPnId);
    }

    //隐藏的数量
    @GetMapping("yingCang")
    @ResponseBody
    public int yingCang (){
        return categoryTbMapper.yingCang();
    }

}

