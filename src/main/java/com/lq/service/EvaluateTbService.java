package com.lq.service;

import com.lq.pojo.EvaluateTb;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tooLate
 * @since 2021-05-21
 */
public interface EvaluateTbService extends IService<EvaluateTb> {

    //增加评论
    int addEvaluate (Integer accountId, String accountName, Integer purchaseOrderProductPnId, String evaluate,String txImg);

    //删除评论
    void delEvaluate (Integer evaluateId);

    //显示本商品的评论
    List<EvaluateTb> selectEvaluate (Integer purchaseOrderProductPnId);

    //显示本商品的两条评论
    List<EvaluateTb> selectEvaluate1 (Integer purchaseOrderProductPnId);

    //评论回复
    void reply (Integer evaluateId,String lianQiReply);

    //当天未回复的评论
    List<EvaluateTb> noReply (String time);
    //当天已回复的评论
    List<EvaluateTb> yesReply (String time);
}
