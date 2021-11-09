package com.lq.service.impl;

import com.lq.pojo.FinishedGoodsListSubTb;
import com.lq.dao.FinishedGoodsListSubTbMapper;
import com.lq.service.FinishedGoodsListSubTbService;
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
 * @since 2021-08-26
 */
@Service
public class FinishedGoodsListSubTbServiceImpl extends ServiceImpl<FinishedGoodsListSubTbMapper, FinishedGoodsListSubTb> implements FinishedGoodsListSubTbService {
    @Resource
    private FinishedGoodsListSubTbMapper finishedGoodsListSubTbMapper;
    @Resource
    private FinishedGoodsListSubTbService finishedGoodsListSubTbService;
    //查询本商品的子商品
    @Override
    public List<FinishedGoodsListSubTb> selectSub(Integer purchaseOrderProductPnId) {
        return finishedGoodsListSubTbMapper.selectSub(purchaseOrderProductPnId);
    }
    //增加本商品的子商品
    @Override
    public int addSub(FinishedGoodsListSubTb finishedGoodsListSubTb) {
        finishedGoodsListSubTbService.save(finishedGoodsListSubTb);
        return finishedGoodsListSubTb.getFinishedGoodsListSubId();
    }
    //删除本商品的子商品
    @Override
    public void delSub(Integer finishedGoodsListSubId) {
        finishedGoodsListSubTbService.removeById(finishedGoodsListSubId);
    }
}
