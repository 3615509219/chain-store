package com.lq.service.impl;

import com.lq.dao.ProductTbMapper;
import com.lq.pojo.RefundTb;
import com.lq.dao.RefundTbMapper;
import com.lq.service.RefundTbService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tooLate
 * @since 2021-06-25
 */
@Service
public class RefundTbServiceImpl extends ServiceImpl<RefundTbMapper, RefundTb> implements RefundTbService {

    @Resource
    private RefundTbService refundTbService;
    @Resource
    private RefundTbMapper refundTbMapper;
    @Resource
    private ProductTbMapper productTbMapper;
    //申请售后
    @Override
    public int shouHou(Integer accountId, String purchaseOrderNumber, Integer productId, String purchaseOrderProductPn, String purchaseOrderProductName, Integer jiFen, Integer referencesId, String reasonsForApplication, String applicationCategory, String applicationReasonDescription) {
        Date time = new Date();
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        RefundTb refundTb = new RefundTb();
        refundTb.setAccountId(accountId);
        refundTb.setPurchaseOrderNumber(purchaseOrderNumber);
        refundTb.setProductId(productId);
        refundTb.setPurchaseOrderProductPn(purchaseOrderProductPn);
        refundTb.setPurchaseOrderProductName(purchaseOrderProductName);
        refundTb.setJiFen(jiFen);
        refundTb.setReferencesId(referencesId);
        refundTb.setReasonsForApplication(reasonsForApplication);
        refundTb.setApplicationCategory(applicationCategory);
        refundTb.setApplicationReasonDescription(applicationReasonDescription);
        refundTb.setRefundApplicationTime(df2.format(time));
        refundTb.setApplicationStatus("申请中");
        refundTbService.save(refundTb);
        productTbMapper.shouHou(productId);
        return refundTbMapper.refundId();
    }

    //售后申请中
    @Override
    public List<RefundTb> applicationInProgress() {
        return refundTbMapper.applicationInProgress();
    }

    //售后已完成
    @Override
    public List<RefundTb> completed() {
        return refundTbMapper.completed();
    }

    //修改售后状态
    @Override
    public void updateStatus(Integer refundId,String bz) {
        Date time = new Date();
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        refundTbMapper.updateStatus(refundId,bz,df2.format(time));
    }

    //查询此物品是否在售后
    @Override
    public RefundTb select(Integer productId) {
        return refundTbMapper.select(productId);
    }
}
