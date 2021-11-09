package com.lq.controller;


import com.lq.service.CategoriesTbService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author tooLate
 * @since 2021-06-15
 */
@Controller
@RequestMapping("/categoriesTb")
public class CategoriesTbController {

    @Resource
    private CategoriesTbService categoriesTbService;

    //添加物品总分类
    @PostMapping("addCategoriesS")
    @ResponseBody
    public void addCategoriesS (String categories, Integer categoriesSort){
        categoriesTbService.addCategories(categories, categoriesSort);
    }
    //修改总分类名称
    @PostMapping("updateCategoriesS")
    @ResponseBody
    public void updateCategoriesS (String gaiCategory,String yuanCategory){
        categoriesTbService.updateCategories(gaiCategory, yuanCategory);
    }

    //修改总分类排序
    @PostMapping("updateCategoriesSortS")
    @ResponseBody
    public void updateCategoriesSortS (Integer categoriesSort, Integer categoriesId){
        categoriesTbService.updateCategoriesSort(categoriesSort, categoriesId);
    }
}

