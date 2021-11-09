package com.lq.service;

import com.lq.pojo.RefundTb;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tooLate
 * @since 2021-06-25
 */
public interface RefundTbService extends IService<RefundTb> {

    int shouHou (Integer accountId,String purchaseOrderNumber,Integer productId,String purchaseOrderProductPn,String purchaseOrderProductName,Integer jiFen
                ,Integer referencesId ,String reasonsForApplication ,String applicationCategory ,String applicationReasonDescription
    );

    List<RefundTb> applicationInProgress ();

    List<RefundTb> completed ();

    void updateStatus (Integer refundId,String bz);

    RefundTb select (Integer productId);

}
