package com.lq.service.impl;

import com.lq.pojo.CategoryTb;
import com.lq.dao.CategoryTbMapper;
import com.lq.service.CategoryTbService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tooLate
 * @since 2021-05-21
 */
@Service
public class CategoryTbServiceImpl extends ServiceImpl<CategoryTbMapper, CategoryTb> implements CategoryTbService {

    @Resource
    private CategoryTbService categoryTbService;

    @Resource
    private CategoryTbMapper categoryTbMapper;

    //添加分类
    @Override
    public String addCategory(Integer purchaseOrderProductPnId, String finishedGoodsCategory) {
        try {
            CategoryTb categoryTb = new CategoryTb();
            categoryTb.setPurchaseOrderProductPnId(purchaseOrderProductPnId);
            categoryTb.setFinishedGoodsCategory(finishedGoodsCategory);
            categoryTbService.save(categoryTb);
            return "成功";
        } catch (Exception e) {
            e.printStackTrace();
            return "失败";
        }
    }

    //查看这个商品的分类
    @Override
    public List<CategoryTb> getCategory(Integer purchaseOrderProductPnId) {
        return categoryTbMapper.getCategory(purchaseOrderProductPnId);
    }
}
