package com.lq.dao;

import com.lq.pojo.CategoriesTb;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tooLate
 * @since 2021-06-15
 */
public interface CategoriesTbMapper extends BaseMapper<CategoriesTb> {

    @Update("update categories_tb set categories = '${gaiCategory}' where categories = '${yuanCategory}'")
    void updateCategories (String gaiCategory,String yuanCategory);

    @Update("update categories_tb set categories_sort = ${categoriesSort} where categories_Id = ${categoriesId}")
    void updateCategoriesSort (Integer categoriesSort,Integer categoriesId);

}
