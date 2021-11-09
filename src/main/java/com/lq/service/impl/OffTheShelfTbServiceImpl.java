package com.lq.service.impl;

import com.lq.dao.FinishedGoodsListTbMapper;
import com.lq.pojo.OffTheShelfTb;
import com.lq.dao.OffTheShelfTbMapper;
import com.lq.service.OffTheShelfTbService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tooLate
 * @since 2021-05-11
 */
@Service
public class OffTheShelfTbServiceImpl extends ServiceImpl<OffTheShelfTbMapper, OffTheShelfTb> implements OffTheShelfTbService {

    @Resource
    private OffTheShelfTbService offTheShelfTbService;
    @Resource
    private OffTheShelfTbMapper offTheShelfTbMapper;
    @Resource
    private FinishedGoodsListTbMapper finishedGoodsListTbMapper;
    //申请下架
    @Override
    public void applyForRemoval(Integer accountId, Integer purchaseOrderProductPnId,String purchaseOrderProductPn,String finishedGoodsName,String reason) {
        Date time = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        OffTheShelfTb offTheShelfTb = new OffTheShelfTb();
        offTheShelfTb.setAccountId(accountId);
        offTheShelfTb.setPurchaseOrderProductPnId(purchaseOrderProductPnId);
        offTheShelfTb.setPurchaseOrderProductPn(purchaseOrderProductPn);
        offTheShelfTb.setFinishedGoodsName(finishedGoodsName);
        offTheShelfTb.setReason(reason);
        offTheShelfTb.setApplicationTime(df.format(time));
        offTheShelfTb.setAuditTime(df.format(time));
        offTheShelfTb.setExamine("待处理");
        offTheShelfTb.setReasonsForRemoval("空");
        offTheShelfTbService.save(offTheShelfTb);
    }

    //处理下架
    @Override
    public void handle(Integer shelfId) {
        Date time = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        offTheShelfTbMapper.upd(df.format(time),shelfId);
    }

    //填写下架原因
    @Override
    public void updateReasonsForRemoval(String ReasonsForRemoval, Integer shelfId) {
        offTheShelfTbMapper.updateReasonsForRemoval(ReasonsForRemoval, shelfId);
    }
}
