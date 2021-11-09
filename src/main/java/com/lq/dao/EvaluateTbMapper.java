package com.lq.dao;

import com.lq.pojo.EvaluateTb;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tooLate
 * @since 2021-05-21
 */
public interface EvaluateTbMapper extends BaseMapper<EvaluateTb> {

    List<EvaluateTb> e (@Param(value="purchaseOrderProductPnId")Integer purchaseOrderProductPnId);
    List<EvaluateTb> e1 (@Param(value="purchaseOrderProductPnId")Integer purchaseOrderProductPnId);

    @Select("select evaluate_Id from evaluate_tb order by evaluate_Id desc limit 1")
    int evaluateId();

    @Update("update evaluate_tb set lian_qi_reply = '${lianQiReply}' , reply_time = '${replyTime}' where evaluate_Id = ${evaluateId}")
    void updateReply(String lianQiReply,String replyTime,Integer evaluateId);

    List<EvaluateTb> noReply (String time);

    List<EvaluateTb> yesReply (String time);

    List<EvaluateTb> allEvaluate ();

    List<EvaluateTb> noReplyId (String time,Integer purchaseOrderProductPnId);

    List<EvaluateTb> yesReplyId (String time,Integer purchaseOrderProductPnId);

    List<EvaluateTb> noId (Integer purchaseOrderProductPnId);

    List<EvaluateTb> yesId (Integer purchaseOrderProductPnId);

    List<EvaluateTb> id ();
}
