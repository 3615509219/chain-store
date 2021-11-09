package com.lq.service.impl;

import com.lq.dao.EvaluateImgTbMapper;
import com.lq.pojo.EvaluateTb;
import com.lq.dao.EvaluateTbMapper;
import com.lq.service.EvaluateTbService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author tooLate
 * @since 2021-05-21
 */
@Service
public class EvaluateTbServiceImpl extends ServiceImpl<EvaluateTbMapper, EvaluateTb> implements EvaluateTbService {

    @Resource
    private EvaluateTbService evaluateTbService;
    @Resource
    private EvaluateTbMapper evaluateTbMapper;

    @Resource
    private EvaluateImgTbMapper evaluateImgTbMapper;

    //增加评论
    @Override
    public int addEvaluate(Integer accountId, String accountName, Integer purchaseOrderProductPnId, String evaluate, String txImg) {
        Date time = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        EvaluateTb evaluateTb = new EvaluateTb();
        evaluateTb.setAccountId(accountId);
        evaluateTb.setAccountName(accountName);
        evaluateTb.setPurchaseOrderProductPnId(purchaseOrderProductPnId);
        evaluateTb.setEvaluate(evaluate);
        evaluateTb.setTime(df.format(time));
        evaluateTb.setTxImg(txImg);
        evaluateTbService.save(evaluateTb);
        return evaluateTbMapper.evaluateId();

    }

    //删除评论
    @Override
    public void delEvaluate(Integer evaluateId) {
        evaluateTbService.removeById(evaluateId);
        evaluateImgTbMapper.delImg(evaluateId);
    }

    //显示评论
    @Override
    public List<EvaluateTb> selectEvaluate(Integer purchaseOrderProductPnId) {
        return evaluateTbMapper.e(purchaseOrderProductPnId);
    }

    //显示两条评论
    @Override
    public List<EvaluateTb> selectEvaluate1(Integer purchaseOrderProductPnId) {
        return evaluateTbMapper.e1(purchaseOrderProductPnId);
    }

    //回复评论
    @Override
    public void reply(Integer evaluateId,String lianQiReply ) {
        Date time = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        evaluateTbMapper.updateReply(lianQiReply,df.format(time),evaluateId);
    }

    //当天未回复的评论
    @Override
    public List<EvaluateTb> noReply(String time) {
        return evaluateTbMapper.noReply(time);
    }
    //当天已回复的评论
    @Override
    public List<EvaluateTb> yesReply(String time) {
        return evaluateTbMapper.yesReply(time);
    }
}
