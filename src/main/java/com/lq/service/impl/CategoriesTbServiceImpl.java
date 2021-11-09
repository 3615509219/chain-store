package com.lq.service.impl;

import com.lq.dao.CategoryTbMapper;
import com.lq.pojo.CategoriesTb;
import com.lq.dao.CategoriesTbMapper;
import com.lq.service.CategoriesTbService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lq.service.CategoryTbService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tooLate
 * @since 2021-06-15
 */
@Service
public class CategoriesTbServiceImpl extends ServiceImpl<CategoriesTbMapper, CategoriesTb> implements CategoriesTbService {

    @Resource
    private CategoriesTbService categoriesTbService;
    @Resource
    private CategoriesTbMapper categoriesTbMapper;
    @Resource
    private CategoryTbMapper categoryTbMapper;
    //添加总分类
    @Override
    public void addCategories(String categories, Integer categoriesSort) {
        CategoriesTb categoriesTb = new CategoriesTb();
        categoriesTb.setCategories(categories);
        categoriesTb.setCategoriesSort(categoriesSort);
        categoriesTbService.save(categoriesTb);
    }

    //修改总分类名称
    @Override
    public void updateCategories(String gaiCategory,String yuanCategory) {
        categoriesTbMapper.updateCategories(gaiCategory, yuanCategory);
        categoryTbMapper.updateCategory(gaiCategory, yuanCategory);
    }

    //修改总分类排序
    @Override
    public void updateCategoriesSort(Integer categoriesSort, Integer categoriesId) {
        categoriesTbMapper.updateCategoriesSort(categoriesSort, categoriesId);
    }
}
