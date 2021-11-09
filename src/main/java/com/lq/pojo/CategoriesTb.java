package com.lq.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author tooLate
 * @since 2021-06-15
 */
public class CategoriesTb implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "categories_Id", type = IdType.AUTO)
    private Integer categoriesId;

    private String categories;

    private Integer categoriesSort;


    public Integer getCategoriesId() {
        return categoriesId;
    }

    public void setCategoriesId(Integer categoriesId) {
        this.categoriesId = categoriesId;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public Integer getCategoriesSort() {
        return categoriesSort;
    }

    public void setCategoriesSort(Integer categoriesSort) {
        this.categoriesSort = categoriesSort;
    }

    @Override
    public String toString() {
        return "CategoriesTb{" +
        "categoriesId=" + categoriesId +
        ", categories=" + categories +
        ", categoriesSort=" + categoriesSort +
        "}";
    }
}
