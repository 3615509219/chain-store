package com.lq.service;

import com.lq.pojo.CategoriesTb;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tooLate
 * @since 2021-06-15
 */
public interface CategoriesTbService extends IService<CategoriesTb> {

    //添加总分类
    void addCategories (String categories,Integer categoriesSort);

    //修改总分类名称
    void updateCategories (String gaiCategory,String yuanCategory);

    //修改总分类排序
    void updateCategoriesSort (Integer categoriesSort,Integer categoriesId);
}
